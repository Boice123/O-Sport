<template>
  <div class="updateshopContainer">
    <div class="updateshopknow">修改昵称</div>
    <el-form ref="form" :rules="rules" :model="form" label-width="80px">
      <el-form-item label="昵称" prop="username">
        <el-input v-model="form.username" :value="form.username"></el-input>
      </el-form-item>
      <el-form id="pictureForm" method="POST" enctype="multipart/form-data">
        <el-form-item label="上传头像">
          <input class="uploadInput" id="fileUpload" name="fileUpload" @change="uploadPic(this)" accept="image/gif,image/jpeg,image/jpg,image/png,image/svg" type="file"/>
        </el-form-item>
      </el-form>
      <el-form-item label="当前头像" prop="userimg">
        <img class="shopimg" :src="form.userimg"/>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm('form')">修改</el-button>
      </el-form-item>
    </el-form>

    
  </div>
</template>

<script>
import axios from 'axios'
import {API_getUserInfoURl, API_uploadFileURL, API_updateUserInfoURl} from '../../constants/index.js'
import $ from 'jquery'
import ajaxSubmit from '../../../static/js/jquery.form.js'
export default {
    name: 'updateUserInfo',
    data() {
      // 检验店铺名
      var validateShopname = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入姓名'));
        }
        callback()
      }
      var validateUserimg = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请上传头像'));
        }
        callback()
      }
      return {
        form: {
          username: '',
          userimg: ''
        },
        rules: {
          username: [
            { validator: validateShopname, trigger: 'blur' }
          ],
          userimg: [
            { validator: validateUserimg, trigger: 'blur' }
          ]
        }
      }
    },
    created() {
         //信息加入param
        axios({
            method:'post',
            url:API_getUserInfoURl,
        })
        .then((response) => {
            console.log(response.data)
            if(response.data.code == 0) {
                this.form.username = response.data.data.username
                this.form.userimg = response.data.data.userimg
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
      uploadPic (obj) {
          var options = {
            contentType:"multipart/form-data",
            url: API_uploadFileURL,
            type: "post",
            dataType: "json",
            success: (result) => {
              this.form.userimg = result.data;
            },
            error: (XMLHttpRequest, textStatus, errorThrown) => {
              alert("服务器出错，上传图片失败！")
            }
          }
          $("#pictureForm").ajaxSubmit(options)
          console.log("点击上传后的图片"+this.form.userimg)
      },
      submitForm(formName) {
         //信息加入param
        var param = new FormData()
        param.append('username',this.form.username)
        param.append('userimg',this.form.userimg)
        this.$refs[formName].validate((valid) => {
          if (valid) {
            axios({
              method:'post',
              url:API_updateUserInfoURl,
              data: param
            })
              .then((response) => {
                console.log(response.data)
                if(response.data.code == 0) {
                  this.$message({
                    message: response.data.msg,
                    type: 'success'
                  });
                  this.$router.push('/userCenter')
                  location.reload()
                }
                else if(response.data.code == 1) {
                  this.$message({
                    message: response.data.msg,
                    type: 'warning'
                  });
                }else {
                  this.$message.error('修改店铺信息失败，请稍后重试');
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
