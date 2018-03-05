<template>
    <div class="shopContainer">
        <div class="shopTopNav">
            O-Sport店铺中心 
            <ul class="shopTopNavUl">
                <router-link to="shopinfo"><li class="shopTopNavLi">首页</li></router-link>
                <router-link to="shopinfo"><li class="shopTopNavLi">设置</li></router-link>
                <router-link to="shopinfo"><li class="shopTopNavLi">更多</li></router-link>
            </ul>
        </div>
        <div class="shopContent">
            <div class="shopContentLeft">
                <div class="shopContentLeftBox">
                    <div class="shopContentLeftTitle">
                        <img class="shopContentLeftTitleImg" src="../../assets/images/shopmanage.svg"/>
                        <h3>店铺管理</h3>
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
                    <router-link to="shopmanagetrainorder"><div class="shopContentLeftList">户外培训订单</div></router-link>
                </div>
                <div class="shopContentLeftBox">
                    <div class="shopContentLeftTitle">
                        <img class="shopContentLeftTitleImg" src="../../assets/images/goodsmanage.svg"/>
                        <h3>出团管理</h3>
                    </div>
                    <router-link to="shopmanagetrip"><div class="shopContentLeftList">报团出行管理</div></router-link>
                    <router-link to="shopmanagetrain"><div class="shopContentLeftList">户外培训管理</div></router-link>
                </div>
            </div>
            <div class="shopContentRight">
                <keep-alive>
                    <router-view></router-view>
                </keep-alive>
            </div>
        </div>
    </div>
</template>

<script>
import {API_checkShopExistURl} from '../../constants/index.js'
import axios from 'axios'
  export default {
    name: 'trip',
    data() {
      return {
      }
    },
    created() {
       
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
    margin: 2rem 1rem;
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
</style>