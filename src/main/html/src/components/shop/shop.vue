<template>
    <div class="shopContainer">
        <div class="shopTopNav">
            O-Sport店铺中心 
            <ul class="shopTopNavUl">
                <!-- <router-link to="shopinfo"><li class="shopTopNavLi">店铺首页</li></router-link>
                <router-link to="shopinfo"><li class="shopTopNavLi">设置</li></router-link>
                <router-link to="shopinfo"><li class="shopTopNavLi">更多</li></router-link> -->
            </ul>
        </div>
        <div class="shopContent">
            <div class="shopContentLeft">
                <div class="shopContentLeftBox shopInfoBox">
                    <!-- <div class="shopInfoknow">我的店铺</div> -->
                    <div class="shopInfo">
                        <img class="shopInfoimg" :src="shopimg" />
                        <h3>{{shopname}}</h3>
                    </div>
                    <div class="shopInfoDetail">
                        <div class="shopInfoDetailBox">
                            <span class="shopInfoDetailTitle">总成交量</span>
                            <span class="shopInfoDetailNum">{{shopTrading}}</span>
                        </div>
                        <div class="shopInfoDetailBox">
                            <span class="shopInfoDetailTitle">户外出团总数</span>
                            <span class="shopInfoDetailNum">{{shopTripCount}}</span>
                        </div>
                    </div>
                </div>
                <div class="shopContentLeftBox">
                    <div class="shopContentLeftTitle">
                        <img class="shopContentLeftTitleImg" src="../../assets/images/shopmanage.svg"/>
                        <h3>店铺信息管理</h3>
                    </div>
                    <router-link to="openshop" v-if="this.$store.state.shopid == 0"><div class="shopContentLeftList">我要开店</div></router-link>
                    <router-link to="updateshopinfo" v-else><div class="shopContentLeftList">修改店铺信息</div></router-link>
                </div>
                <div class="shopContentLeftBox">
                    <div class="shopContentLeftTitle">
                        <img class="shopContentLeftTitleImg" src="../../assets/images/ordermanage.svg"/>
                        <h3>订单管理</h3>
                    </div>
                    <router-link to="shopmanagetriporder"><div class="shopContentLeftList">报团出行订单</div></router-link>
                    <!-- <router-link to="shopmanagetrainorder"><div class="shopContentLeftList">户外培训订单</div></router-link> -->
                </div>
                <div class="shopContentLeftBox">
                    <div class="shopContentLeftTitle">
                        <img class="shopContentLeftTitleImg" src="../../assets/images/goodsmanage.svg"/>
                        <h3>出团管理</h3>
                    </div>
                    <router-link to="shopmanagetrip"><div class="shopContentLeftList">报团出行管理</div></router-link>
                    <!-- <router-link to="shopmanagetrain"><div class="shopContentLeftList">户外培训管理</div></router-link> -->
                </div>
            </div>
            <div class="shopContentRight">
                <router-view></router-view>
            </div>
        </div>
    </div>
</template>

