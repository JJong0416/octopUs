<template>
  <v-container>
    <v-layout align-center row wrap>
      <v-flex xs12>
        <v-alert v-if="isError" type="error">
          {{ errorMsg }}
        </v-alert>
        <v-card>
          <v-toolbar flat color="indigo">
            <v-toolbar-title
              ><span class="white--text">회원가입</span></v-toolbar-title
            >
          </v-toolbar>
          <div class="pa-5">
            <v-form ref="form" v-model="valid" lazy-validation>
              <v-text-field
                v-model="user.email"
                :rules="emailRules"
                label="Enter E-mail"
                required
              ></v-text-field>
              인증번호 전송
              <v-icon @click="sendemail">mdi-check-bold</v-icon>
          <!-- 이메일 인증 내용 추가 위치 -->
              <div v-if="issendemail">
                  <v-text-field
                    v-model="aouthcode"
                    label="인증번호 입력"
                    required
                  ></v-text-field>
                  인증번호 확인
                  <v-icon @click="codecheck">mdi-check-bold</v-icon>
              </div>
              <!--  -->
              <v-text-field
                v-model="user.userid"
                label="userid"
                required
              ></v-text-field>
              중복검사
              <v-icon @click="idcheck">mdi-check-bold</v-icon>

              <!-- 아바타 선택 화면 변경 -->
              <!-- <v-text-field
                v-model="user.avatar"
                label="Avatar"
                required
              ></v-text-field> -->
              <br>
              <br>
              아바타선택
              <v-container class="pa-4 text-center">
                  <v-row
                    class="fill-height"
                    align="center"
                    justify="center"
                  >
                    <template v-for="(avatar, i) in avatars">
                      <v-col
                        :key="i"
                        cols="3"
                        md="4"
                      >
                        <v-hover v-slot="{ hover }">
                          <v-card
                            :elevation="hover ? 12 : 2"
                            :class="{ 'on-hover': hover }"
                            @click="setavater(avatars[i].num)"
                          >
                            <v-img
                              :src="require(`@/assets/img/Ocsoon/Character/${avatars[i].num}.png`)"
                              height="55px"
                              width="55px"
                              object-fit=cover
                            >
                            <v-img
                              src="../assets/img/Ocsoon/Face/Default.png"
                              height="55px"
                              width="55px"
                              object-fit=cover
                            ></v-img>
                            </v-img>
                          </v-card>
                        </v-hover>
                      </v-col>
                    </template>
                  </v-row>
                </v-container>



<!--  -->
              <v-text-field
                v-model="user.usernickname"
                :counter="10"
                :rules="nameRules"
                label="NickName"
                required
              ></v-text-field>
              <!-- 닉네임 중복검사 추가 -->
              중복검사
              <v-icon @click="nickcheck">mdi-check-bold</v-icon>

              <v-text-field
                v-model="user.userpwd"
                :append-icon="show ? 'mdi-eye' : 'mdi-eye-off'"
                :rules="[rules.required, rules.min]"
                :type="show ? 'text' : 'password'"
                label="Enter Password"
                hint="At least 8 characters"
                counter
                @click:append="show = !show"
              ></v-text-field>

              <v-text-field
                v-model="chkPassword"
                :append-icon="show ? 'mdi-eye' : 'mdi-eye-off'"
                :rules="[rules.required, rules.min]"
                :type="show ? 'text' : 'password'"
                label="Enter Password Again"
                hint="At least 8 characters"
                counter
                @click:append="show = !show"
              ></v-text-field>

              <h6 v-if="sameChk(chkPassword)" class="mb-5 teal--text accent-3">
                Please create the two passwords identical.
              </h6>
              <h6 v-else class="mb-5 red--text lighten-2">
                Please create the two passwords identical.
              </h6>

              <div class="mt-3 d-flex flex-row-reverse">
                <v-btn color="error" class="mr-4" @click="reset"> 리셋 </v-btn>

                <v-btn
                  :disabled="!valid"
                  color="blue"
                  class="mr-4"
                  @click="register()"
                >
                  회원가입
                </v-btn>
              </div>
            </v-form>
          </div>
        </v-card>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
import axios from "axios";
// import { apiInstance } from "../api/index.js";

// import http from "../utils/http-common.js";

// const api = apiInstance();

