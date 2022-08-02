import Vue from "vue";
import App from "./App.vue";
import router from "./routes/index.js";
import store from "./store/index.js";
import vuetify from "./plugins/vuetify";
import vueCookies from "vue-cookies";
import EasyCamera from "easy-vue-camera";
Vue.config.productionTip = false;
Vue.use(vueCookies);
Vue.$cookies.config("7d");
Vue.use(EasyCamera);
new Vue({
  router,
  store,
  vuetify,
  vueCookies,
  render: (h) => h(App),
}).$mount("#app");
