<template>
  <div class="clubContainer">
      <ClubHead/>
      <div class="myClub">
          <div class="myClubLeft">
              <div class="myClubLeftTitle">我关注的部落</div>
              <div class="clubList">
                <div 
                    class="myClubBox" 
                    @mouseover="changeClubUserItemCover(club.clubid)"
                    @mouseout="changeClubUserItemCover('')"
                    v-for="(club, key) in clubuseritem"             
                >
                    <img 
                        :class="{'myclubImg':clubusercover!=club.clubid,'myclubImgActive': clubusercover==club.clubid}" 
                        :src="club.clubimg"
                         @click="gotoClubPage(club.clubid)"
                    />
                    <h2 class="myclubname">{{club.clubname}}</h2>{{club.clubuseritemid}}
                     <button class="exitClubButton" @click="exitClub(club.clubid)">取消关注</button>
                </div>          
              </div>
          </div>
          <div class="myClubRight">
              <div class="myclubtab">
                  <!-- <div class="myclubtabtab" @click="changeChoose('管理部落动态')">管理部落动态</div> -->
                  <div class="myclubtabtab" @click="changeChoose('部落动态')">部落动态</div>
              </div>
              <div class="myClubmessageDiv" v-if="choose == '部落动态'">
                <div class="myClubmessageBox" 
                    v-for="(diary, key) in joinClubDiary"
                >
                    <img class="myClubmessageImg" :src="diary.clubdiaryimg"/>
                    <div class="myClubmessageContent">
                        <div class="myClubmessageUp">
                            <div class="myClubmessageTitle">{{diary.clubdiarytitle}}</div>
                            <div class="myClubmessageTitleRight">
                                <img class="myClubmessageUserImg" :src="diary.user.userimg"/>
                                <span class="myClubmessageUserName">{{diary.username}}</span>
                                <span class="myClubmessageTime">发表于{{diary.clubdiarytime}}</span>
                            </div>
                        </div>
                        <!-- <div :class="{'messageDown': clubdiaryid != diary.clubdiaryid, 'messageDownDrag': clubdiaryid == diary.clubdiaryid}"> -->
                        <div class="messageDownDrag" >    
                            {{diary.clubdiarycontent}}
                            <div class="evalBox">
                                <div v-for="evale in diary.diaryfirsteval">
                                    <span class="evalUser">{{evale.user.username}}</span>评论：{{evale.content}}
                                    <button  v-if="evale.user.username != getCookie('user_username')" class="evaluateButton" @click="secondeval(evale.evalid, evale.user.username)">回复</button>
                                    <div v-for="s in evale.secondevalList">
                                        <span class="evalUser">{{s.fromusername}}</span>评论<span class="evalUser">{{s.tousername}}</span>：{{s.content}}
                                        <button v-if="s.fromusername != getCookie('user_username')" class="evaluateButton" @click="secondeval(evale.evalid, s.fromusername)">回复</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- <span v-if="clubdiaryid != diary.clubdiaryid" class="dragdown" @click="dragPassageDown(diary.clubdiaryid)"><span  class="fromClubname">来自{{diary.club.clubname}}</span><button class="evaluateButton" @click="evaluate(diary.clubdiaryid)">评论</button>点击下拉</span> -->
                        <span class="dragdown"><span  class="fromClubname">来自{{diary.club.clubname}}</span><button class="evaluateButton" @click="evaluate(diary.clubdiaryid)">评论</button></span>
                    </div>
                </div>
              </div>
          </div>
      </div>
      <div class="block">
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page=currentPage
        :page-sizes="[5, 10, 20]"
        :page-size="5"
        layout="total, sizes, prev, pager, next, jumper"
        :total=clubdiaryCount>
      </el-pagination>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
