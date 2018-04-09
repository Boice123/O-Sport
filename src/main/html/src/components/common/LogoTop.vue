<template>
  <div class="logoTopContainer">
    <div class="logoTopWrap">
      <div class="logoLeft">
      <div class="logo">O-Sport</div>
      <div class="logo_content">专业户外运动社交与出行平台</div>
    </div>
      <div class="logoRight">
        <div class="logoTopSection">
          <img class="logoTopsectionImg" src="../../assets/images/responsibility.svg"/>
          <div class="logoTopsectionSpan">提倡责任组织和参与</div>
        </div>
        <div class="logoTopSection">
          <img class="logoTopsectionImg" src="../../assets/images/experience.svg"/>
          <div class="logoTopsectionSpan">边走边玩，一路行摄</div>
        </div>
        <div class="logoTopSection">
          <img class="logoTopsectionImg" src="../../assets/images/share.svg"/>
          <div class="logoTopsectionSpan">分享照片游记心得</div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  export default {
    name: 'searchBar',
    data() {
        return {
            
        }
    }
  }
</script>

<style>
.logoTopContainer {
  width: 100%;
  background: #333;
  align-items: center;
  color: #fff;
  padding-top: 1rem;
  padding-bottom: 1rem;
  text-align: left;
}

.logoTopWrap {
  /* width: 40%; */
  display: flex;
  align-items: center;
  justify-content: space-between;
}
.logoLeft {
  flex: 1;
  /* margin-left: 5rem; */
      display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    margin-right:  28rem;
}
.logoRight {
  display: flex;
    flex: 1;
    flex-direction: row;
}
.logoTopSection {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  margin-right: 2rem;
}
.logoTopsectionImg {
  width: 3rem;
  height: 3rem;
  margin-bottom: 1rem;
}
.logoTopsectionSpan {
  font-size: 0.5rem;
}
</style>
