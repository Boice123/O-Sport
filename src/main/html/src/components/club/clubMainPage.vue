<template>
  <div class="clubMainPageContainer">
      <div class="clubHead">
        <div class="clubHeadDetail">
            <img class="clubHeadImg" :src="clubinfo.clubimg"/>
            <div class="clubHeadName">{{clubinfo.clubname}}</div>
            <div class="clubInfoDetail">
                <div class="clubInfoDetailBox">
                    <span class="clubInfoDetailTitle">会员</span>
                    <span class="clubInfoDetailNum">{{clubinfo.clubpeople}}</span>
                </div>
                <div class="clubInfoDetailBox">
                    <span class="clubInfoDetailTitle">动态</span>
                    <span class="clubInfoDetailNum">{{clubDiary.length}}</span>
                </div>
                <div class="clubInfoDetailBox">
                    <span class="clubInfoDetailTitle">攻略</span>
                    <span class="clubInfoDetailNum">{{clubActivity.length}}</span>
                </div>
            </div>
        </div>
      </div>
      <div class="clubMainPageBody">
          <div class="addNewPart" v-if="clubChoose==='部落动态'" @click="addDiary(clubinfo.clubid)">发布动态</div>
          <div class="clubMainPageNav">
              <ul class="clubMainPageNavUl">
                  <li :class="{'clubMainPageNavLi': clubChoose!='部落动态','clubMainPageNavLiActive': clubChoose == '部落动态'}" @click="changeClubChoose('部落动态')">部落动态</li>
                  <li :class="{'clubMainPageNavLi': clubChoose!='部落攻略','clubMainPageNavLiActive': clubChoose == '部落攻略'}" @click="changeClubChoose('部落攻略')">部落攻略</li>     
                  <li :class="{'clubMainPageNavLi': clubChoose!='部落会员','clubMainPageNavLiActive': clubChoose == '部落会员'}" @click="changeClubChoose('部落会员')">部落会员</li>
              </ul>
          </div>
          <div class="messageDiv" v-if="clubChoose == '部落动态'">
              <div class="messageBox" v-for="(clubdiary, key) in clubDiary">
                  <img class="messageImg" :src="clubdiary.clubdiaryimg"/>
                  <div class="messageContent">
                      <div class="messageUp">
                          <div class="messageTitle">{{clubdiary.clubdiarytitle}}</div>
                          <div class="messageTitleRight">
                              <img class="messageUserImg" :src="clubdiary.user.userimg"/>
                              <span class="messageUserName">{{clubdiary.username}}</span>
                              <span class="messageTime">发表于{{clubdiary.clubdiarytime}}</span>
                          </div>
                      </div>
                      <!-- <div :class="{'messageDown': clubdiaryid != clubdiary.clubdiaryid, 'messageDownDrag': clubdiaryid == clubdiary.clubdiaryid}"> -->
                      <div class="messageDownDrag" >   
                        {{clubdiary.clubdiarycontent}}
                            <div class="evalBox">
                                <div v-for="evale in clubdiary.diaryfirsteval">
                                    <span class="evalUser">{{evale.user.username}}</span>评论：{{evale.content}}
                                    <button  v-if="evale.user.username != getCookie('user_username')" class="evaluateButton">回复</button>
                                    <div v-for="s in evale.secondevalList">
                                        <span class="evalUser">{{s.fromusername}}</span>评论<span class="evalUser">{{s.tousername}}</span>：{{s.content}}
                                        <button v-if="s.fromusername != getCookie('user_username')" class="evaluateButton" @click="secondeval(evale.evalid, s.fromusername)">回复</button>
                                    </div>
                                </div>
                            </div>
                      </div>
                      <span class="dragdown"><span class="fromClubname">来自{{clubdiary.club.clubname}}</span><button class="evaluateButton" @click="evaluate(diary.clubdiaryid)">评论</button></span>
                  </div>
              </div>
          </div> 
          <div class="messageDiv" v-if="clubChoose == '部落攻略'">
            <div class="messageBox" v-for="(clubactivity, key) in clubActivity">
                  <img class="messageImg" :src="clubactivity.clubactivityimg"/>
                  <div class="messageContent">
                      <div class="messageUp">
                          <div class="messageTitle">{{clubactivity.clubactivitytitle}}</div>
                          <div class="messageTitleRight">
                              <span class="messageTime">发表于{{clubactivity.clubactivitytime}}</span>
                          </div>
                      </div>
                      <div class="messageDownDrag" >   
                        {{clubactivity.clubactivitycontent}}<br>
                        <button class="likeButton" @click="like(clubactivity.clubactivityid)">点赞</button>
                      </div>
                  </div>
              </div>
          </div>
          <div class="messageDiv" v-if="clubChoose == '部落会员'">
            <div class="clubusergrid">
                <div class="clubuserbox" v-for="(user, key) in clubUser">
                    <img class="clubuserimg" :src="user.user.userimg"/>
                    <span class="clubusername" >{{user.user.username}}</span>
                </div>
            </div>
          </div>
      </div>
  </div>
