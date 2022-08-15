<template>
  <v-container>
    <v-card>
      <v-row class="pt-3">
        <v-img :src="require(`../assets/img/Theme/${this.mission.missionType}.jpg`)" width="100%">
          <v-row>
            <v-col class="px-6">
              <!-- 뒤로 가기 버튼 -->
              <v-btn @click="goback" icon color="#fa183e">
                <v-icon> mdi-arrow-left</v-icon>
              </v-btn>
            </v-col>
          </v-row>
        </v-img>
      </v-row>
      <v-row>
        <v-card-text>
          <v-row>
            <v-col cols="2" class="pr-0 pl-5">
              <v-img :src="require(`../assets/img/icons/${this.mission.missionType}.png`)"></v-img>
            </v-col>
            <v-col class="pl-1 pt-6" cols="7">
              {{ mission.missionType }}
            </v-col>
            <v-col cols="3" class="pt-4 pr-5">
              <v-chip outlined color="#fa183e">
              {{ mission.missionPoint }} P
            </v-chip>
            </v-col>
          </v-row>
          <v-row class="px-6">
            <p class="text-h5">
              {{ mission.missionTitle }}
            </p>
          </v-row>
        </v-card-text>
      </v-row>
      <v-card-title>
        <v-row>
          <v-col class="pr-6 pb-0" cols="1">
            <v-icon color="#fa183e">mdi-chevron-right</v-icon>
          </v-col>
          <v-col class="pt-4 pl-0 pb-0" cols="8">
            <div class="pl-2 text-subtitle-1">
              <b>미션 공유 코드 : </b> &nbsp; {{ mission.missionCode }}
            </div>
          </v-col>
          <v-col class="pb-0" cols="2">
            <v-btn v-clipboard="mission.missionCode" icon>
              <v-img
                max-height="30px"
                max-width="30px"
                src="../assets/img/icons/layer.png"
              ></v-img>
            </v-btn>
          </v-col>
        </v-row>
      </v-card-title>
      <v-card-text>
        <v-row>
          <v-col class="pr-6 pt-6 pb-0" cols="1">
            <v-icon color="#fa183e">mdi-chevron-right</v-icon>
          </v-col>
          <v-col class="pt-6 pl-0" cols="8">
            <div class="pl-2 text-subtitle-1">
              <b>미션 최대 인원 : </b> &nbsp; {{ mission.missionLimitPersonnel }} 명
            </div>
          </v-col>
        </v-row>
      </v-card-text>
      <v-card-title>
        <v-row>
          <v-col class="pt-0 pr-6 pb-0" cols="1">
            <v-icon color="#fa183e">mdi-chevron-right</v-icon>
          </v-col>
          <v-col class="pt-1 pl-0 pb-0">
            <div class="pl-2 pb-0 text-subtitle-1"><b>미션 인증 방법</b></div>
          </v-col>
        </v-row>
      </v-card-title>
      <v-card-text>
        <v-textarea height="100" disabled outlined v-model="mission.missionContent"/>
      </v-card-text>
      <v-spacer></v-spacer>
      <v-row class="py-3" justify="center">
        <div v-if="!successDialog">
          <v-btn outlined color="#fa183e" @click="dialog=true">
            인증 시간 설정하기
          </v-btn>
        </div>
        <div v-if="successDialog">
          <v-btn outlined color="#fa183e" @click="sendMissionTimeInfo">
            미션 시작하기!
          </v-btn>
        </div>
      </v-row>

      <!-- 인증 시간 설정 창 -->
      <v-dialog
        v-model="dialog"
        fullscreen
        hide-overlay
        transition="dialog-bottom-transition"
        scrollable
      >
        <v-card tile>
          <v-toolbar color="#eefaec">
            <v-row justify="center">
              <v-col cols="3"></v-col>
              <v-col cols="7" class="pt-6 pl-5">
                <v-toolbar-title>
                  <h5>인증 시간 설정하기</h5>
                </v-toolbar-title>
              </v-col>
              <v-col class="pr-3" cols="2">
                <v-btn icon color="#143559" @click="dialog = false">
                  <v-icon>mdi-close</v-icon>
                </v-btn>
              </v-col>
            </v-row>
          </v-toolbar>
          <v-card-text>
            <v-list>
              <v-list-item>
                <v-list-item-content>
                  <v-list-item-title>시작날짜 설정</v-list-item-title>
                </v-list-item-content>
              </v-list-item>
              <v-list-item>
                <v-list-item-content justify="center">
                  <v-date-picker v-model="picker"></v-date-picker>
                </v-list-item-content>
              </v-list-item>
              <v-list-item>
                <v-list-item-content>
                  <v-list-item-title>인증횟수</v-list-item-title>
                </v-list-item-content>
              </v-list-item>
              <v-list-item>
                <v-list-item-content justify="center">
                  <v-text-field
                    v-model="howmanyweeks"
                    label="몇주 동안 진행할지 숫자로 입력해주세요"
                    :rules="weekrules"
                    type="number"
                    min="1"
                    hide-details="auto"
                    oniput="javascript: this.value= this.value.replace(/[ㄱ-ㅎ|ㅏ-ㅣ|가-힣|a-z]/g,'');"
                  ></v-text-field>
                  <v-text-field
                    v-model="authenweeks"
                    label="일주일에 몇 일 인증할지 숫자로 입력해주세요"
                    :rules="weekrules"
                    type="number"
                    min="1"
                    hide-details="auto"
                    oniput="javascript: this.value= this.value.replace(/[ㄱ-ㅎ|ㅏ-ㅣ|가-힣|a-z]/g,'');"
                  ></v-text-field>
                  <v-text-field
                    v-model="authendays"
                    label="하루에 몇 번 인증할지 숫자로 입력해주세요"
                    :rules="weekrules"
                    type="number"
                    min="1"
                    hide-details="auto"
                    oniput="javascript: this.value= this.value.replace(/[ㄱ-ㅎ|ㅏ-ㅣ|가-힣|a-z]/g,'');"
                  ></v-text-field>
                </v-list-item-content>
              </v-list-item>
            </v-list>
            <v-divider></v-divider>
            <v-list>
              <v-list-item v-for="n in Number(authendays)" v-bind:key="n">
                <v-btn
                  color="primary"
                  dark
                  class="ma-2"
                  @click="dialog2 = !dialog2"
                >
                  인증 가능 시간설정 {{ n }}
                </v-btn>
              </v-list-item>
              <v-list-item>
                <v-btn
                  color="primary"
                  dark
                  class="ma-2"
                  @click="dialog = false"
                >
                  save
                </v-btn>
              </v-list-item>
            </v-list>
          </v-card-text>

          <div style="flex: 1 1 auto"></div> </v-card
        >ndAuthenInfosendMissionTimeInfo
      </v-dialog>
