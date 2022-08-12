<template>
  <div class="start">
    <v-container>
      <br /><br />
      <!-- Title -->
      <v-row>
        <v-col cols="12">
          <v-img src="../assets/Title.png" />
        </v-col>
      </v-row>
      <!-- Logo -->
      <v-row>
        <v-col class="animate__animated animate__lightSpeedInLeft">
          <v-img src="../assets/Logo.png" />
        </v-col>
      </v-row>
      <br /><br />
      <!-- 비회원 계속하기 -->
      <v-row>
        <v-col class="nonejoined" cols="12">
          <router-link :to="`/main`">
            <v-btn color="white" block large>비회원으로 계속하기</v-btn>
          </router-link>
        </v-col>
      </v-row>
      <!-- 로그인 -->
      <v-row>
        <v-col class="login" cols="12">
          <router-link :to="`/login`">
            <v-btn color="#ffadad" block large>로그인</v-btn>
          </router-link>
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>

<script>
import "animate.css";
export default {};
</script>

<style>
.start {
  height: 100vh;
  background-color: #eefaec;
}
.login a {
  text-decoration-line: none;
}
.login a:hover {
  text-decoration-line: none;
}
.nonejoined a {
  text-decoration-line: none;
}
.nonejoined a:hover {
  text-decoration-line: none;
}
::v-deep .v-btn__content {
  color: #143559 !important;
}
</style>
