<template>
<<<<<<< HEAD
  <div>
    <!-- 왕문어 -->
    <v-container>
      <v-row>
        <v-col class="logo-img-wrapper">
          <v-img src="../assets/Logo.png" max-width="60%"></v-img>
        </v-col>
      </v-row>
      <!-- 검색창 -->
      <v-row>
        <v-col cols="4">
          <v-select :items="items" label="검색 선택"></v-select>
        </v-col>
        <v-col cols="5">
          <v-text-field hide-details single-line></v-text-field>
        </v-col>
        <v-col cols="">
          <v-card-text>
            <v-btn icon>
              <v-icon> mdi-magnify </v-icon>
            </v-btn>
          </v-card-text>
        </v-col>
      </v-row>

      <!-- login -->
      <v-row>
        <v-col cols="2"></v-col>
        <v-col cols="4">
          <v-card-actions
            ><div class="login">
              <router-link :to="`/login`">
                <v-btn color="#ff809d" dark large block>Login</v-btn>
              </router-link>
            </div>
          </v-card-actions></v-col
        >
        <!-- 회원가입 -->
        <v-col cols="4">
          <v-card-actions>
            <div class="signup">
              <router-link :to="`/signup`">
                <v-btn color="#ff809d" dark large block
                  >Sign Up</v-btn
                ></router-link
              >
            </div>
          </v-card-actions></v-col
        >
        <v-col cols="2"></v-col>
      </v-row>
      <!-- Hot 실시간 반영 -->
      <v-row>
        <v-col cols="2"><v-img src="../assets/img/Hot.png"></v-img></v-col>
        <v-col cols="7">
=======
  <div class="app">
    <header-view></header-view>
    <v-container>
      <v-row class="animate__animated animate__bounce">
        <v-col class="logo-img-wrapper">
          <v-img
            max-width="85%"
            :src="require(`../assets/img/Ocsoon/Pet/${userAvatar[3]}.png`)"
          >
            <v-img
              :src="
                require(`../assets/img/Ocsoon/Character/${userAvatar[0]}.png`)
              "
            >
              <v-img
                :src="require(`../assets/img/Ocsoon/Face/${userAvatar[1]}.png`)"
              >
                <v-img
                  :src="
                    require(`../assets/img/Ocsoon/Hat/${userAvatar[2]}.png`)
                  "
                ></v-img
              ></v-img> </v-img
          ></v-img>
        </v-col>
      </v-row>

      <!-- 검색창 -->
      <v-row>
        <v-col class="pt-0 pb-5 pl-5 pr-0" cols="9">
          <v-text-field
            hide-details
            single-line
            label="미션 코드를 입력해주세요"
            v-model="tofindsearch"
            @keyup.enter="transmit"
          ></v-text-field>
        </v-col>
        <v-col class="pl-1" cols="3">
          <v-btn @click="transmit" outlined color="#fa183e"> Go! </v-btn>
        </v-col>
      </v-row>

      <!-- hot, new icon -->
      <v-row>
        <v-col cols="1"></v-col>
        <v-col class="pa-0" cols="4">
          <!-- hot -->
          <v-img @click="moveHot()" src="../assets/img/Hot.png"></v-img>
        </v-col>
        <v-col cols="1"></v-col>
        <v-col cols="1"></v-col>
        <v-col class="pa-0" cols="4">
          <!-- new -->
          <v-img @click="moveNew()" src="../assets/img/New.png"></v-img>
        </v-col>
        <v-col cols="1"></v-col>
      </v-row>

      <!-- Hot 실시간 반영 -->
      <v-row>
        <v-col cols="6">
>>>>>>> ac048782d308840093efdf3b8146c990ec2fcf95
          <v-carousel
            cycle
            interval="1500"
            height="80"
            hide-delimiter-background
            hide-delimiters
            show-arrows-on-hover
<<<<<<< HEAD
          >
            <v-carousel-item v-for="(slide, i) in slides" :key="i">
              <v-sheet height="100%">
                <v-row class="fill-height" align="center" justify="center">
                  <div class="text-h4">{{ slide }}</div>