import ClubHead from './clubHead.vue'
import { API_getClubUserItemURl, API_getJoinClubDiaryURl, API_exitClubURl, API_getJoinClubDiaryCountURL, API_saveDiaryFirstEvalURl, API_saveDiarySecondEvalURl } from '../../constants/index.js'
  export default {
    name: 'myclub',
    data() {
      return {
        choose: '部落动态',
        // ownclubcover: '',
        // ownClub: {},
        clubusercover: '',
        clubuseritem: [],
        // ownClubDiary: [],
        joinClubDiary: [{
            club: {
                clubname: ''
            },            
            user: {
                userimg: ''
            },
            diaryfirsteval: [{
                content: '',
                user: {
                    username: ''
                },
                secondevalList: {
                    content: '',
                    fromusername: '', 
                    tousername: ''
                }
            }]
        }],
        clubdiaryid: '',
        clubdiaryCount: 0,
        pageSize: 5,
        currentPage: 1,
      }
    },
    created () {
        this.getClubUserItem()
        this.getJoinClubDiaryCount()
        this.getjoinClubDiary()
    },
    methods: {
    //   changeOwnClubCover(text) {
    //       this.ownclubcover = text
    //   },
      changeClubUserItemCover(text) {
          this.clubusercover = text
      },
      changeChoose(text) {
          this.choose = text
      },
      gotoClubPage(clubid) {
          this.setCookie("clubid", clubid)
          this.$router.push({name:'clubMainPage'})
      },
      dragPassageDown(clubdiaryid) {
         if(this.clubdiaryid == '') {
            this.clubdiaryid = clubdiaryid
          }else {
            this.clubdiaryid = ''
          }
      },
      //获取我参与Club的信息
      getClubUserItem() {
        var params = new URLSearchParams();
        params.append('userid',this.getCookie('user_userid'))
        axios({
            method:'post',
            url:API_getClubUserItemURl,
            params
        })
        .then((response) => {
            console.log(response.data)
            if(response.data.code == 0) {
                this.clubuseritem = response.data.data
            }else if(response.data.code == 1) {
                this.$message({
                    message: response.data.msg,
                    type: 'warning'
                }); 
            }else {
                this.$message.error('获取我参与的部落信息失败，请稍后重试');
            }        
        }).catch((err) => {
            console.log(err)
        })
      },
      // 退出部落
      exitClub(clubid) {
         var params = new URLSearchParams() 
         params.append('userid', this.getCookie("user_userid"))
         params.append('clubid',clubid)
         axios({
            method:'post',
            url:API_exitClubURl,
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
                this.$message.error('退出部落失败，请稍后重试');
            }        
        }).catch((err) => {
            console.log(err)
        }) 
      },
      //获取我参与Club的动态信息，分页，排序
      getjoinClubDiary() {
        var params = new URLSearchParams();
        params.append('start', (this.currentPage-1) * this.pageSize)
        params.append('size',this.pageSize)
        params.append('order','clubdiarytime')
        params.append('userid',this.getCookie('user_userid'))
        axios({
            method:'post',
            url:API_getJoinClubDiaryURl,
            params
        })
        .then((response) => {
            console.log(response.data)
            if(response.data.code == 0) {
                this.joinClubDiary = response.data.data
                console.log(response.data.data[0].clubdiarytitle)
                console.log(response.data.data[0].diaryfirsteval[0])
            }else if(response.data.code == 1) {
                this.$message({
                    message: response.data.msg,
                    type: 'warning'
                }); 
            }else {
                this.$message.error('获取参与的部落动态失败，请稍后重试');
            }        
        }).catch((err) => {
            console.log(err)
        })
      },
      // 获取用户参加部落的动态，主要用于获取长度
      getJoinClubDiaryCount() {
        var params = new URLSearchParams();
        params.append('userid',this.getCookie('user_userid'))
        axios({
            method:'post',
            url:API_getJoinClubDiaryCountURL,
            params
        })
        .then((response) => {
            console.log(response.data)
            if(response.data.code == 0) {
                this.clubdiaryCount = response.data.data.length
            }else if(response.data.code == 1) {
                this.$message({
                    message: response.data.msg,
                    type: 'warning'
                }); 
            }else {
                this.$message.error('获取参与的部落动态数量失败，请稍后重试');
            }        
        }).catch((err) => {
            console.log(err)
        })
      },
      // 评论一级动态
      evaluate(clubdiaryid) {
          this.$prompt('请输入评论', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            inputPattern: /\S/,
            inputErrorMessage: '评论不能为空'
            }).then(({ value }) => {
                var params = new URLSearchParams();
                params.append('content', value)
                params.append('userid', this.getCookie("user_userid"))
                params.append('clubdiaryid', clubdiaryid);
                //评论订单信息
                axios({
                    method:'post',
                    url:API_saveDiaryFirstEvalURl,
                    params
                })
                .then((response) => {
                    console.log(response.data)
                    if(response.data.code == 0) {
                        this.$message({
                            message: response.data.msg,
                            type: 'success'
                        }); 
                        setTimeout(() => {
                            location.reload()
                        },2000)
                    }else if(response.data.code == 1) {
                        this.$message({
                            message: response.data.msg,
                            type: 'warning'
                        }); 
                    }else {
                        this.$message.error('评论失败，请稍后重试');
                    }        
                }).catch((err) => {
                    console.log(err)
                })  
            }).catch(() => {
    
            });          
      },
      // 评论二级动态
      secondeval(firstevalid, tousername) {
          this.$prompt('请输入回复', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            inputPattern: /\S/,
            inputErrorMessage: '回复不能为空'
            }).then(({ value }) => {
                var params = new URLSearchParams();
                params.append('content', value)
                params.append('fromusername', this.getCookie("user_username"))
                params.append('tousername', tousername)
                params.append('firstevalid', firstevalid);
                //回复评论信息
                axios({
                    method:'post',
                    url:API_saveDiarySecondEvalURl,
                    params
                })
                .then((response) => {
                    console.log(response.data)
                    if(response.data.code == 0) {
                        this.$message({
                            message: response.data.msg,
                            type: 'success'
                        }); 
                        setTimeout(() => {
                            location.reload()
                        },2000)
                    }else if(response.data.code == 1) {
                        this.$message({
                            message: response.data.msg,
                            type: 'warning'
                        }); 
                    }else {
                        this.$message.error('评论失败，请稍后重试');
                    }        
                }).catch((err) => {
                    console.log(err)
                })  
            }).catch(() => {
    
            });          
      },
      handleSizeChange(val) {      
        this.pageSize = val
        console.log(`每页 ${this.pageSize} 条`)
      },
      handleCurrentChange(val) {
        this.currentPage = val
        this.getjoinClubDiary()
        console.log(`当前页: ${this.currentPage}`);
      }
    },
    components: {
        ClubHead
    }
  }
