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
                          @keyup.enter="confirm"
                        ></v-text-field>
                      </div>
                      <div class="text-center">
                        <v-dialog v-model="dialog" width="500">
                          <template v-slot:activator="{ on, attrs }">
                            <v-btn v-bind="attrs" v-on="on">
                              비밀번호 찾기
                            </v-btn>
                          </template>

                          <v-card>
                            <v-card-title class="text-h5 red lighten-2">
                              이메일을 입력해주세요
                            </v-card-title>
                            <v-card-actions>
                              <v-text-field
                                v-model="email"
                                label="Email"
                                hide-details="auto"
                              ></v-text-field>
                            </v-card-actions>
                            <v-divider></v-divider>

                            <v-card-actions>
                              <v-spacer></v-spacer>
                              <v-btn
                                color="primary"
                                dark
                                @click="findPwByEmail"
                              >
                                Send Email
                              </v-btn>
                              <v-btn
                                color="primary"
                                text
                                @click="dialog = false"
                              >
                                Close
                              </v-btn>
                            </v-card-actions>
                          </v-card>
                        </v-dialog>
                      </div>
                    </div>

                    <v-card-actions>
                      <v-btn color="#ffadad" dark large block @click="confirm"
                        >Login</v-btn
                      >
                    </v-card-actions>
                    <v-card-actions>
                      <v-btn @click="signup" color="#ffadad" dark large block
                        >Sign Up</v-btn
                      >
                    </v-card-actions>
                    <v-card-actions>
                      <v-img
                        @click="KakaoLogin"
                        src="../assets/img/Kakao/kakaologin.png"
                      ></v-img>
                    </v-card-actions>
                    <v-card-actions>
                      <v-img
                        @click="KakaoLogin"
                        src="../assets/img/Kakao/kakao_login_large_wide.png"
                      ></v-img>
                    </v-card-actions>

                    <!-- 회원가입 -->
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
import axios from "axios";

export default {
  data() {
    return {
      email: "",
      dialog: false,
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
      console.log(this.isLogin);
      if (this.isLogin) {
        this.$router.push({ name: "MainView" });
      } else {
        alert("로그인 정보가 잘못되었습니다.", { icon: "error" });
      }
    },
    findPwByEmail() {
      axios
        .post("/api/find-pw", { userEmail: this.email })
        .then((response) => {
          console.log(response);
          alert("이메일로 임시비밀번호가 발송되었습니다.");
        })
        .catch((error) => {
          console.log(error);
        })
        .finally();
    },
    signup() {
      this.$router.push({ name: "Signup" });
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
<style>
#app {
  height: 90vh;
}
</style>
