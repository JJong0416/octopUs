<template>
  <v-card class="footer">
    <v-footer fixed padless>
      <v-card
        flat
        tile
        width="100%"
        class="text-center"
        height="10vh"
        color="#white"
      >
        <v-card-text>
          <v-btn class="mx-4" icon>
            <router-link :to="'/main'">
              <v-icon size="24px"> mdi-home </v-icon></router-link
            >
          </v-btn>
          <v-btn class="mx-4" icon>
            <router-link :to="'/hotnew'">
              <v-icon size="24px"> mdi-fire </v-icon></router-link
            >
          </v-btn>
          <v-btn class="mx-4" icon>
            <router-link :to="'/search'">
              <v-icon size="24px"> mdi-magnify </v-icon></router-link
            >
          </v-btn>
          <v-btn v-if="userInfo" class="mx-4" icon>
            <router-link :to="'/mypage'">
              <v-icon size="24px"> mdi-account </v-icon></router-link
            >
          </v-btn>
          <v-btn v-if="!userInfo" class="mx-4" icon>
            <router-link :to="`/login`">
              <v-icon size="24px">mdi-login-variant</v-icon>
            </router-link>
          </v-btn>
        </v-card-text>
      </v-card>
    </v-footer>
  </v-card>
</template>

<script>
import { mapState } from "vuex";
const memberStore = "memberStore";

export default {
  computed: {
    ...mapState(memberStore, ["isLogin", "userInfo"]),
  },
};
</script>

<style>
.v-btn a {
  text-decoration: none;
  color: #143559;
}
.v-btn a:hover {
  text-decoration: none;
  color: #143559;
}
.footer{
  height: 10vh;
}
</style>
