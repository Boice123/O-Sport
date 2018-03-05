<template>
  <div class="updateshopContainer">
    <div class="updateshopknow">修改店铺信息</div>
    <el-form ref="form" :rules="rules" :model="form" label-width="80px">
      <el-form-item label="店铺名称" prop="shopname">
        <el-input v-model="form.shopname" value="{}"></el-input>
      </el-form-item>
      <el-form-item label="店铺图片" prop="shopimg">
        <div class="shopimg">
            <img src="{}"/>
        </div>
      </el-form-item>
      <!-- <el-form-item label="店铺标签" prop="shoplabel">
        <el-checkbox-group v-model="checkboxGroup" size="small">
        <el-checkbox label="自行车" border></el-checkbox>
        <el-checkbox label="划艇" border></el-checkbox>
        <el-checkbox label="徒步" border></el-checkbox>
        </el-checkbox-group>
      </el-form-item> -->
      <el-form-item>
        <el-button type="primary" @click="submitForm('form')">修改</el-button>
      </el-form-item>
    </el-form>  
  </div>
</template>

<script>
export default {
    name: 'openshop',
    data() {
      // 检验店铺名
      var validateShopname = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入姓名'));
        }
        callback()
      };
      return {
        form: {
          shopname: '',
          checkboxGroup: ['自行车']
        },
        rules: {
          shopname: [
            { validator: validateShopname, trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      submitForm(formName) {
         //信息加入param
        var params = new URLSearchParams();
        params.append('shopname',this.form.shopname);
        this.$refs[formName].validate((valid) => {
          if (valid) {
            axios({
              method:'post',
              url:API_signinURL,
              params: params
            })
              .then((response) => {
                console.log(response.data)
                if(response.data.code == 0) {
                  this.$message({
                    message: response.data.msg,
                    type: 'success'
                  }); 
                  this.$router.push('/')
                }
                else if(response.data.code == 1) {
                  this.$message({
                    message: response.data.msg,
                    type: 'warning'
                  }); 
                }else {
                  this.$message.error('修改店铺信息失败，请稍后重试');
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
