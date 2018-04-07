<template>
  <div class="indexContainer">
      <!-- 搜索栏 -->
      <searchBar/>
      <!-- topNav导航栏 -->
      <topNav/>
      <!-- banner -->
      <el-carousel trigger="click" height="400px">
        <el-carousel-item :key="1">
          <a href="#"><img class="bannerImg" src="../assets/images/banner/banner_01.jpg"/></a>
          <a href="#"><img class="bannerImg" src="../assets/images/banner/banner_02.jpg"/></a>
          <a href="#"><img class="bannerImg" src="../assets/images/banner/banner_03.jpg"/></a>
        </el-carousel-item>
        <el-carousel-item :key="2">
          <a href="#"><img class="bannerImg" src="../assets/images/banner/banner_02.jpg"/></a>
        </el-carousel-item>
        <el-carousel-item :key="3">
          <a href="#"><img class="bannerImg" src="../assets/images/banner/banner_03.jpg"/></a>
        </el-carousel-item>
      </el-carousel>
      <!-- 报名出团活动 -->
      <div class="tripBox">
        <div class="tripTitle">
          <div :class="{'tripTitleSectionActive':tripTagChoose == 1,'tripTitleSection':tripTagChoose != 1}" @click="changetripTagChoose(1)">最新出团</div>
          <div :class="{'tripTitleSectionActive':tripTagChoose == 2,'tripTitleSection':tripTagChoose != 2}" @click="changetripTagChoose(2)">最热出团</div>
        </div>
        <div class="more" @click="gotoSerachList">>>更多</div>
        <div class="tripContent" >
          <div :class="{'tripContentImgActive':tripChoose == key,'tripContentImg':tripChoose != key}" 
                @mouseover="changetripChoose(key)"
                @click="gotoTripPage(trip.tripid)"
                v-for="(trip,key) in tripList"
          >
            <div class="tripname">{{trip.tripname}}</div>
            <img src="../assets/images/banner/banner_01.jpg"/>
          </div>
        </div>
      </div>
      <!-- 自营和入驻商家出团活动 -->
      <div class="tripIntroduceBox">
        <div class="tripTitle">
          <div :class="{'tripTitleSectionActive':tripTagTwoChoose == 1,'tripTitleSection':tripTagTwoChoose != 1}" @click="changetripTagTwoChoose(1)">本站自营出团</div>
          <div :class="{'tripTitleSectionActive':tripTagTwoChoose == 2,'tripTitleSection':tripTagTwoChoose != 2}" @click="changetripTagTwoChoose(2)">入驻商家出团</div>
        </div>
        <div class="tripIntroduceContent">
        <div class="tripInLeft">
          <div class="tripInLeftBottom">
            <div class="tripInLeftBottomSection"
              @mouseover="changeInTripCover(index)" 
               @mouseout="changeInTripCover(0)"
               @click="gotoTripPage(trip.tripid)"
               v-for="(trip, index) in tripTwoList"
               v-if="index >= 1">
              <img class="introduceImg" src="../assets/images/banner/banner_01.jpg"/>
              <div class="clubTextMask" v-if="clubChoose == index"></div>
              <div :class="{'tripInText': clubChoose!= index,'tripInTextActive': clubChoose==index}">
                <h2 class="tripInname">{{trip.tripname}}</h2>
                <p class="tripInnum"><br>已有{{trip.triptrading}}人参加</p>
                <span 
                    :class="{'tripInJoin': clubChoose!=index,'tripInJoinActive': clubChoose==index }"
                >+ 报名
                </span>
              </div>
            </div>
          </div>
        </div>
        <div class="tripInRight"
          @mouseover="changeInTripCover(0)" 
          @mouseout="changeInTripCover(8)"
          @click="gotoTripPage(trip.tripid)"
          v-for="(trip, index) in tripTwoList"
          v-if="index == 0">
          <img class="introduceImg" src="../assets/images/banner/banner_01.jpg"/>
          <div class="clubTextMask" v-if="clubChoose == 0"></div>
              <div :class="{'tripInText': clubChoose!= 0,'tripInTextActive': clubChoose==0}">
                <h2 class="tripInname">{{trip.tripname}}</h2>
                <p class="tripInnum"><br>已有{{trip.triptrading}}人参加</p>
                <span 
                    :class="{'tripInJoin': clubChoose!=0,'tripInJoinActive': clubChoose==0}"
                >+ 报名
                </span>
              </div>
          </div>
          </div>
      </div>
      <!-- 部落列表 -->
      <div class="trainBox">
        <div class="trainTitle">
          <div :class="{'trainTitleSectionActive':clubTagChoose == 1,'trainTitleSection':clubTagChoose != 1}" @click="changeclubTagChoose(1)">最新部落</div>
          <div :class="{'trainTitleSectionActive':clubTagChoose == 2,'trainTitleSection':clubTagChoose != 2}" @click="changeclubTagChoose(2)">最热部落</div>
        </div>
        <div class="more" @click="gotoClub">>>更多</div>
        <div class="trainContent">
          <div class="trainContentLeft" v-for="(club, key) in clubList"
               v-if="key == 0"
               @click="gotoClubMainPage(club.clubid)"
          >
            <div class="trainname">{{club.clubname}}</div>
            <img src="../assets/images/banner/banner_01.jpg"/>
          </div>
          <div class="trainContentRight">
            <div class="trainContentRightBox"
                 v-for="(club,key) in clubList"
                 v-if="key >= 1"
                 @click="gotoClubMainPage(club.clubid)"
            >
              <div :class="{'trainContentImgActive':clubChoose == key,'trainContentImg':clubChoose != key}" @mouseover="changeclubChoose(key)">
                <div class="trainname">{{club.clubname}}</div>
                <img src="../assets/images/banner/banner_01.jpg"/>
              </div>
            </div>
          </div>        
        </div>
      </div>
      <!-- 部落活动 -->
      <div class="activityDivWrap">
        <div class="tripActivityTitle">部落活动</div>
      <div class="activityDiv">
        <div 
                class="activityBox"
                @mouseover="changeActivityCover(activity.clubactivityid)" 
                @mouseout="changeActivityCover('')"
                v-for="(activity, index) in clubactivity"
            >
                  <img :class="{'activityImg': activityCover!=activity.clubactivityid,'activityImgActive': activityCover==activity.clubactivityid}" src="../assets/images/bgc/manycamp.jpg"/>
                  <div class="clubTextMask" v-if="activityCover == activity.clubactivityid"></div>
                  <div :class="{'clubText': activityCover!=activity.clubactivityid,'clubTextActive': activityCover==activity.clubactivityid}">
                    <h2 class="activityname">{{activity.clubactivitytitle}}</h2>
                    <span 
                        :class="{'joinActivity': activityCover!=activity.clubactivityid,'joinActivityActive': activityCover==activity.clubactivityid }"
                        @click="joinActivity(activity.clubactivityid)"
                    >参加
                    </span>
                    <span 
                        :class="{'opendetail': activityCover!=activity.clubactivityid,'opendetailActive': activityCover==activity.clubactivityid }"
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
import searchBar from './common/searchBar'
import topNav from './common/topNav'
import axios from 'axios'
import { API_getTripList, API_getClubList, API_getClubActivityPaginationURl, API_getWebTripPagination, API_joinClubActivityURl, API_getShopTripPagination } from '../constants/index.js'
  export default {
    name: 'index',
    data() {
      return {
        tripTagChoose: 1,
        tripChoose: 0,
        tripTagTwoChoose: 1,
        clubTagChoose: 1,
        clubChoose: 1,       
        activityCover: '',
        tripList: [],
        tripTwoList: [],
        clubList: [],
        clubactivity: [],
        
      }
    },
    created () {
        this.getTripListByDate()
        this.getTripOnWeb()
        this.getClubListByDate()
        this.getHotClubActivity()
    },
    methods: {
      open(content) {
          this.$alert(content, '活动详情', {
          confirmButtonText: '确定'
        });
      },
      gotoClubMainPage(clubid) {
        this.$router.push({name: 'clubMainPage', params: {clubid}})
      },
      gotoClub () {
        this.$router.push('/club')
      },
      gotoSerachList() {
        this.$router.push({name: 'searchList', params: { tripprovice: '', tripcity: '', searchKey: '' }})
      },
      joinActivity(clubactivityid) {
        if(this.getCookie('user_userid') === '' || this.getCookie('user_userid') === null){
          this.$router.push('/signin')
          return
        }
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
      },
      getTripListByDate() {
        //按日期排序获取出团活动信息
        var params = new URLSearchParams();
        params.append('start', 0)
        params.append('size',3)
        params.append('order','trippublishtime')
        axios({
            method:'post',
            url:API_getTripList,
            params
        })
        .then((response) => {
            console.log(response.data)
            if(response.data.code == 0) {
                this.tripList = response.data.data
            }else if(response.data.code == 1) {
                this.$message({
                    message: response.data.msg,
                    type: 'warning'
                }); 
            }else {
                this.$message.error('获取按日期排序获取出团活动信息失败，请稍后重试');
            }        
        }).catch((err) => {
            console.log(err)
        })
      },
      getTripListByTrading() {
        //按成交量热度获取出团活动信息
        var params = new URLSearchParams();
        params.append('start', 0)
        params.append('size',3)
        params.append('order','triptrading')
        axios({
            method:'post',
            url:API_getTripList,
            params
        })
        .then((response) => {
            console.log(response.data)
            if(response.data.code == 0) {
                this.tripWebList = response.data.data
            }else if(response.data.code == 1) {
                this.$message({
                    message: response.data.msg,
                    type: 'warning'
                }); 
            }else {
                this.$message.error('获取按热度排序获取出团活动信息失败，请稍后重试');
            }        
        }).catch((err) => {
            console.log(err)
        })
      },
      getTripOnWeb() {
        //按热度排序获取本站自营的Trip
        var params = new URLSearchParams();
        params.append('start',0)
        params.append('size',7)
        params.append('shopid', '000000')
        params.append('order','triptrading')
        axios({
            method:'post',
            url:API_getWebTripPagination,
            params
        })
        .then((response) => {
            console.log(response.data)
            if(response.data.code == 0) {
                this.tripTwoList = response.data.data
            }else if(response.data.code == 1) {
                this.$message({
                    message: response.data.msg,
                    type: 'warning'
                }); 
            }else {
                this.$message.error('获取按日期排序部落列表失败，请稍后重试');
            }        
        }).catch((err) => {
            console.log(err)
        })
      },
      getTripOnShop() {
        //按热度排序获取入驻商店的Trip
        var params = new URLSearchParams();
        params.append('start',0)
        params.append('size',7)
        params.append('shopid', '000000')
        params.append('order','triptrading')
        axios({
            method:'post',
            url:API_getShopTripPagination,
            params
        })
        .then((response) => {
            console.log(response.data)
            if(response.data.code == 0) {
                this.tripTwoList = response.data.data
            }else if(response.data.code == 1) {
                this.$message({
                    message: response.data.msg,
                    type: 'warning'
                }); 
            }else {
                this.$message.error('获取按日期排序部落列表失败，请稍后重试');
            }        
        }).catch((err) => {
            console.log(err)
        })
      },
      getClubListByDate() {
        //按日期排序获取部落列表
        var params = new URLSearchParams();
        params.append('start',0)
        params.append('size',7)
        params.append('order','clubpublishtime')
        axios({
            method:'post',
            url:API_getClubList,
            params
        })
        .then((response) => {
            console.log(response.data)
            if(response.data.code == 0) {
                this.clubList = response.data.data
            }else if(response.data.code == 1) {
                this.$message({
                    message: response.data.msg,
                    type: 'warning'
                }); 
            }else {
                this.$message.error('获取按日期排序部落列表失败，请稍后重试');
            }        
        }).catch((err) => {
            console.log(err)
        })
      },
      getClubListByPeople() {
        //按热度排序获取部落列表
        var params = new URLSearchParams();
        params.append('start',0)
        params.append('size',7)
        params.append('order','clubpeople')
        axios({
            method:'post',
            url:API_getClubList,
            params
        })
        .then((response) => {
            console.log(response.data)
            if(response.data.code == 0) {
                this.clubList = response.data.data
            }else if(response.data.code == 1) {
                this.$message({
                    message: response.data.msg,
                    type: 'warning'
                }); 
            }else {
                this.$message.error('获取按热度排序部落列表失败，请稍后重试');
            }        
        }).catch((err) => {
            console.log(err)
        })
      },
      getHotClubActivity() {
        //按热度获取部落活动信息
        var params = new URLSearchParams();
        params.append('start', 0)
        params.append('size',2)
        params.append('order','clubactivitypeople')
        params.append('clubid','')
        axios({
            method:'post',
            url:API_getClubActivityPaginationURl,
            params
        })
        .then((response) => {
            console.log(response.data)
            if(response.data.code == 0) {
                this.clubactivity = response.data.data
            }else if(response.data.code == 1) {
                this.$message({
                    message: response.data.msg,
                    type: 'warning'
                }); 
            }else {
                this.$message.error('获取按热度排序获取部落活动信息失败，请稍后重试');
            }        
        }).catch((err) => {
            console.log(err)
        })
      },
      changetripTagChoose(choose) {
        this.tripTagChoose = choose
        if(choose === 1) {
          this.getTripListByDate()
          return
        }
        if(choose === 2) {
          this.getTripListByTrading()
          return
        }
      },
      changetripTagTwoChoose(choose) {
        this.tripTagTwoChoose = choose
        if(choose === 1) {
          this.getTripOnWeb()
          return
        }
        if(choose === 2) {
          this.getTripOnShop()
          return
        }
      },
      changetripChoose(choose) {
        this.tripChoose = choose
      },
      changeclubTagChoose(choose) {
        this.clubTagChoose = choose
        if(choose === 1) {
          this.getClubListByDate()
          return
        }
        if(choose === 2) {
          this.getClubListByPeople()
          return
        }
      },
      changeclubChoose(choose) {
        this.clubChoose = choose
      },
      changeActivityCover(activityid) {
        this.activityCover = activityid
      },
      changeInTripCover(cover) {
        this.clubChoose = cover
      },
      gotoTripPage(tripid) {
        this.$router.push({name:'trip', params:{tripid}})
      }
    },
    components: {
        searchBar,
        topNav
    }
  }
