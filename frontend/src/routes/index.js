import Vue from "vue";
import VueRouter from "vue-router";
import MainView from "../views/MainView.vue";

Vue.use(VueRouter);

export default new VueRouter({
  mode: "history",
  routes: [
    {
      path: "/",
      component: MainView,
    },
  ],
});
