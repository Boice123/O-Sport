<template>
  <div class="updateshopContainer">
    <div class="updateshopknow">修改攻略信息</div>
    <el-form ref="form" :rules="rules" :model="form" label-width="80px">
      <el-form-item label="攻略标题" prop="clubactivitytitle">
        <el-input v-model="form.clubactivitytitle" :value="form.clubactivitytitle"></el-input>
      </el-form-item>
      <el-form-item label="攻略内容" prop="clubactivitycontent">
        <el-input v-model="form.clubactivitycontent" :value="form.clubactivitycontent" type="textarea"></el-input>
      </el-form-item>
       <el-form id="pictureForm" method="POST" enctype="multipart/form-data">
        <el-form-item label="攻略封面">
          <input class="uploadInput" id="fileUpload" name="fileUpload" @change="uploadPic(this)" accept="image/gif,image/jpeg,image/jpg,image/png,image/svg" type="file"/>
        </el-form-item>
      </el-form>
      <el-form-item label="" prop="clubactivityimg">
        <img class="shopimg" :src="form.clubactivityimg"/>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm('form')">修改</el-button>
      </el-form-item>
    </el-form>

   
  </div>
</template>

<script>
import axios from 'axios'
import {API_getClubActivityByidURl, API_uploadFileURL, API_updateClubActivityURL} from '../../constants/index.js'
import $ from 'jquery'
import ajaxSubmit from '../../../static/js/jquery.form.js'
export default {
    name: 'openshop',
    data() {
      // 检验店铺名
      var validateClubactivitytitle = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入部落攻略标题'));
        }
        callback()
      }
      var validateClubactivitycontent = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入部落攻略内容'));
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
        form: {
          clubactivitytitle: '',
          clubactivitycontent: '',
          clubactivityimg: ''
        },
        rules: {
          clubactivitytitle: [
            { validator: validateClubactivitytitle, trigger: 'blur' }
          ],
          clubactivitycontent: [
            { validator: validateClubactivitycontent, trigger: 'blur' }
          ],
          clubactivityimg: [
               { validator: validateclubactivityimg, trigger: 'blur' }
            ]
        }
      }
    },
    created() {
         //信息加入param
        var params = new URLSearchParams();
        params.append('clubactivityid',this.$route.params.clubactivityid)
        axios({
            method:'post',
            url:API_getClubActivityByidURl,
            params
        })
        .then((response) => {
            console.log(response.data)
            if(response.data.code == 0) {
              this.form.clubactivitytitle = response.data.data.clubactivitytitle
              this.form.clubactivitycontent = response.data.data.clubactivitycontent
              this.form.clubactivityimg = response.data.data.clubactivityimg 
            }else if(response.data.code == 1) {
                this.$message({
                    message: response.data.msg,
                    type: 'warning'
                });
            }else {
                this.$message.error('获取部落攻略信息失败，请稍后重试');
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
              this.form.clubactivityimg = result.data;
            },
            error: (XMLHttpRequest, textStatus, errorThrown) => {
              alert("服务器出错，上传图片失败！")
            }
          }
          $("#pictureForm").ajaxSubmit(options)
      },
      submitForm(formName) {
        var param = new FormData()
        param.append('clubactivityid',this.$route.params.clubactivityid)
        param.append('clubactivitytitle',this.form.clubactivitytitle)
        param.append('clubactivitycontent',this.form.clubactivitycontent)
        param.append('clubactivityimg', this.form.clubactivityimg);
        this.$refs[formName].validate((valid) => {
          if (valid) {
            axios({
              method:'post',
              url:API_updateClubActivityURL,
              data: param
            })
              .then((response) => {
                console.log(response.data)
                if(response.data.code == 0) {
                  this.$message({
                    message: response.data.msg,
                    type: 'success'
                  });
                  this.$router.push({name: 'adminClubActivity'})
                }
                else if(response.data.code == 1) {
                  this.$message({
                    message: response.data.msg,
                    type: 'warning'
                  });
                }else {
                  this.$message.error('修改部落攻略信息失败，请稍后重试');
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
