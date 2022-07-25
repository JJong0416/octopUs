import Vue from "vue";
import VueRouter from "vue-router";
import MainView from "../views/MainView.vue";
import AboutUs from "../views/AboutusView.vue";
import SignupView from "../views/SignupView.vue";
import CreateView from "../views/CreateView.vue";
import MypageView from "../views/MypageView.vue";
import MissionView from "../views/MissionView.vue";
import SearchView from "../views/SearchView.vue";
import PreView from "../views/PreView.vue";

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
    {
      path: "/signup",
      component: SignupView,
    },
    {
      path: "/create",
      component: CreateView,
    },
    {
      path: "/mypage",
      component: MypageView,
    },
    {
      path: "/mission",
      component: MissionView,
    },
    {
      path: "/search",
      component: SearchView,
    },
    {
      path: "/preview",
      component: PreView,
    },
  ],
});
