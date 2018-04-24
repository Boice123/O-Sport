<template>
  <div id="app">
   <Top v-if="!isSign&&!admin"/>
   <LogoTop v-if="isSign&&!admin"/>
   <router-view/>
    <Foot/>
  </div>
</template>

<script>
import Top from './components/common/top'
import LogoTop from './components/common/LogoTop'
import Foot from './components/common/foot'
export default {
  name: 'App',
  data() {
    return {
      isSign: false,
      admin: false
    }
  },
  components: {
    Top,
    LogoTop,
    Foot
  },
  watch:{
    '$route':function(to,from){
        if(to.fullPath == '/signin' || to.fullPath == '/signup'){
          this.isSign = true
          this.admin = false
        }
        else if(to.fullPath == '/adminsignin') {
          this.admin = true
        }
        else if(to.fullPath == '/adminsignup') {
          this.admin = true
        }
        else if(to.fullPath == '/admin/adminTrip') {
          this.admin = true
        }
        else if(to.fullPath == '/admin/addTrip') {
          this.admin = true
        }
        else if(to.fullPath == '/admin/updateTrip') {
          this.admin = true
        }
        else if(to.fullPath == '/admin/adminTripOrder') {
          this.admin = true
        }
        else if(to.fullPath == '/admin/adminClub') {
          this.admin = true
        }
        else if(to.fullPath == '/admin/addClub') {
          this.admin = true
        }
        else if(to.fullPath == '/admin/updateClub') {
          this.admin = true
        }
        else if(to.fullPath == '/admin/adminClubActivity') {
          this.admin = true
        }
        else if(to.fullPath == '/admin/adminClubDiary') {
          this.admin = true
        }
        else if(to.fullPath == '/admin/adminClubUser') {
          this.admin = true
        }
        else{
          this.isSign = false
          this.admin = false
        }
      }
    }
}
</script>

<style>
  * {
    padding: 0;
    margin: 0;
  }
  #app {
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: #2c3e50;
  }
  html,body,div,button,span,applet,object,iframe,h1,h2,h3,h4,h5,h6,p,blockquote,pre,a,abbr,acronym,address,big,cite,code,del,dfn,em,img,ins,kbd,q,s,samp,small,strike,strong,sub,sup,tt,var,b,u,i,center,dl,dt,dd,ol,ul,li,fieldset,form,label,legend,table,caption,tbody,tfoot,thead,tr,th,td,article,aside,canvas,details,embed,figure,figcaption,footer,header,hgroup,menu,nav,output,ruby,section,summary,time,mark,audio,video {
    margin: 0;
    padding: 0;
    border: 0;
  }
  article,aside,details,figcaption,figure,footer,header,hgroup,menu,nav,section {
    display: block;
  }
  body {
    line-height: 1;
  }
  ol,ul {
    list-style: none;
  }
  blockquote,q {
    quotes: none;
  }
  blockquote:before, blockquote:after,
  q:before, q:after {
    content: '';
    content: none;
  }
  table {
    border-collapse: collapse;
    border-spacing: 0;
  }
  a {
    color: inherit;
    text-decoration: none;
  }
  body {
    font-size: 14px;
    color: #444;
  }
  html,body {
    height:100%
  }
  input{
	  outline:none;
  }
</style>
