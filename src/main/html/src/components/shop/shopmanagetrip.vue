<template>
  <div class="shopmanagetripContainer">
    <div class="shopmanagetripknow">本店报团出行信息管理</div>
    <div class="shopaddtrip">
        <router-link to="addTrip"><img class="addtripImg" src="../../assets/images/addtrip.svg"/></router-link>
        新增
    </div>
     <el-table
    :data="tableData"
    style="width: 100%"
    tooltip-effect="dark"
    @selection-change="handleSelectionChange"
    >
    <el-table-column
      type="selection"
      width="55">
    </el-table-column>
    <el-table-column
      prop="trippublishtime"
      label="日期"
      width="100">
    </el-table-column>
    <el-table-column
      prop="tripimg"
      label="出团图片"
      width="100">
      <!-- <img :src={tripimg} /> -->
    </el-table-column>
    <el-table-column
      prop="tripname"
      label="出团名称"
      width="100">
    </el-table-column>
    <el-table-column
      prop="tripprice"
      label="出团价钱"
      width="80">
    </el-table-column>
    <el-table-column
      prop="maxpeople"
      label="最大人数限制"
      width="80">
    </el-table-column>
    <el-table-column
      prop="tripdescription"
      label="出团描述"
      width="80">
    </el-table-column>
    <el-table-column
      prop="tripnotice"
      label="注意事项"
      width="80">
    </el-table-column>
    <el-table-column
      prop="triptrading"
      label="成交量"
      width="80">
    </el-table-column>
    <el-table-column label="操作" prop="tripid">
      <template slot-scope="scope">
        <el-button
          size="mini"
          @click="handleEdit(tableData[scope.$index].tripid)">编辑</el-button>
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
        :total=shopTripCount>
      </el-pagination>
    </div>
  </div>
</template>

<script>
import {API_getShopManageTripURl, API_deleteTripURl, API_getShopTripCountURl, API_getShopManageTripPaginationURl } from '../../constants/index.js'
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
          triptrading: ''}],
          shopTripCount: 0,
          pageSize: 5,
          currentPage: 1
        }
    },
    created() {
        this.getShopManageTrip()
        this.getShopManageTripCount()
    },
    watch: {
      '$route' (to, from) {
        if(to.path === "/shop/shopmanagetrip") {
          console.log("shopManageTrip  watch监听中")
          this.getShopManageTrip()
        }   
      }
    },
    methods: {
      getShopManageTrip () {
        console.log("shopid为："+this.getCookie('shop_id'))
        var params = new URLSearchParams();
        params.append('shopid',this.getCookie('shop_id'))
        params.append('start',(this.currentPage-1) * this.pageSize)
        params.append('size',this.pageSize)
        params.append('order','trippublishtime')
        axios({
            method:'post',
            url:API_getShopManageTripPaginationURl,
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
                this.$message.error('获取户外出团信息失败，请稍后重试');
            }        
        }).catch((err) => {
            console.log(err)
        })
      },
      getShopManageTripCount() {
          //获取商店户外出团总数  
          var params = new URLSearchParams();
          params.append('shopid',this.getCookie('shop_id'))
          axios({
              method:'post',
              url:API_getShopTripCountURl,
              params
          })
          .then((response) => {
              console.log(response.data)
              if(response.data.code == 0) {
                this.shopTripCount = response.data.data
              }else if(response.data.code == 1) {
                  this.$message({
                      message: response.data.msg,
                      type: 'warning'
                  }); 
              }else {
                  this.$message.error('获取店铺户外出团总数信息失败，请稍后重试');
              }        
          }).catch((err) => {
              console.log(err)
          })
      },
      handleEdit(tripid) {
        this.$router.push({name:'updateTripInfo', params:{tripid}})
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
      toggleSelection(rows) {
        if (rows) {
          rows.forEach(row => {
            this.$refs.multipleTable.toggleRowSelection(row);
          });
        } else {
          this.$refs.multipleTable.clearSelection();
        }
      },
      handleSelectionChange(val) {
        this.multipleSelection = val;
      },
      handleSizeChange(val) {      
        this.pageSize = val
        console.log(`每页 ${this.pageSize} 条`)
      },
      handleCurrentChange(val) {
        this.currentPage = val
        this.getShopManageTrip()
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
.shopaddtrip {
    height: 2rem;
    text-align: right;
    padding-right: 5rem;
    display: flex;
    align-items: center;
    flex-direction: row-reverse;
}
.addtripImg {
    width: 2rem;
    padding-left: 1rem;
}
.block {
  margin: 2rem auto;
}
</style>
