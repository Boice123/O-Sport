<template>
  <div class="clubContainer">
      <div class="createClub">
          <div class="createClubBox">
            <el-form ref="form" :rules="rules" :model="form" label-width="80px">
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
                <el-form-item>
                    <el-button type="primary" @click="submitForm('form')">创建部落</el-button>
                </el-form-item>
            </el-form>
            <el-form id="pictureForm" method="POST" enctype="multipart/form-data">
                <el-form-item label="部落头像">
                    <input class="uploadInput" id="fileUpload" name="fileUpload" @change="uploadPic(this)" accept="image/gif,image/jpeg,image/jpg,image/png,image/svg" type="file"/>
                </el-form-item>
            </el-form>
          </div>    
      </div>
  </div>
</template>

<script>
import axios from 'axios'
import { API_saveClubURL ,API_uploadFileURL, API_getClubInfoURl} from '../../constants/index.js'
  export default {
    name: 'createclub',
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
      var validateShopname = (rule, value, callback) => {
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
          ]
        //   shopname: [
        //     { validator: validateShopname, trigger: 'blur' }
        //   ]
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
          console.log("点击上传后的图片"+this.form.clubimg)
        // }
      },
      submitForm(formName,event) {
         //信息加入param
        var param = new FormData()

        param.append('clubname',this.form.clubname)
        param.append('clubtab',this.form.clubtab)
        // param.append('clubhead',this.form.clubhead)
        this.$refs[formName].validate((valid) => {
          if (valid) {
            axios({
              method: 'post',
              url: API_saveClubURL,
              // headers: {'Content-Type': 'multipart/form-data'},
              data: param
            })
              .then((response) => {
                console.log(response.data)
                if(response.data.code == 0) {
                  this.$message({
                    message: response.data.msg,
                    type: 'success'
                  });
                  //查看该用户是否有自己管理的club
                  var params = new URLSearchParams();
                  params.append('clubowner',this.getCookie('user_userid'));
                  axios({
                      method:'post',
                      url:API_getClubInfoURl,
                      params
                  })
                  .then((res) => {
                      console.log(res.data)
                      if(res.data.code == 0) {
                          this.setCookie('clubid', res.data.data.clubid, 2)
                      }
                  }).catch((err) => {
                      console.log(err)
                  })
                  this.$router.push('/club')
                }
                else if(response.data.code == 1) {
                  this.$message({
                    message: response.data.msg,
                    type: 'warning'
                  });
                }else {
                  this.$message.error('创建部落验证失败，请稍后重试');
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
.createClub {
  width: 100%;
  height: 33rem;
  background: url("../../assets/images/signupBgc.jpg");
  background-size: cover;
  position: relative;
}
.createClubBox {
  border-radius: 1rem;
  position: absolute;
  left: 60%;
  margin-top: 8rem;
  padding-top: 4rem;
  padding-right: 4rem;
  width: 17rem;
  background: #000;
  opacity: 0.9;
}
</style>