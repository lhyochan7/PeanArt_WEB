import Vue from 'vue'
import router from './router'
import App from './App.vue'
import vuetify from './plugins/vuetify'
Vue.config.productionTip = false
Vue.prototype.$Url = 'http://15.164.142.253:8080/'
new Vue({
  vuetify,
  router,
  render: h => h(App)
}).$mount('#app')
