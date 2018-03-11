<template>
  <div class="indexContainer">
      <!-- 搜索栏 -->
      <searchBar/>
      <!-- topNav导航栏 -->
      <topNav/>
      <!-- banner -->
      <el-carousel trigger="click" height="400px">
        <el-carousel-item :key="1">
          <a href="#"><img class="bannerImg" src="../assets/images/banner/banner_01.jpg"/></a>
          <a href="#"><img class="bannerImg" src="../assets/images/banner/banner_02.jpg"/></a>
          <a href="#"><img class="bannerImg" src="../assets/images/banner/banner_03.jpg"/></a>
        </el-carousel-item>
        <el-carousel-item :key="2">
          <a href="#"><img class="bannerImg" src="../assets/images/banner/banner_02.jpg"/></a>
        </el-carousel-item>
        <el-carousel-item :key="3">
          <a href="#"><img class="bannerImg" src="../assets/images/banner/banner_03.jpg"/></a>
        </el-carousel-item>
      </el-carousel>
      <!-- 报名出团活动 -->
      <div class="tripBox">
        <div class="tripTitle">
          <div :class="{'tripTitleSectionActive':tripTagChoose == 1,'tripTitleSection':tripTagChoose != 1}" @click="changetripTagChoose(1)">最新出团</div>
          <div :class="{'tripTitleSectionActive':tripTagChoose == 2,'tripTitleSection':tripTagChoose != 2}" @click="changetripTagChoose(2)">最热出团</div>
          <div :class="{'tripTitleSectionActive':tripTagChoose == 3,'tripTitleSection':tripTagChoose != 3}" @click="changetripTagChoose(3)">公益活动</div>
        </div>
        <div class="more">>>更多</div>
        <div class="tripContent">
          <div :class="{'tripContentImgActive':tripChoose == 1,'tripContentImg':tripChoose != 1}" @mouseover="changetripChoose(1)"><img src="../assets/images/banner/banner_01.jpg"/></div>
          <div :class="{'tripContentImgActive':tripChoose == 2,'tripContentImg':tripChoose != 2}" @mouseover="changetripChoose(2)"><img src="../assets/images/banner/banner_02.jpg"/></div>
          <div :class="{'tripContentImgActive':tripChoose == 3,'tripContentImg':tripChoose != 3}" @mouseover="changetripChoose(3)"><img src="../assets/images/banner/banner_03.jpg"/></div>
        </div>
      </div>
      <!-- 户外培训 -->
      <div class="trainBox">
        <div class="trainTitle">
          <div :class="{'trainTitleSectionActive':trainTagChoose == 1,'trainTitleSection':trainTagChoose != 1}" @click="changetrainTagChoose(1)">最新培训</div>
          <div :class="{'trainTitleSectionActive':trainTagChoose == 2,'trainTitleSection':trainTagChoose != 2}" @click="changetrainTagChoose(2)">最热培训</div>
        </div>
        <div class="more">>>更多</div>
        <div class="trainContent">
          <div class="trainContentLeft">
            <img src="../assets/images/banner/banner_01.jpg"/>
          </div>
          <div class="trainContentRight">
            <div class="trainContentRight-Up">
              <div :class="{'trainContentImgActive':trainChoose == 1,'trainContentImg':trainChoose != 1}" @mouseover="changetrainChoose(1)"><img src="../assets/images/banner/banner_01.jpg"/></div>
              <div :class="{'trainContentImgActive':trainChoose == 2,'trainContentImg':trainChoose != 2}" @mouseover="changetrainChoose(2)"><img src="../assets/images/banner/banner_02.jpg"/></div>
              <div :class="{'trainContentImgActive':trainChoose == 3,'trainContentImg':trainChoose != 3}" @mouseover="changetrainChoose(3)"><img src="../assets/images/banner/banner_03.jpg"/></div>
            </div>
            <div class="trainContentRight-Down">
              <div :class="{'trainContentImgActive':trainChoose == 4,'trainContentImg':trainChoose != 4}" @mouseover="changetrainChoose(4)"><img src="../assets/images/banner/banner_01.jpg"/></div>
              <div :class="{'trainContentImgActive':trainChoose == 5,'trainContentImg':trainChoose != 5}" @mouseover="changetrainChoose(5)"><img src="../assets/images/banner/banner_02.jpg"/></div>
              <div :class="{'trainContentImgActive':trainChoose == 6,'trainContentImg':trainChoose != 6}" @mouseover="changetrainChoose(6)"><img src="../assets/images/banner/banner_03.jpg"/></div>
            </div>
          </div>        
        </div>
      </div>
      <!-- 部落活动 -->
      <div class="clubBox">
        <div class="clubTitle">
          <div :class="{'clubTitleSectionActive':clubTagChoose == 1,'clubTitleSection':clubTagChoose != 1}" @click="changeclubTagChoose(1)">部落活动</div>
          <div :class="{'clubTitleSectionActive':clubTagChoose == 2,'clubTitleSection':clubTagChoose != 2}" @click="changeclubTagChoose(2)">最热部落</div>
          <div :class="{'clubTitleSectionActive':clubTagChoose == 3,'clubTitleSection':clubTagChoose != 3}" @click="changeclubTagChoose(3)">推荐部落</div>
        </div>
        <div class="more">>>更多</div>
        <div class="clubContent">
          <div class="clubImg" @click="changeclubChoose(1)"><img src="../assets/images/banner/banner_01.jpg"/></div>
          <div class="clubImg" @click="changeclubChoose(2)"><img src="../assets/images/banner/banner_02.jpg"/></div>
          <div class="clubImg" @click="changeclubChoose(3)"><img src="../assets/images/banner/banner_03.jpg"/></div>
          <div class="clubImgL" @click="changeclubChoose(4)"><img src="../assets/images/banner/banner_03.jpg"/></div>
        </div>
      </div>
  </div>
