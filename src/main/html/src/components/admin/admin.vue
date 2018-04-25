<template>
    <div class="shopContainer">
        <div class="shopTopNav">
            O-Sport后台管理
        </div>
        <div class="shopContent">
            <div class="shopContentLeft">
                <div class="shopContentLeftBox">
                     <router-link to="adminTrip">
                    <div class="shopContentLeftTitle">
                        <img class="shopContentLeftTitleImg" src="../../assets/images/ordermanage.svg"/>
                        <h3>出行活动与订单管理</h3>
                    </div>
                     </router-link>
                </div>
                <div class="shopContentLeftBox">
                    <router-link to="adminClub">
                    <div class="shopContentLeftTitle">
                        <img class="shopContentLeftTitleImg" src="../../assets/images/goodsmanage.svg"/>
                        <h3>部落管理</h3>
                    </div>
                    </router-link>
                </div>
                <div class="shopContentLeftBox">
                     <router-link to="adminUser">
                    <div class="shopContentLeftTitle">
                        <img class="shopContentLeftTitleImg" src="../../assets/images/ordermanage.svg"/>
                        <h3>用户管理</h3>
                    </div>
                     </router-link>
                </div>
            </div>
            <div class="shopContentRight">
                <router-view></router-view>
            </div>
        </div>
    </div>
</template>

<script>
import {} from '../../constants/index.js'
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
    padding-top: 1rem;
    padding-bottom: 1rem;
    background: #2b2b2b;
    display: flex;
    color: #fff;
    font-size: 2rem;
    align-items: center;
    justify-content: center;
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
    /* display: flex; */
}
.shopContentLeft {
    display: flex;
    margin-left: 1rem;
    margin-top: 1rem;
    margin-bottom: 1rem;
    background: #fff;
    /* border: 2px solid orange; */
    border-radius: 1rem;
}
.shopContentRight {
    margin-left: 1rem;
    margin-top: 1rem;
    margin-right: 1rem;
    margin-bottom: 1rem;
    /* flex: 4; */
    border: 2px solid orange;
    border-radius: 1rem;
}
.shopContentLeftBox {
    /* display: flex;
    flex-direction: column;
    text-align: left; */
    margin: 2rem 2rem;
    /* border-bottom: 1px solid #dfdede; */
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
    font-size: 1rem;
    padding-bottom: 0.5rem;
}
.shopInfoDetailNum {
    font-size: 1rem;
    color: orange;
}
</style>