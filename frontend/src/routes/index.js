import Vue from "vue";
import VueRouter from "vue-router";
import MainView from "../views/MainView.vue";
import AboutUs from "../views/AboutusView.vue";

Vue.use(VueRouter);

export default new VueRouter({
  mode: "history",
  routes: [
    {
      path: "/",
      component: MainView,
    },
    {
      path: "/aboutus",
      component: AboutUs,
    },
  ],
});
