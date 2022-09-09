import Vue from "vue";
import App from "./App.vue";
import router from "./routes/index.js";
import store from "./store/index.js";
import vuetify from "./plugins/vuetify";
import vueCookies from "vue-cookies";
<<<<<<< HEAD

Vue.config.productionTip = false;
Vue.use(vueCookies);
=======
import Clipboard from "v-clipboard";

Vue.config.productionTip = false;
Vue.use(vueCookies);
Vue.use(Clipboard);
>>>>>>> ac048782d308840093efdf3b8146c990ec2fcf95
Vue.$cookies.config("7d");
new Vue({
  router,
  store,
  vuetify,
  vueCookies,
  render: (h) => h(App),
}).$mount("#app");
