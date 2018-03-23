<template>
  <div class="clubContainer">
      <ClubHead/>
      <div class="myClub">
          <div class="myClubLeft">
              <div class="myClubLeftTitle">我管理的部落</div>
              <div class="clubList">
                <div class="myClubBox" @mouseover="changeClubCover('自行车')" @mouseout="changeClubCover('')">
                    <img :class="{'myclubImg':clubcover!='自行车','myclubImgActive': clubcover=='自行车'}" src="../../assets/images/bgc/manycamp.jpg"/>
                    <h2 class="myclubname">自行车俱乐部</h2>
                </div>
                <div class="myClubBox" @mouseover="changeClubCover('徒步')" @mouseout="changeClubCover('')">
                    <img :class="{'myclubImg':clubcover!='徒步','myclubImgActive': clubcover=='徒步'}" src="../../assets/images/bgc/manycamp.jpg"/>
                    <h2 class="myclubname">徒步俱乐部</h2>
                </div>
              </div>
              <div class="myClubLeftTitle">我参与的部落</div>
              <div class="clubList">
                <div class="myClubBox" @mouseover="changeClubCover('自行车')" @mouseout="changeClubCover('')">
                    <img :class="{'myclubImg':clubcover!='自行车','myclubImgActive': clubcover=='自行车'}" src="../../assets/images/bgc/manycamp.jpg"/>
                    <h2 class="myclubname">自行车俱乐部</h2>
                </div>
                <div class="myClubBox" @mouseover="changeClubCover('徒步')" @mouseout="changeClubCover('')">
                    <img :class="{'myclubImg':clubcover!='徒步','myclubImgActive': clubcover=='徒步'}" src="../../assets/images/bgc/manycamp.jpg"/>
                    <h2 class="myclubname">徒步俱乐部</h2>
                </div>
              </div>
          </div>
          <div class="myClubRight">
              <div class="myclubtab">
                  <div class="myclubtabtab">管理部落动态</div>
                  <div class="myclubtabtab">参与部落动态</div>
              </div>
              <div class="myClubmessageDiv">
                <div class="myClubmessageBox">
                    <img class="myClubmessageImg" src="../../assets/images/bgc/climbmountain.jpg"/>
                    <div class="myClubmessageContent">
                        <div class="myClubmessageUp">
                            <div class="myClubmessageTitle">哈哈哈是标题</div>
                            <div class="myClubmessageTitleRight">
                                <img class="myClubmessageUserImg" src="../../assets/images/bgc/climbmountain.jpg"/>
                                <span class="myClubmessageUserName">用户名字</span>
                                <span class="myClubmessageTime">发表于19:08</span>
                            </div>
                        </div>
                        <div class="myClubsmessageDown">
                            出游小日记出游小日记出游小日记出游小日记出游小日记出
                            游小日记出游小日记出游小日记出游小日记vvvvv出游小日记出游小日记
                            出游小日记
                            出游小日记出游小日记出游小日记出游小日记出游小日记出
                            游小日记出游小日记出游小日记出游小日记vvvvv出游小日记出游小日记
                            出游小日记
                            出游小日记出游小日记出游小日记出游小日记出游小日记出
                            游小日记出游小日记出游小日记出游小日记vvvvv出游小日记出游小日记
                            出游小日记
                            出游小日记出游小日记出游小日记出游小日记出游小日记出
                            游小日记出游小日记出游小日记出游小日记vvvvv出游小日记出游小日记
                            出游小日记
                            出游小日记出游小日记出游小日记出游小日记出游小日记出
                            游小日记出游小日记出游小日记出游小日记vvvvv出游小日记出游小日记
                            出游小日记出游小日记出游小日记出游小日记出游小日记出游小日记出
                            游小日记出游小日记出游小日记出游小日记vvvvv出游小日记出游小日记
                            出游小日记
                        </div>
                    </div>
                </div>
                <div class="myClubmessageBox">
                    <img class="myClubmessageImg" src="../../assets/images/bgc/climbmountain.jpg"/>
                    <div class="myClubmessageContent">
                        <div class="myClubmessageUp">
                            <div class="myClubmessageTitle">哈哈哈是标题</div>
                            <div class="myClubmessageTitleRight">
                                <img class="myClubmessageUserImg" src="../../assets/images/bgc/climbmountain.jpg"/>
                                <span class="myClubmessageUserName">用户名字</span>
                                <span class="myClubmessageTime">发表于19:08</span>
                            </div>
                        </div>
                        <div class="myClubsmessageDown">
                            出游小日记出游小日记出游小日记出游小日记出游小日记出
                            游小日记出游小日记出游小日记出游小日记vvvvv出游小日记出游小日记
                            出游小日记
                            出游小日记出游小日记出游小日记出游小日记出游小日记出
                            游小日记出游小日记出游小日记出游小日记vvvvv出游小日记出游小日记
                            出游小日记
                            出游小日记出游小日记出游小日记出游小日记出游小日记出
                            游小日记出游小日记出游小日记出游小日记vvvvv出游小日记出游小日记
                            出游小日记
                            出游小日记出游小日记出游小日记出游小日记出游小日记出
                            游小日记出游小日记出游小日记出游小日记vvvvv出游小日记出游小日记
                            出游小日记
                            出游小日记出游小日记出游小日记出游小日记出游小日记出
                            游小日记出游小日记出游小日记出游小日记vvvvv出游小日记出游小日记
                            出游小日记出游小日记出游小日记出游小日记出游小日记出游小日记出
                            游小日记出游小日记出游小日记出游小日记vvvvv出游小日记出游小日记
                            出游小日记
                        </div>
                    </div>
                </div>
                <div class="myClubmessageBox">
                    <img class="myClubmessageImg" src="../../assets/images/bgc/climbmountain.jpg"/>
                    <div class="myClubmessageContent">
                        <div class="myClubmessageUp">
                            <div class="myClubmessageTitle">哈哈哈是标题</div>
                            <div class="myClubmessageTitleRight">
                                <img class="myClubmessageUserImg" src="../../assets/images/bgc/climbmountain.jpg"/>
                                <span class="myClubmessageUserName">用户名字</span>
                                <span class="myClubmessageTime">发表于19:08</span>
                            </div>
                        </div>
                        <div class="myClubsmessageDown">
                            出游小日记出游小日记出游小日记出游小日记出游小日记出
                            游小日记出游小日记出游小日记出游小日记vvvvv出游小日记出游小日记
                            出游小日记
                            出游小日记出游小日记出游小日记出游小日记出游小日记出
                            游小日记出游小日记出游小日记出游小日记vvvvv出游小日记出游小日记
                            出游小日记
                            出游小日记出游小日记出游小日记出游小日记出游小日记出
                            游小日记出游小日记出游小日记出游小日记vvvvv出游小日记出游小日记
                            出游小日记
                            出游小日记出游小日记出游小日记出游小日记出游小日记出
                            游小日记出游小日记出游小日记出游小日记vvvvv出游小日记出游小日记
                            出游小日记
                            出游小日记出游小日记出游小日记出游小日记出游小日记出
                            游小日记出游小日记出游小日记出游小日记vvvvv出游小日记出游小日记
                            出游小日记出游小日记出游小日记出游小日记出游小日记出游小日记出
                            游小日记出游小日记出游小日记出游小日记vvvvv出游小日记出游小日记
                            出游小日记
                        </div>
                    </div>
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
        clubcover: ''
      }
    },
    created () {
    },
    methods: {
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
    background: url('../../assets/images/bgc/campbgc.jpg')
}
.myClub {
    width: 90%;
    margin: 2rem auto;
    display: flex;
    flex-direction: row;
}
.myClubLeft {
    flex: 1;
    display: flex;
    flex-direction: column;
    border-right: 1px solid gray;
    padding-right: 1rem;
}
.myClubRight {
    flex:4;
    margin-left: 1rem;
    margin-bottom: 1rem;
}
.myClubLeftTitle {
    color: #000;
    text-align: left;
    margin-bottom: 1rem;
}
.clubList {
    display: flex;
    margin-bottom: 4rem;
}
.myClubBox {
    width: 8rem;
    height: 8rem;
}
.myclubImg {
    width: 8rem;
    height: 8rem;
    margin-right: 1rem;
}
.myclubImgActive {
    width: 8rem;
    height: 8rem;
    opacity: 0.5;
    margin-right: 1rem;
}
.myclubname {
    font-size: 1rem;
}
.myclubtab {
    height: 2rem;
    display: flex;
    flex-direction: row;

}
.myclubtabtab {
    width: 8rem;
    height: 2rem;
    border: 1px solid pink;
    display: flex;
    align-items: center;
    justify-content: center;
}
.myClubmessageDiv {
    width: 50rem;
}
.myClubmessageBox {
    padding: 1rem;
    margin-top: 1rem;
    background: #fff;
    display: flex;
    flex-direction: row;
    height: 12rem;
}
.myClubmessageDiv {
    width: 98%;
    margin: 0 auto;
}
.myClubmessageImg {
    width: 15rem;
    height: 12rem;
}
.myClubmessageContent {
    display: flex;
    flex-direction: column;
}
.myClubmessageUp {
    display: flex;
    align-items: center;
    justify-content: space-between;
    margin-bottom: 1rem;
}
.myClubmessageUserImg {
    width: 4rem;
    height: 4rem;
    border-radius: 2rem;
}
.myClubmessageTitle {
    margin-left: 1rem;
    font-size: 20px;
    font-weight: 600;
}
.myClubmessageTitleRight {
    display: flex;
    align-items: center;
}
.myClubmessageUserName {
    font-size: 15px;
    margin-left: 1rem;
    margin-right: 1rem;
}
.myClubmessageTime {
    color: gray;
}
.myClubmessageDown {
    color: gray;
    overflow: hidden;
    margin-left: 1rem;
    line-height: 1.4rem;
    font-size: 16px;
    text-align: left;
    text-overflow:ellipsis;
    display: -webkit-box;
    -webkit-box-orient: vertical;
    -webkit-line-clamp: 5;
}
</style>