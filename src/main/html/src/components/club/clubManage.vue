<template>
    <div class="shopContainer">
        <div class="shopTopNav">
            O-Sport部落管理 
            <ul class="shopTopNavUl">
                <router-link to="shopinfo"><li class="shopTopNavLi">部落设置首页</li></router-link>
                <router-link to="shopinfo"><li class="shopTopNavLi">设置</li></router-link>
                <router-link to="shopinfo"><li class="shopTopNavLi">更多</li></router-link>
            </ul>
        </div>
        <div class="shopContent">
            <div class="shopContentLeft">
                <div class="shopContentLeftBox shopInfoBox">
                    <!-- <div class="shopInfoknow">我的店铺</div> -->
                    <div class="shopInfo">
                        <img class="shopInfoimg" />
                        <h3>{{clubInfo.clubname}}</h3>
                    </div>
                    <div class="shopInfoDetail">
                        <div class="shopInfoDetailBox">
                            <span class="shopInfoDetailTitle">会员</span>
                            <span class="shopInfoDetailNum">{{clubInfo.clubpeople}}</span>
                        </div>
                        <div class="shopInfoDetailBox">
                            <span class="shopInfoDetailTitle">活动</span>
                            <span class="shopInfoDetailNum">{{clubactivity.length}}</span>
                        </div>
                        <div class="shopInfoDetailBox">
                            <span class="shopInfoDetailTitle">动态</span>
                            <span class="shopInfoDetailNum">{{clubdiary.length}}</span>
                        </div>
                    </div>
                </div>
                <div class="shopContentLeftBox">
                    <div class="shopContentLeftTitle">
                        <img class="shopContentLeftTitleImg" src="../../assets/images/shopmanage.svg"/>
                        <h3>部落管理</h3>
                    </div>
                    <router-link to="updateClubinfo"><div class="shopContentLeftList">修改部落信息</div></router-link>
                </div>
                <div class="shopContentLeftBox">
                    <div class="shopContentLeftTitle">
                        <img class="shopContentLeftTitleImg" src="../../assets/images/ordermanage.svg"/>
                        <h3>部落活动</h3>
                    </div>
                    <div class="shopContentLeftList" @click="goToActivity(clubInfo.clubid)">部落活动管理</div>
                    <!-- <router-link to="shopmanagetrainorder"><div class="shopContentLeftList">户外培训订单</div></router-link> -->
                </div>
                <div class="shopContentLeftBox">
                    <div class="shopContentLeftTitle">
                        <img class="shopContentLeftTitleImg" src="../../assets/images/goodsmanage.svg"/>
                        <h3>部落动态</h3>
                    </div>
                    <div class="shopContentLeftList" @click="goToDiary(clubInfo.clubid)">部落动态管理</div>
                    <!-- <router-link to="shopmanagetrain"><div class="shopContentLeftList">户外培训管理</div></router-link> -->
                </div>
            </div>
            <div class="shopContentRight">
                <router-view></router-view>
            </div>
        </div>
    </div>
</template>

