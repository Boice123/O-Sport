<template>
  <div class="updateshopContainer">
    <div class="updateshopknow">修改密码</div>
    <el-form ref="form" :rules="rules" :model="form" label-width="80px">
      <el-form-item label="原密码" prop="oldpwd">
        <el-input v-model="form.oldpwd"></el-input>
      </el-form-item>     
      <el-form-item label="新密码" prop="newpwd">
        <el-input v-model="form.newpwd"></el-input>
      </el-form-item>
      <el-form-item label="确认密码" prop="renewpwd">
        <el-input v-model="form.renewpwd"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm('form')">修改</el-button>
      </el-form-item>
    </el-form>  
  </div>
</template>

<script>
import axios from 'axios'
import {API_updatePasswordURl} from '../../constants/index.js'
import $ from 'jquery'
import ajaxSubmit from '../../../static/js/jquery.form.js'
export default {
    name: 'updateUserPassword',
    data() {
      // 检验原密码
      var validateOldPwd = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入原密码'));
        }
        callback()
      }
      //检验新密码
      var validateNewPwd = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入新密码'));
        }
        callback()
      }
      //再次检验新密码
      var validateReNewPwd = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入确认密码'));
        }
        if(value != this.form.newpwd) {
          console.log(value)
          console.log(this.form.newpwd)
          callback(new Error('新密码前后两次输入不一致'));
        }
        callback()
      }
      return {
        form: {
          oldpwd: '',
          newpwd: '',
          renewpwd: ''
        },
        rules: {
          oldpwd: [
            { validator: validateOldPwd, trigger: 'blur' }
          ],
          newpwd: [
            { validator: validateNewPwd, trigger: 'blur' }
          ],
          renewpwd: [
             { validator: validateReNewPwd, trigger: 'blur' }
          ]
        }
      }
    },
    created() {
         
    },
    methods: {
      submitForm(formName) {
        //信息加入param
         var params = new FormData()
         params.append('oldpwd', this.form.oldpwd)
         params.append('newpwd', this.form.newpwd)
         this.$refs[formName].validate((valid) => {
         if (valid) {
            axios({
                method:'post',
                url:API_updatePasswordURl,
                data: params
            })
            .then((response) => {
                console.log(response.data)
                if(response.data.code == 0) {
                    this.$message({
                        message: response.data.msg,
                        type: 'success'
                    });
                    this.$router.push('updateUserInfo')
                }else if(response.data.code == 1) {
                    this.$message({
                        message: response.data.msg,
                        type: 'warning'
                    });
                }else {
                    this.$message.error('修改密码失败，请稍后重试');
                }
            }).catch((err) => {
                console.log(err)
            })
         }else {
           console.log('请确认填写的信息是否正确')
           return false;
         }
      })
    }
  }
}
</script>

<style>
.updateshopContainer {
    width: 100%;
    display: flex;
    flex-direction: column;
    justify-content: center;
}
.updateshopknow {
  font-size: 1.5rem;
  margin-top: 3rem;
  margin-bottom: 2rem;
}
.el-form {
    width: 25rem;
    margin: 0 auto;
}
.shopimg {
    width: 5rem;
    height: 6rem;
    border: 1px solid #e1e0e0;
    border-radius: .5rem;
    background: url("../../assets/images/addimg.svg") no-repeat center;
    background-size: 2rem 2rem;
}
</style>
