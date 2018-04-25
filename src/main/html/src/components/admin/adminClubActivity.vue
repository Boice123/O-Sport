<template>
  <div class="shopmanagetripContainer">
    <div class="shopmanagetripknow">部落攻略管理</div>
    <div class="shopButton">
      <div class="shopdeletetrip" @click="addClubActivity">新增</div>
      <!-- <router-link to="addTrip"><div class="shopaddtrip">新增</div></router-link> -->
    </div>      
     <el-table
    :data="tableData"
    style="width: 100%"
    tooltip-effect="dark"
    >
    <el-table-column
      type="index"
      width="55">
    </el-table-column>
    <el-table-column
      prop="clubactivityimg"
      label="攻略图片"
      width="100">
       <template slot-scope="scope">
        <img class="miniimg" :src="tableData[scope.$index].clubactivityimg"/>
      </template>
    </el-table-column>
    <el-table-column
      prop="clubactivitytitle"
      label="攻略名称"
      width="150">
    </el-table-column>
    <el-table-column
      prop="clubactivitycontent"
      label="攻略内容"
      width="500">
    </el-table-column>
    <el-table-column
      prop="clubactivitypeople"
      label="点赞人数"
      width="80">
    </el-table-column>
    <el-table-column
      prop="clubactivitytime"
      label="发布日期"
      width="100">
    </el-table-column>
    <el-table-column label="">
      <template slot-scope="scope">
        <el-button
          size="mini"
          @click="handleEdit(tableData[scope.$index].clubactivityid)">编辑</el-button>
        <el-button
          size="mini"
          type="danger"
          @click="handleDelete(tableData[scope.$index].clubactivityid)">删除</el-button>
      </template>
    </el-table-column>
  </el-table>
    <!-- <div class="block">
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page=currentPage
        :page-sizes="[5, 10, 20]"
        :page-size="5"
        layout="total, sizes, prev, pager, next, jumper"
        :total=activityCount>
      </el-pagination>
    </div> -->
  </div>
</template>

<script>
import {API_getClubActivity, API_deleteActivityURl} from '../../constants/index.js'
import axios from 'axios'
export default {
    data() {
        return {
          tableData: [{
          clubactivityid: '',
          clubactivitytime: '',
          clubactivityimg: '',
          clubactivitytitle: '',
          clubactivitycontent: '',
          clubid: '',
          }],
          activityCount: 0,
          pageSize: 5,
          currentPage: 1,
          batchdelete: []
        }
    },
    created() {
        this.getClubactivity()
    },
    methods: {
      getClubactivity () {
        var params = new URLSearchParams();
        params.append('clubid',this.getCookie("clubid"))
        axios({
            method:'post',
            url:API_getClubActivity,
            params
        })
        .then((response) => {
            console.log(response.data)
            if(response.data.code == 0) {
              this.tableData = response.data.data
            }else if(response.data.code == 1) {
               console.log(response.data.msg)
            }else {
                console.log('获取部落攻略失败，请稍后重试');
            }        
        }).catch((err) => {
            console.log(err)
        })
      },
      addClubActivity() {
        this.$router.push({name: 'addActivity'})
      },
      handleEdit(clubactivityid) {
        this.$router.push({name: 'updateActivity', params: {clubactivityid}})
      },
      handleDelete(clubactivityid) {
        var params = new URLSearchParams();
        params.append('clubactivityid',clubactivityid)
        axios({
            method:'post',
            url:API_deleteActivityURl,
            params
        })
        .then((response) => {
            console.log(response.data)
            if(response.data.code == 0) {
              this.$message({
                    message: response.data.msg,
                    type: 'success'
                }); 
            this.$router.push({name: 'adminClub'})
            }else if(response.data.code == 1) {
               console.log(response.data.msg)
            }else {
                this.$message.error('删除失败，请稍后重试');
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
        this.getAdminClubactivity()
        console.log(`当前页: ${this.currentPage}`);
      }
    }
}
</script>

<style>
.shopmanagetripContainer {
    width: 100%;
    min-height: 50rem;
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
