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
                    <span class="clubInfoDetailTitle">活动</span>
                    <span class="clubInfoDetailNum">{{clubActivity.length}}</span>
                </div>
            </div>
        </div>
      </div>
      <div class="clubMainPageBody">
          <div class="addNewPart" v-if="clubChoose==='部落动态'" @click="addDiary(clubinfo.clubid)">发布动态</div>
          <!-- <div class="addNewPart" v-if="clubChoose==='部落活动'" @click="addActivity(clubinfo.clubid)">发布活动</div> -->
          <!-- <div class="addNewPart" v-if="clubChoose==='摄影作品'">摄影作品</div> -->
          <div class="clubMainPageNav">
              <ul class="clubMainPageNavUl">
                  <li :class="{'clubMainPageNavLi': clubChoose!='部落活动','clubMainPageNavLiActive': clubChoose == '部落活动'}" @click="changeClubChoose('部落活动')">部落活动</li>     
                  <li :class="{'clubMainPageNavLi': clubChoose!='部落动态','clubMainPageNavLiActive': clubChoose == '部落动态'}" @click="changeClubChoose('部落动态')">部落动态</li>
                  <li :class="{'clubMainPageNavLi': clubChoose!='部落会员','clubMainPageNavLiActive': clubChoose == '部落会员'}" @click="changeClubChoose('部落会员')">部落会员</li>
                  <!-- <li :class="{'clubMainPageNavLi': clubChoose!='摄影作品','clubMainPageNavLiActive': clubChoose == '摄影作品'}" @click="changeClubChoose('摄影作品')">摄影作品</li> -->
              </ul>
          </div>

          <!-- <div class="photoDiv" v-if="clubChoose == '摄影作品'">
              <div class="clubBox" @mouseover="changeClubCover('自行车')" @mouseout="changeClubCover('')">
                  <img :class="{'clubImg':clubCover!='自行车','clubImgActive': clubCover=='自行车'}" src="../../assets/images/bgc/manycamp.jpg"/>
                  <div class="clubTextMask" v-if="clubCover == '自行车'"></div>
                  <div :class="{'clubText': clubCover!='自行车','clubTextActive': clubCover=='自行车'}">
                    <h2 class="clubname">摄影集</h2>
                    <p class="clubnum"><br>created by 喵喵</p>
                 </div>
              </div>
              <div class="clubBox" @mouseover="changeClubCover('自行车')" @mouseout="changeClubCover('')">
                  <img :class="{'clubImg':clubCover!='自行车','clubImgActive': clubCover=='自行车'}" src="../../assets/images/bgc/manycamp.jpg"/>
                  <div class="clubTextMask" v-if="clubCover == '自行车'"></div>
                  <div :class="{'clubText': clubCover!='自行车','clubTextActive': clubCover=='自行车'}">
                    <h2 class="clubname">自行车俱乐部</h2>
                    <p class="clubnum"><br>共93名会员</p>
                 </div>
              </div>
              <div class="clubBox" @mouseover="changeClubCover('自行车')" @mouseout="changeClubCover('')">
                  <img :class="{'clubImg':clubCover!='自行车','clubImgActive': clubCover=='自行车'}" src="../../assets/images/bgc/manycamp.jpg"/>
                  <div class="clubTextMask" v-if="clubCover == '自行车'"></div>
                  <div :class="{'clubText': clubCover!='自行车','clubTextActive': clubCover=='自行车'}">
                    <h2 class="clubname">自行车俱乐部</h2>
                    <p class="clubnum"><br>共93名会员</p>
                 </div>
              </div>
              <div class="clubBox" @mouseover="changeClubCover('自行车')" @mouseout="changeClubCover('')">
                  <img :class="{'clubImg':clubCover!='自行车','clubImgActive': clubCover=='自行车'}" src="../../assets/images/bgc/manycamp.jpg"/>
                  <div class="clubTextMask" v-if="clubCover == '自行车'"></div>
                  <div :class="{'clubText': clubCover!='自行车','clubTextActive': clubCover=='自行车'}">
                    <h2 class="clubname">自行车俱乐部</h2>
                    <p class="clubnum"><br>共93名会员</p>
                 </div>
              </div> -->
          <!-- </div> -->
          <div class="messageDiv" v-if="clubChoose == '部落动态'">
              <div class="messageBox" v-for="(clubdiary, key) in clubDiary">
                  <img class="messageImg" :src="clubdiary.clubdiaryimg"/>
                  <div class="messageContent">
                      <div class="messageUp">
                          <div class="messageTitle">{{clubdiary.clubdiarytitle}}</div>
                          <div class="messageTitleRight">
                              <img class="messageUserImg"/>
                              <span class="messageUserName">{{clubdiary.username}}</span>
                              <span class="messageTime">发表于{{clubdiary.clubdiarytime}}</span>
                          </div>
                      </div>
                      <div :class="{'messageDown': clubdiaryid == clubdiary.clubdiaryid, 'messageDownDrag': clubdiaryid != clubdiary.clubdiaryid}">
                        {{clubdiary.clubdiarycontent}}
                      </div>
                      <span class="dragdown" @click="dragPassageDown(clubdiary.clubdiaryid)">点击下拉</span>
                  </div>
              </div>
              <!-- <div class="messageBox">
                  <img class="messageImg" src="../../assets/images/bgc/climbmountain.jpg"/>
                  <div class="messageContent">
                      <div class="messageUp">
                          <div class="messageTitle">哈哈哈是标题</div>
                          <div class="messageTitleRight">
                              <img class="messageUserImg" src="../../assets/images/bgc/climbmountain.jpg"/>
                              <span class="messageUserName">用户名字</span>
                              <span class="messageTime">发表于19:08</span>
                          </div>
                      </div>
                      <div :class="{'messageDown': passageDragDown == false, 'messageDownDrag': passageDragDown == true}">
                        出游小日记出游小日记出游小日记出游小日记出游小日记出
                          游小日记出游小日记出游小日记出游小日记vvvvv出游小日记出游小日记
                          出游小日记
                          出游小日记出游小日记出游小日记出游小日记出游小日记出
                          游小日记出游小日记出游小日记出游小日记vvvvv出游小日记出游小日记
                          出游小日记
                          出游小日记出游小日记出游小日记出游小日记出游小日记出
                          游小日记出游小日记出游小日记出游小日记vvvvv出游小日记出游小日记
                          出游小日记
                          出游小日记出游小日记出游小日记出游小日记出游小日记出
                          游小日记出游小日记出游小日记出游小日记vvvvv出游小日记出游小日记
                          出游小日记
                          出游小日记出游小日记出游小日记出游小日记出游小日记出
                          游小日记出游小日记出游小日记出游小日记vvvvv出游小日记出游小日记
                          出游小日记出游小日记出游小日记出游小日记出游小日记出游小日记出
                          游小日记出游小日记出游小日记出游小日记vvvvv出游小日记出游小日记
                          出游小日记
                      </div>
                      <span class="dragdown" @click="dragPassageDown">点击下拉</span>
                  </div>
              </div>
              <div class="messageBox">
                  <img class="messageImg" src="../../assets/images/bgc/climbmountain.jpg"/>
                  <div class="messageContent">
                      <div class="messageUp">
                          <div class="messageTitle">哈哈哈是标题</div>
                          <div class="messageTitleRight">
                              <img class="messageUserImg" src="../../assets/images/bgc/climbmountain.jpg"/>
                              <span class="messageUserName">用户名字</span>
                              <span class="messageTime">发表于19:08</span>
                          </div>
                      </div>
                      <div :class="{'messageDown': passageDragDown == false, 'messageDownDrag': passageDragDown == true}">
                        出游小日记出游小日记出游小日记出游小日记出游小日记出
                          游小日记出游小日记出游小日记出游小日记vvvvv出游小日记出游小日记
                          出游小日记
                          出游小日记出游小日记出游小日记出游小日记出游小日记出
                          游小日记出游小日记出游小日记出游小日记vvvvv出游小日记出游小日记
                          出游小日记
                          出游小日记出游小日记出游小日记出游小日记出游小日记出
                          游小日记出游小日记出游小日记出游小日记vvvvv出游小日记出游小日记
                          出游小日记
                          出游小日记出游小日记出游小日记出游小日记出游小日记出
                          游小日记出游小日记出游小日记出游小日记vvvvv出游小日记出游小日记
                          出游小日记
                          出游小日记出游小日记出游小日记出游小日记出游小日记出
                          游小日记出游小日记出游小日记出游小日记vvvvv出游小日记出游小日记
                          出游小日记出游小日记出游小日记出游小日记出游小日记出游小日记出
                          游小日记出游小日记出游小日记出游小日记vvvvv出游小日记出游小日记
                          出游小日记
                      </div>
                      <span class="dragdown" @click="dragPassageDown">点击下拉</span>
                  </div>
              </div>-->
          </div> 
          <div class="activityDiv" v-if="clubChoose == '部落活动'">
            <div 
                class="activityBox"
                @mouseover="changeClubCover(activity.clubactivityid)" 
                @mouseout="changeClubCover('')"
                v-for="(activity,key) in clubActivity"
            >
                  <img :class="{'activityImg':clubCover!=activity.clubactivityid,'activityImgActive': clubCover==activity.clubactivityid}" :src="activity.clubactivityimg"/>
                  <div class="clubTextMask" v-if="clubCover == activity.clubactivityid"></div>
                  <div :class="{'clubText': clubCover!=activity.clubactivityid,'clubTextActive': clubCover==activity.clubactivityid}">
                    <h2 class="activityname">{{activity.clubactivitytitle}}</h2>
                    <p class="activitypeople"><br>已有{{activity.clubactivitypeople}}人参加</p>
                    <span 
                        :class="{'joinActivity': clubCover!=activity.clubactivityid,'joinActivityActive': clubCover==activity.clubactivityid }"
                        @click="joinActivity(activity.clubactivityid)"
                    >参加
                    </span>
                    <span 
                        :class="{'opendetail': clubCover!=activity.clubactivityid,'opendetailActive': clubCover==activity.clubactivityid }"
                        @click="open(activity.clubactivitycontent)"
                    >查看详情
                    </span>
                 </div>
            </div>
          </div>
      </div>
  </div>
