<template>
  <div class="updateshopContainer">
    <div class="updateshopknow">修改店铺信息</div>
    <el-form ref="form" :rules="rules" :model="form" label-width="80px">
      <el-form-item label="店铺名称" prop="shopname">
        <el-input v-model="form.shopname" :value="form.shopname"></el-input>
      </el-form-item>
      <el-form id="pictureForm" method="POST" enctype="multipart/form-data">
      <el-form-item class="pictureForm" label="店铺头像">
        <input class="uploadInput" id="fileUpload" name="fileUpload" @change="uploadPic(this)" accept="image/gif,image/jpeg,image/jpg,image/png,image/svg" type="file"/>
      </el-form-item>
     </el-form>
      <el-form-item label="" prop="shopimg">
        <img class="shopimg" :src="form.shopimg"/>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm('form')">修改</el-button>
      </el-form-item>
    </el-form>

    
  </div>
</template>

<script>
import axios from 'axios'
import {API_getShopInfoURl, API_uploadFileURL, API_updateShopInfoURL} from '../../constants/index.js'
import $ from 'jquery'
import ajaxSubmit from '../../../static/js/jquery.form.js'
export default {
    name: 'openshop',
    data() {
      // 检验店铺名
      var validateShopname = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入姓名'));
        }
        callback()
      };
      return {
        form: {
          shopname: '',
          shopimg: ''
        },
        rules: {
          shopname: [
            { validator: validateShopname, trigger: 'blur' }
          ]
        }
      }
    },
    created() {
         //信息加入param
        console.log(this.getCookie('shop_id'))
        var params = new URLSearchParams();
        params.append('shopid',this.getCookie('shop_id'))
        axios({
            method:'post',
            url:API_getShopInfoURl,
            params
        })
        .then((response) => {
            console.log(response.data)
            if(response.data.code == 0) {
                this.form.shopname = response.data.data.shopname
                this.form.shopimg = response.data.data.shopimg
                console.log(this.shopimg)
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
        // if( this.checkFile(obj) ){
          var options = {
            contentType:"multipart/form-data",
            url: API_uploadFileURL,
            type: "post",
            dataType: "json",
            success: (result) => {
              this.form.shopimg = result.data;
            },
            error: (XMLHttpRequest, textStatus, errorThrown) => {
              alert("服务器出错，上传图片失败！")
            }
          }
          $("#pictureForm").ajaxSubmit(options)
          console.log("点击上传后的图片"+this.form.shopimg)
        // }
      },
      submitForm(formName) {
         //信息加入param
        var param = new FormData()
        param.append('shopname',this.form.shopname)
        param.append('shopimg',this.form.shopimg)
        this.$refs[formName].validate((valid) => {
          if (valid) {
            axios({
              method:'post',
              url:API_updateShopInfoURL,
              data: param
            })
              .then((response) => {
                console.log(response.data)
                if(response.data.code == 0) {
                  this.$message({
                    message: response.data.msg,
                    type: 'success'
                  });
                  this.$router.push('/shop')
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
.pictureForm {
  padding-left: 1rem;
}
</style>
