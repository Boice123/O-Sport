<template>
  <div class="addTripContainer">
    <div class="addTripknow">发布部落攻略</div>
    <el-form ref="form" :rules="rules" :model="form" label-width="140px">
      <el-form-item label="标题" prop="clubactivitytitle">
        <el-input v-model="form.clubactivitytitle"></el-input>
      </el-form-item>
      <el-form-item label="内容" prop="clubactivitycontent">
        <el-input v-model="form.clubactivitycontent" type="textarea"></el-input>
      </el-form-item>
      <el-form id="pictureForm" class="pictureForm1" method="POST" enctype="multipart/form-data">
        <el-form-item label="封面图">
          <input class="uploadInput" id="fileUpload" name="fileUpload" @change="uploadPic(this)" accept="image/gif,image/jpeg,image/jpg,image/png,image/svg" type="file"/>
        </el-form-item>
      </el-form>
       <el-form-item label="" prop="clubactivityimg">
        <img v-if="form.clubactivityimg != ''" class="shopimg" :src="form.clubactivityimg"/>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm('form')">提交</el-button>
      </el-form-item>
    </el-form>
   
  </div>
</template>

<script>
import axios from 'axios'
import { API_addActivityURL, API_uploadFileURL } from '../../constants/index.js'
import $ from 'jquery'
import ajaxSubmit from '../../../static/js/jquery.form.js'
export default {
    name: 'addActivity',
    data() {
      // 请输入攻略标题
      var validateclubactivitytitle = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入攻略标题'));
        }
          callback()
      }
      // 请输入攻略内容
      var validateclubactivitycontent = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入攻略内容'));
        } 
          callback()
      }
      var validateclubactivityimg = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请选择攻略封面'));
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
            ],
            clubactivityimg: [
               { validator: validateclubactivityimg, trigger: 'blur' }
            ]
          }
        }
    },
    methods: {
      uploadPic (obj) {
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
      },
      submitForm(formName) {
        this.$refs[formName].validate((valid) => {
        if (valid) {
            //信息加入param       
          var params = new FormData()
          params.append('clubid',this.getCookie("clubid"));
          params.append('clubactivitytitle',this.form.clubactivitytitle);
          params.append('clubactivitycontent', this.form.clubactivitycontent);
           params.append('clubactivityimg', this.form.clubactivityimg);
          axios({
            method: 'post',
            url:API_addActivityURL,
            data: params
          }).then((response) => {
            console.log(response.data)
                if(response.data.code == 0) {
                  this.$router.push({name: 'adminClubActivity'})
                }else if(response.data.code == 1) {
                  this.$message.error(response.data.msg);
                }
                else {
                  this.$message.error('添加部落攻略失败，请稍后重试');
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
.shopimg {
    width: 5rem;
    height: 6rem;
    border: 1px solid #e1e0e0;
    border-radius: .5rem;
    background: url("../../assets/images/addimg.svg") no-repeat center;
    background-size: 2rem 2rem;
}
.pictureForm1 {
  margin-left: 6rem;
}
</style>
