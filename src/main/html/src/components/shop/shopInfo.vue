<template>
  <div class="shopInfoContainer">
    <div class="shopInfoknow">我的店铺</div>
    <div class="shopInfo">
        <img class="shopInfoimg" :src="shopimg" />
        <h3>{{shopname}}</h3>
        <div class="shopInfoDetail">
            <div class="shopInfoDetailBox">
                <span class="shopInfoDetailTitle">今日成交量</span>
                <span class="shopInfoDetailNum">10</span>
            </div>
            <div class="shopInfoDetailBox">
                <span class="shopInfoDetailTitle">户外出团总数</span>
                <span class="shopInfoDetailNum">{{shopTripCount}}</span>
            </div>
            <div class="shopInfoDetailBox">
                <span class="shopInfoDetailTitle">户外培训总数</span>
                <span class="shopInfoDetailNum">{{shopTrainCount}}</span>
            </div>
        </div>
    </div>
  </div>
</template>

<script>
import {API_getShopInfoURl, API_getShopTripCountURl, API_getShopTrainCountURl} from '../../constants/index.js'
import axios from 'axios'
export default {
    name: 'shopInfo',
    data() {
        return {
            shopname: '',
            shopimg: '',
            shopTripCount: 0,
            shopTrainCount: 0
        }
    },
    created() {
        //获取商店信息
        console.log(this.getCookie('shop_id'))
        var params = new URLSearchParams();
        params.append('shopid',this.getCookie('shop_id'))
        axios({
            method:'post',
            url:API_getShopInfoURl,
            params
        })
        .then((response) => {
            console.log(response.data)
            if(response.data.code == 0) {
                this.shopname = response.data.data.shopname
                this.shopimg = response.data.data.shopimg
                console.log(this.shopimg)
            }else if(response.data.code == 1) {
                this.$message({
                    message: response.data.msg,
                    type: 'warning'
                }); 
            }else {
                this.$message.error('获取店铺信息失败，请稍后重试');
            }        
        }).catch((err) => {
            console.log(err)
        })
      //获取商店户外出团总数  
      var params = new URLSearchParams();
        params.append('shopid',this.getCookie('shop_id'))
        axios({
            method:'post',
            url:API_getShopTripCountURl,
            params
        })
        .then((response) => {
            console.log(response.data)
            if(response.data.code == 0) {
               this.shopTripCount = response.data.data
            }else if(response.data.code == 1) {
                this.$message({
                    message: response.data.msg,
                    type: 'warning'
                }); 
            }else {
                this.$message.error('获取店铺户外出团总数信息失败，请稍后重试');
            }        
        }).catch((err) => {
            console.log(err)
        })
      //获取商店户外培训总数  
      var params = new URLSearchParams();
        params.append('shopid',this.getCookie('shop_id'))
        axios({
            method:'post',
            url:API_getShopTrainCountURl,
            params
        })
        .then((response) => {
            console.log(response.data)
            if(response.data.code == 0) {
               this.shopTrainCount = response.data.data
            }else if(response.data.code == 1) {
                this.$message({
                    message: response.data.msg,
                    type: 'warning'
                }); 
            }else {
                this.$message.error('获取店铺户户外培训总数信息失败，请稍后重试');
            }        
        }).catch((err) => {
            console.log(err)
        })
    }

}
</script>

<style>
.shopInfoContainer {
    width: 100%;
    display: flex;
    flex-direction: column;
    justify-content: center;
}
.shopInfoknow {
  font-size: 1.5rem;
  margin-top: 3rem;
  margin-bottom: 2rem;
}
.el-form {
    width: 30rem;
    margin: 0 auto;
}
.shopInfoimg {
    width: 6rem;
    height: 7rem;
    padding-bottom: 1rem;
}
.shopInfoDetail {
    width: 30rem;
    display: flex;
    justify-content: center;
    margin: 1rem auto;
}
.shopInfoDetailBox {
    display:flex;
    flex-direction: column;
    padding-left: 1rem;
}
.shopInfoDetailTitle {
    font-size: 1rem;
    padding-bottom: 1rem;
}
.shopInfoDetailNum {
    font-size: 1rem;
    color: orange;
}
</style>
