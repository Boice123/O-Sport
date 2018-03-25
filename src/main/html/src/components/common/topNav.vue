<template>
  <div class="topNavContainer">
      <div class="topNavLeft">
        <div :class="{'topNavSections':showul != 1,'topNavSectionsActive': showul == 1}" @mouseover="changeShowUl(1)">
          运动部落
          <ul class="topNavUl" v-if="showul == 1"  @mouseout="changeShowUl(0)">
            <li :class="{'topNavLi': showli != 1,'topNavLiActive': showli == 1}" @mouseover="changeShowLi(1)" @mouseout="changeShowLi(0)" @click="gotoClub">部落主页</li>
            <li :class="{'topNavLi': showli != 2,'topNavLiActive': showli == 2}" @mouseover="changeShowLi(2)" @mouseout="changeShowLi(0)" @click="createClub">创建部落</li>
            <li :class="{'topNavLi': showli != 3,'topNavLiActive': showli == 3}" @mouseover="changeShowLi(3)" @mouseout="changeShowLi(0)" @click="gotoMyClub">我的部落</li>
          </ul>
        </div>
        <div :class="{'topNavSections':showul != 2,'topNavSectionsActive': showul == 2}" @mouseover="changeShowUl(2)" @mouseout="changeShowUl(0)">报团出行</div>
        <div :class="{'topNavSections':showul != 3,'topNavSectionsActive': showul == 3}" @mouseover="changeShowUl(3)" @mouseout="changeShowUl(0)">我的店铺</div>
        <div :class="{'topNavSections':showul != 4,'topNavSectionsActive': showul == 4}" @mouseover="changeShowUl(4)" @mouseout="changeShowUl(0)">知识课堂</div>
      </div>
      <div class="topNavRight"></div>
  </div>
</template>

<script>
import axios from 'axios'
import { API_getClubInfoURl, API_getClubUserItemURl } from '../../constants/index.js'
  export default {
    name: 'topNav',
    data() {
      return {
        showul: 0,
        showli: 0
      }
    },
    methods: {
      changeShowUl(num) {
        this.showul = num
      },
      changeShowLi(num) {
        this.showli = num
      },
      gotoClub() {
        this.$router.push('/club')
      },
      createClub() {
       //判断当前用户是否已经创建过部落
        var params = new URLSearchParams();
        params.append('clubowner',this.getCookie('user_userid'))
        axios({
            method:'post',
            url:API_getClubInfoURl,
            params
        })
        .then((response) => {
            console.log(response.data)
            if(response.data.code == 0) {
               this.$message({
                    message: '您已经创建过部落，不能再创建',
                    type: 'warning'
                });  
            }else if(response.data.code == 1) {
                this.$router.push('/createClub')
            }else {
                this.$message.error('获取部落信息失败，请稍后重试');
            }        
        }).catch((err) => {
            console.log(err)
        })    
      },
      gotoMyClub() {
       this.$router.push('/myClub') 
      }
    }
  }
</script>

<style>
.topNavContainer {
  width: 100%;
  background: #2b2b2b;
  display: flex;
}
.topNavLeft {
    width: 40rem;
    display: flex;
    padding-left: 2rem;
    padding-top:1rem;
    padding-bottom: 1rem;
}
.topNavSection {
    flex: 1;
    color: #fff;
}
.topNavSections {
    position: relative;
    flex: 1;
    color: #fff;
    height: 1rem;
    background: url("../../assets/images/pulldown.svg") no-repeat right;
    background-size: 0.5rem 0.5rem;
}
.topNavSectionsActive {
    position: relative;
    flex: 1;
    color: yellow;
    height: 1rem;
    background: url("../../assets/images/pulldown.svg") no-repeat right;
    background-size: 0.5rem 0.5rem;
}
.topNavUl {
  width: 100%;
  z-index: 10;
  position: absolute;
  top: 2rem;
  left: 0;
  background: #2b2b2b;
}
.topNavLi {
  padding-top: .8rem;
  padding-bottom: .8rem;
}
.topNavLiActive {
  color: yellow;
  padding-top: .8rem;
  padding-bottom: .8rem;
}
</style>
