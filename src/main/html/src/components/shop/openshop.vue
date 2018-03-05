<template>
  <div class="openshopContainer">
    <div class="openshopknow">在本网站开店需先完成实名认证和缴纳保证金，如无问题请遵循以下的步骤进行</div>
    <el-form id="myForm" ref="form" :rules="rules" :model="form" label-width="80px">
      <el-form-item label="身份证号" prop="realid">
        <el-input name="realid" v-model="form.realid"></el-input>
      </el-form-item>
      <el-form-item label="真实姓名" prop="realname">
        <el-input name="realname" v-model="form.realname"></el-input>
      </el-form-item>
      <el-form-item label="店铺名称" prop="shopname">
        <el-input name="shopname" v-model="form.shopname"></el-input>
      </el-form-item>
      <el-form-item label="店铺头像">
        <input id="file" type="file" name="shopimg" @change="getFile($event)"/>
      </el-form-item>
      <el-form-item>
        <!-- <el-button type="primary" @click="submitForm('form')">缴纳保证金</el-button> -->
        <button type="button" @click="submitForm('form',$event)">缴纳保证金 </button>
      </el-form-item>
    </el-form>  
  </div>
</template>

<script>
import {API_saveShopURL,API_getImgPathURL} from '../../constants/index.js'
import axios from 'axios'
import $ from 'jquery'
export default {
    name: 'openshop',
    data() {
         // 身份证号
      var validateRealid = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入身份证号'));
        } else {
          if (!/(^[1-9]\d{5}(18|19|([23]\d))\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$)|(^[1-9]\d{5}\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{2}$)/.test(this.form.realid)) {
            callback(new Error('请输入正确的身份证号'));
          }
          callback()
        }
      };
      // 检验姓名
      var validateRealname = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入姓名'));
        }
        callback()
      };
      var validateShopname = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入店铺名称'));
        }
        callback()
      };
      return {
        form: {
          realid: '',
          realname: '',
          shopname: '',
          shopimg: ''
        },
        rules: {
          realid: [
            { validator: validateRealid, trigger: 'blur' }
          ],
          realname: [
            { validator: validateRealname, trigger: 'blur' }
          ],
          shopname: [
            { validator: validateShopname, trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      getFile(event) {
        this.form.shopimg = event.target.files[0]
      } ,     
      submitForm(formName,event) {
         //信息加入param
        // this.shopimg = event.target.files[0]
        // console.log(this.shopimg)
        event.preventDefault()
        // var param = new FormData(document.getElementById("myForm"))
        var param = new FormData()
        
        param.append('realid',this.form.realid)
        param.append('realname',this.form.realname)
        param.append('shopname',this.form.shopname)
        param.append('shopimg',document.getElementById("file").files[0])
        console.log(param)
        this.$refs[formName].validate((valid) => {
          if (valid) {  
            axios({
              method:'post',
              url:API_saveShopURL,
              headers: {'Content-Type': 'multipart/form-data'},
              data: param
            })
              .then((response) => {
                console.log(response.data)
                if(response.data.code == 0) {
                  this.$message({
                    message: response.data.msg,
                    type: 'success'
                  }); 
                  this.setCookie('shop_id', response.data.data.shopid, 2)
                  this.$store.commit('shopidChange',response.data.data.shopid)
                  this.$router.push('/')
                }
                else if(response.data.code == 1) {
                  this.$message({
                    message: response.data.msg,
                    type: 'warning'
                  }); 
                }else {
                  this.$message.error('开店验证失败，请稍后重试');
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
.openshopContainer {
    width: 100%;
    display: flex;
    flex-direction: column;
    justify-content: center;
}
.openshopknow {
  font-size: 1.5rem;
  margin-top: 3rem;
  margin-bottom: 2rem;
}
.el-form {
    width: 30rem;
    margin: 0 auto;
}

.avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }
  .avatar-uploader .el-upload:hover {
    border-color: #409EFF;
  }
  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    line-height: 178px;
    text-align: center;
  }
  .avatar {
    width: 178px;
    height: 178px;
    display: block;
  }
</style>
