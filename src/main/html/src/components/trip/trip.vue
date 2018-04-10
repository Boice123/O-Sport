<template>
    <div class="tripContainer">
        <!-- 搜索栏 -->
        <searchBar/>
        <!-- topNav导航栏 -->
        <topNav/>
        <!-- 介绍部分 -->
        <div class="tripDetail">
            <div class="tripDetailLeft">
                <div class="tripBigImgboxWrap">
                    <img class="tripBigImg" src="../../assets/images/banner/banner_01.jpg"/>
                </div>
            </div>
            <div class="tripDetailRight">
                <h2 class="tripTitle">{{trip.tripname}}</h2>
                <br>
                <h4 class="tripdescription">{{trip.tripdescription}}</h4>
                <div class="tripPrice">
                    产品价格：<span class="tripPriceSpan">{{trip.tripprice}}</span>元起（成人）
                </div>
                <el-form class="tripgoDate">
                    <el-form-item label="出发日期：" prop="date">
                        <el-select v-model="form.chooseTriptime" placeholder="请选择" @change="getTriptime">
                            
                            <el-option
                            v-for="(item, key) in triptime"
                            :key="key"
                            :label="item.triptime"
                            :value="item.triptime">
                            </el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="参加人数：" prop="person">
                        <el-input-number v-model="form.person" :min="1" :max="10"></el-input-number>
                    </el-form-item>
                    <span class="tripnotice">注意事项：{{trip.tripnotice}}</span>
                    <el-form-item>
                        <el-button type="primary" @click="submitForm('form')">立刻报名</el-button>
                    </el-form-item>
                </el-form>
            </div>
        </div>
        <!-- 评价 -->
        <div class="tripBottom">
            <div class="introduceTrip">
                <div class="introduceTripBox">
                    <span class="introduceTripTitle">出行推荐</span>
                </div>
                <div class="introduceTripImg" 
                        @click="gotoTripPage(trip.tripid)"
                        v-for="(trip,key) in tripList"
                    >
                    <div class="introname">{{trip.tripname}}</div>
                    <!-- <img :src="trip.tripimg"/> -->
                    <img class="introImg" src="../../assets/images/banner/banner_01.jpg"/>
                </div>
            </div>
            <div class="evaluate">
                <div class="introduceTripBox">
                    <span class="introduceTripTitle">评价</span>
                </div>
                <div class="evaluateBox"
                    v-for="(evaluate, key) in evaluateList"
                >
                    <div class="evaluateUser">
                        <img class="evaluateUserImg" src="../../assets/images/banner/banner_01.jpg"/>
                        <div>{{evaluate.evaluatetime}}</div>
                    </div>
                    <div class="evaluateContent">{{evaluate.content}}</div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import searchBar from '../common/searchBar'
import topNav from '../common/topNav'
import {API_getTripInfoURl, API_getTriptimeInfoURl, API_getTripList, API_getEvaluateURL} from '../../constants/index.js'
import axios from 'axios'
  export default {
    name: 'trip',
    data() {
      return {
          trip: {},
          tripid: '',
          value: "",
          triptime:[],
          form: {
              person: 1,
              chooseTriptime: '',
              chooseTriptimeid: ''
          },
          tripList: [],
          evaluateList: []
      }
    },
    created() {
        this.getTripInfo()
        this.getTriptimeInfo()
        this.getTripListByTrading()
        this.getEvaluate()
    },
    methods: {
      gotoTripPage(tripid) {
        this.$router.push({name:'trip', params:{tripid}})
      },
      getEvaluate() {
        //获取评价
        var params = new URLSearchParams();
        params.append('tripid', this.$route.params.tripid)
        params.append('order', 'evaluatetime')
        axios({
            method:'post',
            url:API_getEvaluateURL,
            params
        })
        .then((response) => {
            console.log(response.data)
            if(response.data.code == 0) {
                this.evaluateList = response.data.data
            }else if(response.data.code == 1) {
                this.$message({
                    message: response.data.msg,
                    type: 'warning'
                }); 
            }else {
                this.$message.error('获取评价失败，请稍后重试');
            }        
        }).catch((err) => {
            console.log(err)
        })
      },
      getTripListByTrading() {
        //按成交量热度获取出团活动信息
        var params = new URLSearchParams();
        params.append('start', 0)
        params.append('size',2)
        params.append('order','triptrading')
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
                this.$message.error('获取按热度排序获取出团活动信息失败，请稍后重试');
            }        
        }).catch((err) => {
            console.log(err)
        })
      },
      //获取trip信息
      getTripInfo() {
          //获取Trip信息
        var params = new URLSearchParams();
        params.append('tripid',this.$route.params.tripid)
        this.tripid = this.$route.params.tripid
        axios({
            method:'post',
            url:API_getTripInfoURl,
            params
        })
        .then((response) => {
            console.log(response.data)
            if(response.data.code == 0) {
                this.trip = response.data.data
            }else if(response.data.code == 1) {
                this.$message({
                    message: response.data.msg,
                    type: 'warning'
                }); 
            }else {
                this.$message.error('获取出行团信息失败，请稍后重试');
            }        
        }).catch((err) => {
            console.log(err)
        })
      },
      //获取Trip时间
      getTriptimeInfo() {
        var params = new URLSearchParams()
        params.append('tripid', this.$route.params.tripid)
        axios({
            method:'post',
            url:API_getTriptimeInfoURl,
            params
        })
        .then((response) => {
            console.log(response.data)
            if(response.data.code == 0) {
                var dataArray = response.data.data.slice(0)
                console.log(dataArray)
                for(let i = 0 ;i < dataArray.length; i++) {
                    if(dataArray[i].triptime === "" ) {
                        dataArray.splice(i,1)
                        i = -1
                        continue
                    }
                    var triptime = dataArray[i].triptime.split("-")
                    var startTime = new Date(triptime[0],triptime[1] -1 ,triptime[2])
                    var startTimes = startTime.getTime()
                    var endTime = new Date(new Date().getFullYear(), new Date().getMonth(), new Date().getDay()+ 1)
                    var endTimes = endTime.getTime()
                    if(startTimes <= endTimes) {
                        dataArray.splice(i,1)
                        i = -1
                    }
                }
                console.log(dataArray)
                this.triptime = dataArray
                this.form.chooseTriptime = dataArray[0].triptime
                this.form.chooseTriptimeid= dataArray[0].triptimeid
            }else if(response.data.code == 1) {
                console.log("triptime数据为空")
            }else {
                this.$message.error('获取户外时间信息失败，请稍后重试');
            }        
        }).catch((err) => {
            console.log(err)
        })
      },
        submitForm(formName) {
            console.log("this.form.chooseTriptime"+ this.form.chooseTriptime)
            console.log("this.form.chooseTriptimeid"+ this.form.chooseTriptimeid)
            console.log("this.form.person"+ this.form.person)
                this.$router.push({name:'checkOrder', params: {
                    tripid: this.tripid,
                    person: this.form.person,
                    //用作确认订单显示用
                    triptime: this.form.chooseTriptime,
                    //用作提交订单的后台数据用
                    triptimeid: this.form.chooseTriptimeid
                }})       
        },
        getTriptime(val) {
            for(var i=0; i< this.triptime.length;i++) {
                if(val === this.triptime[i].triptime) {
                    this.form.chooseTriptimeid = this.triptime[i].triptimeid
                    break
                }
            }
            // console.log(this.form.chooseTriptimeid)
        }
    },
    components: {
        searchBar,
        topNav
    }
  }
