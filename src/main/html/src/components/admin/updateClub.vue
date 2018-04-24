<template>
  <div class="updateshopContainer">
    <div class="updateshopknow">修改部落信息</div>
    <el-form ref="form" :rules="rules" :model="form" label-width="140px">
      <el-form-item label="部落名称" prop="clubactivitytitle">
        <el-input v-model="form.clubname" :value="form.clubname"></el-input>
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
      <el-form-item label="活动图片" prop="clubactivityimg">
        <img class="shopimg" :src="form.clubimg"/>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm('form')">修改</el-button>
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
       // 检验图片
      var validateClubimg = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请选择部落图片'));
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
              this.form.clubimg = response.data.data.clubimg
              this.form.clubtab = response.data.data.clubtab
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
      submitForm(formName) {
         //信息加入param
        var param = new FormData()
        param.append('clubid',this.getCookie('clubid'))
        param.append('clubname',this.form.clubname)
        param.append('clubimg',this.form.clubimg)
        param.append('clubtab', this.form.clubtab)
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
                  this.$router.push('/admin/adminClub')
                }
                else if(response.data.code == 1) {
                  this.$message({
                    message: response.data.msg,
                    type: 'warning'
                  });
                }else {
                  this.$message.error('修改部落信息失败，请稍后重试');
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
