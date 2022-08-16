<template>
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
</template>

<script>
import { mapActions, mapState } from "vuex";

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
  computed: {
    ...mapState("memberStore", ["isLogin", "isLoginError"]),
  },
  methods: {
    kakaoLogin() {
      window.location.replace(
        "https://kauth.kakao.com/oauth/authorize?client_id=9a4a29dd046d8945a94faa4566beb2f9&redirect_uri=http://localhost:8080/main&response_type=code"
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
    },
  },
};
</script>

<style></style>
