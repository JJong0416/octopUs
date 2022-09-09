<template>
<<<<<<< HEAD
  <div>
    <v-card :loading="loading" class="mx-auto my-12" max-width="374">
      <template slot="progress">
        <v-progress-linear
          color="deep-purple"
          height="10"
          indeterminate
        ></v-progress-linear>
      </template>

      <v-card-title>MissionTitle</v-card-title>

      <v-card-text>
        <div class="my-4 text-subtitle-1">룸 코드</div>
      </v-card-text>
      <v-card-text>
        <div class="my-4 text-subtitle-1">참가자 명단</div>
      </v-card-text>

      <v-card-title> 필요한 포인트 </v-card-title>

      <v-card-actions>
        <v-card-text color="orange" text> Explore </v-card-text>

        <v-spacer></v-spacer>

        <v-btn icon @click="show = !show">
          <v-icon>{{ show ? "mdi-chevron-up" : "mdi-chevron-down" }}</v-icon>
        </v-btn>
      </v-card-actions>

      <v-expand-transition>
        <div v-show="show">
          <v-divider></v-divider>

          <v-card-text> 방 설명 </v-card-text>
        </div>
      </v-expand-transition>

      <!-- calendar 서랍 ---------------------------------------- -->
      <v-card-actions>
        <v-card-text color="orange" text> Calendar </v-card-text>

        <v-spacer></v-spacer>

        <v-btn icon @click="calendarShow = !calendarShow">
=======
  <v-container>
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

      <!-- ---------------------------------------------------------------------------- -->

      <v-card-actions>
        <div class="my-4 text-subtitle-1">미션 정보 확인하기</div>

        <v-spacer></v-spacer>

        <v-btn icon @click="missionInfoShow = !missionInfoShow">
          <v-icon>{{
            missionInfoShow ? "mdi-chevron-up" : "mdi-chevron-down"
          }}</v-icon>
        </v-btn>
      </v-card-actions>
      <v-expand-transition>
        <div v-show="missionInfoShow">
          <v-divider></v-divider>
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
            <v-icon color="pink darken-1">mdi-calendar-check</v-icon
            >&nbsp;&nbsp;
            <div class="my-4 text-subtitle-1">
              <b>미션은 언제부터인가요?</b>
            </div>
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
                          size="40"
                          color="#ffadad"
                          @click="previewUser(item)"
                          v-bind="attrs"
                          v-on="on"
                        >
                          <b>{{ item }}</b>
                        </v-avatar>
                      </v-col>
                    </v-row>
                  </v-container>
                </template>

                <v-card>
                  <v-card-title class="text-h6">
                    {{ previewUsernickname }}
                  </v-card-title>

                  <v-row class="animate__animated animate__tada">
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

                    <v-btn color="#143559" outlined @click="userdialog = false">
                      확인
                    </v-btn>
                  </v-card-actions>
                </v-card>
              </v-dialog>
            </div>
          </v-card-text>
        </div>
      </v-expand-transition>

      <v-divider></v-divider>
      <!-- -------------------------------------------------------- -->
      <!-- 캘린더 view --------------------------------------------- -->
      <v-card-actions>
        <div class="my-4 text-subtitle-1">캘린더로 미션 진행 상황 확인하기</div>

        <v-spacer></v-spacer>

        <v-btn icon @click="getInfo()">
>>>>>>> ac048782d308840093efdf3b8146c990ec2fcf95
          <v-icon>{{
            calendarShow ? "mdi-chevron-up" : "mdi-chevron-down"
          }}</v-icon>
        </v-btn>
      </v-card-actions>
<<<<<<< HEAD
      <!-- -------------------------------------------------------- -->
      <!-- 캘린더 view --------------------------------------------- -->
      <v-expand-transition>
        <div v-show="calendarShow">
          <v-divider></v-divider>
=======
      <v-expand-transition>
        <div v-show="calendarShow">
          <v-card-title>
            <v-icon color="pink darken-1">mdi-clock-time-four-outline</v-icon
            >&nbsp;&nbsp;
            <div
              v-if="mission.missionStatus == 'ONGOING'"
              class="my-4 text-subtitle-1"
            >
              <b>현재 미션 {{ weekInProgress }}주차 진행 중</b>
            </div>
            <div
              v-else-if="mission.missionStatus == 'CLOSE'"
              class="my-4 text-subtitle-1"
            >
              <b>해당 미션은 종료되었습니다.</b>
            </div>
          </v-card-title>
