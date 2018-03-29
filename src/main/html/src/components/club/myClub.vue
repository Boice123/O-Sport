<template>
  <div class="clubContainer">
      <ClubHead/>
      <div class="myClub">
          <div class="myClubLeft">
              <div v-if="this.getCookie('clubid') != null && this.getCookie('clubid') != '' ">
              <div class="myClubLeftTitle">我管理的部落</div>
              <div class="myClubLeftMa" @click="gotoClubManage(ownClub.clubid)">部落管理</div>
              <div class="clubList">
                <div 
                    class="myClubBox" 
                    @mouseover="changeOwnClubCover(true)" 
                    @mouseout="changeOwnClubCover(false)"
                    @click="gotoClubPage(ownClub.clubid)"
                >
                    <img 
                        :class="{'myclubImg':ownclubcover==false,'myclubImgActive': ownclubcover==true}" 
                        src="../../assets/images/bgc/manycamp.jpg"
                    />
                    <h2 class="myclubname">{{ownClub.clubname}}</h2>
                </div>
              </div>
              </div>
              <div class="myClubLeftTitle">我参与的部落</div>
              <div class="clubList">
                <div 
                    class="myClubBox" 
                    @mouseover="changeClubUserItemCover(club.clubid)"
                    @mouseout="changeClubUserItemCover('')"
                    v-for="(club, key) in clubuseritem"
                    @click="gotoClubPage(club.clubid)"
                >
                    <img 
                        :class="{'myclubImg':clubusercover!=club.clubid,'myclubImgActive': clubusercover==club.clubid}" 
                        src="../../assets/images/bgc/manycamp.jpg"
                    />
                    <h2 class="myclubname">{{club.clubname}}</h2>
                </div>
              </div>
          </div>
          <div class="myClubRight">
              <div class="myclubtab">
                  <div class="myclubtabtab" @click="changeChoose('管理部落动态')">管理部落动态</div>
                  <div class="myclubtabtab" @click="changeChoose('参与部落动态')">参与部落动态</div>
              </div>
              <div class="myClubmessageDiv" v-if="choose == '管理部落动态'">
                <div class="myClubmessageBox" 
                    v-for="(clibDiary, key) in ownClubDiary"
                >
                    <img class="myClubmessageImg" src="../../assets/images/bgc/climbmountain.jpg"/>
                    <div class="myClubmessageContent">
                        <div class="myClubmessageUp">
                            <div class="myClubmessageTitle">{{clibDiary.clubdiarytitle}}</div>
                            <div class="myClubmessageTitleRight">
                                <img class="myClubmessageUserImg" src="../../assets/images/bgc/climbmountain.jpg"/>
                                <span class="myClubmessageUserName">{{clibDiary.username}}</span>
                                <span class="myClubmessageTime">发表于{{clibDiary.clubdiarytime}}</span>
                            </div>
                        </div>
                        <div class="myClubsmessageDown">
                            {{clibDiary.clubdiarycontent}}
                        </div>
                    </div>
                </div>
              </div>

              <div class="myClubmessageDiv" v-if="choose == '参与部落动态'">
                <div class="myClubmessageBox" 
                    v-for="(diary, key) in joinClubDiary"
                >
                    <img class="myClubmessageImg" src="../../assets/images/bgc/climbmountain.jpg"/>
                    <div class="myClubmessageContent">
                        <div class="myClubmessageUp">
                            <div class="myClubmessageTitle">{{diary.clubdiarytitle}}</div>
                            <div class="myClubmessageTitleRight">
                                <img class="myClubmessageUserImg" src="../../assets/images/bgc/climbmountain.jpg"/>
                                <span class="myClubmessageUserName">{{diary.username}}</span>
                                <span class="myClubmessageTime">发表于{{diary.clubdiarytime}}</span>
                            </div>
                        </div>
                        <div class="myClubsmessageDown">
                            {{diary.clubdiarycontent}}
                        </div>
                    </div>
                </div>
              </div>

          </div>
      </div>
  </div>
</template>

