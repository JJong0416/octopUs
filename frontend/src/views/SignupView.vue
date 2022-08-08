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

              <v-text-field
                v-model="user.userid"
                label="userid"
                required
              ></v-text-field>
              중복검사
              <v-icon @click="idcheck">mdi-check-bold</v-icon>

              <v-text-field
                v-model="user.avatar"
                label="Avatar"
                required
              ></v-text-field>

              <v-text-field
                v-model="user.usernickname"
                :counter="10"
                :rules="nameRules"
                label="Name"
                required
              ></v-text-field>

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
    userIdChk: true,

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
        .get(`api/user/idcheck/${this.user.userid}`)
        .then(({ data }) => {
          let msg = "중복된 아이디입니다. 다시 입력해주세요";
          if (data === "success") {
            msg = "사용가능한 아이디입니다.";
            this.userIdChk = true;
            alert(msg);
          } else {
            alert(msg);
            this.$router.push({ name: "signUp" });
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
        !this.user.avatar
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
  },
};
</script>
