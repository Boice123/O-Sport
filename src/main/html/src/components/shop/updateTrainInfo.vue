<template>
  <div class="addtrainContainer">
    <div class="addtrainknow">修改报团出行活动</div>
    <el-form ref="form" :rules="rules" :model="form" label-width="140px">
      <el-form-item label="户外培训名称" prop="trainname">
        <el-input v-model="form.trainname"></el-input>
      </el-form-item>
      <el-form-item label="户外培训描述" prop="traindescription">
        <el-input v-model="form.traindescription"></el-input>
      </el-form-item>
      <el-form-item label="注意事项" prop="trainnotice">
        <el-input v-model="form.trainnotice"></el-input>
      </el-form-item>
      <el-form-item label="户外培训价钱" prop="trainprice">
        <el-input v-model="form.trainprice"></el-input>
      </el-form-item>
      <el-form-item label="最大人数限制" prop="maxpeople">
        <el-input v-model="form.maxpeople"></el-input>
      </el-form-item>
      <el-form-item label="户外培训图片" prop="shopimg">
        <img class="shopimg" :src="form.trainimg"/>
        <div class="shopimg">
        </div>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm('form')">提交</el-button>
      </el-form-item>
    </el-form>
    <el-form id="pictureForm" method="POST" enctype="multipart/form-data">
      <el-form-item label="户外培训图片">
        <input class="uploadInput" id="fileUpload" name="fileUpload" @change="uploadPic(this)" accept="image/gif,image/jpeg,image/jpg,image/png,image/svg" type="file"/>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import axios from 'axios'
import { API_uploadFileURL, API_getTrainInfoURl, API_updateTrainURL } from '../../constants/index.js'
import bus from '../../utils/passValue'
import $ from 'jquery'
import ajaxSubmit from '../../../static/js/jquery.form.js'
export default {
    name: 'addtrain',
    data() {
      // 价钱
      var validatetrainprice = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入户外培训价格'));
        } else if (value <=0 ) {
            callback(new Error('请输入不少于0的户外培训价格'));
        }else {
          if (!/\d+(,\d+)*/.test(this.form.trainprice)) {
            callback(new Error('请输入只限于数字的文字'));
          }
          callback()
        }
      }
      // 最大人数限制
      var validateMaxpeople = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入最大人数限制'));
        } else if (value <=0 ) {
            callback(new Error('请输入不少于0的最大人数限制'));
        }else {
          if (!/\d+(,\d+)*/.test(this.form.maxpeople)) {
            callback(new Error('请输入只限于数字的文字'));
          }
          callback()
        }
      }
      // 户外培训价格
      var validatetrainimg = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入户外培训价格'));
        }
        callback()
      }
      // 户外培训名称
      var validatetrainname = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入户外培训名称'));
        }
        callback()
      }
      // 户外培训描述
      var validatetraindescription = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入户外培训描述'));
        }
        callback()
      }
       // 户外培训注意事项
      var validatetrainnotice = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入户外培训注意事项'));
        }
        callback()
      }
        return {
          form: {
            trainId: '',
            trainimg: '',
            trainname: '',
            maxpeople: 0,
            traindescription: '',
            trainnotice: '',
            trainprice: 0,
          },
          rules: {
            trainprice: [
              { validator: validatetrainprice, trigger: 'blur' }
            ],
            trainimg: [
              { validator: validatetrainimg, trigger: 'blur' }
            ],
            maxpeople: [
              { validator: validateMaxpeople, trigger: 'blur' }
            ],
            traindescription: [
              { validator: validatetraindescription, trigger: 'blur' }
            ],
            trainnotice: [
              { validator: validatetrainnotice, trigger: 'blur' }
            ],
            trainname: [
              { validator: validatetrainname, trigger: 'blur' }
            ]
          }
        }
    },
    created() {
        this.gettrainInfo()
    },
    watch: {
      '$route' (to, from) {
        if(to.path == "/shop/updatetrainInfo") {
          console.log("updatetrainInfo  watch监听中："+this.$route.params.trainid)
          this.gettrainInfo()
        }       
      }
    },
    methods: {
      //获取train的信息
      gettrainInfo () { 
        var params = new URLSearchParams()
        params.append('trainid', this.$route.params.trainid)
        axios({
            method:'post',
            url:API_getTrainInfoURl,
            params
        })
        .then((response) => {
            console.log(response.data)
            if(response.data.code == 0) {
                this.form.trainimg = response.data.data.trainimg
                this.form.trainname = response.data.data.trainname
                this.form.maxpeople = response.data.data.maxpeople
                this.form.traindescription = response.data.data.traindescription
                this.form.trainnotice = response.data.data.trainnotice
                this.form.trainprice = response.data.data.trainprice
            }else if(response.data.code == 1) {
                this.$message({
                    message: response.data.msg,
                    type: 'warning'
                }); 
            }else {
                this.$message.error('获取户外户外培训信息失败，请稍后重试');
            }        
        }).catch((err) => {
            console.log(err)
        })
      },
      uploadPic (obj) {
        // if( this.checkFile(obj) ){
          var options = {
            contentType:"multipart/form-data",
            url: API_uploadFileURL,
            type: "post",
            dataType: "json",
            success: (result) => {
              this.form.trainimg = result.data;
            },
            error: (XMLHttpRequest, textStatus, errorThrown) => {
              alert("服务器出错，上传图片失败！")
            }
          }
          $("#pictureForm").ajaxSubmit(options)
          console.log("点击上传后的图片"+this.form.trainimg)
        // }
      },
      //修改train信息
      submitForm(formName) {
        var params = new FormData()
        params.append('trainid', this.$route.params.trainid)
        params.append('trainname',this.form.trainname);
        params.append('traindescription', this.form.traindescription);
        params.append('trainnotice', this.form.trainnotice);
        params.append('trainprice', this.form.trainprice);
        params.append('maxpeople', this.form.maxpeople);
        params.append('trainimg', this.form.trainimg);
        this.$refs[formName].validate((valid) => {
          if (valid) {
            axios({
              method:'post',
              url:API_updateTrainURL,
              data: params
            })
              .then((response) => {
                console.log(response.data)
                if(response.data.code == 0) {
                  this.$message({
                    message: response.data.msg,
                    type: 'success'
                  });
                  this.$router.push('/shop/shopmanagetrain')
                }
                else if(response.data.code == 1) {
                  this.$message({
                    message: response.data.msg,
                    type: 'warning'
                  });
                }else {
                  this.$message.error('修改报团户外培训信息失败，请稍后重试');
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
.addtrainContainer {
    width: 100%;
    display: flex;
    flex-direction: column;
    justify-content: center;
}
.addtrainknow {
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
