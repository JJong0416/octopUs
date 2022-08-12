<template>
  <div>
    <v-card class="mx-auto my-12" max-width="374">
      <template slot="progress">
        <v-progress-linear
          color="deep-purple"
          height="10"
          indeterminate
        ></v-progress-linear>
      </template>

      <v-card-title> {{ mission.missionTitle }} </v-card-title>

      <v-card-text>
        <div class="my-4 text-subtitle-1">{{ mission.missionCode }}</div>
      </v-card-text>
      <v-card-text>
        <div class="my-4 text-subtitle-1">
          참가자 명단
          <v-list>
            <template v-for="(item, index) in this.missionuser">
              <v-list-tiem :key="index">
                <v-dialog v-model="userdialog" width="500">
                  <template v-slot:activator="{ on, attrs }">
                    <v-btn color="red lighten-2" dark v-bind="attrs" v-on="on">
                      {{ item }}
                    </v-btn>
                  </template>

                  <v-card>
                    <v-card-title class="text-h5 yellow lighten-2">
                      정말로 추방하시겠어요?
                    </v-card-title>
                    유저정보
                    <v-divider></v-divider>

                    <v-card-actions>
                      <v-spacer></v-spacer>
                      <v-btn color="warning" dark @click="kickOutUser(item)">
                        강퇴하기
                      </v-btn>
                      <v-btn color="primary" text @click="userdialog = false">
                        close
                      </v-btn>
                    </v-card-actions>
                  </v-card>
                </v-dialog>
              </v-list-tiem>
            </template>
          </v-list>
        </div>
      </v-card-text>
      <v-card-title
        >방 인원제한 : {{ mission.missionLimitPersonnel }}</v-card-title
      >
      <v-card-title
        >미션 참가시 필요한 포인트 : {{ mission.missionPoint }}</v-card-title
      >

      <v-card-actions>
        <v-card-text color="orange" text> 방 설명 </v-card-text>

        <v-spacer></v-spacer>

        <v-btn icon @click="show = !show">
          <v-icon>{{ show ? "mdi-chevron-up" : "mdi-chevron-down" }}</v-icon>
        </v-btn>
      </v-card-actions>

      <v-expand-transition>
        <div v-show="show">
          <v-divider></v-divider>
          <v-card-title>방 설명 / 인증 방법</v-card-title>
          <v-card-text>
            {{ mission.missionContent }}
            <br />
            흐르는 물에 손을 씻는 장면을 찍어 인증합니다!
          </v-card-text>
          <v-card-title>인증 요일 / 시간</v-card-title>
          <v-card-text>
            일주일에 5 번, 오전 6시 ~ 오전 7시 에 인증합니다.
          </v-card-text>
        </div>
      </v-expand-transition>
      <v-btn color="primary" class="ma-2" dark @click="dialog = true">
        인증 정보 설정(방장만 보이는 버튼)
      </v-btn>

      <v-btn color="primary" class="ma-2" dark @click="sendMissionTimeInfo">
        시작하기(방장만보이는버튼)
      </v-btn>
    </v-card>

    <v-dialog
      v-model="dialog"
      fullscreen
      hide-overlay
      transition="dialog-bottom-transition"
      scrollable
    >
      <v-card tile>
        <v-toolbar flat dark color="primary">
          <v-btn icon dark @click="dialog = false">
            <v-icon>mdi-close</v-icon>
          </v-btn>
          <v-toolbar-title>Settings</v-toolbar-title>
          <v-spacer></v-spacer>
        </v-toolbar>
        <v-card-text>
          <v-list three-line subheader>
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
              <v-btn color="primary" dark class="ma-2" @click="dialog = false">
                save
              </v-btn>
            </v-list-item>
          </v-list>
        </v-card-text>

        <div style="flex: 1 1 auto"></div>
      </v-card>
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
  </div>
</template>

<script>
import axios from "axios";
export default {
  data: () => ({
    show: false,
    roomNo: "",
    userdialog: false,
    missionuser: [],
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
    items: [
      {
        title: "Click Me",
      },
      {
        title: "Click Me",
      },
      {
        title: "Click Me",
      },
      {
        title: "Click Me 2",
      },
    ],
    select: [
      { text: "State 1" },
      { text: "State 2" },
      { text: "State 3" },
      { text: "State 4" },
      { text: "State 5" },
      { text: "State 6" },
      { text: "State 7" },
    ],
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
    console.log("missionNo는 뭘까요?");
    console.log("missionNo는 : " + this.$route.params.missionNo);
    axios
      .get(`api/mission/${this.$route.params.missionNo}`, {
        headers: {
          "Access-Control-Allow-Origin": "*",
          "Content-Type": "application/json",
        },
      })
      .then(function (response) {
        console.log(response);

        console.log("첫번째 데이터가 뭐게요");
        console.log(response.data[0]);
        vm.mission = response.data;
        console.log(vm.mission);
        vm.missionuser = response.data.missionUsers.split(", ");
      })
      .catch(function (err) {
        console.log(err);
      });
  },

  methods: {
    previewUser() {
      axios.get(`api/user/`);
    },
    kickOutUser(nickname) {
      axios
        .delete(`api/mission/${this.missionNo}/user/유저아이디??유저닉넴??`)
        .then((respponse) => {
          console.log(nickname);
          console.log(respponse);
          console.log("유저추방성공");
        })
        .catch((error) => {
          console.log(error);
          alert("본인은 강퇴하실 수 없습니다.");
        })
        .finally(() => {
          console.log(this.roomNo);
          this.userdialog = false;
        });
    },
    sendAuthenInfo() {
      axios
        .post(`api/mission/${this.missionNo}/authentication`, {
          authenticationStartTime: this.start,
          authenticationEndTime: this.end,
        })
        .then((response) => {
          console.log(response);
          console.log("authentication 정보 전송 성공");
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
      axios
        .post(`api/mission/${this.missionNo}/mission-time`, {
          missionTimeStartTime: this.picker + "T00:00:00",
          missionTimeWeek: this.howmanyweeks,
          missionTimeDPW: this.authenweeks,
          missionTimeTPD: this.authendays,
        })
        .then((response) => {
          console.log(response);
          console.log("missiontime 정보 전송 성공");
          this.movePage();
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
    },
    movePage() {
      this.$router.push({
        name: "proceeding",
        params: this.roomNo,
      });
    },
  },
};
</script>

<style></style>
