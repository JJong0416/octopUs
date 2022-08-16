<template>
  <div>
    <v-img
      :src="require(`../assets/img/Theme/${this.mission.missionType}.jpg`)"
      width="100%"
    >
      <!-- 뒤로 가기 버튼 -->
      <v-btn @click="goback" icon color="pink" class="pa-3">
        <v-icon> mdi-arrow-left</v-icon>
      </v-btn></v-img
    >
    <v-card>
      <v-card-text>
        <div>
          {{ mission.missionType }}
          <span style="float: right"
            ><v-chip>{{ mission.missionPoint }}P</v-chip></span
          >
        </div>

        <p class="text-h4 text--primary">
          {{ mission.missionTitle }}
        </p>
      </v-card-text>

      <v-divider></v-divider>

      <v-card-title v-if="isParticipated">
        <v-icon color="pink darken-1">mdi-chevron-right</v-icon>&nbsp;&nbsp;
        <div class="my-4 text-subtitle-1">
          <b>미션 코드 : </b>&nbsp;&nbsp;&nbsp;{{ mission.missionCode }}
        </div>
        <v-btn v-clipboard="mission.missionCode" icon>
          <v-img
            max-height="30px"
            max-width="30px"
            src="../assets/img/icons/layer.png"
          ></v-img>
        </v-btn>
      </v-card-title>
      <v-card-title>
        <v-icon color="pink darken-1">mdi-help</v-icon>&nbsp;&nbsp;
        <div class="my-4 text-subtitle-1"><b>인증은 어떻게 하나요?</b></div>
      </v-card-title>
      <v-card-text
        class="px-7"
        style="justify-content: center; align-items: center"
      >
        {{ mission.missionContent }}
      </v-card-text>

      <v-card-title>
        <v-icon color="pink darken-1">mdi-calendar-check</v-icon>&nbsp;&nbsp;
        <div class="my-4 text-subtitle-1"><b>미션은 언제부터인가요?</b></div>
      </v-card-title>
      <v-card-text
        style="display: flex; justify-content: center; align-items: center"
      >
        {{ missionDetail.missionTimeStartTime | changeDateFormat }} ~
        {{ missionDetail.missionTimeEndTime | changeDateFormat }}
      </v-card-text>

      <v-card-title>
        <v-icon color="pink darken-1">mdi-clock-time-four-outline</v-icon
        >&nbsp;&nbsp;
        <div class="my-4 text-subtitle-1"><b>인증은 언제하나요?</b></div>
      </v-card-title>
      <v-card-text
        style="display: flex; justify-content: center; align-items: center"
      >
        <div>
          <div>
            해당 미션은 일주일에 &nbsp;&nbsp;<font
              style="font-weight: bold"
              size="4"
              color="#D81B60"
              >{{ missionDetail.missionTimeDPW }}</font
            >&nbsp;&nbsp;번 인증해야 합니다.
          </div>
          <br />
          <div>
            해당 미션은 하루에 총 &nbsp;&nbsp;<font
              style="font-weight: bold"
              size="4"
              color="#D81B60"
              >{{ missionDetail.missionTimeTPD }}</font
            >&nbsp;&nbsp;번 인증해야 합니다.
          </div>
          <v-col
            v-for="(
              authentication, index
            ) in missionDetail.authenticationInfoList"
            :key="index"
            cols="12"
            style="padding: 12px 12px 0px 12px"
          >
            <b>
              > &nbsp;&nbsp;{{
                authentication.authenticationStartTime
              }}&nbsp;~&nbsp;{{ authentication.authenticationEndTime }}</b
            >
          </v-col>
        </div>
      </v-card-text>

      <v-card-title style="padding-bottom: 0px">
        <div class="my-4 text-subtitle-1" style="padding-bottom: 0px">
          <v-icon color="pink darken-1">mdi-account-supervisor</v-icon
          >&nbsp;&nbsp;<b
            >참여중인 옥순이들 {{ missionuser.length }}/{{
              mission.missionLimitPersonnel
            }}</b
          >
        </div></v-card-title
      >
      <v-card-text>
        <div class="my-4 text-subtitle-1">
          <v-dialog v-model="userdialog">
            <template v-slot:activator="{ on, attrs }">
              <v-container fluid>
                <v-row>
                  <v-col
                    cols="2"
                    v-for="(item, index) in missionuser"
                    :key="index"
                    style="padding: 0px 15px 10px 15px !important"
                  >
                    <v-avatar
                      v-if="userInfo.userId != null"
                      size="40"
                      color="red lighten-3"
                      @click="previewUser(item)"
                      v-bind="attrs"
                      v-on="on"
                    >
                      <b>{{ item }}</b>
                    </v-avatar>

                    <v-avatar v-else size="40" color="red lighten-3">
                      <b>{{ item }}</b>
                    </v-avatar>
                  </v-col>
                </v-row>
              </v-container>
            </template>

            <v-card>
              <v-card-title class="text-h6 yellow lighten-2">
                {{ previewUsernickname }}
              </v-card-title>

              <v-row class="animate__animated animate__bounce">
                <v-col class="logo-img-wrapper">
                  <v-img
                    max-width="85%"
                    :src="
                      require(`../assets/img/Ocsoon/Pet/${userAvatar[3]}.png`)
                    "
                  >
                    <v-img
                      :src="
                        require(`../assets/img/Ocsoon/Character/${userAvatar[0]}.png`)
                      "
                    >
                      <v-img
                        :src="
                          require(`../assets/img/Ocsoon/Face/${userAvatar[1]}.png`)
                        "
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

              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn
                  v-if="mission.missionLeaderId == userInfo.userId"
                  color="warning"
                  dark
                  @click="kickOutUser()"
                >
                  강퇴하기
                </v-btn>
                <v-btn color="primary" text @click="userdialog = false">
                  close
                </v-btn>
              </v-card-actions>
            </v-card>
          </v-dialog>
        </div>
      </v-card-text>
      <v-row>
        <v-col cols="6"></v-col>
        <v-btn
          justify="end"
          style="margin: 10px"
          color="pink darken-1"
          v-if="!isParticipated"
          @click="joinMission"
          ><font color="white">미션에 참여하기</font></v-btn
        >
      </v-row>
    </v-card>
  </div>
