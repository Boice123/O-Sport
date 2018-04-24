<template>
  <div class="footContainer">
      <div class="shortIntroduce">
        <div class="footsection">
        <img class="footsectionImg" src="../../assets/images/responsibility.svg"/>
        <span class="footsectionSpan">提倡责任组织和参与</span>
        </div>
        <div class="footsection">
        <img class="footsectionImg" src="../../assets/images/routes.svg"/>
        <span class="footsectionSpan">专业策划完美线路</span>
        </div>
        <div class="footsection">
        <img class="footsectionImg" src="../../assets/images/service.svg"/>
        <span class="footsectionSpan">热忱和敬业的服务团队</span>
        </div>
        <div class="footsection">
        <img class="footsectionImgE" src="../../assets/images/experience.svg"/>
        <span class="footsectionSpanE">边走边玩，一路行摄</span>
        </div>
        <div class="footsection">
        <img class="footsectionImg" src="../../assets/images/happy.svg"/>
        <span class="footsectionSpan">崇尚追求一路快乐</span>
        </div>
        <div class="footsection">
        <img class="footsectionImg" src="../../assets/images/share.svg"/>
        <span class="footsectionSpan">分享照片游记心得</span>
        </div>
      </div>
      <div class="cooperation">
          <ul class="cooperationul">
              <li class="cooperationli">关于O-Sport</li>
              <li class="cooperationli">合作伙伴</li>
              <li class="cooperationli">联系客服</li>
              <li class="cooperationli">开放平台</li>
              <li class="cooperationli">诚征英才</li>
              <li class="cooperationli">联系我们</li>
          </ul>
          <ul class="cooperationul">
              <li class="cooperationli">@2018-2019  osport.com版权所有</li>
              <li class="cooperationli">联系电话：020-123456789</li>
              <li class="cooperationli">投诉建议邮箱：893430191@qq.com</li>
          </ul>
      </div>
  
  
  
  </div>
</template>

<script>
  export default {
    name: 'signup'
  }
</script>

<style>
.footContainer {
  width: 100%;
  background: #2b2b2b;
  
}
.shortIntroduce {
    display: flex;
    color: #fff;
    border-bottom: 2px solid gray;
}
.footsection {
    flex: 1;
    display: flex;
    flex-direction: column;
    justify-content: center;
    padding-top: 3rem;
    padding-bottom: 3rem;
}
.footsectionImg {
    margin: 0 auto;
    width: 5rem;
    padding-bottom: 1rem;
}
.footsectionImgE {
    margin: 0.6rem auto;
    width: 5rem;
    padding-bottom: 1rem;
}
.footsectionSpan {
    font-size: .5rem;
}
.footsectionSpanE {
    font-size: .5rem;
    /* margin-top: 1rem; */
}
.cooperation {
    color: #fff;
    padding-top: 3rem;
    padding-bottom: 3rem;
    display: flex;
    flex-direction: column;
    justify-content: center;
}
.cooperationul {
    padding-bottom: 1rem;
    margin: 0 auto;
}
.cooperationli {
    padding-right: 1rem;
    float: left;
}
</style>
