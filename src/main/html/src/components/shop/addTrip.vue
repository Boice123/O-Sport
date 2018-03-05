<template>
  <div class="addTripContainer">
    <div class="addTripknow">新增报团出行活动</div>
    <el-form ref="form" :rules="rules" :model="form" label-width="140px">
      <el-form-item label="活动名称" prop="tripname">
        <el-input v-model="form.tripname"></el-input>
      </el-form-item>
      <el-form-item label="活动描述" prop="tripdescription">
        <el-input v-model="form.tripdescription"></el-input>
      </el-form-item>
      <el-form-item label="活动注意事项" prop="tripnotice">
        <el-input v-model="form.tripnotice"></el-input>
      </el-form-item>
      <el-form-item label="活动价钱(每人)" prop="tripprice">
        <el-input v-model="form.tripprice"></el-input>
      </el-form-item>
      <el-form-item label="活动最大人数限制" prop="tripmaxpeople">
        <el-input v-model="form.tripmaxpeople"></el-input>
      </el-form-item>
      <el-form-item label="活动图片(5张)" prop="tripimgs">
      <el-upload
        class="upload-demo"
        action="https://jsonplaceholder.typicode.com/posts/"
        :on-preview="handlePreview"
        :on-remove="handleRemove"
        :file-list="fileList2"
        list-type="picture">
        
        <span slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</span>
        <el-button size="small" type="primary">点击上传</el-button>
      </el-upload>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm('form')">提交</el-button>
      </el-form-item>
    </el-form>  
  </div>
</template>

<script>
export default {
    name: 'openshop',
    data() {
         // 价钱
      var validateTripprice = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入活动价格'));
        } else if (value <=0 ) {
            callback(new Error('请输入不少于0的活动价格'));
        }else {
          if (!/\d+(,\d+)*/.test(this.form.tripprice)) {
            callback(new Error('请输入只限于数字的文字'));
          }
          callback()
        }
      };
      // 最大人数限制
      var validateTripmaxpeople = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入最大人数限制'));
        } else if (value <=0 ) {
            callback(new Error('请输入不少于0的最大人数限制'));
        }else {
          if (!/\d+(,\d+)*/.test(this.form.tripmaxpeople)) {
            callback(new Error('请输入只限于数字的文字'));
          }
          callback()
        }
      };
        return {
          fileList2: [{name: 'food.jpeg', url: 'https://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100'}, {name: 'food2.jpeg', url: 'https://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100'}],
            form: {
                tripname:'',
                tripdescription: '',
                tripnotice: '',
                tripprice: 0,
                tripmaxpeople:0
            },
            rules: {
            tripprice: [
                { validator: validateTripprice, trigger: 'blur' }
            ],
            tripmaxpeople: [
               { validator: validateTripmaxpeople, trigger: 'blur' }
            ]
            }
            
        }
    },
    methods: {
      handleRemove(file, fileList) {
        console.log(file, fileList);
      },
      handlePreview(file) {
        console.log(file);
      },
      submitForm(formName) {
         //信息加入param
        var params = new URLSearchParams();
        params.append('tripname',this.form.tripname);
        params.append('tripdescription', this.form.tripdescription);
        params.append('tripnotice', this.form.tripnotice);
        params.append('tripprice', this.form.tripprice);
        params.append('tripmaxpeople', this.form.tripmaxpeople);
        this.$refs[formName].validate((valid) => {
          if (valid) {
            axios({
              method:'post',
              url:API_saveTripURL,
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
                  this.$message.error('添加报团活动信息失败，请稍后重试');
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
