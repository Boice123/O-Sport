<template>
  <div class="shopmanagetripContainer">
    <div class="shopmanagetripknow">用户管理</div>     
    <el-table
    :data="user"
    style="width: 100%"
    tooltip-effect="dark"
    >
    <el-table-column
      type="index"
      width="50">
    </el-table-column>
    <el-table-column
      prop="username"
      label="会员昵称"
      width="150">
    </el-table-column>
    <el-table-column
      label="会员头像"
      width="100">
       <template slot-scope="scope">
        <img class="miniimg" :src="user[scope.$index].userimg"/>
      </template>
    </el-table-column>
    <el-table-column label="" prop="tripid">
      <template slot-scope="scope">
        <el-button
          size="mini"
          type="danger"
          @click="handleDelete(user[scope.$index].userid)">移出</el-button>
      </template>
    </el-table-column>
  </el-table>
  </div>
</template>

<script>
import {API_getUserURl, API_deleteUserURl} from '../../constants/index.js'
import axios from 'axios'
export default {
    data() {
        return {
          user: []
        }
    },
    created() {
        this.getuser()
    },
    methods: {
      getuser() {
         axios({
            method:'post',
            url:API_getUserURl,
        })
        .then((response) => {
            console.log(response.data)
            if(response.data.code == 0) {
                this.user = response.data.data
            }else if(response.data.code == 1) {
                console.log(response.data.msg)
            }else {
                console.log('获取用户失败，请稍后重试');
            }        
        }).catch((err) => {
            console.log(err)
        })
      },
      handleDelete(userid) {
        var params = new URLSearchParams();
        params.append('userid',userid)
        axios({
            method:'post',
            url:API_deleteUserURl,
            params
        })
        .then((response) => {
            console.log(response.data)
            if(response.data.code == 0) {
              this.$message({
                    message: response.data.msg,
                    type: 'success'
                }); 
            this.$router.push({name: 'adminTrip'})
            }else if(response.data.code == 1) {
               console.log(response.data.msg)
            }else {
                console.log('删除部落会员失败，请稍后重试');
            }        
        }).catch((err) => {
            console.log(err)
        })
      },
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
.messageDiv {
    width: 98%;
    margin: 0 auto;
}
.messageBox {
    padding: 1rem;
    margin-top: 1rem;
    background: #fff;
    display: flex;
    flex-direction: row;
    border-bottom: 1px solid orange;
}
.messageImg {
    width: 15rem;
    height: 12rem;
}
.messageContent {
    display: flex;
    flex: 1;
    flex-direction: column;
    position: relative;
}
.messageUp {
    display: flex;
    align-items: center;
    justify-content: space-between;
    margin-bottom: 1rem;
}
.messageUserImg {
    width: 4rem;
    height: 4rem;
    border-radius: 2rem;
    margin-left: 1rem;
}
.messageTitle {
    margin-left: 1rem;
    font-size: 20px;
    font-weight: 600;
}
.messageTitleRight {
    display: flex;
    align-items: center;
}
.messageUserName {
    font-size: 15px;
    margin-left: 1rem;
    margin-right: 1rem;
}
.messageTime {
    color: gray;
}
.messageDown {
    color: gray;
    overflow: hidden;
    margin-left: 1rem;
    line-height: 1.4rem;
    font-size: 16px;
    text-align: left;
    text-overflow:ellipsis;
    display: -webkit-box;
    -webkit-box-orient: vertical;
    -webkit-line-clamp: 4;
}
.messageDownDrag {
    color: gray;
    overflow: hidden;
    margin-left: 1rem;
    line-height: 1.4rem;
    font-size: 16px;
    text-align: left;
    text-overflow:ellipsis;
    display: -webkit-box;
    -webkit-box-orient: vertical;
    /* -webkit-line-clamp: 5; */
}
.block {
  margin: 2rem auto;
}

.el-table th>.cell {
  text-align: center
}
.fromClubname {
    color: orange;
    margin-right: 1rem;
    font-size: 0.8rem;
}
.evaluateButton {
    padding: .1rem .4rem;
    border: 1px solid orange;
    background: #fff;
    border-radius: 0.5rem;
    margin-right: 1rem;
}
.evalBox {
    margin-top: 1rem;
    padding-top: 1rem;
    border-top: 1px solid orange;
    margin-bottom: 3rem;
    font-size: 0.8rem;
}
.evalUser {
    font-size: 0.8rem;
    color: orange;
}
</style>
