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
                    오늘 {{pictures.length}} 번 인증하셨습니다.
                    <div v-for="index in pictures" :key="index">
                      <v-img :src="index" max-width="30vh"></v-img> 
                    </div>
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
    pictureList : {},
    pictures : [],
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
      var vm = this;
      vm.pictureList = {};
      for(let i = 0; i < this.calendarUserInfos.length; i++){
        
        for(let j = 0; j < this.calendarUserInfos[i].userPictures.length; j++){
         
          const nickname = this.calendarUserInfos[i].userNickname;
          const date = this.calendarUserInfos[i].userPictures[j].date.split('T')[0];
          const avartar = this.calendarUserInfos[i].userAvatar;
          if(vm.pictureList[(nickname +" " + date+" " + avartar)] == undefined){
            vm.pictureList[(nickname +" " + date+" " + avartar)] = [this.calendarUserInfos[i].userPictures[j].pictureUrl];
          }else{
            var tempList = vm.pictureList[(nickname +" " + date+" " + avartar)];
            tempList.push(this.calendarUserInfos[i].userPictures[j].pictureUrl);
            vm.pictureList[(nickname +" " + date+" " + avartar)] = tempList;
          }
          
        }
      }
     
      for(var key in vm.pictureList){
        events.push({
            name: key.split(" ")[0],
            start:  key.split(" ")[1],
            timed : true,
            color: this.colors[key.split(" ")[2]-1], 
          });     

      }
     
       this.events = events;
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
      const temp = event.name + " " + event.start + " " + (this.colors.indexOf(event.color)+1);
      
      const open = () => {
        this.pictures = this.pictureList[temp];
       
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
::v-deep .white--text{
  height: 50px;
}
</style>
