import Vue from 'vue'
import Router from 'vue-router'
import Index from '@/components/Index'
import Signup from '@/components/user/signup'
import Signin from '@/components/user/signin'
import Trip from '@/components/trip/trip'
import CheckOrder from '@/components/order/checkOrder'
// import Shop from '@/components/shop/shop'
// import OpenShop from '@/components/shop/openshop'
// import UpdateshopInfo from '@/components/shop/updateshopInfo'
// import Shopmanagetrip from '@/components/shop/shopmanagetrip'
// import Shopmanagetriporder from '@/components/shop/shopmanagetriporder'
// import AddTrip from '@/components/shop/addTrip'
// import UpdateTripInfo from '@/components/shop/updateTripInfo'
import UserOrder from '@/components/user/userOrder'
import UserCenter from '@/components/user/userCenter'
import UpdateUserInfo from '@/components/user/updateUserInfo'
import UpdatePassword from '@/components/user/updatePassword'
import Club from '@/components/club/club'
import ClubMainPage from '@/components/club/clubMainPage'
import MyClub from '@/components/club/myClub'
import AddActivity from '@/components/admin/addActivity'
import AddDiary from '@/components/club/addDiary'
import ClubManage from '@/components/club/clubManage'
import ClubManageDiary from '@/components/club/clubManageDiary'
import ClubManageActivity from '@/components/club/clubManageActivity'
// import UpdateClubInfo from '@/components/club/updateClubInfo'
import UpdateActivity from '@/components/admin/updateActivity'
import SearchList from '@/components/searchList'
import SearchClubList from '@/components/club/searchClubList'

import Admin from '@/components/admin/admin'
import Adminsignup from '@/components/admin/adminsignup'
import Adminsignin from '@/components/admin/adminsignin'
// import AdminShop from '@/components/admin/adminShop'
// import AdminShopTrip from '@/components/admin/adminShopTrip'
// import AdminShopTripOrder from '@/components/admin/adminShopTripOrder'
import AdminTrip from '@/components/admin/adminTrip'
import AdminTripOrder from '@/components/admin/adminTripOrder'
import AdminClub from '@/components/admin/adminClub'
import AddClub from '@/components/admin/addClub'
import UpdateClub from '@/components/admin/updateClub'
import AdminClubActivity from '@/components/admin/adminClubActivity'
import AdminClubDiary from '@/components/admin/adminClubDiary'
import AdminAddTrip from '@/components/admin/addTrip'
import AdminupdateTrip from '@/components/admin/updateTrip'
import AdminClubUser from '@/components/admin/adminClubUser'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'index',
      component: Index
    },
    {
      path: '/searchList',
      name: 'searchList',
      component: SearchList
    },
    {
      path: '/searchclubList',
      name: 'searchclubList',
      component: SearchClubList
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
      path: '/userOrder',
      name: 'userOrder',
      component: UserOrder
    },
    {
      path: '/userCenter',
      name: 'userCenter',
      component: UserCenter,
      redirect: 'userCenter/updateuserinfo',
      children: [
        {
          path: 'updateuserinfo',
          name: 'updateuserinfo',
          component: UpdateUserInfo
        },
        {
          path: 'updatepassword',
          name: 'updatepassword',
          component: UpdatePassword
        }
      ]
    },
    {
      path: '/trip',
      name: 'trip',
      component: Trip
    },
    {
      path: '/checkOrder',
      name: 'checkOrder',
      component: CheckOrder
    },
    // {
    //   path: '/shop',
    //   name: 'shop',
    //   component: Shop,
    //   redirect: 'shop/shopmanagetrip',
    //   children: [
    //     {
    //       path: 'openshop',
    //       name: 'openshop',
    //       component: OpenShop
    //     },
    //     {
    //       path: 'updateshopinfo',
    //       name: 'updateshopinfo',
    //       component: UpdateshopInfo
    //     },
    //     {
    //       path: 'shopmanagetrip',
    //       name: 'shopmanagetrip',
    //       component: Shopmanagetrip
    //     },
    //     // {
    //     //   path: 'shopmanagetrain',
    //     //   name: 'shopmanagetrain',
    //     //   component: Shopmanagetrain
    //     // },
    //     {
    //       path: 'shopmanagetriporder',
    //       name: 'shopmanagetriporder',
    //       component: Shopmanagetriporder
    //     },
    //     // {
    //     //   path: 'shopmanagetrainorder',
    //     //   name: 'shopmanagetrainorder',
    //     //   component: Shopmanagetrainorder
    //     // },
    //     {
    //       path: 'addTrip',
    //       name: 'addTrip',
    //       component: AddTrip
    //     },
    //     {
    //       path: 'updateTripInfo',
    //       name: 'updateTripInfo',
    //       component: UpdateTripInfo
    //     },
    //     // {
    //     //   path: 'addTrain',
    //     //   name: 'addTrain',
    //     //   component: AddTrain
    //     // },
    //     // {
    //     //   path: 'updateTrainInfo',
    //     //   name: 'updateTrainInfo',
    //     //   component: UpdateTrainInfo
    //     // },
    //   ]
    // },
  {
    path: '/club',
    name: 'club',
    component: Club
  },
  {
    path: '/clubMainPage',
    name: 'clubMainPage',
    component: ClubMainPage
  },
  {
    path: '/myClub',
    name: 'myClub',
    component: MyClub
  },
  {
    path: '/addDiary',
    name: 'addDiary',
    component: AddDiary
  },
  {
    path: '/clubManage',
    name: 'clubManage',
    component: ClubManage,
    redirect: 'clubManage/clubManageActivity',
    children: [
      {
        path: 'clubManageActivity',
        name: 'clubManageActivity',
        component: ClubManageActivity
      },
      {
        path: 'clubManageDiary',
        name: 'clubManageDiary',
        component: ClubManageDiary
      },
    ]
  },
  {
    path: '/admin',
    name: 'admin',
    component: Admin,
    redirect: 'admin/adminTrip',
    children: [
      {
        path: 'adminTrip',
        name: 'adminTrip',
        component: AdminTrip
      },
      {
        path: 'addTrip',
        name: 'addTrip',
        component: AdminAddTrip
      },
      {
        path: 'updateTrip',
        name: 'updateTrip',
        component: AdminupdateTrip
      },
      {
        path: 'adminTripOrder',
        name: 'adminTripOrder',
        component: AdminTripOrder
      },
      {
        path: '/addActivity',
        name: 'addActivity',
        component: AddActivity
      },
      {
        path: 'updateActivity',
        name: 'updateActivity',
        component: UpdateActivity
      },
      {
        path: 'adminClub',
        name: 'adminClub',
        component: AdminClub
      },
      {
        path: 'addClub',
        name: 'addClub',
        component: AddClub
      },
      {
        path: 'updateClub',
        name: 'updateClub',
        component: UpdateClub
      },
      {
        path: 'adminClubActivity',
        name: 'adminClubActivity',
        component: AdminClubActivity
      },
      {
        path: 'adminClubDiary',
        name: 'adminClubDiary',
        component: AdminClubDiary
      },
      {
        path: 'adminClubUser',
        name: 'adminClubUser',
        component: AdminClubUser
      }
    ]
  },
  {
    path: '/adminsignin',
    name: 'adminsignin',
    component: Adminsignin
  },
  {
    path: '/adminsignup',
    name: 'adminsignup',
    component: Adminsignup
  },
  ]
})