<script>
import {API_getClubURl, API_getClubActivityURl, API_getClubDiaryURl} from '../../constants/index.js'
import axios from 'axios'
  export default {
    name: 'trip',
    data() {
      return {
          clubInfo: {},
          clubdiary: [],
          clubactivity: []
      }
    },
    created() {
       //获取club信息
        var params = new URLSearchParams();
        params.append('clubid',this.getCookie("clubid"))
        axios({
            method:'post',
            url:API_getClubURl,
            params
        })
        .then((response) => {
            console.log(this.$route.params.clubid)
            console.log(response.data)
            if(response.data.code == 0) {
                this.clubInfo = response.data.data
            }else if(response.data.code == 1) {
                this.$message({
                    message: response.data.msg,
                    type: 'warning'
                }); 
            }else {
                this.$message.error('获取Club信息失败，请稍后重试');
            }        
        }).catch((err) => {
            console.log(err)
        })
        //获取club diary信息
        var params = new URLSearchParams();
        params.append('clubid',this.getCookie("clubid"))
        axios({
            method:'post',
            url:API_getClubDiaryURl,
            params
        })
        .then((response) => {
            console.log(this.$route.params.clubid)
            console.log(response.data)
            if(response.data.code == 0) {
                this.clubdiary = response.data.data
            }else if(response.data.code == 1) {
                this.$message({
                    message: response.data.msg,
                    type: 'warning'
                }); 
            }else {
                this.$message.error('获取Club信息失败，请稍后重试');
            }        
        }).catch((err) => {
            console.log(err)
        })
        //获取club activity信息
        var params = new URLSearchParams();
        params.append('clubid',this.getCookie("clubid"))
        axios({
            method:'post',
            url:API_getClubActivityURl,
            params
        })
        .then((response) => {
            console.log(this.$route.params.clubid)
            console.log(response.data)
            if(response.data.code == 0) {
                this.clubactivity = response.data.data
            }else if(response.data.code == 1) {
                this.$message({
                    message: response.data.msg,
                    type: 'warning'
                }); 
            }else {
                this.$message.error('获取Club信息失败，请稍后重试');
            }        
        }).catch((err) => {
            console.log(err)
        })
    },
    methods: {
        goToActivity(clubid) {
            this.$router.push({name:'clubManageActivity',params:{clubid}})
        },
        goToDiary (clubid) {
            this.$router.push({name:'clubManageDiary',params:{clubid}})
        }
    },
    components: {

    }
  }
</script>

<style>
.shopContainer {
    width: 100%;
}
.shopTopNav {
    padding-left: 1rem;
    padding-top: 1rem;
    padding-bottom: 1rem;
    background: #2b2b2b;
    display: flex;
    color: #fff;
    font-size: 2rem;
    align-items: center;
}
.shopTopNavUl {
    display: flex;
    justify-content: center;
    color: #fff;
    font-size: 1rem;
    width: 20rem;
}
.shopTopNavLi {
    padding-left: 2rem;
    flex: 1;
}
.shopContent {
    width: 100%;
    display: flex;
}
.shopContentLeft {
    flex: 1;
    margin-left: 1rem;
    margin-top: 1rem;
    margin-bottom: 1rem;
    background: #fff;
    border: 2px solid orange;
    border-radius: 1rem;
}
.shopContentRight {
    margin-left: 1rem;
    margin-top: 1rem;
    margin-right: 1rem;
    margin-bottom: 1rem;
    flex: 4;
    border: 2px solid orange;
    border-radius: 1rem;
}
.shopContentLeftBox {
    display: flex;
    flex-direction: column;
    text-align: left;
    margin: 2rem 2rem;
    border-bottom: 1px solid #dfdede;
}
.shopContentLeftTitle {
    display: flex;
    align-items: center;
    padding-bottom: .8rem;
}
.shopContentLeftTitleImg {
    width: 1.5rem;
}
.shopContentLeftList {
    padding-bottom: 1rem;
    padding-left: 1.5rem;
    font-size: 0.9rem;
}
.shopInfoBox {
    display: flex;
    flex-direction: column;
    justify-content: center;
    padding-bottom: 1rem;
}
.shopInfo {
    width: 100%;
    text-align: center;
    display: flex;
    flex-direction: column;
    justify-content: center;
    padding-bottom: 1rem;
}
.el-form {
    margin: 0 auto;
}
.shopInfoimg {
    width: 6rem;
    height: 7rem;
    padding-bottom: 1rem;
    margin: 0 auto;
}
.shopInfoDetail {
    width: 100%;
    display: flex;
    flex-direction: row;
    justify-content: center;
    margin: 0 auto;
}
.shopInfoDetailBox {
    display:flex;
    flex: 1;
    flex-direction: column;
    justify-content: center;
    align-items: center;
}
.shopInfoDetailTitle {
    font-size: 1rem;
    padding-bottom: 0.5rem;
}
.shopInfoDetailNum {
    font-size: 1rem;
    color: orange;
}
</style>