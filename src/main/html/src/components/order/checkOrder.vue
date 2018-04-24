<template>
    <div class="checkOrderContainer">
        <div class="tripDetailBox">
            <div class="checkOrdertitle">确认订单信息</div>
            <div class="tripDetail">
                <div class="tripDetailLeft">
                    <div class="orderItem">出团名称：{{trip.tripname}}</div>
                     <img src="../../assets/images/dingdan_img_xuxian.png"/>
                    <div class="orderItem">每人价格：{{trip.tripprice}}</div>
                     <img src="../../assets/images/dingdan_img_xuxian.png"/>
                    <div class="orderItem">参团人数：{{trip.person}}</div>
                     <img src="../../assets/images/dingdan_img_xuxian.png"/>
                    <div class="orderItem">出行时间：{{trip.triptime}}</div>
                    <img src="../../assets/images/dingdan_img_xuxian.png"/>
                    <div class="ordertotal">总价：{{trip.person * trip.tripprice}}</div>
                </div>
                <div class="tripDetailRight">
                    <img :src="trip.tripimg"/>
                    <div class="buttonBox"> 
                        <div class="cancelButton" @click="gotoIndex">取消</div>
                        <div class="payButton" @click="payOrder">确认订单</div>
                    </div>
                </div>
            </div>
        </div>
  </div>
</template>

<script>
import {API_getTripInfoURl, API_saveOrderURl} from '../../constants/index.js'
import axios from 'axios'
  export default {
    name: 'checkOrder',
    data() {
      return {
          trip: [{
              tripid: '',
              tripimg: '',
              tripname: '',
              tripprice: '',
              person: 1,
              triptime: ''
          }],
          
      }
    },
    created() {
        //获取Trip信息
        var params = new URLSearchParams();
        params.append('tripid',this.$route.params.tripid)
        console.log("this.$route.params.tripid"+ this.$route.params.tripid)
        axios({
            method:'post',
            url:API_getTripInfoURl,
            params
        })
        .then((response) => {
            console.log(response.data)
            if(response.data.code == 0) {
                this.trip = response.data.data
                this.trip.person = this.$route.params.person
                this.trip.triptime = this.$route.params.triptime
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
        gotoIndex() {
            this.$router.push('/')
        },
        payOrder() {
        //支付
        var params = new URLSearchParams();
        params.append('tripid', this.$route.params.tripid)
        params.append('people', this.trip.person)
        params.append('triptimeid', this.$route.params.triptimeid)
        params.append('tripordertotal',this.trip.person * this.trip.tripprice)
        axios({
            method:'post',
            url:API_saveOrderURl,
            params
        })
        .then((response) => {
            console.log(response.data)
            if(response.data.code == 0) {
                this.$message({
                    message: response.data.msg,
                    type: 'success'
                }); 
                this.$router.push('/')
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
        }
    }
  }
</script>

<style>
.checkOrderContainer {
    width: 100%;
    margin: 2rem auto;
}
.tripDetailBox {
    width: 60rem;
    box-shadow:4px 4px 10px gray;
    margin: 0 auto;
}
.checkOrdertitle {
    text-align: left;
    padding-left: 3rem;
    padding-top: 2rem;
    font-size: 1.5rem;
    font-weight: 500;
}
.tripDetail {
    margin-bottom: 2rem;
    display: flex;
    flex-direction: row;
}
.tripDetailLeft {
    text-align: left;
    flex: 2;
}
.orderItem {
    margin-top: 2rem;
    margin-bottom: 1rem;
}
.ordertotal {
    margin-top: 2rem;
    margin-bottom: 1rem;
    color: orange;
    font-size: 2rem;
    font-weight: 600;
}
.tripDetailLeft > img{
    width: 30rem;
}
.tripDetailRight {
    flex: 1;
}
.tripDetailRight > img{
    width: 18rem;
    height: 18rem;
}
.buttonBox {
    display: flex;
}
.cancelButton {
    display: flex;
    justify-content: center;
    align-items: center;
    margin: 1rem;
    flex: 1;
    height: 3rem;
    background: yellow;
    color: #000;
    border-radius: .8rem;
    box-shadow:4px 4px 10px gray;
}
.payButton {
    display: flex;
    justify-content: center;
    align-items: center;
    margin: 1rem;
    flex: 1;
    height: 3rem;
    background: #000;
    color: yellow;
    border-radius: .8rem;
    box-shadow:4px 4px 10px gray;
}
</style>