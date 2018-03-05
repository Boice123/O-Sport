<template>
  <div class="signin">
    <div class="signinBox">
    <el-form ref="form" :rules="rules" :model="form" label-width="80px">
      <el-form-item label="手机" prop="tel">
        <el-input type="phone" v-model="form.tel"></el-input>
      </el-form-item>
      <el-form-item label="密码" prop="password">
        <el-input type="password" v-model="form.password"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm('form')">登录</el-button>
      </el-form-item>
    </el-form>
    </div>    
  </div>
</template>

<script>
import {API_signinURL,API_checkShopExistURl} from '../../constants/index.js'
import axios from 'axios'
  export default {
    name: 'signin',
    data() {
      // 手机号
      var validateTel = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入手机号'));
        } else {
          if (!/^0?(13[0-9]|15[012356789]|18[0236789]|14[57])[0-9]{8}$/.test(this.form.tel)) {
            callback(new Error('请输入正确的手机号'));
          }
          callback()
        }
      };
      // 检验密码
      var validatePassword = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入密码'));
        }
        callback()
      };
      return {
        form: {
          password: '',
          tel: ''
        },
        rules: {
          tel: [
            { validator: validateTel, trigger: 'blur' }
          ],
          password: [
            { validator: validatePassword, trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      submitForm(formName) {
         //信息加入param
        var params = new URLSearchParams();
        params.append('password',this.form.password);
        params.append('tel', this.form.tel);
        this.$refs[formName].validate((valid) => {
          if (valid) {
            axios({
              method:'post',
              url:API_signinURL,
              params: params
            })
              .then((response) => {
                console.log(response.data)
                if(response.data.code == 0) {
                  this.$message({
                    message: response.data.msg,
                    type: 'success'
                  }); 
                  //查看该用户是否开店
                  axios({
                      method:'post',
                      url:API_checkShopExistURl
                  })
                  .then((res) => {
                      console.log(res.data)
                      if(res.data.code == 0) {
                          this.setCookie('shop_id', res.data.data.shopid, 2)
                          this.$store.commit('shopidChange',res.data.data.shopid)
                      } 
                  }).catch((err) => {
                      console.log(err)
                  })
                  this.setCookie('user_tel', response.data.data.tel, 2)
                  this.setCookie('user_username', response.data.data.username, 2)
                  this.setCookie('user_userid', response.data.data.userid, 2)       
                  this.$store.commit('usernameChange', response.data.data.username)
                  this.$router.push('/')
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
          } else {
            console.log('请确认填写的信息是否正确')
            return false;
          }
        })
      }
    }
  }
</script>

<style>
.signin {
  width: 100%;
  height: 33rem;
  background: url("../../assets/images/signupBgc.jpg");
  background-size: cover;
  position: relative;
}
.signinBox {
  border-radius: 1rem;
  position: absolute;
  left: 60%;
  margin-top: 8rem;
  padding-top: 4rem;
  padding-right: 4rem;
  width: 20rem;
  background: #000;
  opacity: 0.9;
}
</style>