</script>

<style>
.clubContainer {
}
.myClub {
    width: 90%;
    margin: 2rem auto;
    display: flex;
    flex-direction: row;
}
.myClubLeft {
    flex: 0.7;
    display: flex;
    flex-direction: column;
    border-right: 1px solid gray;
    padding-right: 1rem;
    position: relative;
}
.myClubRight {
    flex:4;
    margin-left: 1rem;
    margin-bottom: 1rem;
}
.myClubLeftTitle {
    color: #000;
    text-align: left;
    margin-bottom: 1rem;
    font-size: 1rem;
    font-weight: 600;
}
.myClubLeftMa {
    color: orange;
    position: absolute;
    right: 10%;
    border: 1px solid orange;
    padding: 5px;
}
.clubList {
    display: flex;
    flex-direction: column;
    margin-bottom: 4rem;
}
.myClubBox {
    width: 8rem;
    height: 8rem;
    margin-bottom: 5rem;
}
.myclubImg {
    width: 8rem;
    height: 8rem;
    margin-right: 1rem;
}
.myclubImgActive {
    width: 8rem;
    height: 8rem;
    opacity: 0.5;
    margin-right: 1rem;
}
.myclubname {
    margin-top: 0.5rem;
    font-size: .8rem;
    color: #000;
}
.myclubtab {
    height: 2rem;
    display: flex;
    flex-direction: row;
}
.myclubtabtab {
    width: 8rem;
    height: 2rem;
    border: 1px solid pink;
    display: flex;
    align-items: center;
    justify-content: center;
    color: #000;
}
.myClubmessageDiv {
    width: 50rem;
}
.myClubmessageBox {
    padding: 1rem;
    margin-top: 1rem;
    border: 1px solid orange;
    display: flex;
    flex-direction: row;
    /* height: 12rem; */
    /* position: relative; */
}
.myClubmessageDiv {
    width: 98%;
    margin: 0 auto;
}
.myClubmessageImg {
    width: 15rem;
    height: 12rem;
}
.myClubmessageContent {
    display: flex;
    flex: 1;
    flex-direction: column;
    position: relative;
}
.myClubmessageUp {
    width: 100%;
    display: flex;
    align-items: center;
    justify-content: space-between;
    margin-bottom: 1rem;
}
.myClubmessageUserImg {
    width: 4rem;
    height: 4rem;
    border-radius: 2rem;
}
.myClubmessageTitle {
    margin-left: 1rem;
    font-size: 20px;
    font-weight: 600;
}
.myClubmessageTitleRight {
    display: flex;
    align-items: center;
}
.myClubmessageUserName {
    font-size: 15px;
    margin-left: 1rem;
    margin-right: 1rem;
}
.myClubmessageTime {
    color: gray;
}
.myClubmessageDown {
    color: gray;
    overflow: hidden;
    margin-left: 1rem;
    line-height: 1.4rem;
    font-size: 16px;
    text-align: left;
    text-overflow:ellipsis;
    display: -webkit-box;
    -webkit-box-orient: vertical;
    -webkit-line-clamp: 5;
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
.dragdown {
    position: absolute;
    right: 0;
    bottom: 0;
}
.fromClubname {
    color: orange;
    margin-right: 1rem;
    font-size: 0.8rem;
}
.block {
  margin-bottom: 2rem;
}
.evaluateButton {
    padding: .1rem .4rem;
    border: 1px solid orange;
    background: #fff;
    border-radius: 0.5rem;
    margin-right: 1rem;
}
.exitClubButton {
    padding: .1rem .4rem;
    border: 1px solid orange;
    background: #fff;
    border-radius: 0.5rem;
    margin-top: 0.5rem;
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