</script>

<style>
.indexContainer {
    width: 100%;
}
.el-carousel-item {
  width: 100%;
}
.bannerImg {
  width: 100%;
  height: 400px;
}
.tripBox {
  background: #333;
  padding-top: 1rem;
  padding-bottom: 1rem;
  padding-left: 2rem;
  padding-right: 2rem;
  margin: 0 auto;
  position: relative;
}
.more {
  color: #fdd000;
  font-size: 18px;
  position: absolute;
  top: 0;
  right: 0;
  padding-right: 2rem;
  padding-top: 1.5rem;
}
.tripTitle {
  width: 30%;
  display: flex;
}
.tripTitleSection {
  flex: 1;
  color: #fdd000;
  font-Weight: 600;
  font-size: 1rem;
  padding-top: 0.5rem;
  padding-bottom: 0.5rem;
}
.tripTitleSectionActive {
  flex: 1;
  color: #000;
  font-Weight: 600;
  font-size: 1rem;
  background: #fdd000;
  padding-top: 0.5rem;
  padding-bottom: 0.5rem;
  border-radius: 5px 5px 0 0;
}
.tripActivityTitle {
  width: 10rem;
  color: #fdd000;
  font-Weight: 600;
  font-size: 1.5rem;
  /* background: #; */
  padding-top: 0.5rem;
  padding-bottom: 0.5rem;
  margin-left: 2rem;
  border-radius: 5px 5px 0 0;
}
@-webkit-keyframes tripshow /* Safari 和 Chrome */
{
  from {  flex: 1;opacity: .5;}
  to {  flex: 2;opacity: 1;}
}
@-webkit-keyframes triphide /* Safari 和 Chrome */
{
  from {  flex: 2;opacity: 1;}
  to {  flex: 1;opacity: .5;}
}
.tripContent {
  width: 100%;
  display: flex;
  margin: 0 auto;
}
.tripname {
  width: 100%;
  position: absolute;
  margin: 0 auto;
  bottom: 10%;
  color: yellow;
  font-size: 1rem;
  font-weight: 600;
}

