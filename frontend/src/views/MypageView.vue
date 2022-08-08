<template>
  <div>
    <v-img height="30vh" src="../assets/img/1.png">
      <!-- 이미지 수정 버튼 -->
      <v-row>
        <v-col cols="10"></v-col>
        <v-col cols="1">
          <v-btn small class="ma-0" outlined fab color="indigo">
            <v-icon>mdi-pencil</v-icon>
          </v-btn></v-col
        >
        <v-col cols="1"></v-col>
      </v-row>
    </v-img>
    <!-- Nickname -->
    <v-row>
      <v-col cols="10">
        <v-card-title>{{ userInfo.userNickname }}</v-card-title></v-col
      >
      <v-col cols="2">
        <v-btn small class="ma-2" outlined fab color="indigo">
          <v-icon>mdi-pencil</v-icon>
        </v-btn></v-col
      >
    </v-row>
    <!-- id, Email -->
    <v-card-text>
      <div>Id : {{ userInfo.userId }}</div>
      <div>Email : {{ userInfo.userEmail }}</div>
    </v-card-text>

    <v-divider class="mx-4"></v-divider>

    <v-row>
      <v-col cols="6">
        <v-card-title>{{ userInfo.userPoint }} Point</v-card-title>
      </v-col>
      <v-col cols="3"> 충전하기 </v-col>
      <v-col cols="3">
        <v-img style="max-width: 70px" src="../assets/kakaopay.png"></v-img>
      </v-col>
    </v-row>

    <br />
    비밀번호 수정하기
    <v-btn small class="ma-2" outlined fab color="indigo">
      <v-icon>mdi-pencil</v-icon>
    </v-btn>
    <v-divider class="mx-4"></v-divider>
    <br />
    <router-link :to="{ name: 'proceeding', params: { missionNo: 1 } }"
      >참여 중인 방</router-link
    >
    <br />
  </div>
</template>
<script>
import axios from "axios";
import cookie from "vue-cookies";

export default {
  data() {
    return {
      userInfo: [],
    };
  },
  created() {
    var vm = this;
    let token = cookie.get("token");
    console.log("쿠키는?" + token);

    axios
      .get(`api/user/info`, {
        headers: {
          "Access-Control-Allow-Origin": "*",
          "Content-Type": "application/json",
        },
      })
      .then(function (response) {
        console.log(response);
        vm.userInfo = response.data;
      })
      .catch(function (err) {
        console.log(err);
      });
  },
};
</script>
