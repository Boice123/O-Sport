<template>
  <div class="clubContainer">
      <!-- <div class="clubHead">
        <div class="inputDiv">
            <input type="text" class="clubinput"/>
            <span class="clubspan"></span>
        </div>
      </div> -->
      <ClubHead/>
      <div class="clubBody">
          <div class="clubNav">
              <ul class="clubNavUl">
                  <li :class="{'clubNavLi': clubChoose!='全部','clubNavLiActive': clubChoose == '全部'}" @click="changeClubChoose('全部')">全部</li>
                  <li :class="{'clubNavLi': clubChoose!='自行车','clubNavLiActive': clubChoose == '自行车'}" @click="changeClubChoose('自行车')">自行车</li>
                  <li :class="{'clubNavLi': clubChoose!='徒步','clubNavLiActive': clubChoose == '徒步'}" @click="changeClubChoose('徒步')">徒步</li>
                  <li :class="{'clubNavLi': clubChoose!='露营','clubNavLiActive': clubChoose == '露营'}" @click="changeClubChoose('露营')">露营</li>
                  <li :class="{'clubNavLi': clubChoose!='马拉松','clubNavLiActive': clubChoose == '马拉松'}" @click="changeClubChoose('马拉松')">马拉松</li>
                  <li :class="{'clubNavLi': clubChoose!='登山','clubNavLiActive': clubChoose == '登山'}" @click="changeClubChoose('登山')">登山</li>
                  <li :class="{'clubNavLi': clubChoose!='野外定向','clubNavLiActive': clubChoose == '野外定向'}" @click="changeClubChoose('野外定向')">野外定向</li>
                  <li :class="{'clubNavLi': clubChoose!='轮滑','clubNavLiActive': clubChoose == '轮滑'}" @click="changeClubChoose('轮滑')">轮滑</li>
                  <li :class="{'clubNavLi': clubChoose!='潜水','clubNavLiActive': clubChoose == '潜水'}" @click="changeClubChoose('潜水')">潜水</li>
              </ul>
          </div>
          <div class="clubBodyDown">
          <div class="clubContent">
              <div class="clubBox" @mouseover="changeClubCover('自行车')" @mouseout="changeClubCover('')">
                  <img :class="{'clubImg':clubcover!='自行车','clubImgActive': clubcover=='自行车'}" src="../../assets/images/bgc/manycamp.jpg"/>
                  <div class="clubTextMask" v-if="clubcover == '自行车'"></div>
                  <div :class="{'clubText': clubcover!='自行车','clubTextActive': clubcover=='自行车'}">
                    <h2 class="clubname">自行车俱乐部</h2>
                    <p class="clubnum"><br>共93名会员</p>
                 </div>
              </div>
              <div class="clubBox" @mouseover="changeClubCover('徒步')" @mouseout="changeClubCover('')">
                  <img :class="{'clubImg':clubcover!='徒步','clubImgActive': clubcover=='徒步'}" src="../../assets/images/bgc/manycamp.jpg"/>
                  <div class="clubTextMask" v-if="clubcover == '徒步'"></div>
                  <div :class="{'clubText': clubcover!='徒步','clubTextActive': clubcover=='徒步'}">
                    <h2 class="clubname">徒步俱乐部</h2>
                    <p class="clubnum"><br>共93名会员</p>
                 </div>
              </div>
              <div class="clubBox" @mouseover="changeClubCover('徒步')" @mouseout="changeClubCover('')">
                  <img :class="{'clubImg':clubcover!='徒步','clubImgActive': clubcover=='徒步'}" src="../../assets/images/bgc/manycamp.jpg"/>
                  <div class="clubTextMask" v-if="clubcover == '徒步'"></div>
                  <div :class="{'clubText': clubcover!='徒步','clubTextActive': clubcover=='徒步'}">
                    <h2 class="clubname">徒步俱乐部</h2>
                    <p class="clubnum"><br>共93名会员</p>
                 </div>
              </div>
              <div class="clubBox" @mouseover="changeClubCover('徒步')" @mouseout="changeClubCover('')">
                  <img :class="{'clubImg':clubcover!='徒步','clubImgActive': clubcover=='徒步'}" src="../../assets/images/bgc/manycamp.jpg"/>
                  <div class="clubTextMask" v-if="clubcover == '徒步'"></div>
                  <div :class="{'clubText': clubcover!='徒步','clubTextActive': clubcover=='徒步'}">
                    <h2 class="clubname">徒步俱乐部</h2>
                    <p class="clubnum"><br>共93名会员</p>
                 </div>
              </div>
              <div class="clubBox" @mouseover="changeClubCover('徒步')" @mouseout="changeClubCover('')">
                  <img :class="{'clubImg':clubcover!='徒步','clubImgActive': clubcover=='徒步'}" src="../../assets/images/bgc/manycamp.jpg"/>
                  <div class="clubTextMask" v-if="clubcover == '徒步'"></div>
                  <div :class="{'clubText': clubcover!='徒步','clubTextActive': clubcover=='徒步'}">
                    <h2 class="clubname">徒步俱乐部</h2>
                    <p class="clubnum"><br>共93名会员</p>
                 </div>
              </div>
              <div class="clubBox" @mouseover="changeClubCover('徒步')" @mouseout="changeClubCover('')">
                  <img :class="{'clubImg':clubcover!='徒步','clubImgActive': clubcover=='徒步'}" src="../../assets/images/bgc/manycamp.jpg"/>
                  <div class="clubTextMask" v-if="clubcover == '徒步'"></div>
                  <div :class="{'clubText': clubcover!='徒步','clubTextActive': clubcover=='徒步'}">
                    <h2 class="clubname">徒步俱乐部</h2>
                    <p class="clubnum"><br>共93名会员</p>
                 </div>
              </div>
          </div>
          <div class="clubrank">
              <div class="clubranktitle">部落排名Top10</div>
              <div>
                  <ul>
                      <li class="clubrankli">1.自行车部落</li>
                      <li class="clubrankli">1.自行车部落</li>
                      <li class="clubrankli">1.自行车部落</li>
                      <li class="clubrankli">1.自行车部落</li>
                      <li class="clubrankli">1.自行车部落</li>

                  </ul>
              </div>
          </div>
          </div>
      </div>
  </div>
