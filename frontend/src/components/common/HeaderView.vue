<template>
<<<<<<< HEAD
  <div class="header-nav-wrap">
    <v-card class="mx-auto overflow-hidden" height="100vh">
      <v-app-bar>
        <!-- 좌측 메뉴 바 -->
        <v-app-bar-nav-icon @click="drawer = true"></v-app-bar-nav-icon>
        <!-- 여백 -->
        <v-spacer></v-spacer>
        <!-- Home 화면 이동 링크 -->
        <div class="title">
          <router-link :to="`/`">
            <v-img
              src="../../assets/Title.png"
              max-width="150"
              max-height="80"
            ></v-img>
          </router-link>
        </div>
        <!-- 여백 -->
        <v-spacer></v-spacer>
        <!-- 새로운 방 만들기 -->
        <div class="create">
          <router-link :to="`/create`">
            <v-btn elevation="2">Create</v-btn>
          </router-link>
        </div>
      </v-app-bar>

      <v-navigation-drawer v-model="drawer" absolute temporary height="100vh">
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

            <v-list-item>
              <v-list-item-icon>
                <v-icon>mdi-logout</v-icon>
              </v-list-item-icon>
              <v-list-item-title @click="onClickLogout"
                >Logout</v-list-item-title
              >
            </v-list-item>

            <v-list-item>
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
    </v-card>
=======
  <div class="header">
    <v-container>
      <v-row>
        <v-col cols="2">
          <!-- 로그인 되어 있으면 -->
          <div v-if="userInfo" class="leftbtn">
            <!-- 로그아웃 버튼 -->
            <v-btn class="py-7 pl-9" @click="onClickLogout" icon>
              <v-icon color="black" size="6vh">mdi-logout</v-icon>
            </v-btn>
          </div>
          <!-- 로그인 안 되어 있으면 -->
          <div v-if="!userInfo" class="leftbtn">
            <v-btn class="py-7 pl-9" icon>
              <router-link :to="'/aboutus'">
                <v-icon size="6vh"> mdi-information-outline </v-icon>
              </router-link>
            </v-btn>
          </div>
        </v-col>
        <v-col class="pl-10 py-4" cols="8">
          <router-link :to="`/main`">
            <v-img max-width="85%" src="../../assets/Title.png" />
          </router-link>
        </v-col>
        <v-col cols="2">
          <!-- 로그인 되어 있으면 -->
          <div v-if="userInfo" class="rightbtn">
            <!-- 방 생성 버튼 -->
            <v-btn class="py-7 pr-9" icon>
              <router-link :to="`/create`">
                <v-img
                  src="../../assets/img/icons/plus-icon.png"
                  max-width="45"
                />
              </router-link>
            </v-btn>
          </div>
          <!-- 로그인 안 되어 있으면 -->
          <div v-if="!userInfo" class="rightbtn">
            <!-- 로그인 버튼 -->
            <v-btn class="py-7 pr-9" icon>
              <router-link :to="`/login`">
                <v-icon size="6vh">mdi-login</v-icon>
              </router-link>
            </v-btn>
          </div>
        </v-col>
      </v-row>
    </v-container>
    <v-dialog v-model="logoutDialog" max-width="500px">
      <v-card>
        <v-card-title>
          <h4>로그아웃 되었습니다.</h4>
        </v-card-title>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="pink" text @click="logoutDialog = false"> Close </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
>>>>>>> ac048782d308840093efdf3b8146c990ec2fcf95
  </div>
</template>

<script>
import { mapMutations, mapState } from "vuex";
import cookie from "vue-cookies";
const memberStore = "memberStore";
<<<<<<< HEAD

export default {
  data: () => ({
    drawer: false,
    group: null,
  }),
=======
export default {
  data() {
    return {
      logoutDialog: false,
    };
  },
  computed: {
    ...mapState(memberStore, ["isLogin", "userInfo"]),
  },
>>>>>>> ac048782d308840093efdf3b8146c990ec2fcf95
  methods: {
    ...mapMutations(memberStore, ["SET_IS_LOGIN", "SET_USER_INFO"]),
    onClickLogout() {
      // console.log("memberStore : ", ms);
      this.SET_IS_LOGIN(false);
      this.SET_USER_INFO(null);
      cookie.remove("token");
<<<<<<< HEAD

      alert("로그아웃됐습니다");
      if (this.$route.path != "/") this.$router.push({ name: "MainView" });
      this.drawer = false;
    },
  },
  computed: {
    ...mapState(memberStore, ["isLogin", "userInfo"]),
=======
      sessionStorage.removeItem("token");
      this.logoutDialog = true;
      setTimeout(this.movepage, 1000);
      this.drawer = false;
    },
    movepage() {
      window.location.href = "/";
    },
>>>>>>> ac048782d308840093efdf3b8146c990ec2fcf95
  },
};
</script>

<style>
<<<<<<< HEAD
.title a {
  text-decoration: none;
  color: white;
}
.title a:hover {
  text-decoration: none;
  color: white;
}
.create a {
  text-decoration: none;
  color: none;
}
.create a:hover {
  text-decoration: none;
  color: none;
}
.header-nav-wrap {
  height: 6.2vh;
=======
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
  background-color: #f7f5fc;
>>>>>>> ac048782d308840093efdf3b8146c990ec2fcf95
}
</style>
