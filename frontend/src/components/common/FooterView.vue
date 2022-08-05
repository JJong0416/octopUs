<template>
  <div class="footer-nav">
    <v-tabs fixed-tabs background-color="#FFF4F2">
      <!-- 홈 화면 탭 -->
      <v-tab href="#mobile-tabs-5-1" class="primary--text">
        <router-link :to="`/`"> <v-icon>mdi-home</v-icon></router-link>
      </v-tab>
      <!-- Hot 탭 -->
      <v-tab href="#mobile-tabs-5-2" class="primary--text">
        <router-link :to="`/hotnew`"> <v-icon>mdi-fire</v-icon></router-link>
      </v-tab>
      <!-- 검색 탭 -->
      <v-tab href="#mobile-tabs-5-3" class="primary--text">
        <router-link :to="`/search`"><v-icon>mdi-magnify</v-icon></router-link>
      </v-tab>
      <!-- 마이페이지 탭 -->
      <v-tab href="#mobile-tabs-5-4" class="primary--text">
        <router-link :to="`/mypage`"><v-icon>mdi-account</v-icon></router-link>
      </v-tab>
    </v-tabs>
  </div>
</template>

<script>
export default {};
</script>

<style>
.v-tab a {
  text-decoration: none;
  color: #ff2456;
}
.v-tab a:hover {
  text-decoration: none;
  color: #ff2456;
}
.footer-nav {
  height: 5.4vh;
}
</style>
