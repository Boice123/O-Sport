<template>
  <div class="addTripContainer">
    <div class="addTripknow">新增报团出行活动</div>
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
      <el-form-item label="每个最大人数限制" prop="maxpeople">
        <el-input v-model="form.maxpeople"></el-input>
      </el-form-item>
      <el-form-item label="选择地区" prop="region">
        <el-cascader
          expand-trigger="hover"
          :options="options"
          v-model="selectedOptions2"
          @change="handleChange">
        </el-cascader>
      </el-form-item>
      <el-form-item label="出行日期" prop="date">
        <el-date-picker
          v-model="form.triptime"
          :picker-options="pickerBeginDateBefore"
          format="yyyy 年 MM 月 dd 日"
          value-format="yyyy-MM-dd"
          placeholder="选择日期">
        </el-date-picker>
      </el-form-item>
      <el-form id="pictureForm" method="POST" enctype="multipart/form-data">
        <el-form-item  class="pictureForm" label="出团图片">
          <input class="uploadInput" id="fileUpload" name="fileUpload" @change="uploadPic(this)" accept="image/gif,image/jpeg,image/jpg,image/png,image/svg" type="file"/>
        </el-form-item>
      </el-form>
       <el-form-item label="" prop="tripimg">
        <img v-if="form.tripimg != ''" class="shopimg" :src="form.tripimg"/>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm('form')">提交</el-button>
      </el-form-item>
    </el-form>
    
  </div>
</template>

<script>
import axios from 'axios'
import regionList from '../../constants/regionList'
import { API_uploadFileURL, API_saveTripURL } from '../../constants/index.js'
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
      // 出团图片
      var validateTripimg = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请选择出团图片'));
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
      // 出行日期
      var validateTriptime = (rule, value, callback) => {
        if (value === '' || value === null) {
          callback(new Error('请选择出行日期'));
        }
        callback()
      }
        return {
          form: {
            tripimg: '',
            tripname: '',
            maxpeople: '',
            tripdescription: '',
            tripnotice: '',
            tripprice: '',
            tripprovice: '',
            tripcity: '',
            triptime: '',
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
            ],
            triptime: [
              { validator: validateTriptime, trigger: 'blur' }
            ]
          },
          options: [
          {
            value: '广东',
            label: '广东',
            children: [
              {
                value: '惠州',
                label: '惠州'
              }, 
              {
                value: '广州',
                label: '广州',
              },
              {
                value: "深圳",
                label: "深圳",
              }
            ]
          },
          {
            value: '湖南',
            label: '湖南',
            children: [
              {
                value: '长沙',
                label: '长沙'
              }
            ],
          },
          {
            value: '浙江',
            label: '浙江',
            children: [
              {
                value: '杭州',
                label: '杭州'
              },
              {
                value: '台州',
                label: '台州'
              }
            ],
          }
        ],
        selectedOptions: [],
        selectedOptions2: [],
        pickerBeginDateBefore:{
          disabledDate(time) {
            return time.getTime() < Date.now();
          }
        },
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
              this.form.tripimg = result.data;
            },
            error: (XMLHttpRequest, textStatus, errorThrown) => {
              alert("服务器出错，上传图片失败！")
            }
          }
          $("#pictureForm").ajaxSubmit(options)
          console.log("点击上传后的图片"+this.form.tripimg)
      },
      submitForm(formName) {
        this.$refs[formName].validate((valid) => {
        if (valid) {
          if (this.form.tripprovice == '' || this.form.tripcity == '') {
            this.$message({
                  message: "请选择完整地区",
                  type: 'warning'
              });
              return
          }
          var params = new FormData()
          params.append('tripname',this.form.tripname);
          params.append('tripdescription', this.form.tripdescription);
          params.append('tripnotice', this.form.tripnotice);
          params.append('tripprice', this.form.tripprice);
          params.append('maxpeople', this.form.maxpeople);
          params.append('tripimg', this.form.tripimg);
          params.append('tripprovice', this.form.tripprovice);
          params.append('tripcity', this.form.tripcity);
          params.append('triptime',this.form.triptime)
          //请求addTrip接口
          axios({
            method:'post',
            url:API_saveTripURL,
            data: params
          })
            .then((response) => {
              console.log(response.data)
              if(response.data.code == 0) {
                this.$message({
                  message: response.data.msg,
                  type: 'success'
                })
                this.$router.push('/admin/adminTrip')
              }else {
                this.$message.error('添加报团出团信息失败，请稍后重试');
              }
            }).catch((err) => {
              console.log(err)
            })
        } else {
          console.log('请确认填写的信息是否正确')
          return false;
        }
        })
      },
      handleChange(value) {
        console.log(value);
        this.form.tripprovice = value[0]
        this.form.tripcity = value[1]
        console.log(this.form.tripprovice)
        console.log(this.form.tripcity)
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
.pictureForm {
  margin-left: 4rem;
}
</style>