<v-dialog v-model="dialog2" max-width="500px">
        <v-card justify="space-around" align="center">
          <v-card-title> 인증 가능 시간 설정 </v-card-title>
          <v-card-title> 시작 시간 </v-card-title>
          <v-time-picker v-model="start" :max="end"></v-time-picker>
          <v-card-title> 마감 시간 </v-card-title>
          <v-time-picker v-model="end" :min="start"></v-time-picker>

          <v-card-actions>
            <v-btn color="primary" dark @click="dialog2 = false"> Close </v-btn>

            <v-btn color="primary" dark @click="sendAuthenInfo"> save </v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
      <v-dialog v-model="authenFailDialog" max-width="500px">
        <v-card justify="space-around" align="center">
          <v-card-title><h4>인증정보를 설정해주세요</h4></v-card-title>

          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn text color="pink" @click="authenFailDialog = false">
              Close
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
      <!-- 미션 시작 창 -->
      <v-dialog v-model="successDialog" max-width="500px">
        <v-card justify="space-around" align="center">
          <v-card-title><h4>미션이 활성화되었습니다</h4> </v-card-title>

          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn text color="pink" @click="successDialog = false">
              Close
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
    </v-card>
  </v-container>
</template>
<script>
import axios from "axios";
export default {
  data: () => ({
    show: false,
    roomNo: "",
    isAuthentication: false,
    successDialog: false,
    userdialog: false,
    authenFailDialog: false,
    missionuser: [],
    previewUsernickname: "",
    userAvatar: [0, 0, 0, 0],
    picker: new Date(Date.now() - new Date().getTimezoneOffset() * 60000)
      .toISOString()
      .substr(0, 10),
    authenweeks: null,
    howmanyweeks: null,
    authendays: null,
    start: null,
    end: null,
    weekrules: [
      (value) => !!value || "입력해주세요",
      (value) => (value && value >= 1) || "숫자로 입력해주세요",
    ],
    mission: null,
    dialog: false,
    dialog2: false,
    dialog3: false,
    notifications: false,
    sound: true,
    widgets: false,
  }),
  created() {
    axios.defaults.headers.common[
      "Authorization"
    ] = `Bearer ${sessionStorage.getItem("token")}`;
    var vm = this;
    vm.roomNo = vm.$route.params.missionNo;

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
      })
      .catch(function (err) {
        console.log(err);
      });
  },

  methods: {
    sendAuthenInfo() {
      axios
        .post(`../api/mission/${this.roomNo}/authentication`, {
          authenticationStartTime: this.start,
          authenticationEndTime: this.end,
        })
        .then((response) => {
          console.log(response);
          console.log("authentication 정보 전송 성공");
          this.isAuthentication = true;
        })
        .catch((error) => {
          console.log(error);
          alert("athentication 정보 전송 실패");
        })
        .finally(() => {
          console.log(this.start);
          console.log(this.end);
          this.dialog2 = false;
          this.start = null;
          this.end = null;
        });
    },
    sendMissionTimeInfo() {
      if (this.isAuthentication) {
        axios
          .post(`../api/mission/${this.roomNo}/mission-time`, {
            missionTimeStartTime: this.picker + "T00:00:00",
            missionTimeWeek: this.howmanyweeks,
            missionTimeDPW: this.authenweeks,
            missionTimeTPD: this.authendays,
          })
          .then((response) => {
            console.log(response);
            this.successDialog = true;
            setTimeout(this.movePage, 2000);
          })
          .catch((error) => {
            console.log(error);
            alert("missiontime 정보 전송 실패");
          })
          .finally(() => {
            console.log(this.picker + "T00:00:00");
            console.log(this.howmanyweeks);
            console.log(this.authenweeks);
            console.log(this.authendays);
          });
      } else {
        this.authenFailDialog = true;
      }
    },
    movePage() {
      this.$router.push({
        name: "Mypage",
        params: { missionNo: this.missionNo },
      });
    },
    goback() {
      this.$router.go(-1);
    },
  },
};
</script>

<style></style>
