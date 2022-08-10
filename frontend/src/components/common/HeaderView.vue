<template>
  <div>
    <v-container>
        <v-row>
            <v-col cols="4">
              <div class="leftbtn">
                <router-link :to="`/aboutus`">
                  <v-btn>
                      AboutUS
                  </v-btn>
                </router-link>
              </div>
            </v-col>
            <v-col cols="6">
                <v-img src="../../assets/Title.png"/>
            </v-col>
            <v-col cols="2">
                <div v-if="userInfo" class="rightbtn">
                    <v-btn @click="onClickLogout">Log OUT</v-btn>
                    <router-link :to="`/create`">
                        <v-btn>Create</v-btn>
                    </router-link>
                </div>
                <div v-if="!userInfo" class="rightbtn">
                    <router-link :to="`/login`">
                        <v-btn>LogIN</v-btn>
                    </router-link>
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
      if (this.$route.path != "/") this.$router.push({ name: "MainView" });
      this.drawer = false;
    },
  },
}

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
</style>