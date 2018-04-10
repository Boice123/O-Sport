<template>
    <div class="shopContainer">
        <div class="shopTopNav">
            O-Sport用户中心 
            <ul class="shopTopNavUl">
            </ul>
        </div>
        <div class="shopContent">
            <div class="shopContentLeft">
                <div class="shopContentLeftBox shopInfoBox">
                    <!-- <div class="shopInfoknow">我的店铺</div> -->
                    <div class="shopInfo">
                        <img class="shopInfoimg" :src="userimg" />
                        <h3>{{username}}</h3>
                    </div>
                </div>
                <div class="shopContentLeftBox">
                    <div class="shopContentLeftTitle">
                        <img class="shopContentLeftTitleImg" src="../../assets/images/shopmanage.svg"/>
                        <h3>个人信息管理</h3>
                    </div>
                    <router-link to="updateUserInfo"><div class="shopContentLeftList">修改资料</div></router-link>
                    <router-link to="updatePassword"><div class="shopContentLeftList">修改密码</div></router-link>
                </div>
                <!-- <div class="shopContentLeftBox">
                    <div class="shopContentLeftTitle">
                        <img class="shopContentLeftTitleImg" src="../../assets/images/ordermanage.svg"/>
                        <h3>订单管理</h3>
                    </div>
                    <router-link to="shopmanagetriporder"><div class="shopContentLeftList">我的订单</div></router-link>
                </div>
                <div class="shopContentLeftBox">
                    <div class="shopContentLeftTitle">
                        <img class="shopContentLeftTitleImg" src="../../assets/images/goodsmanage.svg"/>
                        <h3>部落管理</h3>
                    </div>
                    <router-link to="shopmanagetrip"><div class="shopContentLeftList">我管理的部落</div></router-link>
                    <router-link to="shopmanagetrip"><div class="shopContentLeftList">我加入的部落</div></router-link>
                </div> -->
            </div>
            <div class="shopContentRight">
                <router-view></router-view>
            </div>
        </div>
    </div>
</template>

<script>
import { API_getUserInfoURl } from '../../constants/index.js'
import axios from 'axios'
  export default {
    name: 'userCenter',
    data() {
      return {
          username: '',
          userimg: '',
      }
    },
    created() {
      axios({
            method:'post',
            url:API_getUserInfoURl,
        })
        .then((response) => {
            console.log(response.data)
            if(response.data.code == 0) {
                this.username = response.data.data.username
                this.userimg = response.data.data.userimg
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
    font-size: 1rem;
    padding-bottom: 0.5rem;
}
.shopInfoDetailNum {
    font-size: 1rem;
    color: orange;
}
</style>