<template>
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
  </div>
</template>

<script>
import { mapMutations, mapState } from "vuex";
import cookie from "vue-cookies";
const memberStore = "memberStore";
export default {
  data() {
    return {
      logoutDialog: false,
    };
  },
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
      sessionStorage.removeItem("token");
      this.logoutDialog = true;
      setTimeout(this.movepage, 1000);
      this.drawer = false;
    },
    movepage() {
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
</style>
