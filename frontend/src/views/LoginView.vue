<template>
  <v-app id="app">
    <v-main>
      <v-container
        style="position: relative; top: 20%; margin: 10px"
        class="text-xs-center"
      >
        <v-layout row wrap class="text-xs-center">
          <v-flex>
            <v-card flat class="mx-auto" max-width="800">
              <v-row style="margin-top: 60px">
                <v-col>
                  <v-form style="width: 350px; height: 300px">
                    <div class="mx-3">
                      <v-icon>mdi-account</v-icon>
                      Id
                      <div class="mx-1">
                        <v-text-field
                          placeholder="userId"
                          v-model="user.userId"
                          required
                        ></v-text-field>
                      </div>
                    </div>
                    <div class="mx-3">
                      <v-icon>mdi-lock</v-icon>
                      Password
                      <div class="mx-1">
                        <v-text-field
                          placeholder="userPassword"
                          type="password"
                          v-model="user.userPassword"
                          required
                        ></v-text-field>
                      </div>
                    </div>

                    <v-card-actions>
                      <v-btn color="#ff809d" dark large block @click="confirm"
                        >Login</v-btn
                      >
                    </v-card-actions>
                    <v-card-actions>
                      <v-img
                        @click="KakaoLogin"
                        src="../assets/kakaologin.png"
                      ></v-img>
                    </v-card-actions>
                  </v-form>
                </v-col>
              </v-row>
            </v-card>
          </v-flex>
        </v-layout>
      </v-container>
    </v-main>
  </v-app>
</template>

<script>
import { mapActions, mapState } from "vuex";
import http from "@/utils/http-common.js";

export default {
  data() {
    return {
      user: {
        userId: "",
        userPassword: "",
      },
      kakaouser: {
        userid: null,
        userpwd: null,
        username: null,
        email: null,
      },
    };
  },

  computed: {
    ...mapState("memberStore", ["isLogin", "isLoginError"]),
  },
  methods: {
    ...mapActions("memberStore", ["userConfirm", "getUserInfo"]),

    async confirm() {
      console.log(this.user);
      await this.userConfirm(this.user);
      // let token = sessionStorage.getItem("access-token");
      console.log(this.isLogin);
      if (this.isLogin) {
        // await this.getUserInfo(token);
        this.$router.push({ name: "MainView" });
      } else {
        alert("로그인 정보가 잘못되었습니다.", { icon: "error" });
      }
    },
    movePage() {
      this.$router.push({ name: "signUp" });
    },
    KakaoLogin() {
      console.log(window.Kakao);
      window.Kakao.Auth.login({
        scope: "account_email, gender, profile_nickname",
        success: this.GetMe,
        fail: function (error) {
          console.log(error);
        },
      });
    },
    GetMe(authObj) {
      console.log(authObj);
      window.Kakao.API.request({
        url: "/v2/user/me",
        success: (res) => {
          const kakao_account = res.kakao_account;
          console.log(kakao_account);
          alert("로그인 성공 !!");
          console.log(kakao_account);
          this.login(kakao_account);
        },
      });
    },
    async login(kakao_account) {
      const loginuser = {
        userid: kakao_account.email,
        userpwd: "kakao",
        username: kakao_account.profile.nickname,
        email: kakao_account.email,
      };
      http
        .get("/user/idcheck/" + loginuser.userid)
        .then(({ data }) => {
          console.log(data);
          this.registerKakao(loginuser);
        })
        .catch(({ error }) => {
          console.log(error);
          this.confirmKakao(loginuser);
        });
    },
    registerKakao(login_user) {
      http
        .post("/user/register", login_user)
        .then(({ data }) => {
          console.log(data);
          this.confirmKakao(login_user);
        })
        .catch(({ error }) => {
          console.log(error);
          this.confirmKakao(login_user);
        });
    },
    async confirmKakao(loginuser) {
      await this.userConfirm(loginuser);
      let token = sessionStorage.getItem("access-token");

      if (this.isLogin) {
        await this.getUserInfo(token);
        this.$router.push({ name: "MainView" });
      }
    },
  },
};
</script>
