<template>
  <div class="addTripContainer">
    <div class="addTripknow">发布部落活动</div>
    <el-form ref="form" :rules="rules" :model="form" label-width="140px">
      <el-form-item label="标题" prop="clubactivitytitle">
        <el-input v-model="form.clubactivitytitle"></el-input>
      </el-form-item>
      <el-form-item label="内容" prop="clubactivitycontent">
        <el-input v-model="form.clubactivitycontent"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm('form')">提交</el-button>
      </el-form-item>
    </el-form>
    <el-form id="pictureForm" method="POST" enctype="multipart/form-data">
      <el-form-item label="图片">
        <input class="uploadInput" id="fileUpload" name="fileUpload" @change="uploadPic(this)" accept="image/gif,image/jpeg,image/jpg,image/png,image/svg" type="file"/>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import axios from 'axios'
import { API_addActivityURL } from '../../constants/index.js'
import $ from 'jquery'
import ajaxSubmit from '../../../static/js/jquery.form.js'
export default {
    name: 'addDiary',
    data() {
      // 请输入动态标题
      var validateclubactivitytitle = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入动态标题'));
        }
          callback()
      }
      // 请输入动态内容
      var validateclubactivitycontent = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入动态内容'));
        } 
          callback()
      }
        return {
          value1:'',
          form: {
            clubactivitytitle: '',
            clubactivitycontent: '',
            clubactivityimg: ''
          },
          rules: {
            clubactivitytitle: [
              { validator: validateclubactivitytitle, trigger: 'blur' }
            ],
            clubactivitycontent: [
              { validator: validateclubactivitycontent, trigger: 'blur' }
            ]
          }
        }
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
              this.form.clubactivityimg = result.data;
            },
            error: (XMLHttpRequest, textStatus, errorThrown) => {
              alert("服务器出错，上传图片失败！")
            }
          }
          $("#pictureForm").ajaxSubmit(options)
          console.log("点击上传后的图片"+this.form.clubactivityimg)
        // }
      },
      submitForm(formName) {
        this.$refs[formName].validate((valid) => {
        if (valid) {
            //信息加入param       
          var params = new FormData()
          params.append('clubid',this.getCookie("clubid"));
          params.append('clubactivitytitle',this.form.clubactivitytitle);
          params.append('clubactivitycontent', this.form.clubactivitycontent);
          axios({
            method: 'post',
            url:API_addActivityURL,
            data: params
          }).then((response) => {
            console.log(response.data)
                if(response.data.code == 0) {
                  this.$router.push('/clubManage')
                }else if(response.data.code == 1) {
                  this.$message.error(response.data.msg);
                }
                else {
                  this.$message.error('添加部落活动失败，请稍后重试');
                }
            })
          .catch((err) => {
            console.log(err)
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
.addTripContainer {
    width: 100%;
    display: flex;
    flex-direction: column;
    justify-content: center;
}
.addTripknow {
  font-size: 1.5rem;
  margin-top: 3rem;
  margin-bottom: 2rem;
}
.el-form {
    width: 30rem;
    margin: 0 auto;
}
.el-upload__tip {
  padding-left: 1rem;
}
</style>
