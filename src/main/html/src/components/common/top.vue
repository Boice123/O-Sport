<template>
  <div class="topContainer">
    <div class="left">
      <ul v-if="this.$store.state.username == ''">
        <li class="leftLi" @click="goToSignup">注册</li>
        <li class="leftLi">|</li>
        <li class="leftLi" @click="goToSignin">登录</li>
      </ul>
      <ul v-else>
        <li class="leftLi">{{this.$store.state.username}}</li>
        <li class="leftLi">|</li>
        <li class="leftLi" @click="goToLogout">退出登录</li>
      </ul>
    </div>
    <div class="right">
      <ul>
        <router-link to='/userOrder'><li class="rightLi">我的订单</li></router-link>
        <li class="rightLi">|</li>
        <router-link to='/myClub'><li class="rightLi">我的部落</li></router-link>
        <li class="rightLi">|</li>
        <router-link to='/userCenter'><li class="rightLi">我的o-sport</li></router-link>
         <li class="rightLi">|</li>
        <router-link to='/club'><li class="rightLi">部落主页</li></router-link>
        <li class="rightLi">|</li>
        <router-link to='/'><li class="rightLi">首页</li></router-link>
      </ul>
    </div>
  </div>
</template>

<script>
import {API_signinURL} from '../../constants/index.js'
import axios from 'axios'
  export default {
    name: 'signup',
    data() {
      return {
        showUl: 0
      }
    },
    created() {
      console.log(this.getCookie('user_tel'))
      console.log(this.getCookie('user_password'))
      if(this.getCookie('user_password') == "" || this.getCookie('user_password') == null){
        return
      }
      var password = this.getCookie('user_password') 
      var tel = this.getCookie('user_tel') 
      var params = new URLSearchParams();
      params.append('password',password);
      params.append('tel', tel);
      //取cookie的userid进行自动登录
      axios({
              method:'post',
              url:API_signinURL,
              params: params
            })
              .then((response) => {
                console.log(response.data)
                if(response.data.code == 0) {          
                  this.setCookie('user_tel', response.data.data.tel, 2)
                  this.setCookie('user_password', response.data.data.password, 2)
                  this.setCookie('user_username', response.data.data.username, 2)
                  this.setCookie('user_userid', response.data.data.userid, 2)       
                  this.$store.commit('usernameChange', response.data.data.username)
                }
                else if(response.data.code == 1) {
                  this.$message({
                    message: response.data.msg,
                    type: 'warning'
                  }); 
                }else {
                  this.$message.error('登录失败，请稍后重试');
                }        
            })
              .catch((err) => {
                console.log(err)
              });      
    },
    methods: {
      goToSignup() {
        this.$router.push('./signup')
      },
      goToSignin() {
        this.$router.push('./signin')
      },
      goToLogout() {
        this.clearCookie()
        this.$store.commit('usernameChange','')
        this.$store.commit('shopidChange','')
        console.log("logout")
      },
      changeShowUl(value) {
        this.showUl = value
      }
    }
  }
</script>

<style>
.topContainer {
  width: 100%;
  background: #2b2b2b;
  display: flex;
  color: #fff;
}

.left {
  padding-left: 2rem;
  padding: 0.8rem;
  flex: 1;
  float: left;
}

.right {
  padding-right: 2rem;
  padding: 0.8rem;
  flex:5;
  float: right;
  position: relative;
}

/* .rightActive {
  padding-right: 2rem;
  padding: 0.8rem;
  flex:5;
  float: right;
  position: relative;
} */

.leftLi {
  padding: 0.5rem;
  float: left;
}

.rightLi {
  padding: 0.5rem;
  float: right;
}

.leftLi:hover {
  color: #fdd000;
  cursor: pointer;
}

.rightLi:hover {
  color: #fdd000;
  cursor: pointer;
}
.meUl {
  width: 6.5rem;
  background: #2b2b2b;
  position: absolute;
  right: 24.5%;
  top: 2.5rem;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
.meLi {
  width: 100%;
  height: 2rem;
  display: flex;
  justify-content: center;
  align-items: center;
}
</style>