<script>
import {API_checkShopExistURl, API_getShopInfoURl, API_getShopTripCountURl, API_getShopTrainCountURl, API_getShopTodayTradingURl} from '../../constants/index.js'
import axios from 'axios'
  export default {
    name: 'trip',
    data() {
      return {
          shopname: '',
          shopimg: '',
          shopTripCount: 0,
          shopTrainCount: 0,
          shopTrading: 0
      }
    },
    created() {
       //获取商店信息
        console.log(this.getCookie('shop_id'))
        var params = new URLSearchParams();
        params.append('shopid',this.getCookie('shop_id'))
        axios({
            method:'post',
            url:API_getShopInfoURl,
            params
        })
        .then((response) => {
            console.log(response.data)
            if(response.data.code == 0) {
                this.shopname = response.data.data.shopname
                this.shopimg = response.data.data.shopimg
                console.log(this.shopimg)
            }else if(response.data.code == 1) {
                this.$message({
                    message: response.data.msg,
                    type: 'warning'
                }); 
            }else {
                this.$message.error('获取店铺信息失败，请稍后重试');
            }        
        }).catch((err) => {
            console.log(err)
        })
      //获取商店总成交量
      var params = new URLSearchParams();
        params.append('shopid',this.getCookie('shop_id'))
        axios({
            method:'post',
            url:API_getShopTodayTradingURl,
            params
        })
        .then((response) => {
            console.log(response.data)
            if(response.data.code == 0) {
               this.shopTrading = response.data.data.length
            }else if(response.data.code == 1) {
                this.$message({
                    message: response.data.msg,
                    type: 'warning'
                }); 
            }else {
                this.$message.error('获取店铺今日成交量失败，请稍后重试');
            }        
        }).catch((err) => {
            console.log(err)
        })
      //获取商店户外出团总数  
      var params = new URLSearchParams();
        params.append('shopid',this.getCookie('shop_id'))
        axios({
            method:'post',
            url:API_getShopTripCountURl,
            params
        })
        .then((response) => {
            console.log(response.data)
            if(response.data.code == 0) {
               this.shopTripCount = response.data.data
            }else if(response.data.code == 1) {
                this.$message({
                    message: response.data.msg,
                    type: 'warning'
                }); 
            }else {
                this.$message.error('获取店铺户外出团总数信息失败，请稍后重试');
            }        
        }).catch((err) => {
            console.log(err)
        })
      //获取商店户外培训总数  
    //   var params = new URLSearchParams();
    //     params.append('shopid',this.getCookie('shop_id'))
    //     axios({
    //         method:'post',
    //         url:API_getShopTrainCountURl,
    //         params
    //     })
    //     .then((response) => {
    //         console.log(response.data)
    //         if(response.data.code == 0) {
    //            this.shopTrainCount = response.data.data
    //         }else if(response.data.code == 1) {
    //             this.$message({
    //                 message: response.data.msg,
    //                 type: 'warning'
    //             }); 
    //         }else {
    //             this.$message.error('获取店铺户户外培训总数信息失败，请稍后重试');
    //         }        
    //     }).catch((err) => {
    //         console.log(err)
    //     })
    },
    methods: {
    },
    components: {

    }
  }
</script>

<style>
.shopContainer {
    width: 100%;
}
.shopTopNav {
    padding-left: 1rem;
    padding-top: 1rem;
    padding-bottom: 1rem;
    background: #2b2b2b;
    display: flex;
    color: #fff;
    font-size: 2rem;
    align-items: center;
}
.shopTopNavUl {
    display: flex;
    justify-content: center;
    color: #fff;
    font-size: 1rem;
    width: 20rem;
}
.shopTopNavLi {
    padding-left: 2rem;
    flex: 1;
}
.shopContent {
    width: 100%;
    display: flex;
}
.shopContentLeft {
    flex: 1;
    margin-left: 1rem;
    margin-top: 1rem;
    margin-bottom: 1rem;
    background: #fff;
    border: 2px solid orange;
    border-radius: 1rem;
}
.shopContentRight {
    margin-left: 1rem;
    margin-top: 1rem;
    margin-right: 1rem;
    margin-bottom: 1rem;
    flex: 4;
    border: 2px solid orange;
    border-radius: 1rem;
}
.shopContentLeftBox {
    display: flex;
    flex-direction: column;
    text-align: left;
    margin: 2rem 2rem;
    border-bottom: 1px solid #dfdede;
}
.shopContentLeftTitle {
    display: flex;
    align-items: center;
    padding-bottom: .8rem;
}
.shopContentLeftTitleImg {
    width: 1.5rem;
}
.shopContentLeftList {
    padding-bottom: 1rem;
    padding-left: 1.5rem;
    font-size: 0.9rem;
}
.shopInfoBox {
    display: flex;
    flex-direction: column;
    justify-content: center;
    padding-bottom: 1rem;
}
.shopInfo {
    width: 100%;
    text-align: center;
    display: flex;
    flex-direction: column;
    justify-content: center;
    padding-bottom: 1rem;
}
.el-form {
    margin: 0 auto;
}
.shopInfoimg {
    width: 6rem;
    height: 7rem;
    padding-bottom: 1rem;
    margin: 0 auto;
}
.shopInfoDetail {
    width: 100%;
    display: flex;
    flex-direction: row;
    justify-content: center;
    margin: 0 auto;
}
.shopInfoDetailBox {
    display:flex;
    flex: 1;
    flex-direction: column;
    justify-content: center;
    align-items: center;
}
.shopInfoDetailTitle {
    font-size: 0.8rem;
    padding-bottom: 0.5rem;
}
.shopInfoDetailNum {
    font-size: 1rem;
    color: orange;
}
</style>