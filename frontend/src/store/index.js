import Vue from "vue";
import Vuex from "vuex";
import createPersistedState from "vuex-persistedstate";
import EasyCamera from 'easy-vue-camera';
Vue.use(Vuex);
Vue.use(EasyCamera);
import memberStore from "@/store/modules/memberStore.js";

const store = new Vuex.Store({
  modules: {
    memberStore,
  },
  plugins: [
    createPersistedState({
      // 브라우저 종료시 제거하기 위해 localStorage가 아닌 sessionStorage로 변경. (default: localStorage)
      storage: sessionStorage,
    }),
  ],
});

export default store;