</template>

<script>
import axios from 'axios'
import { API_getClubURl, API_getClubActivity, API_getClubDiaryURl, API_joinClubActivityURl, API_saveDiarySecondEvalURl, API_likeClubActivityURl, API_getClubUserURl } from '../../constants/index.js'
  export default {
    name: 'club',
    data() {
      return {
          clubChoose: '部落动态',
          clubCover: '',
          clubinfo: {},
          clubDiary: [{
              club: {
                  clubname: ''
              },
              user: {
                  userimg: '',
              },
              diaryfirsteval: {
                  content: '',
                  user: {
                      username:''
                  },
                  secondevalList: {
                    content: '',
                    fromusername: '', 
                    tousername: ''
                 }
              }
          }],
          clubActivity: [],
          clubUser: [{
             user: {
                username: '',
                userimg: ''
             }
          }],
          activityVisible: true,
          activityDetail: '',
          clubdiaryid: ''
      }
    },
    created () {
        var clubid =this.getCookie("clubid")
        this.getClub(clubid)
        this.getClubDiary(clubid)
        this.getClubActivity(clubid)
        this.getClubUser(clubid)
    },
    methods: {
      changeClubCover(text) {
          this.clubCover = text
      },
      changeClubChoose(text) {
          this.clubChoose = text
      },
      open(content) {
          this.$alert(content, '活动详情', {
          confirmButtonText: '确定'
        });
      },
      dragPassageDown(clubdiaryid) {
          if(this.clubdiaryid == '') {
            this.clubdiaryid = clubdiaryid
          }else {
            this.clubdiaryid = ''
          }
      },
      getClub(clubid) {
         var params = new URLSearchParams() 
         params.append('clubid',clubid)
         axios({
            method:'post',
            url:API_getClubURl,
            params
        })
        .then((response) => {
            console.log(response.data)
            if(response.data.code == 0) {
                this.clubinfo = response.data.data
            }else if(response.data.code == 1) {
                this.$message({
                    message: response.data.msg,
                    type: 'warning'
                }); 
            }else {
                this.$message.error('关注部落失败，请稍后重试');
            }        
        }).catch((err) => {
            console.log(err)
        })
      },
      // 获取部落日记
      getClubDiary(clubid) {
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
                console.log(response.data.msg) 
            }else {
                 console.log(response.data.msg) 
            }        
        }).catch((err) => {
            console.log(err)
        })
      },
      // 获取部落攻略
      getClubActivity(clubid) {
         var params = new URLSearchParams() 
         params.append('clubid',clubid)
         axios({
            method:'post',
            url:API_getClubActivity,
            params
        })
        .then((response) => {
            console.log(response.data)
            if(response.data.code == 0) {
                this.clubActivity = response.data.data
            }else if(response.data.code == 1) {
                console.log(response.data.msg) 
            }else {
                 console.log(response.data.msg) 
            }        
        }).catch((err) => {
            console.log(err)
        })
      },
      // 获取部落成员
      getClubUser(clubid) {
         var params = new URLSearchParams() 
         params.append('clubid',clubid)
         axios({
            method:'post',
            url:API_getClubUserURl,
            params
        })
        .then((response) => {
            console.log(response.data)
            if(response.data.code == 0) {
                this.clubUser = response.data.data
            }else if(response.data.code == 1) {
                console.log(response.data.msg) 
            }else {
                console.log(response.data.msg) 
            }        
        }).catch((err) => {
            console.log(err)
        })
      },
      addDiary(clubid) {
        for(let i = 0; i <  this.clubUser.length; i++) {
            if(this.getCookie("user_username") === this.clubUser[i].user.username) {
               this.$router.push({name:'addDiary', params:{clubid}})
               return
            }
        }
        this.$message.error("必须是该部落成员才可以发表动态，加入部落吧")
      },
      addActivity(clubid) {
          this.$router.push({name:'addActivity', params:{clubid}})
      },
      joinActivity(clubactivityid) {
         var params = new URLSearchParams() 
         params.append('clubactivityid',clubactivityid)
         axios({
            method:'post',
            url:API_joinClubActivityURl,
            params
        })
        .then((response) => {
            console.log(response.data)
            if(response.data.code == 0) {
                this.$message({
                    message: response.data.msg,
                    type: 'success'
                });
            }else if(response.data.code == 1) {
                this.$message({
                    message: response.data.msg,
                    type: 'warning'
                }); 
            }else {
                this.$message.error('参加主题讨论失败，请稍后重试');
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
      // 点赞
      like(clubactivityid) {
         var params = new URLSearchParams() 
         params.append('clubactivityid',clubactivityid)
         axios({
            method:'post',
            url:API_likeClubActivityURl,
            params
        })
        .then((response) => {
            console.log(response.data)
            if(response.data.code == 0) {
                this.$message({
                    message: response.data.msg,
                    type: 'success'
                });
            }else if(response.data.code == 1) {
                this.$message({
                    message: response.data.msg,
                    type: 'warning'
                }); 
            }else {
                this.$message.error('点赞失败，请稍后重试');
            }        
        }).catch((err) => {
            console.log(err)
        })
      },
    },
    components: {
        
    }
  }
</script>

<style>
.clubMainPageContainer {
    width: 100%;
    background: #fff;
    min-height: 50rem;
}
.clubHead {
    width: 100%;
    height: 300px;
    background: url('../../assets/images/bgc/climbmountain.jpg');
    background-size: cover;
    display: flex;
    justify-content: center;
    align-items: center;
}
.clubHeadImg {
    width: 6rem;
    height: 6rem;
    border-radius: 3rem;
}
.clubHeadName {
    margin-top: 1rem;
    margin-bottom: 1rem;
    color: #fdd000;
}
.clubInfoDetail {
    display: flex;
    flex-direction: row;
}
.clubInfoDetailBox {
    display: flex;
    flex: 1;
    flex-direction: column;
    color: #fdd000;
    align-items: center;
    justify-content: center;
}
.clubMainPageBody {
    position: relative;
    width: 80%;
    margin: 0 auto;
    margin-top: 20px;
    min-height: 40rem;
    padding-bottom: 5rem;
    border: 1px solid #fdd000;
    margin-bottom: 20px;
}
.clubMainPageNav {
    width: 100%;
    height: 70px;
    background: rgb(0,0,0,.8); 
}
.clubMainPageNavUl {
    width: 75%;
    height: 70px;
    display: flex;
    flex-direction:row; 
    align-items: center;
}
.clubMainPageNavLi {
    flex: 1;
    color: #000;
    font-size: 15px;
    line-height: 70px;
    height: 70px;
}
.clubMainPageNavLiActive {
    flex: 1;
    color: #000;
    font-size: 15px;
    line-height: 70px;
    height: 70px;
    background: #fdd000;  
}
.photoDiv {
    width: 98%;
    margin: 0 auto;
    display: grid;
    flex-direction: column;
    grid-template-columns: 1fr 1fr 1fr;
    grid-template-rows:1fr 1fr;
    grid-gap: 1rem;
    margin-top: 1rem;
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
    /* height: 12rem; */
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
.activityDiv {
    width: 98%;
    margin: 0 auto;
}
.activityBox {
    width: 100%;
    height: 15rem;
    display: flex;
    flex-direction: row;
    position: relative;
    overflow: hidden;
    margin-top: 1rem;
}
.activityImg {
    position: absolute;
    width: 110%;
    height: 16rem;
    left: -10%;
    top: 0;
    animation: clubImghide .5s;
    -moz-animation: clubImghide .5s;	/* Firefox */
    -webkit-animation: clubImghide .5s;	/* Safari 和 Chrome */
    -o-animation: clubImghide .5s;	/* Opera */
}
.activityImgActive {
    position: absolute;
    width: 110%;
    height: 16rem;
    left: 0;
    top: 0;
    animation: clubImgshow .5s;
    -moz-animation: clubImgshow .5s;	/* Firefox */
    -webkit-animation: clubImgshow .5s;	/* Safari 和 Chrome */
    -o-animation: clubImgshow .5s;	/* Opera */
}
.activityname {
    color: #fff;
    text-align: left;
    padding-left: 1rem;
    position: absolute;
    top: 80%;
}
@-webkit-keyframes clubImgshow /* Safari 和 Chrome */
{
  from { left: -10% }
  to { left: 0 }
}
@-webkit-keyframes clubImghide /* Safari 和 Chrome */
{
  from { left: 0  }
  to { left: -10%  }
}
.addNewPart {
    background: #fdd000;
    color: #000;
    position: absolute;
    right: -10%;
    top: 15%;
    width: 4rem;
    height: 3rem;
    display: flex;
    align-items: center;
    justify-content: center;
}
.activitypeople {
    color: #fff;
    text-align: left;
    padding-left: 1rem;
    position: absolute;
    top: 90%;
}
.opendetail {
    display: flex;
    justify-content: center;
    align-items: center;
    position: absolute;
    right: 2%;
    top: 100%;
    width: 4rem;
    height: 2rem;
    background: #fdd000;
    color: #000;
    border-radius: 10px;
    animation: opendetailhide .5s;
    -moz-animation: opendetailhide .5s;	/* Firefox */
    -webkit-animation: opendetailhide .5s;	/* Safari 和 Chrome */
    -o-animation: opendetailhide .5s;	/* Opera */
}
.joinActivity {
    display: flex;
    justify-content: center;
    align-items: center;
    position: absolute;
    right: 10%;
    top: 100%;
    width: 4rem;
    height: 2rem;
    background: #fdd000;
    color: #000;
    border-radius: 10px;
    animation: opendetailhide .5s;
    -moz-animation: opendetailhide .5s;	/* Firefox */
    -webkit-animation: opendetailhide .5s;	/* Safari 和 Chrome */
    -o-animation: opendetailhide .5s;	/* Opera */
}
.opendetailActive {
    display: flex;
    justify-content: center;
    align-items: center;
    position: absolute;
    right: 2%;
    top: 85%;
    width: 4rem;
    height: 2rem;
    background: #fdd000;
    color: #000;
    border-radius: 10px;
    animation: opendetailshow .5s;
    -moz-animation: opendetailshow .5s;	/* Firefox */
    -webkit-animation: opendetailshow .5s;	/* Safari 和 Chrome */
    -o-animation: opendetailshow .5s;	/* Opera */
}
.joinActivityActive {
    display: flex;
    justify-content: center;
    align-items: center;
    position: absolute;
    right: 10%;
    top: 85%;
    width: 4rem;
    height: 2rem;
    background: #fdd000;
    color: #000;
    border-radius: 10px;
    animation: opendetailshow .5s;
    -moz-animation: opendetailshow .5s;	/* Firefox */
    -webkit-animation: opendetailshow .5s;	/* Safari 和 Chrome */
    -o-animation: opendetailshow .5s;	/* Opera */
}
@-webkit-keyframes opendetailshow /* Safari 和 Chrome */
{
  from { top: 100% }
  to { top: 85%}
}
@-webkit-keyframes opendetailhide /* Safari 和 Chrome */
{
  from { top: 85% }
  to { top: 100% }
}
.dragdown {
    position: absolute;
    right: 0;
    bottom: 0;
}
.clubText {  
    width: 100%;
    height: 17rem;
    position: absolute;
    left: 0;
    top: 0;
    animation: clubTexthide .5s;
    -moz-animation: clubTexthide .5s;	/* Firefox */
    -webkit-animation: clubTexthide .5s;	/* Safari 和 Chrome */
    -o-animation: clubTexthide .5s;	/* Opera */
}
.clubTextActive {  
    width: 100%;
    height: 17rem;
    position: absolute;
    left: 0;
    top: -10%;
    animation: clubTextshow .5s;
    -moz-animation: clubTextshow .5s;	/* Firefox */
    -webkit-animation: clubTextshow .5s;	/* Safari 和 Chrome */
    -o-animation: clubTextshow .5s;	/* Opera */
}
@-webkit-keyframes clubTextshow /* Safari 和 Chrome */
{
  from { top: 0 }
  to { top: -10%}
}
@-webkit-keyframes clubTexthide /* Safari 和 Chrome */
{
  from { top: -10% }
  to { top: 0 }
}
.fromClubname {
    color: #fdd000;
    margin-right: 1rem;
    font-size: 0.8rem;
}
.evaluateButton {
    padding: .1rem .4rem;
    border: 1px solid #fdd000;
    background: #fff;
    border-radius: 0.5rem;
    margin-right: 1rem;
}
.evalBox {
    margin-top: 1rem;
    padding-top: 1rem;
    border-top: 1px solid #fdd000;
    margin-bottom: 3rem;
    font-size: 0.8rem;
}
.activityBox {
    margin-top: 1rem;
    padding-top: 1rem;
    border-top: 1px solid #fdd000;
    margin-bottom: 3rem;
    font-size: 0.8rem;
}
.evalUser {
    font-size: 0.8rem;
    color: #fdd000;
}
.likeButton {
    padding: .1rem .4rem;
    border: 1px solid #fdd000;
    background: #fff;
    border-radius: 0.5rem;
    margin-right: 1rem;
    position: absolute;
    right: 0;
    bottom: -1rem;
}
.clubuserbox {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
}
.clubusergrid {
    flex-direction: column;
    display: grid;
    grid-template-columns: 1fr 1fr 1fr 1fr;
    grid-column-gap: 20px;
    grid-row-gap: 20px;
}
.clubuserimg {
    width: 6rem;
    height: 6rem;
    border-radius: 3rem;
}
.clubusername {
    color: #000;
    font-size: 0.8rem;
    margin-top: 1rem;
}

</style>