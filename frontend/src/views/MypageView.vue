<template>
  <div>
    <mypage-header></mypage-header>
    <v-container>
      <br />
      <v-card>
        <v-row>
          <v-col cols="3"></v-col>
          <v-col cols="6">
            <!-- my Ocsoon -->
            <v-img
              v-if="this.avatarPet != null"
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
                      <v-col class="px-0"> </v-col>
                    </v-row> </v-img
                ></v-img>
              </v-img>
            </v-img>
          </v-col>
          <v-col cols="3" class="pt-5"
            ><router-link
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
              <v-btn class="store" icon
                ><img src="../assets/img/icons/shop.png" width="50" height="50"
              /></v-btn> </router-link
          ></v-col>

          <v-col cols="2"></v-col>
          <v-col cols="8">
            <!-- 개인 정보 카드 -->

            <v-row>
              <v-col class="pl-3">
                <v-card-title
                  style="
                    display: flex;
                    justify-content: center;
                    align-items: center;
                  "
                  ><div>{{ userInfo.userNickname }}</div></v-card-title
                >
              </v-col>
            </v-row>
          </v-col>
          <v-col class="px-0 pt-6">
            <nickname-change></nickname-change>
          </v-col>
          <v-card-text>
            <v-row>
              <v-col cols="1" style="padding: 0px"></v-col>
              <v-col cols="2" style="padding: 0px">ID</v-col>
              <v-col cols="5" style="padding: 0px">{{ userInfo.userId }}</v-col>
              <v-col cols="4" style="padding: 0px">
                <password-change></password-change
              ></v-col>
              <v-col cols="1" style="padding: 0px"></v-col>
              <v-col cols="2" style="padding: 0px">EMAIL</v-col>
              <v-col cols="9" style="padding: 0px 0px 12px 0px">{{
                userInfo.userEmail
              }}</v-col>
            </v-row>
            <br />
            <v-divider></v-divider>
            <v-row>
              <v-col cols="6">
                <v-card-title style="padding-bottom: 0px"
                  >{{ userInfo.userPoint }} P</v-card-title
                >
              </v-col>
              <v-col cols="3">
                <!-- 충전, 환급 -->
                <charge-point @success="refresh"></charge-point>
              </v-col>
              <v-col class="pl-4" cols="3" style="padding-left: 0px !important">
                <refund-point
                  :point="userInfo.userPoint"
                  @success="refresh"
                ></refund-point>
              </v-col>
            </v-row>
          </v-card-text>
        </v-row>
      </v-card>
      <br />
      <br />
      <v-row>
        <!--  mission -->
        <my-missions :missions="Missions"></my-missions>
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
export default {
  components: {
    MypageHeader,
    FooterView,
    NicknameChange,
    PasswordChange,
    ChargePoint,
    RefundPoint,
    MyMissions,
  },
  data() {
    return {
      userInfo: [],
      avatarColor: null,
      avatarHat: null,
      avatarFace: null,
      avatarPet: null,
      Missions: [],
    };
  },
  methods: {
    refresh() {
      this.$router.go();
    },
  },
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
        if (vm.avatarHat == 0) {
          vm.avatarHat = "0_nothing";
        }
        if (vm.avatarPet == 0) {
          vm.avatarPet = "0_nothing";
        }
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
            vm.Missions = response.data;
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