</template>

<script>
import searchBar from './common/searchBar'
import topNav from './common/topNav'
  export default {
    name: 'index',
    data() {
      return {
        tripTagChoose: 1,
        tripChoose: 1,
        trainTagChoose: 1,
        trainChoose:1,
        clubTagChoose: 1,
        clubChoose: 1
      }
    },
    methods: {
      changetripTagChoose(choose) {
        this.tripTagChoose = choose
      },
      changetripChoose(choose) {
        this.tripChoose = choose
      },
      changetrainTagChoose(choose) {
        this.trainTagChoose = choose
      },
      changetrainChoose(choose) {
        this.trainChoose = choose
      },
      changeclubTagChoose(choose) {
        this.clubTagChoose = choose
      },
      changeclubChoose(choose) {
        this.clubChoose = choose
      }
    },
    components: {
        searchBar,
        topNav
    }
  }
</script>

<style>
.indexContainer {
    width: 100%;
}
.el-carousel-item {
  width: 100%;
}
.bannerImg {
  width: 100%;
  height: 400px;
}
.tripBox {
  background: #333;
  padding-top: 1rem;
  padding-bottom: 1rem;
  padding-left: 2rem;
  padding-right: 2rem;
  margin: 0 auto;
  position: relative;
}
.more {
  color: #fdd000;
  font-size: 18px;
  position: absolute;
  top: 0;
  right: 0;
  padding-right: 2rem;
  padding-top: 1.5rem;
}
.tripTitle {
  width: 30%;
  display: flex;
}
.tripTitleSection {
  flex: 1;
  color: #fdd000;
  font-Weight: 600;
  font-size: 20;
  padding-top: 0.5rem;
  padding-bottom: 0.5rem;
}
.tripTitleSectionActive {
  flex: 1;
  color: #000;
  font-Weight: 600;
  font-size: 20;
  background: #fdd000;
  padding-top: 0.5rem;
  padding-bottom: 0.5rem;
  border-radius: 5px 5px 0 0;
}
@-webkit-keyframes tripshow /* Safari 和 Chrome */
{
  from {  flex: 1;opacity: .5;}
  to {  flex: 2;opacity: 1;}
}
@-webkit-keyframes triphide /* Safari 和 Chrome */
{
  from {  flex: 2;opacity: 1;}
  to {  flex: 1;opacity: .5;}
}
.tripContent {
  width: 100%;
  display: flex;
  margin: 0 auto;
}
.tripContentImg {
  opacity: .5;
  height: 15rem;
  flex: 1;
  animation: triphide .5s;
  -moz-animation: triphide .5s;	/* Firefox */
  -webkit-animation: triphide .5s;	/* Safari 和 Chrome */
  -o-animation: triphide .5s;	/* Opera */
}
.tripContentImg > img{
  width: 100%;
  height: 100%;
}
.tripContentImgActive {
  opacity: 1;
  height: 15rem;
  flex: 2;
  animation: tripshow .5s;
  -moz-animation: tripshow .5s;	/* Firefox */
  -webkit-animation: tripshow .5s;	/* Safari 和 Chrome */
  -o-animation: tripshow .5s;	/* Opera */
}
.tripContentImgActive > img{
  width: 100%;
  height: 100%;
}
/* 户外培训 */
.trainBox {
  background: #333;
  padding-top: 1rem;
  padding-bottom: 1rem;
  padding-left: 2rem;
  padding-right: 2rem;
  margin: 0 auto;
  position: relative;
}
.trainTitle {
  width: 30%;
  display: flex;
}
.trainTitleSection {
  flex: 1;
  color: #fdd000;
  font-Weight: 600;
  font-size: 20;
  padding-top: 0.5rem;
  padding-bottom: 0.5rem;
}
.trainTitleSectionActive {
  flex: 1;
  color: #000;
  font-Weight: 600;
  font-size: 20;
  background: #fdd000;
  padding-top: 0.5rem;
  padding-bottom: 0.5rem;
  border-radius: 5px 5px 0 0;
}
.trainContent {
  width: 100%;
  display: flex;
  margin: 0 auto;
}
.trainContentLeft {
  flex:1
}
.trainContentLeft > img {
  width: 100%;
  height: 15rem;
}
.trainContentRight {
  flex: 2;
  display: grid;
  grid-template-columns: 1fr 1fr 1fr;
  grid-template-rows:1fr 1fr;
}
.trainContentRight-Up {
  display: flex;
  flex-direction: row;
}
.trainContentRight-Down {
  display: flex;
  flex-direction: row;
}
@-webkit-keyframes trainshow /* Safari 和 Chrome */
{
  from {  opacity: .5;}
  to {  opacity: 1;}
}
@-webkit-keyframes trainhide /* Safari 和 Chrome */
{
  from {  opacity: 1;}
  to {  opacity: .5;}
}
.trainContentImgActive {
  opacity: 1;
  flex: 1;
  flex-wrap: wrap;
  animation: trainshow .5s;
  -moz-animation: trainshow .5s;	/* Firefox */
  -webkit-animation: trainshow .5s;	/* Safari 和 Chrome */
  -o-animation: trainshow .5s;	/* Opera */
}
.trainContentImgActive > img{
  width: 100%;
  height: 7.5rem;
}
.trainContentImg {
  opacity: .5;
  flex: 1;
  flex-wrap: wrap;
  animation: trainhide .5s;
  -moz-animation: trainhide .5s;	/* Firefox */
  -webkit-animation: trainhide .5s;	/* Safari 和 Chrome */
  -o-animation: trainhide .5s;	/* Opera */
}
.trainContentImg > img {
  width: 100%;
  height: 7.5rem;
}
/* 部落活动 */
.clubBox {
  background: #333;
  padding-top: 1rem;
  padding-bottom: 1rem;
  padding-left: 2rem;
  padding-right: 2rem;
  margin: 0 auto;
  position: relative;
}
.clubTitle {
  width: 30%;
  display: flex;
}
.clubTitleSectionActive {
  flex: 1;
  color: #000;
  font-Weight: 600;
  font-size: 20;
  background: #fdd000;
  padding-top: 0.5rem;
  padding-bottom: 0.5rem;
  border-radius: 5px 5px 0 0;
}
.clubTitleSection {
  flex: 1;
  color: #fdd000;
  font-Weight: 600;
  font-size: 20;
  padding-top: 0.5rem;
  padding-bottom: 0.5rem;
}
.clubContent {
  width: 100%;
  display: flex;
  justify-content: space-between;
}
.clubImg {
  flex: 1;
  padding-right: 1rem;
}
.clubImgL {
  flex: 1;
}
.clubImg > img {
  width: 100%;
  height: 12rem;
}
.clubImgL > img {
  width: 100%;
  height: 12rem;
}
</style>