<template>
  <div class="shopmanagetripContainer">
    <div class="shopmanagetripknow">部落动态与评论管理</div>     
    <div class="clubMainPageBody">
      <div class="messageDiv">
        <div class="messageBox" v-for="(clubdiary, key) in clubDiary">
            <img class="messageImg" :src="clubdiary.clubdiaryimg"/>
            <div class="messageContent">
                <div class="messageUp">
                    <div class="messageTitle">{{clubdiary.clubdiarytitle}}</div>
                    <div class="messageTitleRight">
                      <el-button
                        size="mini"
                        type="danger"
                        @click="deleteClubdiary(clubdiary.clubdiaryid)">删除动态</el-button>
                        <img class="messageUserImg" :src="clubdiary.user.userimg"/>
                        <span class="messageUserName">{{clubdiary.user.username}}</span>
                        <span class="messageTime">发表于{{clubdiary.clubdiarytime}}</span>
                    </div>
                </div>
                <div class="messageDownDrag" >   
                  {{clubdiary.clubdiarycontent}}
                      <div class="evalBox">
                          <div v-for="evale in clubdiary.diaryfirsteval">
                              <span class="evalUser">{{evale.user.username}}</span>评论：{{evale.content}}
                              <button class="evaluateButton" @click="deleteFirstEval(evale.evalid)">删除</button>
                              <div v-for="s in evale.secondevalList">
                                  <span class="evalUser">{{s.fromusername}}</span>评论<span class="evalUser">{{s.tousername}}</span>：{{s.content}}
                                  <button  class="evaluateButton" @click="deleteSecondEval(s.evalid)">删除</button>
                              </div>
                          </div>
                      </div>
                </div>
                <span class="fromClubname">来自{{clubdiary.club.clubname}}</span>
            </div>
        </div> 
    </div>
    </div>
    <div class="block">
      <!-- <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page=currentPage
        :page-sizes="[5, 10, 20]"
        :page-size="5"
        layout="total, sizes, prev, pager, next, jumper"
        :total=clubdiaryCount>
      </el-pagination> -->
    </div>
  </div>
</template>

<script>
import {API_getClubDiaryURl, API_deleteDiaryFirstEvalURl, API_deleteDiarySecondEvalURl, API_deleteClubDiaryURl} from '../../constants/index.js'
import axios from 'axios'
// import bus from '../../utils/passValue'
export default {
    data() {
        return {
          clubDiary: [{
              club: {
                  clubname: ''
              },
              user: {
                  userimg: '',
              },
              diaryfirsteval: {
                  evalid: '',
                  content: '',
                  user: {
                      username:''
                  },
                  secondevalList: {
                    evalid: '',
                    content: '',
                    fromusername: '', 
                    tousername: ''
                 }
              }
          }],
          clubdiaryCount: 0,
          pageSize: 5,
          currentPage: 1,
          batchdelete: []
        }
    },
    created() {
        var clubid = this.getCookie("clubid")
        this.getClubDiary(clubid)
    },
    methods: {
      getClubDiary(clubid) {
        console.log("clibid"+ clubid)
         var params = new URLSearchParams() 
         params.append('clubid',clubid)
         axios({
            method:'post',
            url:API_getClubDiaryURl,
            params
        })
        .then((response) => {
            console.log(response.data)
            if(response.data.code == 0) {
                this.clubDiary = response.data.data
            }else if(response.data.code == 1) {
                this.$message({
                    message: response.data.msg,
                    type: 'warning'
                }); 
            }else {
                this.$message.error('获取部落动态失败，请稍后重试');
            }        
        }).catch((err) => {
            console.log(err)
        })
      },
      deleteClubdiary(clubdiaryid) {
        var params = new URLSearchParams() 
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
                this.$message.error('获取部落动态失败，请稍后重试');
            }        
        }).catch((err) => {
            console.log(err)
        })
      },
      deleteFirstEval(evalid) {
        var params = new URLSearchParams() 
         params.append('evalid',evalid)
         axios({
            method:'post',
            url:API_deleteDiaryFirstEvalURl,
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
                this.$message.error('获取部落动态失败，请稍后重试');
            }        
        }).catch((err) => {
            console.log(err)
        })
      },
      deleteSecondEval(evalid) {
         var params = new URLSearchParams() 
         params.append('evalid',evalid)
         axios({
            method:'post',
            url:API_deleteDiarySecondEvalURl,
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
                this.$message.error('获取部落动态失败，请稍后重试');
            }        
        }).catch((err) => {
            console.log(err)
        })
      },
      // getAdminDiaryCount() {
      //   var params = new URLSearchParams();
      //   params.append('start',-1)
      //   params.append('size',-1)
      //   params.append('clubid', '')
      //   params.append('order','')
      //   axios({
      //       method:'post',
      //       url: API_getAllClubDiary,
      //       params
      //   })
      //   .then((response) => {
      //       console.log(response.data)
      //       if(response.data.code == 0) {
      //         this.diaryCount = response.data.data.length
      //       }else if(response.data.code == 1) {
      //           this.$message({
      //               message: response.data.msg,
      //               type: 'warning'
      //           }); 
      //       }else {
      //           this.$message.error('获取户外出团信息失败，请稍后重试');
      //       }        
      //   }).catch((err) => {
      //       console.log(err)
      //   })
      // },
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
    //  batchdeleteMethod() {
    //     var params = new URLSearchParams();
    //     params.append('batchdelete',this.batchdelete)
    //     axios({
    //         method:'post',
    //         url:API_batchDeleteTripURl,
    //         params
    //     })
    //     .then((response) => {
    //         console.log(response.data)
    //         if(response.data.code == 0) {
    //           this.$message({
    //                 message: response.data.msg,
    //                 type: 'success'
    //             }); 
    //         location.reload()
    //         }else if(response.data.code == 1) {
    //             this.$message({
    //                 message: response.data.msg,
    //                 type: 'warning'
    //             }); 
    //         }else {
    //             this.$message.error('批量删除失败，请稍后重试');
    //         }        
    //     }).catch((err) => {
    //         console.log(err)
    //     })
    //   },
    //   toggleSelection(rows) {
    //     if (rows) {
    //       rows.forEach(row => {
    //         this.$refs.multipleTable.toggleRowSelection(row);
    //       });
    //     } else {
    //       this.$refs.multipleTable.clearSelection();
    //     }
    //   },
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
        this.getAdminDiary()
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