=======
            :show-arrows="false"
          >
            <v-carousel-item v-for="(hotmission, i) in hotmissions" :key="i">
              <v-sheet height="100%">
                <v-row class="fill-height" align="center" justify="center">
                  <div
                    class="twiceLine"
                    style="
                      width: 9em;
                      display: flex;
                      justify-content: center;
                      align-items: center;
                    "
                  >
                    {{ hotmission.missionTitle }}
                  </div>
>>>>>>> ac048782d308840093efdf3b8146c990ec2fcf95
                </v-row>
              </v-sheet>
            </v-carousel-item>
          </v-carousel>
        </v-col>
<<<<<<< HEAD

        <!-- Hot 더보기 -->
        <v-col cols="3"> <v-btn color="red lighten-1">more</v-btn></v-col>
      </v-row>
      <!-- New 실시간 반영 -->
      <v-row>
        <v-col cols="2"><v-img src="../assets/img/New.png"></v-img></v-col>
        <v-col cols="7">
=======
        <!-- new 실시간 반영 -->
        <v-col cols="6">
>>>>>>> ac048782d308840093efdf3b8146c990ec2fcf95
          <v-carousel
            cycle
            interval="1500"
            height="80"
            hide-delimiter-background
            hide-delimiters
            show-arrows-on-hover
<<<<<<< HEAD
          >
            <v-carousel-item v-for="(slide, i) in slides" :key="i">
              <v-sheet height="100%">
                <v-row class="fill-height" align="center" justify="center">
                  <div class="text-h4">{{ slide }}</div>
                </v-row>
              </v-sheet>
            </v-carousel-item>
          </v-carousel></v-col
        >
        <!-- New 더보기 -->
        <v-col cols="3"> <v-btn color="warning">more</v-btn></v-col>
      </v-row>
    </v-container>
=======
            :show-arrows="false"
          >
            <v-carousel-item v-for="(newmission, i) in newmissions" :key="i">
              <v-sheet height="100%">
                <v-row class="fill-height" align="center" justify="center">
                  <div
                    class="twiceLine"
                    style="
                      width: 9em;
                      display: flex;
                      justify-content: center;
                      align-items: center;
                    "
                  >
                    {{ newmission.missionTitle }}
                  </div>
                </v-row>
              </v-sheet>
            </v-carousel-item>
          </v-carousel>
        </v-col>
      </v-row>
    </v-container>
    <!-- searchFail Dialog -->
    <v-dialog v-model="FailDialog" max-width="500px">
      <v-card>
        <v-card-title>
          <h5>미션 검색 결과</h5>
        </v-card-title>
        <v-card-title>
          <h6>{{ this.failMsg }}</h6>
        </v-card-title>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="#fa183e" text @click="FailDialog = false"> 확인 </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
    <footer-view></footer-view>
>>>>>>> ac048782d308840093efdf3b8146c990ec2fcf95
  </div>
</template>

