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
      <v-col cols="3"></v-col>
      <v-col cols="5">
        <!-- 회원 가입 버튼 -->
        <v-btn outlined @click="login" color="#143559">
          로그인하러 가기
        </v-btn>
      </v-col>
    </v-row>
    <v-row class="pl-3">
      <h4>이메일을 통해 본인임을 인증합니다.</h4>
    </v-row>
    <v-row>
      <v-col class="py-0 pr-3" cols="9">
        <v-text-field
          v-model="user.email"
          :rules="[rules.required,emailRules]"
          label="사용가능한 이메일을 입력해주세요."
        ></v-text-field>
      </v-col>
      <v-col class="px-0" cols="3">
        <v-btn class="px-0" @click="sendemail">인증하기</v-btn>
      </v-col>
    </v-row>
    <!-- email 인증 추가하기 -->
    <v-row>
      <v-col class="py-0" cols="8">
        <!-- id input -->
        <v-text-field
          v-model="user.userid"
          label="아이디를 입력해주세요."
          :rules="[rules.required]"
          @change="userIdChk = false"
        ></v-text-field>
      </v-col>
      <v-col cols="4">
        <v-btn class="px-0" @click="idcheck">중복 검사</v-btn>
      </v-col>
    </v-row>
    <v-row>
      <v-col class="py-0">
        <!-- password input -->
        <v-text-field
          v-model="user.userpwd"
          :append-icon="show? 'mdi-eye' : 'mdi-eye-off'"
          :rules="[rules.required, rules.min]"
          :type="show? 'text' : 'password'"
          label="비밀번호를 설정해주세요."
          hint="8자 이상 입력하세요"
          @click:append="show = !show"
          counter
        ></v-text-field>
      </v-col>
    </v-row>
    <v-row>
      <v-col class="py-0">
        <!-- password input again -->
        <v-text-field
          v-model="chkPassword"
          :append-icon="show? 'mdi-eye' : 'mdi-eye-off'"
          :rules="[rules.required, rules.min]"
          :type="show? 'text' : 'password'"
          label="비밀번호를 다시 한 번 입력해주세요."
          hint="8자 이상 입력하세요"
          @click:append="show = !show"
          counter
        ></v-text-field>
      </v-col>
    </v-row>
    <v-row justify="center">
      <h5 v-if="!sameChk(chkPassword)" class="pink--text">
        비밀번호가 같지 않습니다. 다시 한 번 확인해주세요.
      </h5>
    </v-row>
    <v-row>
      <v-col class="py-0" cols="8">
        <v-form ref="nicknameForm" lazy-validation>
        <!-- nickname input -->
        <v-text-field
          v-model="user.usernickname"
          :counter="10"
          label="닉네임을 입력해주세요."
          :rules="nameRules"
          @change="userNickChk = false"
        ></v-text-field></v-form>
      </v-col>
      <v-col cols="4">
        <v-btn class="px-0" @click="nickcheck">중복 검사</v-btn>
      </v-col>
    </v-row>
    <v-row justify="center">
      <h5>옥순이 캐릭터를 골라주세요. 차후 포인트 차감 후 변경 가능합니다.</h5>
    </v-row>
    <v-row justify="center">
      <v-img max-width="30%" :src="require(`../assets/img/Ocsoon/Character/${this.user.avatar}.png`)">
        <v-img src="../assets/img/Ocsoon/Face/0.png"></v-img>
      </v-img>
    </v-row>
    <v-row justify="center" class="py-0">
      <v-btn max-height="30" color="#fa183e" @click="changeColor(0)"/>
      <v-btn max-height="30" color="#ffbbed" @click="changeColor(1)"/>
      <v-btn max-height="30" color="#ffec00" @click="changeColor(2)"/>
      <v-btn max-height="30" color="#b9ffb2" @click="changeColor(3)"/>
    </v-row>
    <v-row justify="center" class="py-0">
      <v-btn max-height="30" color="#a5bbff" @click="changeColor(7)"/>
      <v-btn max-height="30" color="#003fff" @click="changeColor(4)"/>
      <v-btn max-height="30" color="#d2a1ff" @click="changeColor(5)"/>
      <v-btn max-height="30" color="#8500ff" @click="changeColor(6)"/>
    </v-row>
    <v-row>
      <v-col>
        <v-btn outlined @click="reset" color="red">Reset</v-btn>
      </v-col>
      <v-col></v-col>
      <v-col>
        <v-btn color="#ffadad">회원가입하기</v-btn>
      </v-col>
    </v-row>
  </v-container>
</div>
</template>

<script>
import axios from "axios";
export default {
  data:()=>({
    user:{
      userid: null,
      userpwd: null,
      usernickname: null,
      email: null,
      avatar: 0,
    },
    aouthcode: null,
    issendemail: false,
    userIdChk: true,
    userNickChk: true,
    codeChk: false,
    valid : false,
    

    avatars: [
      { num: 0 },
      { num: 1 },
      { num: 2 },
      { num: 3 },
      { num: 4 },
      { num: 5 },
      { num: 6 },
      { num: 7 },
    ],

    nameRules: [
      (v) => !!v || "Name is required",
      (v) => (v && v.length <= 10) || "Name must be less than 10 characters",
      (v) => (v && v.length >= 4) || "Name must be at least 4 characters",
     
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
  methods:{
    changeColor(value) {
      this.user.avatar= value;
    },
    goback(){
      this.$router.go(-1);
    },
    login(){
      this.$router.push({ name: "Login" });
    },
    idcheck() {
      axios
        .get(`api/register/check/id/${this.user.userid}`)
        .then(({ data }) => {
          console.log("아이디중복확인 리턴: " + data);
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
      const validate = this.$refs.nicknameForm.validate();
      if(validate){
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
        });
         }else{
        alert("닉네임이 유효하지 않습니다.")
      }
    },
    codecheck() {
      axios
        .post(`api/email/check`, {
          userEmail: this.user.email,
          emailCode: this.aouthcode,
        })
        .then(({ data }) => {
          let msg = "인증오류";
          console.log("인증코드 전송후 리턴 : " + data);
          if (data === true) {
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
            this.issendemail = true;
            alert(msg);
          } else {
            alert(msg);
          }
        })
        .catch((error) => {
          console.log(error);
          alert("email 전송에 실패했습니다..");
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
        this.user.avatar===null ||
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
          console.log(data);
          let msg = "등록이 완료되었습니다.";
          console.log(msg);
          alert(msg);
          this.movePage();
        })
        .catch((error) => {
          console.log(error);
          alert("등록 실패입니다.");
        })
        .finally(() => {

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
    setavater(i) {
      this.user.avatar = i;
      console.log(this.user.avatar);
    },
  }
}
</script>

<style>

</style>