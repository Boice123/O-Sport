<template>
  <div class="shopmanagetriporderContainer">
    <div class="shopmanagetriporderknow">商家出行订单管理</div>
     <el-table
      :data="tableData"
      style="width: 100%"
      tooltip-effect="dark"
    >
    <el-table-column
      type="selection"
      width="55">
    </el-table-column>
    <el-table-column
      prop="triporderid"
      label="订单号"
      width="300">
    </el-table-column>
    <el-table-column
      prop="tripordertime"
      label="订单时间"
      width="100">
    </el-table-column>
    <el-table-column
      prop="tripordertotal"
      label="订单总价"
      width="100">
    </el-table-column>
    <el-table-column
      prop="username"
      label="用户昵称"
      width="100">
    </el-table-column>
    <el-table-column
      prop="triporderstatus"
      label="订单状态"
      width="100">
    </el-table-column>
    <el-table-column label="操作" prop="tripid">
      <template slot-scope="scope">
        <el-button
          size="mini"
          @click="handleEdit(tableData[scope.$index].triporderid)">确认订单</el-button>
        <el-button
          size="mini"
          type="danger"
          @click="handleDelete(tableData[scope.$index].tripid)">删除</el-button>
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
        :total=shopTripOrderCount>
      </el-pagination>
    </div>
  </div>
</template>

<script>
import { API_getShopAllTripOrderPagination} from '../../constants/index.js'
import axios from 'axios'
export default {
    name: 'shopmanagetriporder',
    data() {
        return {
          tableData: [{
          triporderid: '',
          tripordertime: '',
          tripordertotal: ''}],
          shopTripOrderCount: 0,
          pageSize: 5,
          currentPage: 1
        }
    },
    created() {
      this.getShopManageTripOrder()
      this.getShopTripOrderCount()
    },
    methods: {
      //获得订单信息
      getShopManageTripOrder () {
        console.log(this.getCookie('admin_shopid'))
        var params = new URLSearchParams();
        params.append('start',(this.currentPage-1) * this.pageSize)
        params.append('size',this.pageSize)
        params.append('shopid',this.getCookie('admin_shopid'))
        params.append('order','tripordertime')
        axios({
            method:'post',
            url:API_getShopAllTripOrderPagination,
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
      getShopTripOrderCount() {
            var params = new URLSearchParams();
            params.append('start',-1)
            params.append('size',-1)
            params.append('order','')
            params.append('shopid', this.getCookie('admin_shopid'))
            axios({
                method:'post',
                url:API_getShopAllTripOrderPagination,
                params
            })
            .then((response) => {
                console.log(response.data)
                if(response.data.code == 0) {
                  this.shopTripOrderCount = response.data.data.length
                }else if(response.data.code == 1) {
                    this.$message({
                        message: response.data.msg,
                        type: 'warning'
                    }); 
                }else {
                    this.$message.error('获取所有入驻商家数量失败，请稍后重试');
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
            url:API_shopUpdateOrderStatusURl,
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
        this.getShopManageTripOrder()
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
</style>