</template>

<script>
import axios from "axios";
export default {
  data: () => ({
    roomNo: "",
    userdialog: false,
    userInfo: {},
    missionuser: [],
    clickUser: null,
    previewUsernickname: "",
    missionDetail: null,
    userAvatar: [0, 0, "0_nothing", "0_nothing"],
    picker: new Date(Date.now() - new Date().getTimezoneOffset() * 60000)
      .toISOString()
      .substr(0, 10),
    mission: null,
    isParticipated: false,
  }),
  props: {
    missionNo: { missionNo: String },
  },

  created() {
    axios.defaults.headers.common[
      "Authorization"
    ] = `Bearer ${sessionStorage.getItem("token")}`;
    var vm = this;
    vm.roomNo = this.$route.params.missionNo;
    axios
      .get(`../api/user/info`, {
        headers: {
          "Access-Control-Allow-Origin": "*",
          "Content-Type": "application/json",
        },
      })
      .then(function (response) {
        vm.userInfo = response.data;
        vm.userAvatar = vm.userInfo.userAvatar.split(", ");
      })
      .catch(function (err) {
        console.log(err);
      })
      .finally(() => {
        axios
          .get(`../api/mission/${vm.$route.params.missionNo}`, {
            headers: {
              "Access-Control-Allow-Origin": "*",
              "Content-Type": "application/json",
            },
          })
          .then(function (response) {
            vm.mission = response.data;
            vm.missionuser = response.data.missionUsers.split(", ");
            if (
              vm.userInfo.userId != null &&
              vm.missionuser.includes(vm.userInfo.userNickname)
            ) {
              vm.isParticipated = true;
            }
            console.log(vm.isParticipated);
            axios
              .get(
                `../api/mission/${vm.$route.params.missionNo}/mission-detail`,
                {
                  headers: {
                    "Access-Control-Allow-Origin": "*",
                    "Content-Type": "application/json",
                  },
                }
              )
              .then(function (response) {
                vm.missionDetail = response.data;
              })
              .catch(function (err) {
                console.log(err);
              });
          })
          .catch(function (err) {
            console.log(err);
          });
      });
  },
  filters: {
    changeDateFormat: function (value) {
      if (value == "") return "";
      var date = value.split("T")[0];
      var year = date.split("-")[0];
      var month = date.split("-")[1];
      var day = date.split("-")[2];
      return year + "년 " + month + "일 " + day + "일";
    },
  },
  methods: {
    goback() {
      this.$router.go(-1);
    },
    previewUser(nickname) {
      this.clickUser = nickname;
      axios
        .get(`../api/user/info/${nickname}`, {
          headers: {
            "Access-Control-Allow-Origin": "*",
            "Content-Type": "application/json",
          },
        })
        .then((response) => {
          this.previewUsernickname = response.data.userNickname;
          this.userAvatar = response.data.userAvatar.split(", ");
          if (this.userAvatar[2] == 0) {
            this.userAvatar[2] = "0_nothing";
          }
          if (this.userAvatar[3] == 0) {
            this.userAvatar[3] = "0_nothing";
          }
        })
        .catch((error) => {
          console.log(error);
        })
        .finally(() => {});
    },
    kickOutUser() {
      const vm = this;
      if (confirm(`${this.clickUser}님을 정말로 추방하시겠습니까?`)) {
        axios
          .delete(`../api/mission/${this.roomNo}/user/${this.clickUser}`)
          .then(() => {
            alert(vm.clickUser + "님을 미션에서 제외시켰습니다.");
          })
          .catch((error) => {
            console.log(error);
            alert("본인은 강퇴하실 수 없습니다.");
          })
          .finally(() => {
            this.userdialog = false;
          });
      }
    },
    joinMission() {
      if (this.userInfo.userId != null) {
        axios
          .post(`../api/mission/${this.roomNo}/join`)
          .then(() => {
            alert("미션에 참여하기가 완료되었습니다.");
            this.movePage();
          })
          .catch((error) => {
            console.log(error);
            alert("미션에 참여할 수 없습니다.");
          });
      } else {
        alert("로그인 후 이용해주세요.");
        this.$router.push({
          name: "Login",
        });
      }
    },
    movePage() {
      this.$router.push({
        name: "Mypage",
      });
    },
  },
};
</script>

<style></style>