</script>

<style>
.tripContainer {
    width: 100%;
}
.tripDetail {
    display: flex;
    flex-direction: row;
    padding-top: 1rem;
    padding-bottom: 1rem;
    padding-left: 3rem;
    padding-right: 3rem;
}
.tripDetailLeft {
    flex: 2;
    display:flex;
    flex-direction: column;
}
.tripDetailRight {
    flex: 3;
    padding-left: 1rem;
    text-align: left;
}
.tripBigImg {
    width: 30rem;
    height: 20rem;
}
.tripSmallImgboxWrap {
    padding-top: 0.5rem;
    width: 100%;
    display: flex;
    flex-direction: row;
}
.tripSmallImgBox {
    flex: 1;
}
.tripSmallImg {
    width: 5.5rem;
    height: 3.8rem;
}
.tripTitle {
    font-size: 3rem;
    line-height: 1.5rem;
    margin-top: 1rem;
    font-weight: 500;
}
.tripdescription {
    font-size: 2rem;
    font-weight: 400;
}
.tripPrice {
    padding-top: 1rem;
    font-size: 1rem;
}
.tripPriceSpan {
    color: orange;
    font-size: 3rem;
    padding-left: 1rem;
    padding-right: 1rem;
    font-weight: 500;
}
.tripgoDate {
    padding-top: 2rem;
    margin-left: 0rem;
}
.tripbuy {
    width: 15rem;
    padding-top: 2rem;
}
.buyButton {
    padding: 0.7rem 1rem;
    background: orange;
    color:#fff;
    border-radius: 0.2rem;
}
.tripnotice {
    display: block;
    font-size: .8rem;
    color: red;
    padding-bottom: 1rem;
}
.tripBottom {
    width: 80rem;
    margin: 0 auto;
    display: flex;
    margin-bottom: 2rem;
}
.introduceTrip {
    flex: 1;
    /* height: 10rem; */
    border: 1px solid #fdd000;
}
.evaluate {
    flex: 3;
    /* height: 10rem; */
    border: 1px solid #fdd000;
}
.introduceTripBox {
    border-bottom: 5px dotted gray;
    margin: 2rem 3rem;
    display: flex;
    justify-content: flex-start;
}
.introduceTripTitle {
    font-size: 1rem;
    text-align: left;
    margin-bottom: 1rem;
}
.introduceTripImg {
    width: 100%;
    margin-bottom: 1rem;
    position: relative;
}
.evaluateBox {
   min-height: 10rem;
   margin: auto 3rem;
   padding: 1rem auto;
   background: yellow;
   border-bottom: 1px solid gray;
   display: flex;
   align-items: center;
}
.evaluateUser {
    flex: 1;
}
.evaluateContent {
    flex: 4;
    text-align: left;
}
.evaluateUserImg {
    width: 5rem;
    height: 5rem;
    border-radius: 2.5rem;
    margin-bottom: 1rem;
}
.introImg {
    width: 13rem;
    height: 10rem;
}
.introname {
  position: absolute;
  left: 9rem;
  bottom: 10%;
  color: #fdd000;
  font-size: 1rem;
  font-weight: 600;
}
</style>