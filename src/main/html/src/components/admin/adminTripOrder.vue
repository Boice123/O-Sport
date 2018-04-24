<template>
  <div class="shopmanagetriporderContainer">
    <div class="shopmanagetriporderknow">出行订单管理</div>
    <div class="orderT">
      <img :src="trip.tripimg" class="miniimg"/><span class="Nae">{{trip.tripname}}</span><span>的订单明细如下：</span>
    </div> 
     <el-table
      :data="tableData"
      style="width: 100%"
      tooltip-effect="dark"
    >
    <el-table-column
      type="index"
      width="100">
    </el-table-column>
     <el-table-column
      label="出团名称"
      width="100">
       <template slot-scope="scope">
          <span>{{trip.tripname}}</span>
      </template>
    </el-table-column>
    <el-table-column
      prop="people"
      label="报名人数"
      width="100">
    </el-table-column>
    <el-table-column
      prop="tripordertotal"
      label="订单总价"
      width="100">
    </el-table-column>
    <el-table-column
      label="用户昵称"
      width="100">
       <template slot-scope="scope">
          <span>{{tableData[scope.$index].user.username}}</span>
      </template>
    </el-table-column>
     <el-table-column
      label="用户联系方式"
      width="130">
       <template slot-scope="scope">
          <span>{{tableData[scope.$index].user.tel}}</span>
      </template>
    </el-table-column>
     <el-table-column
      prop="tripordertime"
      label="订单时间"
      width="100">
    </el-table-column>
    <el-table-column
      prop="triporderstatus"
      label="订单状态"
      width="100">
    </el-table-column>
    <el-table-column label="" prop="tripid">
      <template slot-scope="scope">
        <el-button
          size="mini"
          v-if="tableData[scope.$index].triporderstatus === '未付款'"
          @click="handleEdit(tableData[scope.$index].triporderid)">确认已付款</el-button>
        <el-button
          size="mini"
          type="danger"
          v-if="tableData[scope.$index].triporderstatus === '未付款'"
          @click="handleClose(tableData[scope.$index].triporderid)">关闭订单</el-button>
      </template>
    </el-table-column>
  </el-table>
  <div class="block">
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page=currentPage
        :page-sizes="[5, 10, 20]"
        :page-size="5"
        layout="total, sizes, prev, pager, next, jumper"
        :total=tripOrderCount>
      </el-pagination>
    </div>
  </div>
</template>

<script>
import { API_getTripInfoURl, API_getTripOrderList, API_getTripOrderCount, API_sureOrderURl, API_closeOrderURl } from '../../constants/index.js'
import axios from 'axios'
export default {
    name: 'shopmanagetriporder',
    data() {
        return {
          tableData: [{
          triporderid: '',
          tripordertime: '',
          tripordertotal: '',
          user: {
            userid: '',
            username: '',
            tel: ''
          }
          }],
          tripOrderCount: 0,
          trip: {
            tripname: '',
            tripimg: ''
          },
          pageSize: 5,
          currentPage: 1
        }
    },
    created() {
      this.getTripInfo()
      this.getAdminTripOrderCount()
      this.getAdminTripOrder()
    },
    methods: {
      //获取trip信息
      getTripInfo() {
        var params = new URLSearchParams();
        params.append('tripid',this.getCookie('admin_tripid'))
        axios({
            method:'post',
            url:API_getTripInfoURl,
            params
        })
        .then((response) => {
            console.log(response.data)
            if(response.data.code == 0) {
                this.trip = response.data.data
            }else if(response.data.code == 1) {
                this.$message({
                    message: response.data.msg,
                    type: 'warning'
                }); 
            }else {
                this.$message.error('获取出行团信息失败，请稍后重试');
            }        
        }).catch((err) => {
            console.log(err)
        })
      },
      //获得订单信息
      getAdminTripOrder () {
        var params = new URLSearchParams();
        params.append('start',(this.currentPage-1) * this.pageSize)
        params.append('size',this.pageSize)
        params.append('tripid', this.getCookie('admin_tripid'))
        params.append('order','tripordertime')
        params.append('userid', '')
        params.append('triporderstatus', '')
        axios({
            method:'post',
            url:API_getTripOrderList,
            params
        })
        .then((response) => {
            console.log(response.data)
            if(response.data.code == 0) {
              this.tableData = response.data.data
            }else if(response.data.code == 1) {
                this.$message({
                    message: response.data.msg,
                    type: 'warning'
                }); 
            }else {
                this.$message.error('获取户外出团订单信息失败，请稍后重试');
            }        
        }).catch((err) => {
            console.log(err)
        })
      },
      getAdminTripOrderCount() {
            var params = new URLSearchParams();
            params.append('tripid',  this.getCookie('admin_tripid'))
            axios({
                method:'post',
                url:API_getTripOrderCount,
                params
            })
            .then((response) => {
                console.log(response.data)
                if(response.data.code == 0) {
                  this.tripOrderCount = response.data.data
                }else if(response.data.code == 1) {
                    this.$message({
                        message: response.data.msg,
                        type: 'warning'
                    }); 
                }else {
                    this.$message.error('获取订单数量失败，请稍后重试');
                }        
            }).catch((err) => {
                console.log(err)
            })
        },
      handleEdit(triporderid) {
        var params = new URLSearchParams();
        params.append('triporderid',triporderid)
        axios({
            method:'post',
            url:API_sureOrderURl,
            params
        })
        .then((response) => {
            console.log(response.data)
            if(response.data.code == 0) {
              this.$message({
                    message: response.data.msg,
                    type: 'success'
                }); 
              location.reload()
            }else if(response.data.code == 1) {
                this.$message({
                    message: response.data.msg,
                    type: 'warning'
                }); 
            }else {
                this.$message.error('更新订单信息失败，请稍后重试');
            }        
        }).catch((err) => {
            console.log(err)
        })
      },
       handleClose(triporderid) {
        var params = new URLSearchParams();
        params.append('triporderid',triporderid)
        axios({
            method:'post',
            url:API_closeOrderURl,
            params
        })
        .then((response) => {
            console.log(response.data)
            if(response.data.code == 0) {
              this.$message({
                    message: response.data.msg,
                    type: 'success'
                }); 
              location.reload()
            }else if(response.data.code == 1) {
                this.$message({
                    message: response.data.msg,
                    type: 'warning'
                }); 
            }else {
                this.$message.error('更新订单信息失败，请稍后重试');
            }        
        }).catch((err) => {
            console.log(err)
        })
      },
      handleSizeChange(val) {      
        this.pageSize = val
        console.log(`每页 ${this.pageSize} 条`)
      },
      handleCurrentChange(val) {
        this.currentPage = val
        this.getAdminTripOrder()
        console.log(`当前页: ${this.currentPage}`);
      }
    }
}
</script>

<style>
.shopmanagetriporderContainer {
    width: 100%;
}
.shopmanagetriporderknow {
  font-size: 1.5rem;
  margin-top: 3rem;
  margin-bottom: 2rem;
}
.el-table th>.cell {
  text-align: center
}
.miniimg {
  width: 3rem;
  height: 3rem;
  margin-right: 1rem;
}
.orderT {
  text-align: left;
  margin-left: 2rem;
  display: flex;
  align-items: center;
}
.Nae {
  color: orange;
  font-size: 1rem;
  margin-right: 1rem;;
}
</style>
