<template>
  <div>
    <mypage-header></mypage-header>
    <v-container>
      <v-row>
        <v-col cols="6">
          <!-- my Ocsoon -->
          <v-img
            :src="require(`../assets/img/Ocsoon/Pet/${this.avatarPet}.png`)"
          >
            <v-img
              :src="
                require(`../assets/img/Ocsoon/Character/${this.avatarColor}.png`)
              "
            >
              <v-img
                :src="
                  require(`../assets/img/Ocsoon/Face/${this.avatarFace}.png`)
                "
              >
                <v-img
                  :src="
                    require(`../assets/img/Ocsoon/Hat/${this.avatarHat}.png`)
                  "
                >
                  <v-row>
                    <v-col cols="7"></v-col>
                    <v-col class="px-0">
                      <router-link
                        :to="{
                          name: 'avatar',
                          params: {
                            avatarColor,
                            avatarFace,
                            avatarHat,
                            avatarPet,
                          },
                        }"
                      >
                        <v-btn class="store" text>상점</v-btn>
                      </router-link>
                    </v-col>
                  </v-row>
                </v-img></v-img
              >
            </v-img>
          </v-img>
        </v-col>
        <v-col cols="6">
          <!-- 개인 정보 카드 -->
          <v-card height="180">
            <v-row>
              <v-col class="pl-3">
                <v-card-title>{{ userInfo.userNickname }}</v-card-title>
              </v-col>
              <v-col class="px-0">
                <nickname-change></nickname-change>
              </v-col>
            </v-row>
            <v-card-text>
              Id : {{ userInfo.userID }} <br />
              Email : {{ userInfo.userEmail }} <br /><br />
              <v-divider></v-divider>
              <password-change></password-change>
            </v-card-text>
          </v-card>
        </v-col>
      </v-row>
      <v-row class="pr-5">
        <v-col cols="6">
          <!-- point -->
          {{ userInfo.userPoint }} Point
        </v-col>
        <v-col cols="3">
          <!-- 충전, 환급 -->
          <charge-point></charge-point>
        </v-col>
        <v-col class="pl-4" cols="3">
          <refund-point></refund-point>
        </v-col>
      </v-row>
      <v-row>
        <!-- 진행중인 mission -->
        <my-missions></my-missions>
      </v-row>
      <v-row>
        <!-- 도전 종료 mission -->
        <end-missions></end-missions>
      </v-row>
    </v-container>
    <footer-view></footer-view>
  </div>
</template>
<script>
import axios from "axios";
import cookie from "vue-cookies";
import MypageHeader from "../components/common/MypageHeader.vue";
import FooterView from "../components/common/FooterView.vue";
import NicknameChange from "../components/change/NicknameChange.vue";
import PasswordChange from "../components/change/PasswordChange.vue";
import ChargePoint from "../components/point/ChargePoint.vue";
import RefundPoint from "../components/point/RefundPoint.vue";
import MyMissions from "../components/mission/MyMissions.vue";
import EndMissions from "../components/mission/EndMissions.vue";
export default {
  components: {
    MypageHeader,
    FooterView,
    NicknameChange,
    PasswordChange,
    ChargePoint,
    RefundPoint,
    MyMissions,
    EndMissions,
  },
  data() {
    return {
      userInfo: [],
      avatarColor: null,
      avatarHat: null,
      avatarFace: null,
      avatarPet: null,
    };
  },
  methods: {},
  created() {
    var vm = this;
    let token = cookie.get("token");
    console.log("쿠키는?" + token);
    axios.defaults.headers.common[
      "Authorization"
    ] = `Bearer ${sessionStorage.getItem("token")}`;
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
        vm.userAvatar = vm.userInfo.userAvatar.split(", ");
        vm.avatarColor = parseInt(vm.userAvatar[0]);
        vm.avatarFace = parseInt(vm.userAvatar[1]);
        vm.avatarHat = parseInt(vm.userAvatar[2]);
        vm.avatarPet = parseInt(vm.userAvatar[3]);
        console.log(vm.userAvatar);
        // 참여중인 방 정보 받기
        axios
          .get(`api/user/mission/${vm.userInfo.userId}`, {
            headers: {
              "Access-Control-Allow-Origin": "*",
              "Content-Type": "application/json",
            },
          })
          .then(function (response) {
            console.log(response);
            response.data.forEach((mission) => {
              if (mission.missionStatus === "CLOSE") {
                vm.closedMissions.push(mission);
              } else {
                vm.openedMissions.push(mission);
              }
            });
            console.log("열린것" + vm.openedMissions);
            console.log("닫힌것" + vm.closedMissions);
          })
          .catch(function (err) {
            console.log(err);
          });
      })
      .catch(function (err) {
        console.log(err);
      });
  },
};
</script>
<style></style>
