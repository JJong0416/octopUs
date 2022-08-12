<template>
  <div class="header">
    <v-container>
      <v-row>
        <v-col cols="2">
          <v-menu offset-y>
            <template v-slot:activator="{ on, attrs }">
              <v-btn
                color="#143559"
                v-on="on"
                v-bind="attrs"
                icon
                class="py-7 pl-9"
              >
                <v-icon size="6vh">mdi-information-outline</v-icon>
              </v-btn>
            </template>
            <v-list>
              <v-list-item>
                <v-list-item-title>
                  <router-link :to="`../aboutus`"> About US </router-link>
                </v-list-item-title>
              </v-list-item>
              <v-list-item>
                <v-list-item-title>
                  <router-link :to="`../pointpolicy`">
                    포인트 정책
                  </router-link>
                </v-list-item-title>
              </v-list-item>
              <v-list-item>
                <v-list-item-title>
                  <router-link :to="`../privacypolicy`">
                    개인정보 취급
                  </router-link>
                </v-list-item-title>
              </v-list-item>
              <v-divider></v-divider>
              <v-list-item>
                <v-list-item-title>
                  <v-btn class="pa-0" text> 회원 탈퇴하기 </v-btn>
                </v-list-item-title>
              </v-list-item>
            </v-list>
          </v-menu>
        </v-col>
        <v-col class="pl-10 py-4" cols="8">
          <router-link :to="`/main`">
            <v-img max-width="85%" src="../../assets/Title.png" />
          </router-link>
        </v-col>
        <v-col cols="2">
          <div class="rightbtn">
            <v-btn
              color="#143559"
              class="py-7 pr-9"
              @click="onClickLogout"
              icon
            >
              <v-icon size="6vh">mdi-logout</v-icon>
            </v-btn>
          </div>
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>

<script>
import { mapMutations, mapState } from "vuex";
import cookie from "vue-cookies";
const memberStore = "memberStore";
export default {
  computed: {
    ...mapState(memberStore, ["isLogin", "userInfo"]),
  },
  methods: {
    ...mapMutations(memberStore, ["SET_IS_LOGIN", "SET_USER_INFO"]),
    onClickLogout() {
      // console.log("memberStore : ", ms);
      this.SET_IS_LOGIN(false);
      this.SET_USER_INFO(null);
      cookie.remove("token");

      alert("로그아웃됐습니다");
      this.drawer = false;
      window.location.replace("http://localhost:8080");
    },
  },
};
</script>

<style>
.rightbtn a {
  text-decoration: none;
  color: none;
}
.rightbtn a:hover {
  text-decoration: none;
  color: none;
}
.leftbtn a {
  text-decoration: none;
  color: none;
}
.leftbtn a:hover {
  text-decoration: none;
  color: none;
}
.header {
  background-color: #eefaec;
}
.v-list-item a {
  text-decoration: none;
  color: none;
}
.v-list-item a:hover {
  text-decoration: none;
  color: none;
}
</style>
