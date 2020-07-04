import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import display from '@/components/Page/display'
import data_download from '@/components/Page/data_download'
import chinaData from '@/components/Page/chinaData'
import worldData from '@/components/Page/worldData'
import tripData from '@/components/Page/tripData'
Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',//设置/代表入口位置
      component: display
    },
    {
      path:'/data_download',
      component:data_download
    },
    {
      path:'/chinaData',
      component:chinaData
    },
    {
      path:'/worldData',
      component:worldData
    },
    {
      path:'/tripData',
      component:tripData
    }
  ]
})
