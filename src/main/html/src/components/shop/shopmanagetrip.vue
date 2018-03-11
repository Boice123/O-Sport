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
  </div>
</template>

<script>
import {API_getShopManageTripURl, API_deleteTripURl} from '../../constants/index.js'
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
          tripnotice: ''}]
        }
    },
    created() {
        this.getShopManageTrip()
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
        axios({
            method:'post',
            url:API_getShopManageTripURl,
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
</style>
