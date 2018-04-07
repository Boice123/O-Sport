<template>
  <div class="shopmanagetripContainer">
    <div class="shopmanagetripknow">入驻商家管理</div>
    <div class="shopButton">
      <!-- <div class="shopdeletetrip" @click="batchdeleteMethod">批量删除</div> -->
      <!-- <router-link to="addTrip"><div class="shopaddtrip">新增</div></router-link> -->
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
      prop="shopid"
      label="商家Id"
      width="100">
    </el-table-column>
    <el-table-column
      prop="shoppublishtime"
      label="商家入驻日期"
      width="100">
    </el-table-column>
    <el-table-column
      prop="shopimg"
      label="商家头像"
      width="100">
      <!-- <img :src={tripimg} /> -->
    </el-table-column>
    <el-table-column
      prop="shopname"
      label="商家名称"
      width="150">
    </el-table-column>
    <el-table-column
      prop="userid"
      label="创建人"
      width="80">
    </el-table-column>
    <el-table-column
      prop="shopstatus"
      label="商家状态"
      width="100">
    </el-table-column>
    <el-table-column label="操作" prop="shopid">
      <template slot-scope="scope">
        <el-button
          size="mini"
          @click="handleEdit(tableData[scope.$index].shopid)">冻结</el-button>
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
        :total=shopCount>
      </el-pagination>
    </div>
  </div>
</template>

<script>
import {API_getAllShopPagination, API_getShopCount } from '../../constants/index.js'
import axios from 'axios'
// import bus from '../../utils/passValue'
export default {
    name: 'shopmanagetrip',
    data() {
        return {
          tableData: [{
          shopid: '',
          shoppublishtime: '',
          shopimg: '',
          shopname: '',
          userid: '',
          shopstatus: 0
          }],
          shopCount: 0,
          pageSize: 5,
          currentPage: 1,
        }
    },
    created() {
        this.getAllShop()
        this.getShopCount()
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
      getAllShop() {
            //获取所有入驻商家信息
            var params = new URLSearchParams();
            params.append('start', (this.currentPage-1) * this.pageSize)
            params.append('size',this.pageSize)
            params.append('order','shoppublishtime')
            axios({
                method:'post',
                url:API_getAllShopPagination,
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
                    this.$message.error('获取所有入驻商家信息失败，请稍后重试');
                }        
            }).catch((err) => {
                console.log(err)
            })
        },
        getShopCount() {
            axios({
                method:'post',
                url:API_getShopCount
            })
            .then((response) => {
                console.log(response.data)
                if(response.data.code == 0) {
                  this.shopCount = response.data.data
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
      handleSelectionChange(val) {
        this.batchdelete = []
        for(let i = 0; i < val.length; i++) {
          this.batchdelete.push(val[i].tripid)
        }
        console.log(this.batchdelete)
      },
      handleSizeChange(val) {      
        this.pageSize = val
        console.log(`每页 ${this.pageSize} 条`)
      },
      handleCurrentChange(val) {
        this.currentPage = val
        this.getAllShop()
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
.shopButton {
  display: flex;
  flex-direction: row;
  justify-content: flex-end;
  margin-bottom: 1rem;
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
</style>
