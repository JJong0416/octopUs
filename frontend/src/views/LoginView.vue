<template>
<<<<<<< HEAD
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
=======
  <div>
    <v-container>
      <v-row class="py-3">
        <v-col cols="4">
          <!-- 뒤로 가기 버튼 -->
          <v-btn @click="goback" icon>
            <v-icon> mdi-arrow-left</v-icon>
          </v-btn>
        </v-col>
        <v-col cols="4"></v-col>
        <v-col cols="4">
          <!-- 회원 가입 버튼 -->
          <v-btn @click="signup" color="#ffadad"> 회원가입 </v-btn>
        </v-col>
      </v-row>
      <br /><br /><br /><br />
      <v-row>
        <v-col cols="1">
          <v-icon>mdi-account</v-icon>
        </v-col>
        <v-col cols="2"> ID </v-col>
        <v-col cols="9"></v-col>
      </v-row>
      <v-row class="px-3 my-0">
        <v-text-field
          placeholder="아이디를 입력하세요"
          v-model="user.userId"
        ></v-text-field>
      </v-row>
      <v-row>
        <v-col cols="1">
          <v-icon>mdi-lock</v-icon>
        </v-col>
        <v-col cols="2"> Password </v-col>
        <v-col cols="9"></v-col>
      </v-row>
      <v-row class="px-3 my-0">
        <!-- 비밀번호 입력, 엿보기 기능 -->
        <v-text-field
          placeholder="비밀번호를 입력하세요"
          :type="show ? 'text' : 'password'"
          :append-icon="show ? 'mdi-eye' : 'mdi-eye-off'"
          v-model="user.userPassword"
          @keyup.enter="confirm"
          @click:append="show = !show"
        ></v-text-field>
      </v-row>
      <v-row>
        <v-col class="py-0" cols="6" @click="dialog = true"
          >비밀번호 찾기</v-col
        >
      </v-row>
      <template>
        <div class="text-center">
          <v-dialog v-model="dialog" width="500">
            <v-card>
              <v-card-title class="text-h6">
                임시 비밀번호를 발급합니다.
              </v-card-title>
              <v-text-field
                class="px-3"
                v-model="email"
                :rules="emailRules"
                label="가입시 등록한 이메일을 입력해주세요."
              ></v-text-field>
              <v-divider></v-divider>

              <v-card-actions>
                <v-btn outlined color="#143559" @click="dialog = false">
                  취소
                </v-btn>
                <v-spacer></v-spacer>
                <v-btn outlined color="#fa183e" @click="findPwByEmail">
                  임시 비밀번호 전송
                </v-btn>
              </v-card-actions>
            </v-card>
          </v-dialog>
        </div>
        <v-dialog v-model="loginFailDialog" max-width="500px">
          <v-card>
            <v-card-title>
              <h5>로그인에 실패하였습니다.</h5>
              <h6>로그인 정보를 다시 확인해주세요.</h6>
            </v-card-title>
            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn color="#fa183e" text @click="loginFailDialog = false">
                확인
              </v-btn>
            </v-card-actions>
          </v-card>
        </v-dialog>
      </template>
      <br /><br />
      <!-- 로그인 버튼 -->
      <v-row class="pa-3" justify="center">
        <v-btn color="#ffadad" block large @click="confirm"> 로그인 </v-btn>
      </v-row>
      <br />
      <!-- 카카오 회원가입 / 카카오 로그인 -->
      <v-row justify="center" class="py-5">
        <v-img
          max-width="90%"
          @click="kakaoLogin"
          src="../assets/img/Kakao/kakaostart.png"
        ></v-img>
      </v-row>
    </v-container>
  </div>
>>>>>>> ac048782d308840093efdf3b8146c990ec2fcf95
</template>

<script>
import { mapActions, mapState } from "vuex";
<<<<<<< HEAD
import http from "@/utils/http-common.js";

export default {
  data() {
    return {
=======

import axios from "axios";
export default {
  data() {
    return {
      show: false,
      email: "",
      dialog: false,
      loginFailDialog: false,
      userNickChk: true,
      usernickname: "",
>>>>>>> ac048782d308840093efdf3b8146c990ec2fcf95
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
<<<<<<< HEAD
    };
  },

=======
      emailRules: [
        (v) => !!v || "E-mail is required",
        (v) => {
          if (v) {
            const replaceV = v.replace(/(\s*)/g, "");
            const pattern =
              /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/;
            return pattern.test(replaceV) || "이메일 형식이 올바르지 않습니다.";
          } else {
            return !!v || "E-mail is required";
          }
        },
      ],
      nameRules: [
        (v) => !!v || "Name is required",
        (v) => (v && v.length <= 10) || "Name must be less than 10 characters",
      ],
    };
  },
>>>>>>> ac048782d308840093efdf3b8146c990ec2fcf95
  computed: {
    ...mapState("memberStore", ["isLogin", "isLoginError"]),
  },
  methods: {
<<<<<<< HEAD
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
=======
    kakaoLogin() {
      window.location.replace(
        "https://kauth.kakao.com/oauth/authorize?client_id=9a4a29dd046d8945a94faa4566beb2f9&redirect_uri=http://34.64.41.23/main&response_type=code"
      );
    },
    goback() {
      this.$router.go(-1);
    },
    ...mapActions("memberStore", ["userConfirm", "getUserInfo"]),

    async confirm() {
      await this.userConfirm(this.user);
      if (this.isLogin) {
        this.$router.push({ name: "MainView" });
      } else {
        this.loginFailDialog = true;
      }
    },
    nickcheck() {
      axios
        .get(`api/register/check/nickname/${this.usernickname}`)
        .then(({ data }) => {
          let msg = "중복된 닉네임입니다. 다시 입력해주세요";
          if (data === false) {
            msg = "사용가능한 닉네임입니다.";
            this.userNickChk = true;
            alert(msg);
            this.KakaoNickname(this.usernickname);
          } else {
            this.userNickChk = false;
            alert(msg);
          }
        })
        .catch((error) => {
          console.log(error);
          alert("로그인 정보가 잘못되었습니다.", { icon: "error" });
        });
    },

    findPwByEmail() {
      axios
        .post("/api/find-pw", { userEmail: this.email })
        .then(() => {
          alert("이메일로 임시비밀번호가 발송되었습니다.");
        })
        .catch((error) => {
          console.log(error);
        })
        .finally(() => {
          this.dialog = false;
        });
    },
    signup() {
      this.$router.push({ name: "Signup" });
>>>>>>> ac048782d308840093efdf3b8146c990ec2fcf95
    },
  },
};
</script>
<<<<<<< HEAD
=======

<style></style>
>>>>>>> ac048782d308840093efdf3b8146c990ec2fcf95
