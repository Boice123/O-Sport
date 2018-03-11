import Vue from 'vue'
import Router from 'vue-router'
import Index from '@/components/Index'
import Signup from '@/components/user/signup'
import Signin from '@/components/user/signin'
import Trip from '@/components/trip/trip'
import Shop from '@/components/shop/shop'
import OpenShop from '@/components/shop/openshop'
import UpdateshopInfo from '@/components/shop/updateshopInfo'
import Shopmanagetrip from '@/components/shop/shopmanagetrip'
import Shopmanagetrain from '@/components/shop/shopmanagetrain'
import Shopmanagetriporder from '@/components/shop/shopmanagetriporder'
import Shopmanagetrainorder from '@/components/shop/shopmanagetrainorder'
import AddTrip from '@/components/shop/addTrip'
import UpdateTripInfo from '@/components/shop/updateTripInfo'
import AddTrain from '@/components/shop/addTrain'
import UpdateTrainInfo from '@/components/shop/updateTrainInfo'
import Shopinfo from '@/components/shop/shopinfo'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'index',
      component: Index
    },
    {
      path: '/signup',
      name: 'signup',
      component: Signup
    },
    {
      path: '/signin',
      name: 'signin',
      component: Signin
    },
    {
      path: '/trip',
      name: 'trip',
      component: Trip
    },
    {
      path: '/shop',
      name: 'shop',
      component: Shop,
      redirect: 'shop/shopInfo',
      children: [
        {
          path: 'openshop',
          name: 'openshop',
          component: OpenShop
        },
        {
          path: 'updateshopinfo',
          name: 'updateshopinfo',
          component: UpdateshopInfo
        },
        {
          path: 'shopmanagetrip',
          name: 'shopmanagetrip',
          component: Shopmanagetrip
        },
        {
          path: 'shopmanagetrain',
          name: 'shopmanagetrain',
          component: Shopmanagetrain
        },
        {
          path: 'shopmanagetriporder',
          name: 'shopmanagetriporder',
          component: Shopmanagetriporder
        },
        {
          path: 'shopmanagetrainorder',
          name: 'shopmanagetrainorder',
          component: Shopmanagetrainorder
        },
        {
          path: 'addTrip',
          name: 'addTrip',
          component: AddTrip
        },
        {
          path: 'updateTripInfo',
          name: 'updateTripInfo',
          component: UpdateTripInfo
        },
        {
          path: 'addTrain',
          name: 'addTrain',
          component: AddTrain
        },
        {
          path: 'updateTrainInfo',
          name: 'updateTrainInfo',
          component: UpdateTrainInfo
        },
        {
          path: 'shopinfo',
          name: 'shopinfo',
          component: Shopinfo
        }
      ]
    }]
})
