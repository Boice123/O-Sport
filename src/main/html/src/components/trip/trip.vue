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
                <div class="tripSmallImgboxWrap">
                    <div class="tripSmallImgBox">
                        <img class="tripSmallImg" src="../../assets/images/banner/banner_01.jpg"/>
                    </div>
                    <div class="tripSmallImgBox">
                        <img class="tripSmallImg" src="../../assets/images/banner/banner_01.jpg"/>
                    </div>
                    <div class="tripSmallImgBox">
                        <img class="tripSmallImg" src="../../assets/images/banner/banner_01.jpg"/>
                    </div>
                    <div class="tripSmallImgBox">
                        <img class="tripSmallImg" src="../../assets/images/banner/banner_01.jpg"/>
                    </div>
                    <div class="tripSmallImgBox">
                        <img class="tripSmallImg" src="../../assets/images/banner/banner_01.jpg"/>
                    </div>
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
                    <el-form-item label="出发日期：">
                        <el-select v-model="value" placeholder="请选择">
                            <el-option
                            v-for="item in options"
                            :key="item.value"
                            :label="item.label"
                            :value="item.value">
                            </el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="参加人数：" prop="person">
                        <el-input-number v-model="form.person" :min="1" :max="10"></el-input-number>
                    </el-form-item>
                    <span class="tripnotice">{{trip.tripnotice}}</span>
                    <el-form-item>
                        <el-button type="primary" @click="submitForm('form')">立刻报名</el-button>
                    </el-form-item>
                </el-form>
            </div>
        </div>
    </div>
</template>

<script>
import searchBar from '../common/searchBar'
import topNav from '../common/topNav'
import {API_getTripInfoURl} from '../../constants/index.js'
import axios from 'axios'
  export default {
    name: 'trip',
    data() {
      return {
          form: {
              person: 1
          },
          trip: [],
          options: [{
          value: '选项1',
          label: '黄金糕'
        }, {
          value: '选项2',
          label: '双皮奶'
        }, {
          value: '选项3',
          label: '蚵仔煎'
        }, {
          value: '选项4',
          label: '龙须面'
        }, {
          value: '选项5',
          label: '北京烤鸭'
        }],
        value: ''
      }
    },
    created() {
        //获取Trip信息
        var params = new URLSearchParams();
        params.append('tripid',this.$route.params.tripid)
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
    methods: {
        submitForm(formName) {

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
    line-height: 1.5rem;
}
.tripPrice {
    padding-top: 2rem;
    font-size: 1rem;
}
.tripPriceSpan {
    color: orange;
    font-size: 3rem;
    padding-left: 1rem;
    padding-right: 1rem;
}
.tripgoDate {
    padding-top: 2rem;
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
    font-size: .5rem;
    color: red;
    padding-bottom: 1rem;
}
</style>