<template>
  <div class="shopmanagetrainContainer">
    <div class="shopmanagetrainknow">本店户外培训信息管理</div>
     <div class="shopaddtrain">
        <router-link to="addtrain"><img class="addtrainImg" src="../../assets/images/addtrip.svg"/></router-link>
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
      prop="trainpublishtime"
      label="日期"
      width="100">
    </el-table-column>
    <el-table-column
      prop="trainimg"
      label="户外培训图片"
      width="100">
    </el-table-column>
    <el-table-column
      prop="trainname"
      label="户外培训名称"
      width="100">
    </el-table-column>
    <el-table-column
      prop="trainprice"
      label="户外培训价钱"
      width="80">
    </el-table-column>
    <el-table-column
      prop="maxpeople"
      label="最大人数限制"
      width="80">
    </el-table-column>
    <el-table-column
      prop="traindescription"
      label="户外培训描述"
      width="80">
    </el-table-column>
    <el-table-column
      prop="trainnotice"
      label="注意事项"
      width="80">
    </el-table-column>
    <el-table-column label="操作" prop="trainid">
      <template slot-scope="scope">
        <el-button
          size="mini"
          @click="handleEdit(tableData[scope.$index].trainid)">编辑</el-button>
        <el-button
          size="mini"
          type="danger"
          @click="handleDelete(tableData[scope.$index].trainid)">删除</el-button>
      </template>
    </el-table-column>
     </el-table>
  </div>
</template>

<script>
import {API_getShopManageTrainURl, API_deleteTrainURl} from '../../constants/index.js'
import axios from 'axios'
export default {
    name: 'shopmanagetrain',
    data() {
        return {
          tableData: [{
          trainid: '',
          trainpublishtime: '',
          trainimg: '',
          trainname: '',
          trainprice: '',
          maxpeople: '',
          traindescription: '',
          trainnotice: ''}]
        }
    },
    created() {
        this.getShopManagetrain()
    },
    watch: {
      '$route' (to, from) {
        if(to.path === "/shop/shopmanagetrain") {
          console.log("shopManagetrain  watch监听中")
          this.getShopManagetrain()
        }   
      }
    },
    methods: {
      getShopManagetrain () {
        console.log("shopid为："+this.getCookie('shop_id'))
        var params = new URLSearchParams();
        params.append('shopid',this.getCookie('shop_id'))
        axios({
            method:'post',
            url:API_getShopManageTrainURl,
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
      handleEdit(trainid) {
        this.$router.push({name:'updateTrainInfo', params:{trainid}})
      },
      handleDelete(trainid) {
        console.log("即将删除的trainid"+trainid)
        var params = new URLSearchParams();
        params.append('trainid',trainid)
        axios({
            method:'post',
            url:API_deleteTrainURl,
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
.shopmanagetrainContainer {
    width: 100%;
}
.shopmanagetrainknow {
  font-size: 1.5rem;
  margin-top: 3rem;
  margin-bottom: 2rem;
}
.shopaddtrain {
    height: 2rem;
    text-align: right;
    padding-right: 5rem;
    display: flex;
    align-items: center;
    flex-direction: row-reverse;
}
.addtrainImg {
    width: 2rem;
    padding-left: 1rem;
}
</style>

