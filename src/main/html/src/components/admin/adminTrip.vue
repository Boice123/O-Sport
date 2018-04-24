<template>
  <div class="shopmanagetripContainer">
    <div class="shopmanagetripknow">出行活动信息管理</div>
    <div class="buttonWrap">
     <div class="kindButton">
      <div :class="{'kinButtonbActive': chooseTripstatus === '发布中', 'kinButtonb': chooseTripstatus !== '发布中'}" @click="getTripByTripStatus('发布中')">发布中</div>
      <div :class="{'kinButtonbActive': chooseTripstatus !== '发布中', 'kinButtonb': chooseTripstatus === '发布中'}" @click="getTripByTripStatus('已下架')">已下架</div>
    </div>   
    <div class="shopButton">
      <!-- <div v-if="chooseTripstatus === '发布中'" class="shopdeletetrip" @click="batchCloseMethod">批量下架</div>
      <div v-if="chooseTripstatus === '已下架'" class="shopdeletetrip" @click="batchdeleteMethod">批量删除</div> -->
      <router-link to="addTrip"><div class="shopaddtrip">新增</div></router-link>
    </div>    
    </div>  
     <el-table
    :data="tableData"
    style="width: 100%"
    tooltip-effect="dark"
    >
    <el-table-column
      type="index"
      width="50">
    </el-table-column>
    <el-table-column
      label="出团名称"
      width="150">
       <template slot-scope="scope">
        <span @click="gotoOrder(tableData[scope.$index].tripid)">{{tableData[scope.$index].tripname}}</span>
      </template>
    </el-table-column>
    <el-table-column
      prop="tripimg"
      label="出团图片"
      width="100">
       <template slot-scope="scope">
        <img class="miniimg" :src="tableData[scope.$index].tripimg"/>
      </template>
    </el-table-column>
    <el-table-column
      prop="trippublishtime"
      label="出团日期"
      width="100">
    </el-table-column>
    <el-table-column
      prop="tripprice"
      label="出团价钱"
      width="80">
    </el-table-column>
    <el-table-column
      prop="maxpeople"
      label="可报名人数"
      width="80">
    </el-table-column>
    <el-table-column
      prop="tripdescription"
      label="出团描述"
      width="100">
    </el-table-column>
    <el-table-column
      prop="tripnotice"
      label="注意事项"
      width="100">
    </el-table-column>
    <el-table-column
      prop="triptrading"
      label="报名数"
      width="80">
    </el-table-column>
     <el-table-column
      prop="tripsure"
      label="付款数"
      width="80">
    </el-table-column>
    <el-table-column
      prop="closeOrderList.length"
      label="用户取消数"
      width="80">
    </el-table-column>
    <el-table-column label="" prop="tripid">
      <template slot-scope="scope">
        <el-button
          size="mini"
          @click="gotoOrder(tableData[scope.$index].tripid)">查看订单</el-button>
        <el-button
          size="mini"
          v-if="chooseTripstatus === '发布中'"
          @click="handleEdit(tableData[scope.$index].tripid)">编辑</el-button>
        <el-button
          size="mini"
          type="danger"
          v-if="chooseTripstatus === '发布中'"
          @click="changeTripstatus(tableData[scope.$index].tripid, '已下架')">下架</el-button>
          <el-button
          size="mini"
          v-if="chooseTripstatus === '已下架'"
          @click="changeTripstatus(tableData[scope.$index].tripid, '发布中')">上架</el-button>
        <el-button
          size="mini"
          type="danger"
          v-if="chooseTripstatus === '已下架'"
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
        :total=adminTripCount>
      </el-pagination>
    </div>
  </div>
</template>