>>>>>>> ac048782d308840093efdf3b8146c990ec2fcf95
          <v-row class="fill-height">
            <v-col>
              <v-sheet height="64">
                <v-toolbar flat>
                  <!-- 오늘로 가는 버튼 -->
                  <v-btn
                    outlined
                    class="mr-4"
                    color="grey darken-2"
                    @click="setToday"
                  >
                    Today
                  </v-btn>
                  <!-- --------------- -->
                  <v-btn fab text small color="grey darken-2" @click="prev">
                    <v-icon small> mdi-chevron-left </v-icon>
                  </v-btn>
                  <v-btn fab text small color="grey darken-2" @click="next">
                    <v-icon small> mdi-chevron-right </v-icon>
                  </v-btn>
                  <v-toolbar-title v-if="$refs.calendar">
                    {{ $refs.calendar.title }}
                  </v-toolbar-title>
                </v-toolbar>
              </v-sheet>
              <v-sheet height="600">
                <v-calendar
                  ref="calendar"
                  v-model="focus"
<<<<<<< HEAD
                  color="#E63946"
=======
                  color="#ffadad"
>>>>>>> ac048782d308840093efdf3b8146c990ec2fcf95
                  :events="events"
                  :event-color="getEventColor"
                  :type="type"
                  @click:event="showEvent"
<<<<<<< HEAD
                  @click:more="showAllEvent"
                  @change="updateRange"
                ></v-calendar>
                <v-menu
                  v-model="selectedOpen"
                  :close-on-content-click="false"
                  :activator="selectedElement"
                  offset-x
                >
                  <!-- 클릭하면 나오는 상세 창 -------------------------------- -->
                  <v-card color="grey lighten-4" min-width="350px" flat>
                    <v-toolbar :color="selectedEvent.color" dark>
                      <v-toolbar-title
                        v-html="selectedEvent.name"
                      ></v-toolbar-title>
                      <v-spacer></v-spacer>
                      <v-btn icon>
                        <v-icon>mdi-dots-vertical</v-icon>
                      </v-btn>
                    </v-toolbar>
                    <v-card-text>
                      <span v-html="selectedEvent.details"></span>
                    </v-card-text>
                    <v-card-actions>
                      <v-btn
                        text
                        color="secondary"
                        @click="selectedOpen = false"
                      >
                        Cancel
                      </v-btn>
                    </v-card-actions>
                  </v-card>
                  <!-- -------------------------------------------------- -->
                </v-menu>
              </v-sheet>
            </v-col>
          </v-row>
        </div>
      </v-expand-transition>
      <!-- -------------------------------------------------------------------- -->

      <!-- 인증하기 서랍 ----------------------------------------- -->
      <v-card-actions>
        <v-card-text color="orange" text> 인증하기 </v-card-text>
=======
                  short-intervals
                ></v-calendar>
                <div class="text-center">
                  <v-dialog
                    v-model="selectedOpen"
                    :close-on-content-click="false"
                    :activator="selectedElement"
                  >
                    <v-card color="grey lighten-4" max-width="100%" flat>
                      <v-toolbar :color="selectedEvent.color" dark>
                        <v-toolbar-title
                          v-html="selectedEvent.name"
                        ></v-toolbar-title>
                      </v-toolbar>
                      <v-card-text class="text-center">
                        <br />
                        <div>{{ clickDate }}</div>
                        <b>{{ pictures.length }}</b> 번 인증하셨습니다.
                        <div v-for="index in pictures" :key="index">
                          <center>
                            <v-img
                              :src="index"
                              max-width="50vh"
                              style="margin-top: 3vh"
                            ></v-img>
                          </center>
                        </div>
                      </v-card-text>
                      <v-card-actions>
                        <v-spacer></v-spacer>
                        <v-btn
                          outlined
                          color="#143559"
                          @click="selectedOpen = false"
                        >
                          확인
                        </v-btn>
                      </v-card-actions>
                    </v-card>
                  </v-dialog>
                </div>
              </v-sheet>
            </v-col>
          </v-row>

          <v-card-text>
            <h3>팀 성공률</h3>
            <v-progress-linear
              v-model="missionTeamSuccess"
              color="pink"
              height="25"
              style="pointer-events: none"
            >
              <template v-slot:default="{ value }">
                <strong>{{ value }}%</strong>
              </template>
            </v-progress-linear>
          </v-card-text>
          <v-card-text v-for="(user, i) in calendarUserInfos" :key="i">
            <h5>{{ user.userNickname }}</h5>
            <v-progress-linear
              :value="user.successUserRate"
              :color="colors[user.userAvatar]"
              height="25"
              style="pointer-events: none"
            >
              <strong>{{ user.successUserRate }}%</strong>
            </v-progress-linear>
          </v-card-text>
        </div>
      </v-expand-transition>

      <v-divider></v-divider>
      <v-card-actions>
        <div class="my-4 text-subtitle-1">인증하기</div>
