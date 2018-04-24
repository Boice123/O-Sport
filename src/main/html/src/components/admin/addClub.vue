<template>
  <div class="clubContainer">
    <div class="addTripknow">新增部落</div>
    <el-form ref="form" :rules="rules" :model="form" label-width="140px">
        <el-form-item label="部落名称" prop="clubname">
            <el-input type="text" v-model="form.clubname"></el-input>
        </el-form-item>
        <el-form-item label="部落标签" prop="clubtab">
            <el-select v-model="form.clubtab" placeholder="请选择">
                <el-option
                v-for="item in options"
                :key="item.value"
                :label="item.label"
                :value="item.value">
                </el-option>
            </el-select>
        </el-form-item>
        <el-form id="pictureForm" method="POST" enctype="multipart/form-data">
          <el-form-item label="部落头像">
              <input class="uploadInput" id="fileUpload" name="fileUpload" @change="uploadPic(this)" accept="image/gif,image/jpeg,image/jpg,image/png,image/svg" type="file"/>
          </el-form-item>
        </el-form>
        <el-form-item label="" prop="clubimg">
          <img class="shopimg" :src="form.clubimg"/>
        </el-form-item>
        <el-form-item>
            <el-button type="primary" @click="submitForm('form')">创建部落</el-button>
        </el-form-item>
    </el-form>          
  </div>    
</template>

<script>
import axios from 'axios'
import $ from 'jquery'
import { API_saveClubURL ,API_uploadFileURL } from '../../constants/index.js'
  export default {
    name: 'addclub',
    data() {
      // 部落名称
      var validateClubname = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入部落名称'))
        } 
        callback()
      }
      // 部落标签
      var validateClubTab = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入部落标签'));
        }
        callback()
      };
      //部落头像
      var validateClubimg = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请选择部落头像'));
        }
        callback()
      };
      return {
        form: {
          clubname: '',
          clubtab: '',
          clubimg: ''
        },
        rules: {
          clubname: [
            { validator: validateClubname, trigger: 'blur' }
          ],
          clubtab: [
            { validator: validateClubTab, trigger: 'blur' }
          ],
          clubimg: [
            { validator: validateClubimg, trigger: 'blur' }
          ]
        },
        options: [{
          value: '自行车',
          label: '自行车'
        }, {
          value: '徒步',
          label: '徒步'
        }, {
          value: '潜水',
          label: '潜水'
        }, {
          value: '露营',
          label: '露营'
        }, {
          value: '马拉松',
          label: '马拉松'
        },
        {
          value: '登山',
          label: '登山'
        },
        {
          value: '野外定向',
          label: '野外定向'
        },
        {
          value: '轮滑',
          label: '轮滑'
        },
        {
          value: '其他',
          label: '其他'
        }],
      }
    },
    created () {
    },
    methods: {
      changeClubChoose(text) {
          this.clubChoose = text
      },
      changeClubCover(text) {
          this.clubcover = text
      },
      uploadPic (obj) {
          var options = {
            contentType:"multipart/form-data",
            url: API_uploadFileURL,
            type: "post",
            dataType: "json",
            success: (result) => {
              this.form.clubimg = result.data;
            },
            error: (XMLHttpRequest, textStatus, errorThrown) => {
              alert("服务器出错，上传图片失败！")
            }
          }
          $("#pictureForm").ajaxSubmit(options)
          console.log("点击上传后的图片"+this.form.clubimg)
      },
      submitForm(formName,event) {
        var param = new FormData()
        param.append('clubname',this.form.clubname)
        param.append('clubtab',this.form.clubtab)
        param.append('clubimg',this.form.clubimg)
        this.$refs[formName].validate((valid) => {
          if (valid) {
            axios({
              method: 'post',
              url: API_saveClubURL,
              data: param
            })
              .then((response) => {
                console.log(response.data)
                if(response.data.code == 0) {
                  this.$message({
                    message: response.data.msg,
                    type: 'success'
                  })
                }
                this.$router.push('/admin/adminClub')
              }).catch((err) => {
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
.pictureForm {
  padding-left: 1rem;
}
</style>