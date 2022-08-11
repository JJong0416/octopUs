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
          <v-btn @click="signup" color="#ffadad">
            회원가입
          </v-btn>
        </v-col>
      </v-row>
      <br><br><br><br>
      <v-row>
        <v-col cols="1">
          <v-icon>mdi-account</v-icon>
        </v-col>
        <v-col cols="2">
          ID
        </v-col>
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
        <v-col cols="2">
          Password
        </v-col>
        <v-col cols="9"></v-col>
      </v-row>
      <v-row class="px-3 my-0">
          <!-- 비밀번호 입력, 엿보기 기능 -->
          <v-text-field
          placeholder="비밀번호를 입력하세요"
          v-model="user.userPassword"
          @keyup.enter="confirm"
          ></v-text-field>
      </v-row>
      <!-- 로그인 버튼 -->
      <v-row class="pa-3" justify="center">
        <v-btn  color="#ffadad" block large @click="confirm">
          로그인
        </v-btn>
      </v-row>
      <v-row >
        <v-col class="py-0 pl-3" cols="6">아이디 찾기</v-col>
        <v-col cols="2"></v-col>
        <v-col class="py-0" cols="4">비밀번호 찾기</v-col>
      </v-row>
      <br><br><br>
      <!-- 카카오 회원가입 / 카카오 로그인 -->
      <v-row justify="center" class="py-5">
        <v-img max-width="90%" @click="KakaoLogin" src="../assets/img/Kakao/kakaostart.png"></v-img>
      </v-row>
      <v-row justify="center">
        <v-img max-width="90%" @click="KakaoLogin" src="../assets/img/Kakao/kakaolog.png"></v-img>
      </v-row>
    </v-container>
  </div>
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
    goback(){
      this.$router.go(-1);
    },
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
          alert("닉네임중복체크에 실패했습니다..");
        });
    },
    KakaoNickname(nickname) {
      axios
        .post(`api/~`, { nickname: nickname })
        .then((response) => {
          console.log(response);
        })
        .catch((error) => {
          console.log(error);
        });
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
    KakaoSignup() {},
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
}
</script>

<style>

</style>