<script>
import axios from 'axios'
import ClubHead from './clubHead.vue'
import { API_getClubInfoURl, API_getClubUserItemURl, API_getOwnClubDiaryURl, API_getJoinClubDiaryURl } from '../../constants/index.js'
  export default {
    name: 'myclub',
    data() {
      return {
        choose: '管理部落动态',
        ownclubcover: '',
        ownClub: {},
        clubusercover: '',
        clubuseritem: [],
        ownClubDiary: [],
        joinClubDiary: []
      }
    },
    created () {
        var userid = this.getCookie('user_userid')
        console.log("clubid"+ this.getCookie("clubid"))
        this.getClub(userid)
        this.getClubUserItem(userid)
        this.getClubDiary(userid)
        this.getjoinClubDiary(userid)
    },
    methods: {
      changeOwnClubCover(text) {
          this.ownclubcover = text
      },
      changeClubUserItemCover(text) {
          this.clubusercover = text
      },
      changeChoose(text) {
          this.choose = text
      },
      gotoClubPage(clubid) {
          console.log(clubid)
          this.$router.push({name:'clubMainPage',params:{clubid}})
      },
      gotoClubManage(clubid) {
          this.$router.push({name:'clubManage',params:{clubid}})
      },
      //获取我管理Club的信息
      getClub(userid) {
        var params = new URLSearchParams();
        params.append('clubowner',userid)
        axios({
            method:'post',
            url:API_getClubInfoURl,
            params
        })
        .then((response) => {
            console.log(response.data)
            if(response.data.code == 0) {
                this.ownClub = response.data.data
            }else if(response.data.code == 1) {
                this.$message({
                    message: response.data.msg,
                    type: 'warning'
                }); 
            }else {
                this.$message.error('获取部落信息失败，请稍后重试');
            }        
        }).catch((err) => {
            console.log(err)
        })
      },
      //获取我管理Club的动态信息
      getClubDiary(userid) {
        var params = new URLSearchParams();
        params.append('clubowner',userid)
        axios({
            method:'post',
            url:API_getOwnClubDiaryURl,
            params
        })
        .then((response) => {
            console.log(response.data)
            if(response.data.code == 0) {
                this.ownClubDiary = response.data.data
            }else if(response.data.code == 1) {
                this.$message({
                    message: response.data.msg,
                    type: 'warning'
                }); 
            }else {
                this.$message.error('获取管理的部落动态失败，请稍后重试');
            }        
        }).catch((err) => {
            console.log(err)
        })
      },
      //获取我参与Club的信息
      getClubUserItem(userid) {
        var params = new URLSearchParams();
        params.append('userid',userid)
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
      //获取我参与Club的动态信息
      getjoinClubDiary(userid) {
        var params = new URLSearchParams();
        params.append('userid',userid)
        axios({
            method:'post',
            url:API_getJoinClubDiaryURl,
            params
        })
        .then((response) => {
            console.log(response.data)
            if(response.data.code == 0) {
                this.joinClubDiary = response.data.data
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
    },
    components: {
        ClubHead
    }
  }
</script>

<style>
.clubContainer {
    background: url('../../assets/images/bgc/campbgc.jpg')
}
.myClub {
    width: 90%;
    margin: 2rem auto;
    display: flex;
    flex-direction: row;
}
.myClubLeft {
    flex: 1;
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
    color: #fff;
    text-align: left;
    margin-bottom: 1rem;
}
.myClubLeftMa {
    color: #fff;
    position: absolute;
    right: 10%;
    border: 1px solid #fff;
    padding: 5px;
}
.clubList {
    display: flex;
    margin-bottom: 4rem;
}
.myClubBox {
    width: 8rem;
    height: 8rem;
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
    font-size: 1rem;
    color: #fff;
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
    color: #fff;
}
.myClubmessageDiv {
    width: 50rem;
}
.myClubmessageBox {
    padding: 1rem;
    margin-top: 1rem;
    background: #fff;
    display: flex;
    flex-direction: row;
    height: 12rem;
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
    flex-direction: column;
}
.myClubmessageUp {
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
</style>