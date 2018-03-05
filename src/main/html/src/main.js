// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import axios from 'axios'
import {setCookie,getCookie,clearCookie} from '@/utils/cookieMethod'
import store from './store'
import Vuex from 'vuex'

Vue.use(Vuex)

Vue.use(ElementUI)
Vue.prototype.$axios = axios
Vue.prototype.setCookie = setCookie
Vue.prototype.getCookie = getCookie
Vue.prototype.clearCookie = clearCookie
axios.defaults.withCredentials=true

Vue.config.productionTip = false

new Vue({
  el: '#app',
  router,
  store,
  components: { App },
  template: '<App/>',
  //拦截器
  watch: {
    '$route': function(to) {
      if(to.path != '/signup' && to.path !='/' && to.path != '/trip') {
        if(this.$store.state.username == '') {
          this.$router.push('/signin')
        }
      }
    }
  }
})
