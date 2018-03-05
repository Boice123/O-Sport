<template>
  <div class="topNavContainer">
      <div class="topNavLeft">
        <div class="topNavSections">运动部落</div>
        <div class="topNavSections">报团出行</div>
        <div class="topNavSection">户外培训</div>
        <div class="topNavSection">我的店铺</div>
        <div class="topNavSection">知识课堂</div>
      </div>
      <div class="topNavRight"></div>
  </div>
</template>

<script>
  export default {
    name: 'topNav'
  }
</script>

<style>
.topNavContainer {
  width: 100%;
  background: #2b2b2b;
  display: flex;
}
.topNavLeft {
    width: 50%;
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
    flex: 1;
    color: #fff;
    background: url("../../assets/images/pulldown.svg") no-repeat right;
    background-size: 0.5rem 0.5rem;
}
</style>
