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
        <li class="rightLi">我的提问</li>
        <li class="rightLi">|</li>
        <router-link to='/shop'><li class="rightLi">我的商店</li></router-link>
        <li class="rightLi">|</li>
        <li class="rightLi">我的订单</li>
        <li class="rightLi">|</li>
        <li class="rightLi">我的部落</li>
        <li class="rightLi">|</li>
        <li class="rightLi">我的o-sport</li>
        
      </ul>
    </div>
  </div>
</template>

<script>
  export default {
    name: 'signup',
    data() {
      return {

      }
    },
    created() {
      let username = this.getCookie('user_username')
      this.$store.commit('usernameChange',username);
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
}

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
</style>