</template>

<script>
import axios from 'axios'
import ClubHead from './clubHead.vue'
import { API_getTripList, API_getTrainList } from '../../constants/index.js'
  export default {
    name: 'club',
    data() {
      return {
        clubChoose: '全部',
        clubcover: ''
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
      }
    },
    components: {
        ClubHead
    }
  }
</script>

<style>
.clubContainer {
    width: 100%;
}
.clubHead {
    width: 100%;
    height: 300px;
    background: url('../../assets/images/bgc/manycamp.jpg');
    display: flex;
    justify-content: center;
    align-items: center;
}
.inputDiv {
    display: flex;
    justify-content: center;
    align-items: center;
    border: 5px solid #d9d6d6;
    border-radius: 5px;
}
.clubinput {
    width: 20rem;
    height: 3rem;
    border: none;
    padding-left: 10px;
    padding-right: 10px;
    font-size: 15px;
}
.clubspan {
    display: block;
    background: red;
    width: 4rem;
    height: 3rem;
}
.clubBody {
    width: 90%;
    margin: 0 auto;
    margin-top: -70px;
    border-radius: 10px 0;
    padding-bottom: 5rem;
}
.clubNav {
    width: 100%;
    height: 70px;
    background: rgb(0,0,0,.8); 
}
.clubNavUl {
    width: 75%;
    height: 70px;
    display: flex;
    flex-direction:row; 
    align-items: center;
}
.clubNavLi {
    flex: 1;
    color: yellow;
    font-size: 15px;
    line-height: 70px;
    height: 70px;
}
.clubNavLiActive {
    flex: 1;
    color: #000;
    font-size: 15px;
    line-height: 70px;
    height: 70px;
    background: yellow;  
}
.clubContent {
    width: 75%;
    overflow: hidden;
    flex-direction: column;
    display: grid;
    grid-template-columns: 1fr 1fr 1fr;
    grid-template-rows:1fr 1fr;
}
.clubBox {
    width: 100%;
    height: 14rem;
    display: flex;
    flex-direction: row;
    position: relative;
    overflow: hidden;
}
@-webkit-keyframes clubImgshow /* Safari 和 Chrome */
{
  from { left: -10% }
  to { left: 0 }
}
@-webkit-keyframes clubImghide /* Safari 和 Chrome */
{
  from { left: 0  }
  to { left: -10%  }
}
.clubImg {
    position: absolute;
    width: 110%;
    height: 14rem;
    left: -10%;
    top: 0;
    animation: clubImghide .5s;
    -moz-animation: clubImghide .5s;	/* Firefox */
    -webkit-animation: clubImghide .5s;	/* Safari 和 Chrome */
    -o-animation: clubImghide .5s;	/* Opera */
}
.clubImgActive {
    position: absolute;
    width: 110%;
    height: 14rem;
    left: 0;
    top: 0;
    animation: clubImgshow .5s;
    -moz-animation: clubImgshow .5s;	/* Firefox */
    -webkit-animation: clubImgshow .5s;	/* Safari 和 Chrome */
    -o-animation: clubImgshow .5s;	/* Opera */
}
.clubTextMask {
    width: 100%;
    height: 14rem;
    background: rgb(0,0,0,.2);
    position: absolute;
    left: 0;
    top: 0;
}
@-webkit-keyframes clubTextshow /* Safari 和 Chrome */
{
  from { top: 0 }
  to { top: -10%}
}
@-webkit-keyframes clubTexthide /* Safari 和 Chrome */
{
  from { top: -10% }
  to { top: 0 }
}
.clubText {  
    width: 100%;
    height: 17rem;
    position: absolute;
    left: 0;
    top: 0;
    animation: clubTexthide .5s;
    -moz-animation: clubTexthide .5s;	/* Firefox */
    -webkit-animation: clubTexthide .5s;	/* Safari 和 Chrome */
    -o-animation: clubTexthide .5s;	/* Opera */
}
.clubTextActive {  
    width: 100%;
    height: 17rem;
    position: absolute;
    left: 0;
    top: -10%;
    animation: clubTextshow .5s;
    -moz-animation: clubTextshow .5s;	/* Firefox */
    -webkit-animation: clubTextshow .5s;	/* Safari 和 Chrome */
    -o-animation: clubTextshow .5s;	/* Opera */
}
.clubname {
    color: #fff;
    text-align: left;
    padding-left: 1rem;
    position: absolute;
    top: 70%;
}
.clubnum {
    color: #fff;
    text-align: left;
    padding-left: 1rem;
    position: absolute;
    top: 78%;
}
.clubBodyDown {
    display: flex;
    flex-direction: row;
}
.clubrank {
    width: 20%;
    border: 1px solid #000;
    border-radius: 10px;
    margin-top: 2rem;
    margin-left: 2rem;
    padding-top: 1rem;
    padding-bottom: 1rem;
}
.clubranktitle {
    color: yellow;
    padding-bottom: 1rem;
}
.clubrankli {
   margin-bottom: 0.5rem;
}
</style>