.trainname {
  width: 100%;
  position: absolute;
  margin: 0 auto;
  bottom: 10%;
  color: yellow;
  font-size: 1rem;
  font-weight: 600;
  z-index: 2;
}

.tripContentImg {
  opacity: .5;
  height: 15rem;
  flex: 1;
  position: relative;
  animation: triphide .5s;
  -moz-animation: triphide .5s;	/* Firefox */
  -webkit-animation: triphide .5s;	/* Safari 和 Chrome */
  -o-animation: triphide .5s;	/* Opera */
  overflow: hidden;
}
.tripContentImg > img{
  /* width: 100%; */
  height: 100%;
}
.tripContentImgActive {
  opacity: 1;
  height: 15rem;
  flex: 2;
  position: relative;
  animation: tripshow .5s;
  -moz-animation: tripshow .5s;	/* Firefox */
  -webkit-animation: tripshow .5s;	/* Safari 和 Chrome */
  -o-animation: tripshow .5s;	/* Opera */
  overflow: hidden;
}
.tripContentImgActive > img{
  /* width: 100%; */
  height: 100%;
}
/* 户外培训 */
.trainBox {
  background: #333;
  padding-top: 1rem;
  padding-bottom: 1rem;
  padding-left: 2rem;
  padding-right: 2rem;
  margin: 0 auto;
  position: relative;
}
.trainTitle {
  width: 30%;
  display: flex;
}
.trainTitleSection {
  flex: 1;
  color: #fdd000;
  font-Weight: 600;
  font-size: 1rem;
  padding-top: 0.5rem;
  padding-bottom: 0.5rem;
}
.trainTitleSectionActive {
  flex: 1;
  color: #000;
  font-Weight: 600;
  font-size: 1rem;
  background: #fdd000;
  padding-top: 0.5rem;
  padding-bottom: 0.5rem;
  border-radius: 5px 5px 0 0;
}
.trainContent {
  width: 100%;
  display: flex;
  margin: 0 auto;
}
.trainContentLeft {
  flex:1;
  position: relative;
}
.trainContentLeft > img {
  width: 100%;
  height: 100%;
  position: absolute;
  left: 0;
  top: 0;
}
.trainContentRight {
  flex: 2;
  flex-direction: column;
  display: grid;
  grid-template-columns: 1fr 1fr 1fr;
  grid-template-rows:1fr 1fr;
}
.trainContentRightBox {
  display: flex;
  flex-direction: row;
  position: relative;
}
.trainContentImgActive {
  position: relative;
  opacity: 1;
  flex: 1;
  flex-wrap: wrap;
  animation: trainshow .5s;
  -moz-animation: trainshow .5s;	/* Firefox */
  -webkit-animation: trainshow .5s;	/* Safari 和 Chrome */
  -o-animation: trainshow .5s;	/* Opera */
}
.trainContentImgActive > img{
  width: 100%;
  height: 11rem;
}
.trainContentImg {
  position: relative;
  opacity: .5;
  flex: 1;
  flex-wrap: wrap;
  animation: trainhide .5s;
  -moz-animation: trainhide .5s;	/* Firefox */
  -webkit-animation: trainhide .5s;	/* Safari 和 Chrome */
  -o-animation: trainhide .5s;	/* Opera */
}
.trainContentImg > img {
  width: 100%;
  height: 11rem;
}
/* 部落活动 */
.clubindexBox {
  background: #333;
  padding-top: 1rem;
  padding-bottom: 1rem;
  padding-left: 2rem;
  padding-right: 2rem;
  margin: 0 auto;
  position: relative;
}
.clubTitle {
  width: 30%;
  display: flex;
}
.clubTitleSectionActive {
  flex: 1;
  color: #000;
  font-Weight: 600;
  font-size: 20;
  background: #fdd000;
  padding-top: 0.5rem;
  padding-bottom: 0.5rem;
  border-radius: 5px 5px 0 0;
}
.clubTitleSection {
  flex: 1;
  color: #fdd000;
  font-Weight: 600;
  font-size: 20;
  padding-top: 0.5rem;
  padding-bottom: 0.5rem;
}
.clubindexContent {
  width: 100%;
  display: flex;
  justify-content: space-between;
}
.clubImg {
  flex: 1;
  padding-right: 1rem;
}
.clubImgL {
  flex: 1;
}
.clubImg > img {
  width: 100%;
  height: 12rem;
}
.clubImgL > img {
  width: 100%;
  height: 12rem;
}
.tripIntroduceBox {
  background: #333;
  padding-top: 1rem;
  padding-bottom: 1rem;
  padding-left: 2rem;
  padding-right: 2rem;
  margin: 0 auto;
  position: relative;
  height: 500px;
  display: flex;
  flex-direction: column;
}
.tripInLeft {
  flex: 2;
  display: flex;
  flex-direction: column;
}
.tripInRight {
  flex: 1;
  background: #000;
  position: relative;
  overflow: hidden
}
.tripInLeftTop {
  flex: 1;
  position: relative;
  overflow: hidden
}
.tripInLeftBottom {
  display: grid;
  grid-template-columns: 1fr 1fr 1fr;
  grid-template-rows:1fr 1fr;
  position: relative;
  overflow: hidden;
  flex-direction: row;
}
.tripInLeftBottomSection {
  flex: 1;
  position: relative;
  overflow:hidden;
  height: 13rem;
}
.introduceImg {
  width: 100%;
  height: 100%;
}
.tripInText {
   width: 100%;
    height: 100%;
    position: absolute;
    left: 0;
    top: 19%;
    animation: clubTexthide .5s;
    -moz-animation: clubTexthide .5s;	/* Firefox */
    -webkit-animation: clubTexthide .5s;	/* Safari 和 Chrome */
    -o-animation: clubTexthide .5s;	/* Opera */
}
.tripInTextActive {
  width: 100%;
    height: 100%;
    position: absolute;
    left: 0;
    top: 7%;
    animation: clubTextshow .5s;
    -moz-animation: clubTextshow .5s;	/* Firefox */
    -webkit-animation: clubTextshow .5s;	/* Safari 和 Chrome */
    -o-animation: clubTextshow .5s;	/* Opera */
}
.tripInname {
  color: #fff;
    text-align: left;
    padding-left: 1rem;
    position: absolute;
    top: 70%;
}
.tripInnum {
    color: #fff;
    text-align: left;
    padding-left: 1rem;
    position: absolute;
    top: 79%;
}
.tripInJoin {
  display: flex;
    justify-content: center;
    align-items: center;
    position: absolute;
    right: 2%;
    top: 90%;
    width: 4rem;
    height: 2rem;
    background: yellow;
    color: #000;
    border-radius: 10px;
    animation: clubjoinhide .5s;
    -moz-animation: clubjoinhide .5s;	/* Firefox */
    -webkit-animation: clubjoinhide .5s;	/* Safari 和 Chrome */
    -o-animation: clubjoinhide .5s;	/* Opera */
}
.tripInJoinActive {
  display: flex;
    justify-content: center;
    align-items: center;
    position: absolute;
    right: 2%;
    top: 75%;
    width: 4rem;
    height: 2rem;
    background: yellow;
    color: #000;
    border-radius: 10px;
    animation: clubjoinshow .5s;
    -moz-animation: clubjoinshow .5s;	/* Firefox */
    -webkit-animation: clubjoinshow .5s;	/* Safari 和 Chrome */
    -o-animation: clubTextshow .5s;	/* Opera */
}
@-webkit-keyframes clubTextshow /* Safari 和 Chrome */
{
  from { top: 19% }
  to { top: 7%}
}
@-webkit-keyframes clubTexthide /* Safari 和 Chrome */
{
  from { top: 7% }
  to { top: 19% }
}
@-webkit-keyframes clubjoinshow /* Safari 和 Chrome */
{
  from { top: 90% }
  to { top: 75%}
}
@-webkit-keyframes clubjoinhide /* Safari 和 Chrome */
{
  from { top: 75% }
  to { top: 90% }
}
.activityDivWrap {
  width: 100%;
  background: #333;
  padding-top: 2rem;
}
.activityDiv {
    width: 98%;
    margin: 0 auto;
}
.activityBox {
    width: 100%;
    height: 16rem;
    display: flex;
    flex-direction: row;
    position: relative;
    overflow: hidden;
    padding-top: 1rem;
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
    top: 80%;
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
    top: 80%;
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
  to { top: 80%}
}
@-webkit-keyframes opendetailhide /* Safari 和 Chrome */
{
  from { top: 80% }
  to { top: 100% }
}
.dragdown {
    position: absolute;
    right: 0;
    bottom: 0;
}
.tripIntroduceContent {
  display: flex;
  flex-direction: row;
}
</style>