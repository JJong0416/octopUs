<template>
  <div>
    <v-app id="app">
      <v-card class="overflow-hidden">
        <v-app-bar
          absolute
          shrink-on-scroll
          prominent
          fade-img-on-scroll
          height="50px"
          color="#a8dadc"
        >
          <v-app-bar-nav-icon @click="drawer = true"></v-app-bar-nav-icon>

          <v-toolbar-title
            ><router-link :to="`/`" style="text-decoration: none">
              <v-img src="assets/Title.png"></v-img>Title
            </router-link></v-toolbar-title
          >

          <v-spacer></v-spacer>
          <div v-if="userInfo" class="create">
            <router-link :to="`/create`">
              <v-btn elevation="2">Create</v-btn>
            </router-link>
          </div>
          <div v-if="!userInfo" class="create">
            <router-link :to="`/aboutus`">
              <v-btn elevation="2">AboutUs</v-btn>
            </router-link>
          </div>
        </v-app-bar>
        <v-navigation-drawer v-model="drawer" absolute temporary height="90vh">
          <v-list nav dense>
            <v-list-item-group
              v-model="group"
              active-class="deep-purple--text text--accent-4"
            >
              <v-list-item>
                <v-list-item-icon>
                  <v-icon>mdi-home</v-icon>
                </v-list-item-icon>
                <v-list-item-title>Home</v-list-item-title>
              </v-list-item>

              <v-list-item>
                <v-list-item-icon>
                  <v-icon>mdi-account</v-icon>
                </v-list-item-icon>
                <v-list-item-title>My Page</v-list-item-title>
              </v-list-item>

              <v-list-item v-if="userInfo">
                <v-list-item-icon>
                  <v-icon>mdi-logout</v-icon>
                </v-list-item-icon>
                <v-list-item-title @click="onClickLogout"
                  >Logout</v-list-item-title
                >
              </v-list-item>
              <v-list-item v-if="userInfo">
                <v-list-item-icon>
                  <v-icon>mdi-information-variant</v-icon>
                </v-list-item-icon>
                <v-list-item-title>About US</v-list-item-title>
              </v-list-item>
            </v-list-item-group>
          </v-list>
          <!-- 왼쪽 탭 닫기 버튼 -->
          <template v-slot:append>
            <div class="pa-2" @click="drawer = false">
              <v-btn block>
                <v-icon>mdi-close-circle</v-icon>
              </v-btn>
            </div>
          </template>
        </v-navigation-drawer>
        <v-sheet
          id="scrolling-techniques-4"
          class="overflow-y-auto"
          max-height="90vh"
        >
          <v-container>
            <div class="routerpadding"></div>
            <router-view></router-view>
          </v-container>
        </v-sheet>
      </v-card>
      <footer-view></footer-view>
    </v-app>
  </div>
</template>

<script>
import FooterView from "./components/common/FooterView.vue";
import { mapMutations, mapState } from "vuex";
import cookie from "vue-cookies";
const memberStore = "memberStore";

export default {
  name: "App",
  data: () => ({
    drawer: false,
    group: null,
  }),
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
      if (this.$route.path != "/") this.$router.push({ name: "MainView" });
      this.drawer = false;
    },
  },

  components: {
    FooterView,
  },
};
</script>

<style>
/* Router Transition */
.routing-fade-enter-active,
.routing-fade-leave-active {
  transition: opacity 0.3s ease;
}
.routing-fade-enter, .routing-fade-leave-to
/* .routing-fade-leave-active below version 2.1.8 */ {
  opacity: 0;
}
.wrapper {
  height: 88.4vh;
}
.create a {
  text-decoration: none;
  color: none;
}
.create a:hover {
  text-decoration: none;
  color: none;
}
.routerpadding {
  height: 50px;
}
#app {
  background-color: "#f1faee";
}
</style>
