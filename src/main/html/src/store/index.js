import vue from 'vue'
import vuex from 'vuex'
import mutations from './mutations'

vue.use(vuex)

const state = {
	username: "",
	shopid: ""
}

export default new vuex.Store({
	state,
	mutations
})
