import Vue from 'vue'
import Router from 'vue-router'
import Home from '@/components/Home'

import Reciver from '@/components/home_page/Main/Reciver'
import Payloads from '@/components/home_page/Main/Payloads'
import About from '@/components/home_page/Main/About'
import MyPayloads from '@/components/home_page/Main/MyPayloads'
import Tools from '@/components/home_page/Main/Tools'
import Ding from '@/components/home_page/Main/Ding'

Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/home',
      name: 'Home',
      component: Home,
      children: [
        {
          path: 'reciver',
          name: 'reciver',
          component: Reciver
        },
        {
          path: 'payloads',
          name: 'payloads',
          component: Payloads
        },
        {
          path: 'about',
          name: 'about',
          component: About
        },
        {
          path: 'mypayloads',
          name: 'mypayloads',
          component: MyPayloads
        },
        {
          path: 'tools',
          name: 'tools',
          component: Tools
        },
        {
          path: 'ding',
          name: 'ding',
          component: Ding
        }
      ]
    }
  ]
})
