<template>
  <div>
    <template slot="progress">
      <v-progress-linear
        color="deep-purple"
        height="10"
        indeterminate
      ></v-progress-linear>
    </template>

    <div class="font-weight-bold">
      {{ this.missionType }}
    </div>
    {{ this.missionTitle }}

    <v-card-text>
      <div>참가자 명단</div>
      <div class="my-4 text-subtitle-1">{{ this.missionUsers }}</div>
    </v-card-text>

    <v-card-text>
      <div>필요한 포인트</div>
      <div class="my-4 text-subtitle-1">{{ this.missionPoint }}</div>
    </v-card-text>

    <v-card-actions>
      <v-card-text color="orange" text> 방 설명 보기 </v-card-text>

      <v-spacer></v-spacer>

      <v-btn icon @click="show = !show">
        <v-icon>{{ show ? "mdi-chevron-up" : "mdi-chevron-down" }}</v-icon>
      </v-btn>
    </v-card-actions>

    <v-expand-transition>
      <div v-show="show">
        <v-divider></v-divider>

        <v-card-text> {{ this.missionContent }} </v-card-text>
      </div>
    </v-expand-transition>

    <!-- calendar 서랍 ---------------------------------------- -->
    <v-card-actions>
      <v-card-text color="orange" text>
        캘린더로 진행 상황 확인하기
      </v-card-text>

      <v-spacer></v-spacer>

      <v-btn icon @click="getInfo">
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
                
                short-intervals
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
                  </v-toolbar>
                  <v-card-text class="text-center">
                    오늘 {{}} 번 인증하셨습니다.
                    <v-img src="../assets/img/1.png" max-width="30vh"></v-img>
                  </v-card-text>
                  <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn text color="secondary" @click="selectedOpen = false">
                      확인
                    </v-btn>
                  </v-card-actions>
                </v-card>
                <!-- -------------------------------------------------- -->
              </v-menu>
            </v-sheet>
          </v-col>
        </v-row>
        <v-card-text>
          <div>팀성공률</div>
          <div class="my-4 text-subtitle-1">{{ this.missionTeamSuccess }}%</div>
        </v-card-text>
      </div>
    </v-expand-transition>
    <div class="text-center">
      <router-link :to="`/camera`"> <v-btn>인증하기</v-btn></router-link>
    </div>
  </div>
</template>

<script>
import axios from "axios";
//import { apiInstance } from "@/api/index.js"
export default {
  data: () => ({
    missionTitle: "",
    missionContent: "",
    missionCode: "",
    missionUsers: "",
    missionPoint: "",
    missionType: "",
    missionTeamSuccess: 0,
    weekInProgress: 0,
    isCurrentUserPicutrePost: false,
    calendarUserInfos: [],
    picture: "",
    content: "",
    show: false,
    calendarShow: false,
    cameraShow: false,
    cameraon: true,
    bURL: "blob:",
    focus: "",
    type: "week",
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
    events: [
      // {
      //     name: 'Event 1',
      //     // start: '2022-08-09T03:00:00',
      //     // end: '2022-08-09T06:00:00',
      //     start : '2022-08-08',
      //     color : "#A0BAD0",
      //     timed: true,
 
      //   },
      //    {
      //     name: 'Event 2',
      //     // start: '2022-08-09T03:00:00',
      //     // end: '2022-08-09T06:00:00',
      //     start : '2022-08-08',
      //     color : "#A0BAD0",
      //     timed: true,
 
      //   },
      //    {
      //     name: 'Event 3',
      //     // start: '2022-08-09T03:00:00',
      //     // end: '2022-08-09T06:00:00',
      //     start : '2022-08-08',
      //     color : "#A0BAD0",
      //     timed: true,
 
      //   },
      //    {
      //     name: 'Event 4',
      //     // start: '2022-08-09T03:00:00',
      //     // end: '2022-08-09T06:00:00',
      //     start : '2022-08-08',
      //     color : "#A0BAD0",
      //     timed: true,
 
      //   },
      //    {
      //     name: 'Event 5',
      //     // start: '2022-08-09T03:00:00',
      //     // end: '2022-08-09T06:00:00',
      //     start : '2022-08-08',
      //     color : "#A0BAD0",
      //     timed: true,
 
      //   },
         
    ],
    // 캘린더 이벤트 색 8개
    colors: [
      "teal",
      "orange",
      "brown",
      "gray",
      "purple",
      "indigo",
      "red",
      "lime",
    ],
    names: ["Nickname"],
  }),
  created() {
    //const api = apiInstance();
    axios
      .get(`../api/mission/${this.$route.params.missionNo}`, {
        headers: {
          "Access-Control-Allow-Origin": "*",
          "Content-Type": "application/json",
        },
      })
      .then(({ data }) => {
        this.missionTitle = data.missionTitle;
        this.missionContent = data.missionContent;
        this.missionCode = data.missionCode;
        this.missionPoint = data.missionPoint;
        this.missionUsers = data.missionUsers;
        this.missionType = data.missionType;
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
            console.log(data);
            this.missionTeamSuccess = data.successTeamRate;
            this.calendarUserInfos = data.calenderUserInfos;
            this.weekInProgress = data.weekInProgress;
            this.isCurrentUserPicutrePost = data.isCurrentUserPicutrePost;
          })
          .catch(function (err) {
            console.log(err);
          });
  },

  methods: {
    // 달력과 관련된 methods---------------------------
     uploadCalendar(){
      
    },
    getInfo() {
      const events = [];
      
      for(let i = 0; i < this.calendarUserInfos.length; i++){
         
        for(let j = 0; j < this.calendarUserInfos[i].userPictures.length; j++){
          
          console.log(this.calendarUserInfos[i].userPictures[j].date);
          const first = this.calendarUserInfos[i].userPictures[j].date.split('T')[0];
        
          events.push({
            name: this.calendarUserInfos[i].userNickname,
            start: first,
            timed : true,
            color: this.colors[this.calendarUserInfos[i].userAvatar -1],   
          
          });     
        console.log(this.calendarUserInfos[i].userPictures[j].date);
        }
      }
       this.events = events;
       console.log("-----" + this.events);
      this.calendarShow = !this.calendarShow;
    },
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
      console.log(this.calendarUserInfos.length);
      for (let i = 0; i < this.calendarUserInfos.length; i++) {
        console.log(i);
      }
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
::v-deep .v-calendar .v-calendar-daily__body {
  display: none;
}
::v-deep .col .v-sheet.theme--light:nth-child(2){
  height: 100% !important;
}
::v-deep .v-calendar .v-calendar-daily__head .v-calendar-daily__intervals-head {
  display: none;
}
::v-deep .v-calendar v-calendar-daily theme--light v-calendar-events {
  display: none;
}
::v-deep .v-event.v-event-start.v-event-end.white--text{
  height: 30px !important;
 
}
/* ::v-deep .v-calendar-daily__head {
  height: 100%;
} */
::v-deep .white--text{
  height: 50px;
 
}
</style>
