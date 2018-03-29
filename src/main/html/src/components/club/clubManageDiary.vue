<template>
  <div class="shopmanagetripContainer">
    <div class="shopmanagetripknow">部落动态管理</div>
    <div class="shopButton">
      <div class="shopdeletetrip" @click="batchdeleteMethod">批量删除</div>
      <!-- <router-link to="/addDiary"><div class="shopaddtrip">新增</div></router-link> -->
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
      prop="clubdiarytime"
      label="动态发布日期"
      width="100">
    </el-table-column>
    <el-table-column
      prop="clubdiarytitle"
      label="动态标题"
      width="100">
      <!-- <img :src={tripimg} /> -->
    </el-table-column>
    <el-table-column
      prop="clubdiarycontent"
      label="动态内容"
      width="150">
    </el-table-column>
    <el-table-column
      prop="clubdiaryimg"
      label="动态封面"
      width="80">
    </el-table-column>
    <el-table-column
      prop="username"
      label="发布用户昵称"
      width="80">
    </el-table-column>
    <el-table-column label="操作" prop="clubdiaryid">
      <template slot-scope="scope">
        <el-button
          size="mini"
          type="danger"
          @click="handleDelete(tableData[scope.$index].clubdiaryid)">删除</el-button>
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
        :total=tableData.length>
      </el-pagination>
    </div>
  </div>
</template>

<script>
import {API_getClubDiaryPaginationURl, API_deleteClubDiaryURl, API_batchDeleteDiaryURl } from '../../constants/index.js'
import axios from 'axios'
// import bus from '../../utils/passValue'
export default {
    name: 'clubmanageactivity',
    data() {
        return {
          tableData: [{
          clubdiaryid: '',
          clubdiarytime: '',
          clubdiarytitle: '',
          clubdiarycontent: '',
          clubdiaryimg: '',
          userid: '',
          username: ''
          }],
          pageSize: 5,
          currentPage: 1,
          batchdelete: []
        }
    },
    created() {
        this.getClubManageDiary()
    },
    watch: {
      '$route' (to, from) {
        if(to.path === "/club/clubManageDiary") {
          console.log("clubManageActivity  watch监听中")
          // this.getClubManageActivity()
        }   
      }
    },
    methods: {
      getClubManageDiary () {
        var params = new URLSearchParams();
        params.append('clubid',this.getCookie("clubid"))
        params.append('start',(this.currentPage-1) * this.pageSize)
        params.append('size',this.pageSize)
        params.append('order','clubdiarytime')
        axios({
            method:'post',
            url:API_getClubDiaryPaginationURl,
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
                this.$message.error('获取动态信息失败，请稍后重试');
            }        
        }).catch((err) => {
            console.log(err)
        })
      },
      handleDelete(clubdiaryid) {
        console.log("即将删除的clubdiaryid"+clubdiaryid)
        var params = new URLSearchParams();
        params.append('clubdiaryid',clubdiaryid)
        axios({
            method:'post',
            url:API_deleteClubDiaryURl,
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
                this.$message.error('删除动态信息失败，请稍后重试');
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
            url:API_batchDeleteDiaryURl,
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
          this.batchdelete.push(val[i].clubdiaryid)
        }
        console.log(this.batchdelete)
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