>>>>>>> ac048782d308840093efdf3b8146c990ec2fcf95

        <v-spacer></v-spacer>

        <v-btn icon @click="cameraShow = !cameraShow">
          <v-icon>{{
            cameraShow ? "mdi-chevron-up" : "mdi-chevron-down"
          }}</v-icon>
        </v-btn>
      </v-card-actions>
<<<<<<< HEAD
      <!-- -------------------------------------------------------- -->
      <!-- 카메라로 인증하기 --------------------------------------- -->
      <v-expand-transition>
        <div v-show="cameraShow">
          <web-cam
            ref="webcam"
            :device-id="deviceId"
            width="100%"
            @started="onStarted"
            @stopped="onStopped"
            @error="onError"
            @cameras="onCameras"
            @camera-change="onCameraChange"
          />
          <v-btn type="button" class="btn btn-primary" @click="onCapture">
            찍기
          </v-btn>
          <v-spacer></v-spacer>
          미리보기
          <figure class="figure">
            <img :src="img" width="100%" />
          </figure>
          <v-btn v-if="img != null" @click="onSave">올리기</v-btn>
          <v-btn v-if="img != null" @click="onRestart">다시 찍기</v-btn>
        </div>
      </v-expand-transition>
      <!-- -------------------------------------------------------- -->
    </v-card>
  </div>
</template>

