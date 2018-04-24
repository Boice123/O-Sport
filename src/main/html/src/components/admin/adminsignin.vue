<template>
  <div class="signin">
    <div class="signinBox">
      <div class="adminTitle">后台管理员登录</div>
    <el-form ref="form" :rules="rules" :model="form" label-width="80px">
      <el-form-item label="手机号" prop="tel">
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
import {API_adminsigninURL} from '../../constants/index.js'
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
              url:API_adminsigninURL,
              params: params
            })
              .then((response) => {
                console.log(response.data)
                if(response.data.code == 0) {
                  this.$message({
                    message: response.data.msg,
                    type: 'success'
                  })
                   this.setCookie('admin_shopid', '000000', 2)
                   this.$router.push('/admin')
                }else if(response.data.code == 1) {
                  this.$message({
                    message: response.data.msg,
                    type: 'warning'
                  })
                }
              })
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
  display: flex;
  justify-content: center;
  align-items: center;
  /* position: relative; */
}
.signinBox {
  border-radius: 1rem;
  /* position: absolute;
  left: 60%; */
  margin-top: 4rem;
  padding-top: 4rem;
  padding-right: 4rem;
  width: 30rem;
  background: #000;
  opacity: 0.9;
}
.adminTitle {
  color: #fff;
  padding-bottom:2rem;
  padding-left: 4rem; 
} 
</style>
