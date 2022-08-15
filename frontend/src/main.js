import Vue from "vue";
import App from "./App.vue";
import router from "./routes/index.js";
import store from "./store/index.js";
import vuetify from "./plugins/vuetify";
import vueCookies from "vue-cookies";
import Clipboard from "v-clipboard";

Vue.config.productionTip = false;
Vue.use(vueCookies);
Vue.use(Clipboard);
Vue.$cookies.config("7d");
new Vue({
  router,
  store,
  vuetify,
  vueCookies,
  render: (h) => h(App),
}).$mount("#app");
