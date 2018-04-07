<template>
  <div class="updateshopContainer">
    <div class="updateshopknow">修改部落信息</div>
    <el-form ref="form" :rules="rules" :model="form" label-width="80px">
      <el-form-item label="部落名称" prop="clubactivitytitle">
        <el-input v-model="form.clubname" :value="form.clubname"></el-input>
      </el-form-item>
      <!-- <el-form-item label="活动图片" prop="clubactivityimg">
        <img class="shopimg" :src="form.clubactivityimg"/>
        <div class="shopimg">
        </div>
      </el-form-item> -->
      <el-form-item>
        <el-button type="primary" @click="submitForm('form')">修改</el-button>
      </el-form-item>
    </el-form>

    <el-form id="pictureForm" method="POST" enctype="multipart/form-data">
      <el-form-item label="活动图片">
        <input class="uploadInput" id="fileUpload" name="fileUpload" @change="uploadPic(this)" accept="image/gif,image/jpeg,image/jpg,image/png,image/svg" type="file"/>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import axios from 'axios'
import {API_getClubURl, API_uploadFileURL, API_updateClubURL} from '../../constants/index.js'
import $ from 'jquery'
import ajaxSubmit from '../../../static/js/jquery.form.js'
export default {
    name: 'openshop',
    data() {
      // 检验店铺名
      var validateClubname = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入部落名称'));
        }
        callback()
      };
      return {
        form: {
          clubname: '',
          clubimg: ''
        },
        rules: {
          clubname: [
            { validator: validateClubname, trigger: 'blur' }
          ]
        }
      }
    },
    created() {
         //信息加入param
        var params = new URLSearchParams();
        params.append('clubid',this.getCookie('clubid'))
        axios({
            method:'post',
            url:API_getClubURl,
            params
        })
        .then((response) => {
            console.log(response.data)
            if(response.data.code == 0) {
              this.form.clubname = response.data.data.clubname
            }else if(response.data.code == 1) {
                this.$message({
                    message: response.data.msg,
                    type: 'warning'
                });
            }else {
                this.$message.error('获取部落活动信息失败，请稍后重试');
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
        param.append('clubid',this.getCookie('clubid'))
        param.append('clubname',this.form.clubname)
        this.$refs[formName].validate((valid) => {
          if (valid) {
            axios({
              method:'post',
              url:API_updateClubURL,
              data: param
            })
              .then((response) => {
                console.log(response.data)
                if(response.data.code == 0) {
                  this.$message({
                    message: response.data.msg,
                    type: 'success'
                  });
                  this.$router.push('/clubManage')
                }
                else if(response.data.code == 1) {
                  this.$message({
                    message: response.data.msg,
                    type: 'warning'
                  });
                }else {
                  this.$message.error('修改部落活动信息失败，请稍后重试');
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