</template>

<script>
import axios from 'axios'
import { API_getClubURl, API_getClubActivityURl, API_getClubDiaryURl, API_joinClubActivityURl } from '../../constants/index.js'
  export default {
    name: 'club',
    data() {
      return {
          clubChoose: '部落活动',
          clubCover: '',
          clubinfo: {},
          clubDiary: [],
          clubActivity: [],
          activityVisible: true,
          activityDetail: '',
          clubdiaryid: ''
      }
    },
    created () {
        var clubid = this.$route.params.clubid
        this.getClub(clubid)
        this.getClubDiary(clubid)
        this.getClubActivity(clubid)
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
          this.clubdiaryid = clubdiaryid
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
      getClubActivity(clubid) {
         var params = new URLSearchParams() 
         params.append('clubid',clubid)
         axios({
            method:'post',
            url:API_getClubActivityURl,
            params
        })
        .then((response) => {
            console.log(response.data)
            if(response.data.code == 0) {
                this.clubActivity = response.data.data
            }else if(response.data.code == 1) {
                this.$message({
                    message: response.data.msg,
                    type: 'warning'
                }); 
            }else {
                this.$message.error('获取部落活动失败，请稍后重试');
            }        
        }).catch((err) => {
            console.log(err)
        })
      },
      addDiary(clubid) {
         this.$router.push({name:'addDiary', params:{clubid}})
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
                this.$message.error('参加部落活动失败，请稍后重试');
            }        
        }).catch((err) => {
            console.log(err)
        })
      }
    },
    components: {
        
    }
  }
</script>

<style>
.clubMainPageContainer {
    width: 100%;
    background: url('../../assets/images/bgc/campbgc.jpg');
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
    color: yellow;
}
.clubInfoDetail {
    display: flex;
    flex-direction: row;
}
.clubInfoDetailBox {
    display: flex;
    flex: 1;
    flex-direction: column;
    color: yellow;
    align-items: center;
    justify-content: center;
}
.clubMainPageBody {
    position: relative;
    width: 80%;
    margin: 0 auto;
    margin-top: 20px;
    border-radius: 10px;
    padding-bottom: 5rem;
    border: 1px solid #000;
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
    color: yellow;
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
    background: yellow;  
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
    background: yellow;
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
    background: yellow;
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
    background: yellow;
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
    background: yellow;
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
    background: yellow;
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
</style>