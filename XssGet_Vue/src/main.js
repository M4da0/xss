// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.

//import Vue from 'vue'
import App from './App'
import router from './router'
//import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
//import axios from 'axios'
//import VueClipboard from 'vue-clipboard2'
//Vue.use(VueClipboard)
//Vue.use(ElementUI);

const Vue = require('vue')
const ElementUI = require('element-ui')
const axios = require('axios')
//const VueClipboard = require('vue-clipboard2')

Vue.config.productionTip = false

axios.defaults.baseURL = "/api"
Vue.prototype.$axios = axios

var Base64 = require('js-base64').Base64
Vue.prototype.$base64 = Base64

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
