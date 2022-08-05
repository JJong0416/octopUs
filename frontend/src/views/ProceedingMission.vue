<template>
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
          <v-icon>{{
            calendarShow ? "mdi-chevron-up" : "mdi-chevron-down"
          }}</v-icon>
        </v-btn>
      </v-card-actions>
      <!-- -------------------------------------------------------- -->
      <!-- 캘린더 view --------------------------------------------- -->
      <v-expand-transition>
        <div v-show="calendarShow">
          <v-divider></v-divider>
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
                  color="#E63946"
                  :events="events"
                  :event-color="getEventColor"
                  :type="type"
                  @click:event="showEvent"
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

        <v-spacer></v-spacer>

        <v-btn icon @click="cameraShow = !cameraShow">
          <v-icon>{{
            cameraShow ? "mdi-chevron-up" : "mdi-chevron-down"
          }}</v-icon>
        </v-btn>
      </v-card-actions>
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
    cameraShow: false,
    cameraon: true,
    bURL: "blob:",
    focus: "",
    type: "month",
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
      const open = () => {
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
</style>