<script>
<<<<<<< HEAD
export default {
  data: () => ({
    items: ["코드 입력", "제목 검색", "테마 검색"],
    slides: ["First", "Second", "Third", "Fourth", "Fifth"],
  }),
=======
import { mapMutations, mapState } from "vuex";
import axios from "axios";
import cookie from "vue-cookies";
import HeaderView from "../components/common/HeaderView.vue";
import FooterView from "../components/common/FooterView.vue";
import "animate.css";
const memberStore = "memberStore";

export default {
  components: {
    HeaderView,
    FooterView,
  },
  data: () => ({
    FailDialog: false,
    failMsg: "",
    tofindsearch: "",
    hotmissions: [],
    newmissions: [],
    items: ["코드"],
    userInfo2: [],
    userAvatar: [0, 0, "0_nothing", "0_nothing"],
  }),
  computed: {
    ...mapState(memberStore, ["isLogin", "userInfo"]),
  },

  methods: {
    ...mapMutations(memberStore, ["SET_IS_LOGIN", "SET_USER_INFO"]),
    moveHot() {
      this.$router.push("/hotnew");
    },
    moveNew() {
      this.$router.push("/hotnew");
    },
    transmit() {
      if (!this.userInfo) {
        alert("로그인 후 사용가능합니다.");
        return;
      }
      var vm = this;
      axios
        .get(`api/mission/search/code/${vm.tofindsearch}`, {
          headers: {
            "Access-Control-Allow-Origin": "*",
            "Content-Type": "application/json",
          },
        })
        .then(function (response) {
          if (!response.data[0]) {
            vm.failMsg = "참여할 수 없는 방입니다.";
          } else {
            vm.$router.push({
              name: "before",
              params: { missionNo: response.data[0].missionNo },
            });
          }
        })
        .catch(function (err) {
          vm.failMsg = "존재하지 않거나 참가할 수 없는 방입니다.";
          console.log(err);
        });

      this.FailDialog = true;
    },
  },
  created() {
    var vm = this;
    axios.defaults.headers.common[
      "Authorization"
    ] = `Bearer ${sessionStorage.getItem("token")}`;
    // kakao login token settings
    axios
      .get(`api/login/kakao/${vm.$route.query.code}`, {
        headers: {
          "Access-Control-Allow-Origin": "*",
          "Content-Type": "application/json",
        },
      })
      .then((response) => {
        axios.defaults.headers.common[
          "Authorization"
        ] = `Bearer ${response.data.token}`;
        let token = response.data.token;
        vm.SET_IS_LOGIN(true);
        vm.SET_USER_INFO(token);
        cookie.set("token", token);
        sessionStorage.setItem("token", token);
      })
      .catch((error) => {
        console.log(error);
      })
      .finally(() => {
        axios
          .get(`api/user/info`, {
            headers: {
              "Access-Control-Allow-Origin": "*",
              "Content-Type": "application/json",
            },
          })
          .then(function (response) {
            vm.userInfo2 = response.data;
            vm.userAvatar = vm.userInfo2.userAvatar.split(", ");
            if (vm.userAvatar[2] == 0) {
              vm.userAvatar[2] = "0_nothing";
            }
            if (vm.userAvatar[3] == 0) {
              vm.userAvatar[3] = "0_nothing";
            }
          })
          .catch(function (err) {
            console.log(err);
          });
      });

    // new mission
    axios
      .get(`api/mission/new`, {
        headers: {
          "Access-Control-Allow-Origin": "*",
          "Content-Type": "application/json",
        },
      })
      .then(function (response) {
        vm.newmissions = response.data;
      })
      .catch(function (err) {
        console.log(err);
      });
    // hot mission
    axios
      .get(`api/mission/hot`, {
        headers: {
          "Access-Control-Allow-Origin": "*",
          "Content-Type": "application/json",
        },
      })
      .then(function (response) {
        vm.hotmissions = response.data;
      })
      .catch(function (err) {
        console.log(err);
      });
  },
>>>>>>> ac048782d308840093efdf3b8146c990ec2fcf95
};
</script>

<style>
<<<<<<< HEAD
.login a {
  text-decoration: none;
  color: black;
}
.login a:hover {
  text-decoration: none;
  color: black;
}
=======
>>>>>>> ac048782d308840093efdf3b8146c990ec2fcf95
.signup a {
  text-decoration: none;
  color: black;
}
.signup a:hover {
  text-decoration: none;
  color: black;
}
body {
  background-color: #ebebeb;
}
.more a {
  text-decoration: none;
  color: black;
}
.more a:hover {
  text-decoration: none;
  color: black;
}
<<<<<<< HEAD
.login a {
  text-decoration: none;
  color: white;
}
.login a:hover {
  text-decoration: none;
  color: white;
}
.signUp a {
  text-decoration: none;
  color: white;
}
.signUp a:hover {
  text-decoration: none;
  color: white;
}
=======
>>>>>>> ac048782d308840093efdf3b8146c990ec2fcf95
.logo-img-wrapper {
  display: flex;
  justify-content: center;
  align-items: center;
<<<<<<< HEAD
=======
  padding: 0;
}
.app {
  background-color: white;
}

.singleLine {
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  word-wrap: break-word;
}

.twiceLine {
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  word-wrap: break-word;

  line-height: 1;
  white-space: normal;
  line-height: 1.2;

  text-align: left;
  word-wrap: break-word;
  display: -webkit-box;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
}
::v-deep .v-btn.v-btn--icon.v-btn--round.theme--dark.v-size--default {
  display: none;
}
::v-deep .v-window--show-arrows-on-hover.v-window__next {
  display: none;
}
::v-deep .v-carousel__next > button {
  color: red;
>>>>>>> ac048782d308840093efdf3b8146c990ec2fcf95
}
</style>