export default {
  data: () => ({
    user: {
      userid: null,
      userpwd: null,
      usernickname: null,
      email: null,
      avatar: null,
    },
    aouthcode : null,
    issendemail : false,
    userIdChk: true,
    userNickChk : true,
    codeChk : false,

    // 아바타들
    avatars:[
      {num:1},
      {num:2},
      {num:8},
      {num:9},
      {num:10},
      {num:11},
      {num:12},
      {num:13},
    ],
    

    valid: false,
    nameRules: [
      (v) => !!v || "Name is required",
      (v) => (v && v.length <= 10) || "Name must be less than 10 characters",
    ],
    isError: false,
    errorMsg: "",
    emailRules: [
      (v) => !!v || "E-mail is required",
      (v) => /.+@.+\..+/.test(v) || "E-mail must be valid",
    ],
    show: false,
    chkPassword: "",
    rules: {
      required: (value) => !!value || "Required.",
      min: (v) => v.length >= 8 || "Min 8 characters",
    },
  }),
  methods: {
    idcheck() {
      axios
        .get(`api/register/check/id/${this.user.userid}`)
        .then(({ data }) => {
          console.log("아이디중복확인 리턴: "+ data)
          let msg = "중복된 아이디입니다. 다시 입력해주세요";
          if (data === false) {
            msg = "사용가능한 아이디입니다.";
            this.userIdChk = true;
            alert(msg);
          } else {
            this.userIdChk = false;
            alert(msg);

          }
        })
        .catch((error) => {
          console.log(error);
          alert("ID중복체크에 실패했습니다..");
        })
        .finally(() => {
          console.log(this.user.userid);
        });
    },
    nickcheck() {
      axios
        .get(`api/register/check/nickname/${this.user.usernickname}`)
        .then(({ data }) => {
          let msg = "중복된 닉네임입니다. 다시 입력해주세요";
          if (data === false) {
            msg = "사용가능한 닉네임입니다.";
            this.userNickChk = true;
            alert(msg);
          } else {
            this.userNickChk = false;
            alert(msg);
          }
        })
        .catch((error) => {
          console.log(error);
          alert("닉네임중복체크에 실패했습니다..");
        })
    },
    //  인증코드 전송
    codecheck() { 
      axios
        .post(`api/email/check`, {
          userEmail: this.user.email,
          emailCode: this.aouthcode,
        })
        .then(({ data }) => {
          let msg = "인증오류";
          console.log("인증코드 전송후 리턴 : "+data)
          if (data===true) {
            msg = "인증되었습니다.";
            this.codeChk = true;
            alert(msg);
          } else {
            this.codeChk = false;
            alert(msg);
          }
        })
        .catch((error) => {
          console.log(error);
          alert("ID중복체크에 실패했습니다..");
        })
        .finally(() => {
          console.log(this.user.userid);
        });
    },
    //  이메일 전송
    sendemail() { 
      axios
        .post(`api/email`, {
          userEmail: this.user.email,
        })
        .then(({ data }) => {
          console.log(data);
          let msg = "email 전송에 실패했습니다..";
          if (data === true) {
            msg = "인증번호가 전송되었습니다.";
            this.issendemail=true;
            alert(msg);
          } else {
            alert(msg);
          }
        })
        .catch((error) => {
          console.log(error);
          alert("email 전송에 실패했습니다..");
        })
    },
    movePage() {
      this.$router.push({ name: "Login" });
    },

    goToMain() {
      this.$router.push({
        name: "MainView",
      });
    },
    sameChk(password) {
      if (this.user.userpwd == password) return true;
      else {
        this.valid = false;
        return false;
      }
    },
    register() {
      if (
        !this.user.email ||
        !this.user.usernickname ||
        !this.user.userpwd ||
        !this.user.avatar ||
        !this.codeChk ||
        !this.userIdChk ||
        !this.userNickChk
      ) {
        this.isError = true;

        this.errorMsg = "이메일과 닉네임과 비밀번호를 모두 입력해주세요.";
        return;
      }
      axios
        .post(`api/register/domain`, {
          userId: this.user.userid,
          userPassword: this.user.userpwd,
          userEmail: this.user.email,
          userNickname: this.user.usernickname,
          userAvatar: this.user.avatar,
        })
        .then(({ data }) => {
          let msg = "등록 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "등록이 완료되었습니다.";
            console.log(msg);
          }
          console.log(data);
          alert(msg);
          this.movePage();
        })
        .catch((error) => {
          console.log(error);
          alert("등록 실패입니다.");
        })
        .finally(() => {
          console.log(this.user.userid);
          console.log(this.user.userpwd);
          console.log(this.user.username);
        });
    },
    validate() {
      this.$refs.form.validate();
    },
    reset() {
      this.$refs.form.reset();
    },
    resetValidation() {
      this.$refs.form.resetValidation();
    },
    setavater(i){
      this.user.avatar=i;
      console.log(this.user.avatar)
    }
  },
};
</script>

<style scoped>

.v-card:not(.on-hover) {
  opacity: 0.8;
 }
</style>