<script>
import { WebCam } from "vue-web-cam";
import { find, head } from "lodash";
export default {
  components: {
    WebCam,
  },
  data: () => ({
    picture: "",
    content: "",
    show: false,
    calendarShow: false,
=======
      <v-expand-transition>
        <div v-show="cameraShow">
          <v-divider></v-divider>
          <v-card-text class="text-center" v-if="!isCurrentUserPicturePost">
            인증 가능한 시간이 아닙니다.
          </v-card-text>
          <v-card-text class="text-center" v-else>
            <v-btn color="#143559" outlined @click="takepicture"
              >사진 찍기</v-btn
            >
            <input
              id="file"
              type="file"
              accept="image/*"
              style="display: none"
              capture
            />&nbsp;&nbsp;&nbsp;&nbsp;
            <v-btn outlined color="#fa183e" @click="canvas(missionNo)"
              >사진 업로드</v-btn
            >
          </v-card-text>
        </div>
      </v-expand-transition>
    </v-card>
  </v-container>
</template>

<script>
import axios from "axios";
import "animate.css";
export default {
  data: () => ({
    userdialog: false,
    previewUsernickname: "",
    userAvatar: [0, 0, "0_nothing", "0_nothing"],
    missionNo: "",
    mission: null,
    missionDetail: null,
    missionTeamSuccess: 0,
    weekInProgress: 0,
    isCurrentUserPicturePost: false,
    calendarUserInfos: [],
    pictureList: {},
    pictures: [],
    clickDate: "",
    clickSuccessRate: "",
    picture: "",
    calendarShow: false,
    missionInfoShow: false,
>>>>>>> ac048782d308840093efdf3b8146c990ec2fcf95
    cameraShow: false,
    cameraon: true,
    bURL: "blob:",
    focus: "",
<<<<<<< HEAD
    type: "month",
=======
    type: "week",
>>>>>>> ac048782d308840093efdf3b8146c990ec2fcf95
    typeToLabel: {
      month: "Month",
      week: "Week",
      day: "Day",
      "4day": "4 Days",
      imageBase64: null,
    },
    selectedEvent: {},
    selectedElement: null,
    selectedOpen: false,
    events: [],
    // 캘린더 이벤트 색 8개
    colors: [
<<<<<<< HEAD
      "#F1FAEE",
      "#A8DADC",
      "#1D3557",
      "#457B9D",
      "#FDFFB6",
      "#FFADAD",
      "#ED7E9C",
      "#A0BAD0",
    ],
    names: ["Nickname"],
    // camera ----------
    img: null,
    camera: null,
    deviceId: null,
    devices: [],
    // ------------------
  }),
  computed: {
    device() {
      return find(this.devices, (n) => n.deviceId == this.deviceId);
    },
  },
  watch: {
    camera: function (id) {
      this.deviceId = id;
    },
    devices: function () {
      // Once we have a list select the first one
      let first = head(this.devices);
      if (first) {
        this.camera = first.deviceId;
        this.deviceId = first.deviceId;
      }
    },
  },
  methods: {
    //camera methods-------------------------------------------
    onCapture() {
      this.img = this.$refs.webcam.capture();
    },
    onStarted(stream) {
      console.log("On Started Event", stream);
    },
    onStopped(stream) {
      console.log("On Stopped Event", stream);
    },
    onStop() {
      this.$refs.webcam.stop();
    },
    onStart() {
      this.$refs.webcam.start();
    },
    onError(error) {
      console.log("On Error Event", error);
    },
    onCameras(cameras) {
      this.devices = cameras;
      console.log("On Cameras Event", cameras);
    },
    onCameraChange(deviceId) {
      this.deviceId = deviceId;
      this.camera = deviceId;
      console.log("On Camera Change Event", deviceId);
    },
    onRestart() {
      this.$refs.webcam.stop();
      this.img = null;
      this.$refs.webcam.start();
    },
    onSave() {
      console.log(this.img);
      this.$refs.webcam.stop();
      this.cameraShow = false;
      console.log(this.img);
      // 서버에 img 전송하는 코드 추가........ㅠ_ㅠ
      this.img = null;
      this.$refs.webcam.start();
    },
    //------------------------------------------------

    // 달력과 관련된 methods---------------------------
=======
      "#fa183e",
      "#ffbbed",
      "#8500ff",
      "#15ff00",
      "#003fff",
      "#ffec00",
      "#d2alff",
      "#b9ffb2",
      "#a5bbff",
      "#FFF800",
      "black",
      "#FFD000",
      "#000F75",
      "#A26057",
    ],
    names: ["Nickname"],
  }),
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
  created() {
    var vm = this;
    vm.missionNo = this.$route.params.missionNo;
    axios.defaults.headers.common[
      "Authorization"
    ] = `Bearer ${sessionStorage.getItem("token")}`;
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

        axios
          .get(`../api/mission/${vm.$route.params.missionNo}/mission-detail`, {
            headers: {
              "Access-Control-Allow-Origin": "*",
              "Content-Type": "application/json",
            },
          })
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
    axios
      .get(`../api/mission/${this.$route.params.missionNo}/calender`, {
        headers: {
          "Access-Control-Allow-Origin": "*",
          "Content-Type": "application/json",
        },
      })
      .then(({ data }) => {
        this.missionTeamSuccess = data.successTeamRate;
        this.calendarUserInfos = data.calenderUserInfos;
        this.weekInProgress = data.weekInProgress;
        this.isCurrentUserPicturePost = data.isCurrentUserPicturePost;
      })
      .catch(function (err) {
        console.log(err);
      });
  },

  methods: {
    takepicture() {
      document.getElementById("file").click();
    },
    canvas(missionNo) {
      var input = document.querySelector("input[type=file]");
      var file = input.files[0];
      var reader = new FileReader();
      let encodedImg = "";

      reader.onload = function (e) {
        var dataURL = e.target.result,
          c = document.querySelector("canvas"), // see Example 4
          ctx = c.getContext("2d"),
          img = new Image();

        img.onload = function () {
          c.width = img.width;
          c.height = img.height;
          ctx.drawImage(img, 0, 0);
        };
        img.src = dataURL;
      };
      console.log("사진제발찍혀라제발");
      reader.readAsDataURL(file);
      reader.onloadend = function () {
        encodedImg = reader.result;
        console.log("BASE64가 나올겁니다");
        console.log("Base64 String - ", encodedImg);
        axios
          .post(`../../api/mission/${missionNo}/picture`, {
            headers: {
              "Access-Control-Allow-Origin": "*",
              "Content-Type": "application/json",
            },
            encodedImg,
          })
          .then((response) => {
            alert("정상적으로 등록되었습니다.");
            console.log(response);

            window.location.href = `/proceeding/${missionNo}`;
          })
          .catch((error) => {
            console.log(error);
            alert("사진전송실패");
          });
      };
    },
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

    getInfo() {
      const events = [];
      var vm = this;
      vm.pictureList = {};

      for (let i = 0; i < this.calendarUserInfos.length; i++) {
        for (
          let j = 0;
          j < this.calendarUserInfos[i].userPictures.length;
          j++
        ) {
          const nickname = this.calendarUserInfos[i].userNickname;
          const date =
            this.calendarUserInfos[i].userPictures[j].date.split("T")[0];
          const avatarColor =
            this.calendarUserInfos[i].userAvatar.split(", ")[0];
          this.calendarUserInfos[i].userAvatar = avatarColor;
          if (
            vm.pictureList[nickname + " " + date + " " + avatarColor] ==
            undefined
          ) {
            vm.pictureList[nickname + " " + date + " " + avatarColor] = [
              this.calendarUserInfos[i].userPictures[j].pictureUrl,
            ];
          } else {
            var tempList =
              vm.pictureList[nickname + " " + date + " " + avatarColor];
            tempList.push(this.calendarUserInfos[i].userPictures[j].pictureUrl);
            vm.pictureList[nickname + " " + date + " " + avatarColor] =
              tempList;
          }
        }
      }

      for (var key in vm.pictureList) {
        events.push({
          name: key.split(" ")[0],
          start: key.split(" ")[1],
          timed: true,
          color: this.colors[key.split(" ")[2]],
        });
      }
      this.calendarShow = !this.calendarShow;
      this.events = events;
    },
>>>>>>> ac048782d308840093efdf3b8146c990ec2fcf95
    viewDay({ date }) {
      this.focus = date;
      this.type = "day";
    },
    getEventColor(event) {
      return event.color;
    },
    setToday() {
      this.focus = "";
    },
    prev() {
      this.$refs.calendar.prev();
    },
    next() {
      this.$refs.calendar.next();
    },
    showEvent({ nativeEvent, event }) {
<<<<<<< HEAD
      const open = () => {
=======
      const temp =
        event.name + " " + event.start + " " + this.colors.indexOf(event.color);

      const open = () => {
        this.pictures = this.pictureList[temp];
        this.clickDate = event.start;
>>>>>>> ac048782d308840093efdf3b8146c990ec2fcf95
        this.selectedEvent = event;
        this.selectedElement = nativeEvent.target;
        requestAnimationFrame(() =>
          requestAnimationFrame(() => (this.selectedOpen = true))
        );
      };

      if (this.selectedOpen) {
        this.selectedOpen = false;
        requestAnimationFrame(() => requestAnimationFrame(() => open()));
      } else {
        open();
      }

      nativeEvent.stopPropagation();
    },
<<<<<<< HEAD
    // showAllEvent({nativeEvent, event}){
    //   // 해당 요일 모든 이벤트를 출력해주는 함수 생성
    // },
    updateRange({ start, end }) {
      const events = [];

      const min = new Date(`${start.date}T00:00:00`);
      const max = new Date(`${end.date}T23:59:59`);
      const days = (max.getTime() - min.getTime()) / 86400000;
      const eventCount = this.rnd(days, days + 20);

      for (let i = 0; i < eventCount; i++) {
        const allDay = this.rnd(0, 3) === 0;
        const firstTimestamp = this.rnd(min.getTime(), max.getTime());
        const first = new Date(firstTimestamp - (firstTimestamp % 900000));
        const secondTimestamp = this.rnd(2, allDay ? 288 : 8) * 900000;
        const second = new Date(first.getTime() + secondTimestamp);

        events.push({
          name: this.names[this.rnd(0, this.names.length - 1)],
          start: first,
          end: second,
          color: this.colors[this.rnd(0, this.colors.length - 1)],
          timed: !allDay,
        });
      }

      this.events = events;
    },
    rnd(a, b) {
      return Math.floor((b - a + 1) * Math.random()) + a;
    },
=======
>>>>>>> ac048782d308840093efdf3b8146c990ec2fcf95
  },
};
</script>
<style scoped>
h3 {
  margin: 40px 0 0;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
<<<<<<< HEAD
=======
::v-deep .v-calendar .v-calendar-daily__body {
  display: none;
}
::v-deep .col .v-sheet.theme--light:nth-child(2) {
  height: 100% !important;
}
::v-deep .v-calendar .v-calendar-daily__head .v-calendar-daily__intervals-head {
  display: none;
}
::v-deep .v-calendar v-calendar-daily theme--light v-calendar-events {
  display: none;
}
::v-deep .v-event.v-event-start.v-event-end.white--text {
  height: 30px !important;
}
::v-deep .white--text {
  height: 50px;
}
.logo-img-wrapper {
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 0;
}
>>>>>>> ac048782d308840093efdf3b8146c990ec2fcf95
</style>