<script>
import {API_getTripList, API_getTripCloseList, API_changeTripstatusURl, API_deleteTripURl, API_batchDeleteTripURl, API_getTripCountURl, API_getTripCountCloseURl} from '../../constants/index.js'
import axios from 'axios'
// import bus from '../../utils/passValue'
export default {
    name: 'shopmanagetrip',
    data() {
        return {
          tableData: [{
          tripid: '',
          trippublishtime: '',
          tripimg: '',
          tripname: '',
          tripprice: '',
          maxpeople: '',
          tripdescription: '',
          tripnotice: '',
          triptrading: '',
          tripsure: '',
          closeOrderList: []}],
          pageSize: 5,
          currentPage: 1,
          batchdelete: [],
          adminTripCount: 0,
          chooseTripstatus: '发布中'
        }
    },
    created() {
        this.getTripByTripStatus('发布中')
    },
    methods: {
      gotoOrder(tripid) {
        this.setCookie('admin_tripid', tripid)
        this.$router.push({name: 'adminTripOrder'})
      },
      getTripByTripStatus(value) {
        this.chooseTripstatus = value
         //获取户外出团总数  
          var url = this.chooseTripstatus === '发布中' ? API_getTripCountURl: API_getTripCountCloseURl
          axios({
              method:'post',
              url
          })
          .then((response) => {
              console.log(response.data)
              if(response.data.code == 0) {
                this.adminTripCount = response.data.data
              }else if(response.data.code == 1) {
                  this.$message({
                      message: response.data.msg,
                      type: 'warning'
                  }); 
              }else {
                  this.$message.error('获取出团总数信息失败，请稍后重试');
              }        
          }).catch((err) => {
              console.log(err)
          })
          this.getTripList()
      },
      getTripList() {
        var params = new URLSearchParams();
          params.append('start',(this.currentPage-1) * this.pageSize)
          params.append('size',this.pageSize)
          params.append('order','trippublishtime')
          var url = this.chooseTripstatus === '发布中' ? API_getTripList : API_getTripCloseList
          axios({
              method:'post',
              url,
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
                  this.$message.error('获取出团总数信息失败，请稍后重试');
              }        
          }).catch((err) => {
              console.log(err)
          })
      },
      handleEdit(tripid) {
        this.$router.push({name:'updateTrip', params:{tripid}})
      },
      handleDelete(tripid) {
        console.log("即将删除的tripid"+tripid)
        var params = new URLSearchParams();
        params.append('tripid',tripid)
        axios({
            method:'post',
            url:API_deleteTripURl,
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
                this.$message.error('删除户外出团信息失败，请稍后重试');
            }        
        }).catch((err) => {
            console.log(err)
        })
      },
      changeTripstatus(tripid, tripstatus) {
        console.log("即将下架的tripid"+tripid)
        var params = new URLSearchParams();
        params.append('tripid',tripid)
        params.append('tripstatus',tripstatus)
        axios({
            method:'post',
            url:API_changeTripstatusURl,
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
                this.$message.error('操作失败，请稍后重试');
            }        
        }).catch((err) => {
            console.log(err)
        })
      },
     batchdeleteMethod() {
        var params = new URLSearchParams();
        params.append('batchdelete',this.batchdelete)
        axios({
            method:'post',
            url:API_batchDeleteTripURl,
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
                this.$message.error('批量删除失败，请稍后重试');
            }        
        }).catch((err) => {
            console.log(err)
        })
      },
      toggleSelection(rows) {
        if (rows) {
          rows.forEach(row => {
            this.$refs.multipleTable.toggleRowSelection(row);
          });
        } else {
          this.$refs.multipleTable.clearSelection();
        }
      },
      // handleSelectionChange(val) {
      //   this.batchdelete = []
      //   for(let i = 0; i < val.length; i++) {
      //     this.batchdelete.push(val[i].tripid)
      //   }
      //   console.log(this.batchdelete)
      // },
      handleSizeChange(val) {      
        this.pageSize = val
        console.log(`每页 ${this.pageSize} 条`)
      },
      handleCurrentChange(val) {
        this.currentPage = val
        this.getTripList()
        console.log(`当前页: ${this.currentPage}`);
      }
    }
}
</script>

<style>
.shopmanagetripContainer {
    width: 100%;
}
.shopmanagetripknow {
  font-size: 1.5rem;
  margin-top: 3rem;
  margin-bottom: 2rem;
}
.buttonWrap {
  display: flex;
  justify-content: space-between
}
.shopButton {
  display: flex;
  flex-direction: row;
  justify-content: flex-end;
  margin-bottom: 1rem;
}
.kindButton {
   display: flex;
    flex-direction: row;
    justify-content: flex-start;
    margin-bottom: 1rem;
}
.kinButtonb {
    width: 5rem;
    height: 2rem;
    font-weight: 500;
    display: flex;
    justify-content: center;
    align-items: center;
    border: 1px solid orange;
    border-radius: 0.5rem;
    margin-left: 2rem;
}
.kinButtonbActive {
    width: 5rem;
    height: 2rem;
    font-weight: 500;
    display: flex;
    justify-content: center;
    align-items: center;
    border: 1px solid orange;
    border-radius: 0.5rem;
    margin-left: 2rem;
    color: #fff;
    background-color: orange;
}
.shopdeletetrip {
  background: red;
  color: #fff;
  width: 5rem;
  height: 2rem;
  font-weight: 500;
  display: flex;
  align-items: center;
  justify-content: center;
  border-radius: 0.5rem;
  margin-right: 1rem;
}
.shopaddtrip {
    width: 5rem;
    height: 2rem;
    font-weight: 500;
    display: flex;
    justify-content: center;
    align-items: center;
    border: 1px solid red;
    border-radius: 0.5rem;
    margin-right: 5rem;
}
.addtripImg {
    width: 2rem;
    padding-left: 1rem;
}
.block {
  margin: 2rem auto;
}

.el-table th>.cell {
  text-align: center
}
.miniimg {
  width: 3rem;
  height: 3rem;
}
</style>
