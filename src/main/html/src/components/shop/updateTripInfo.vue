<template>
  <div class="addTripContainer">
    <div class="addTripknow">修改报团出行活动</div>
    <el-form ref="form" :rules="rules" :model="form" label-width="140px">
      <el-form-item label="出团名称" prop="tripname">
        <el-input v-model="form.tripname"></el-input>
      </el-form-item>
      <el-form-item label="出团描述" prop="tripdescription">
        <el-input v-model="form.tripdescription"></el-input>
      </el-form-item>
      <el-form-item label="注意事项" prop="tripnotice">
        <el-input v-model="form.tripnotice"></el-input>
      </el-form-item>
      <el-form-item label="出团价钱" prop="tripprice">
        <el-input v-model="form.tripprice"></el-input>
      </el-form-item>
      <el-form-item label="最大人数限制" prop="maxpeople">
        <el-input v-model="form.maxpeople"></el-input>
      </el-form-item>
      <el-form-item label="出团图片" prop="shopimg">
        <img class="shopimg" :src="form.tripimg"/>
        <div class="shopimg">
        </div>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm('form')">提交</el-button>
      </el-form-item>
    </el-form>
    <el-form id="pictureForm" method="POST" enctype="multipart/form-data">
      <el-form-item label="出团图片">
        <input class="uploadInput" id="fileUpload" name="fileUpload" @change="uploadPic(this)" accept="image/gif,image/jpeg,image/jpg,image/png,image/svg" type="file"/>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import axios from 'axios'
import { API_uploadFileURL, API_getTripInfoURl, API_updateTripURL } from '../../constants/index.js'
import bus from '../../utils/passValue'
import $ from 'jquery'
import ajaxSubmit from '../../../static/js/jquery.form.js'
export default {
    name: 'addtrip',
    data() {
      // 价钱
      var validateTripprice = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入出团价格'));
        } else if (value <=0 ) {
            callback(new Error('请输入不少于0的出团价格'));
        }else {
          if (!/\d+(,\d+)*/.test(this.form.tripprice)) {
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
      // 出团价格
      var validateTripimg = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入出团价格'));
        }
        callback()
      }
      // 出团名称
      var validateTripname = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入出团名称'));
        }
        callback()
      }
      // 出团描述
      var validateTripdescription = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入出团描述'));
        }
        callback()
      }
       // 出团注意事项
      var validateTripnotice = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入出团注意事项'));
        }
        callback()
      }
        return {
          form: {
            tripId: '',
            tripimg: '',
            tripname: '',
            maxpeople: 0,
            tripdescription: '',
            tripnotice: '',
            tripprice: 0,
          },
          rules: {
            tripprice: [
              { validator: validateTripprice, trigger: 'blur' }
            ],
            tripimg: [
              { validator: validateTripimg, trigger: 'blur' }
            ],
            maxpeople: [
              { validator: validateMaxpeople, trigger: 'blur' }
            ],
            tripdescription: [
              { validator: validateTripdescription, trigger: 'blur' }
            ],
            tripnotice: [
              { validator: validateTripnotice, trigger: 'blur' }
            ],
            tripname: [
              { validator: validateTripname, trigger: 'blur' }
            ]
          }
        }
    },
    created() {
        this.getTripInfo()
    },
    watch: {
      '$route' (to, from) {
        if(to.path == "/shop/updateTripInfo") {
          console.log("updateTripInfo  watch监听中："+this.$route.params.tripid)
          this.getTripInfo()
        }       
      }
    },
    methods: {
      //获取Trip的信息
      getTripInfo () { 
        var params = new URLSearchParams()
        params.append('tripid', this.$route.params.tripid)
        axios({
            method:'post',
            url:API_getTripInfoURl,
            params
        })
        .then((response) => {
            console.log(response.data)
            if(response.data.code == 0) {
                this.form.tripimg = response.data.data.tripimg
                this.form.tripname = response.data.data.tripname
                this.form.maxpeople = response.data.data.maxpeople
                this.form.tripdescription = response.data.data.tripdescription
                this.form.tripnotice = response.data.data.tripnotice
                this.form.tripprice = response.data.data.tripprice
            }else if(response.data.code == 1) {
                this.$message({
                    message: response.data.msg,
                    type: 'warning'
                }); 
            }else {
                this.$message.error('获取户外出团信息失败，请稍后重试');
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
              this.form.tripimg = result.data;
            },
            error: (XMLHttpRequest, textStatus, errorThrown) => {
              alert("服务器出错，上传图片失败！")
            }
          }
          $("#pictureForm").ajaxSubmit(options)
          console.log("点击上传后的图片"+this.form.tripimg)
        // }
      },
      //修改Trip信息
      submitForm(formName) {
        var params = new FormData()
        params.append('tripid', this.$route.params.tripid)
        params.append('tripname',this.form.tripname);
        params.append('tripdescription', this.form.tripdescription);
        params.append('tripnotice', this.form.tripnotice);
        params.append('tripprice', this.form.tripprice);
        params.append('maxpeople', this.form.maxpeople);
        params.append('tripimg', this.form.tripimg);
        this.$refs[formName].validate((valid) => {
          if (valid) {
            axios({
              method:'post',
              url:API_updateTripURL,
              data: params
            })
              .then((response) => {
                console.log(response.data)
                if(response.data.code == 0) {
                  this.$message({
                    message: response.data.msg,
                    type: 'success'
                  });
                  this.$router.push('/shop/shopmanagetrip')
                }
                else if(response.data.code == 1) {
                  this.$message({
                    message: response.data.msg,
                    type: 'warning'
                  });
                }else {
                  this.$message.error('修改报团出团信息失败，请稍后重试');
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
