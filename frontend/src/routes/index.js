import Vue from "vue";
import VueRouter from "vue-router";
<<<<<<< HEAD
// import HomeView from "../views/HomeView.vue";
import store from "@/store/index.js";
// import MainView from "@/components/MainView.vue";
import MainView from "@/views/MainView.vue";
=======
import store from "@/store/index.js";
>>>>>>> ac048782d308840093efdf3b8146c990ec2fcf95
Vue.use(VueRouter);

// https://router.vuejs.org/kr/guide/advanced/navigation-guards.html
const onlyAuthUser = async (to, from, next) => {
  // console.log(store);
  const checkUserInfo = store.getters["memberStore/checkUserInfo"];
  const getUserInfo = store._actions["memberStore/getUserInfo"];
  let token = sessionStorage.getItem("access-token");
  if (checkUserInfo == null && token) {
    await getUserInfo(token);
  }
  if (checkUserInfo === null) {
    alert("로그인이 필요한 페이지입니다..");
    next({ name: "signIn" });
<<<<<<< HEAD
    // router.push({ name: "signIn" });
  } else {
    // console.log("로그인 했다.");
=======
  } else {
>>>>>>> ac048782d308840093efdf3b8146c990ec2fcf95
    next();
  }
};

const routes = [
  {
    path: "/",
<<<<<<< HEAD
    name: "MainView",
    component: MainView,
  },

=======
    name: "StartView",
    component: () => import("@/views/StartView.vue"),
  },
  {
    path: "/main",
    name: "MainView",
    component: () => import("@/views/MainView.vue"),
  },
>>>>>>> ac048782d308840093efdf3b8146c990ec2fcf95
  {
    path: "/mypage",
    name: "Mypage",
    onlyAuthUser,
    component: () => import("@/views/MypageView.vue"),
  },
  {
    path: "/search",
    name: "Search",
    component: () => import("@/views/SearchView.vue"),
  },
  {
    path: "/signup",
    name: "Signup",
    component: () => import("@/views/SignupView.vue"),
  },
  {
    path: "/login",
    name: "Login",
    component: () => import("@/views/LoginView.vue"),
  },
  {
    path: "/aboutus",
    name: "Aboutus",
<<<<<<< HEAD
    component: () => import("@/views/AboutusView.vue"),
=======
    component: () => import("@/views/Information/AboutusView.vue"),
  },
  {
    path: "/pointpolicy",
    name: "Pointpolicy",
    component: () => import("@/views/Information/PointPolicy.vue"),
  },
  {
    path: "/privacypolicy",
    name: "Privacypolicy",
    component: () => import("@/views/Information/PrivacyPolicy.vue"),
>>>>>>> ac048782d308840093efdf3b8146c990ec2fcf95
  },
  {
    path: "/create",
    name: "Create",
    component: () => import("@/views/CreateView.vue"),
  },
  {
<<<<<<< HEAD
    path: "/before",
    name: "before",
    component: () => import("@/views/BeforeStart.vue"),
  },
  {
    path: "/proceeding",
    name: "proceeding",
    component: () => import("@/views/ProceedingMission.vue"),
  },
  {
    path: "/finished",
    name: "finished",
    component: () => import("@/views/FinishedMission.vue"),
  },
=======
    path: "/before/:missionNo",
    name: "before",
    component: () => import("@/views/BeforeStart.vue"),
    props: true,
  },
  {
    path: "/proceeding/:missionNo",
    name: "proceeding",
    component: () => import("@/views/ProceedingMission.vue"),
    props: true,
  },
  
>>>>>>> ac048782d308840093efdf3b8146c990ec2fcf95
  {
    path: "/hotnew",
    name: "hotnew",
    component: () => import("@/views/HotnewView.vue"),
  },
<<<<<<< HEAD
=======
  {
    path: "/avatar/:avatarColor/:avatarFace/:avatarHat/:avatarPet",
    name: "avatar",
    component: () => import("@/views/AvatarView.vue"),
    props: true,
  },

  {
    path: "/unactivated/:missionNo",
    name: "unactivated",
    component: () => import("@/views/UnactivatedMission.vue"),
  },
>>>>>>> ac048782d308840093efdf3b8146c990ec2fcf95
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
