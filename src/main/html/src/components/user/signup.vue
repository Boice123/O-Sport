<template>
  <div class="signup">
    <div class="signupBox">
    <el-form ref="form" :rules="rules" :model="form" label-width="80px">
      <el-form-item label="手机" prop="tel">
        <el-input type="phone" v-model="form.tel" @blur="checkTel"></el-input>
      </el-form-item>
      <el-form-item label="密码" prop="password">
        <el-input type="password" v-model="form.password"></el-input>
      </el-form-item>
       <el-form-item label="确认密码" prop="repassword">
        <el-input type="password" v-model="form.repassword"></el-input>
      </el-form-item>
      <el-form-item label="昵称" prop="username">
        <el-input type="text" v-model="form.username"></el-input>
      </el-form-item>
      <el-form-item class="yzmlbox" label="验证码" prop="checkcode">
        <el-input class="yzmInput" type="text" v-model="form.checkcode"></el-input>
        <img id="checkImg" class="checkImage" src=API_checkImgURL @click="changeYZM" title="点击更换验证码">
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm('form')">注册</el-button>
      </el-form-item>
    </el-form>
    </div>    
  </div>
</template>

<script>
import {API_saveUserURL,API_checkImgURL,API_checkUserTelExistURL} from '../../constants/index.js'
import axios from 'axios'
  export default {
    name: 'signup',
    data() {
      // 手机号
      var validateTel = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入手机号'));
        } else {
          if (!/^0?(13[0-9]|15[012356789]|18[0236789]|14[57])[0-9]{8}$/.test(this.form.tel)) {
            callback(new Error('请输入正确的手机号'));
          }
          callback();
        }
      };
      // 检验密码
      var validatePassword = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入密码'));
        } else {
          if (this.form.repassword !== '') {
            this.$refs.form.validateField('repassword');
          }
          callback();
        }
      };
      // 检验确认密码是否与密码一致
      var validateRePassword = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请再次输入密码'));
        } else if (value !== this.form.password) {
          callback(new Error('两次输入密码不一致!'));
        } else {
          callback();
        }
      };
      return {
        form: {
          username: '',
          password: '',
          repassword: '',
          tel: '',
          checkcode: ''
        },
        rules: {
          username: [
            { required: true, message: '请输入昵称', trigger: 'blur' },
            { min: 3, max: 10, message: '长度在 3 到 10 个字符', trigger: 'blur' }
          ],
          tel: [
            { validator: validateTel, trigger: 'blur' }
          ],
          password: [
            { validator: validatePassword, trigger: 'blur' }
          ],
          repassword: [
            { validator: validateRePassword, trigger: 'blur' }
          ],
          checkcode: [
            { required: true, message: '请输入验证码', trigger: 'blur' },
          ]
        }
      }
    },
    mounted() {
      var img1 = document.getElementById("checkImg");
      img1.src=API_checkImgURL+"?"+new Date().getTime();
    },
    methods: {
      //更换验证码
      changeYZM() {
        var img1 = document.getElementById("checkImg");
        img1.src=API_checkImgURL+"?"+new Date().getTime();
      },
      //检查手机是否已注册
      checkTel() {
        var params = new URLSearchParams();
        params.append('tel', this.form.tel);
        axios({
              method:'post',
              url:API_checkUserTelExistURL,
              params: params
            })
              .then((response) => {
                if(response.data.code == 1) {
                  this.$message({
                    message: response.data.msg,
                    type: 'warning'
                  }); 
                }  
            })
              .catch((err) => {
                console.log(err)
              });
      },
      submitForm(formName) {
        //信息加入param
        var params = new URLSearchParams();
        params.append('username', this.form.username);
        params.append('password',this.form.password);
        params.append('tel', this.form.tel);
        params.append('checkcode', this.form.checkcode);
        this.$refs[formName].validate((valid) => {
          if (valid) {
            axios({
              method:'post',
              url:API_saveUserURL,
              params: params
            })
              .then((response) => {
                if(response.data.code == 0) {
                  console.log(response.data)
                  this.$message({
                    message: '注册成功！',
                    type: 'success'
                  });   
                  this.setCookie('user_tel',  response.data.data.tel, 2)
                  this.setCookie('user_username',  response.data.data.username, 2)
                  this.setCookie('user_userid',  response.data.data.userid, 2)
                  this.$store.commit('usernameChange', response.data.data.username)
                  this.$router.push('/')
                } else if(response.data.code == 1) {
                  this.$message({
                    message: response.data.msg,
                    type: 'warning'
                  }); 
                } else {
                  console.log(response.data)
                  this.$message.error('注册失败，请稍后重试');
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
.signup {
  width: 100%;
  height: 35rem;
  background: url("../../assets/images/signupBgc.jpg");
  background-size: cover;
  position: relative;
}
.signupBox {
  border-radius: 1rem;
  position: absolute;
  left: 60%;
  margin-top: 5rem;
  padding-top: 3rem;
  padding-right: 4rem;
  width: 20rem;
  background: #000;
  opacity: 0.9;
}
.yzmlbox {
  position: relative;
}
.yzmInput {
  position: absolute;
  width: 48%;
  left: 0;
}
.checkImage {
  display: inline;
  position: absolute;
  right:0;
  top: 0;
}
</style>
