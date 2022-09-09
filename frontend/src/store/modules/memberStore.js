import jwt_decode from "jwt-decode";
import { login } from "@/api/member.js";
import { findById } from "../../api/member";
import cookie from "vue-cookies";
import axios from "axios";
const memberStore = {
  namespaced: true,
  state: {
    isLogin: false,
    isLoginError: false,
    userInfo: null,
  },
  getters: {
    checkUserInfo: function (state) {
      return state.userInfo;
    },
  },
  mutations: {
    SET_IS_LOGIN: (state, isLogin) => {
      state.isLogin = isLogin;
    },
    SET_IS_LOGIN_ERROR: (state, isLoginError) => {
      state.isLoginError = isLoginError;
    },
    SET_USER_INFO: (state, userInfo) => {
<<<<<<< HEAD
      console.log(userInfo);
      state.isLogin = true;
=======
>>>>>>> ac048782d308840093efdf3b8146c990ec2fcf95
      state.userInfo = userInfo;
    },
  },
  actions: {
    async userConfirm({ commit }, user) {
      await login(
        user,
        (response) => {
<<<<<<< HEAD
          console.log(response);
          console.log("login은 일단됨, 근데 response모름");
          if (response.status === 200) {
            console.log("rd ? " + response.data);
            console.log("rdt ? " + response.data.token);
            let token = response.data.token;
            cookie.set("token", token);
            console.log("쿠키가 없는것같기도...");
            console.log("쿠키있나요?" + cookie.isKey("token"));
            commit("SET_IS_LOGIN", true);
            commit("SET_IS_LOGIN_ERROR", false);

=======
          if (response.status === 200) {
            let token = response.data.token;
            cookie.set("token", token);
            // console.log("쿠키가 없는것같기도...");
            // console.log("쿠키있나요?" + cookie.isKey("token"));
            sessionStorage.setItem("token", token);
            commit("SET_IS_LOGIN", true);
            commit("SET_IS_LOGIN_ERROR", false);
            commit("SET_USER_INFO", token);
>>>>>>> ac048782d308840093efdf3b8146c990ec2fcf95
            // header에 토큰 담기
            axios.defaults.headers.common[
              "Authorization"
            ] = `Bearer ${response.data.token}`;
          } else {
            commit("SET_IS_LOGIN", false);
            commit("SET_IS_LOGIN_ERROR", true);
          }
        },
        () => {}
      );
    },
    getUserInfo({ commit }, token) {
      let decode_token = jwt_decode(token);
      findById(
        decode_token.userid,
        (response) => {
          if (response.data.message === "success") {
            commit("SET_USER_INFO", response.data.userInfo);
          } else {
            console.log("유저 정보 없음!!");
          }
        },
        (error) => {
          console.log(error);
        }
      );
    },
  },
};

export default memberStore;
