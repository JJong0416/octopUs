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

          <v-card-text>
            I'm a thing. But, like most politicians, he promised more than he
            could deliver. You won't have time for sleeping, soldier, not with
            all the bed making you'll be doing. Then we'll go with that data
            file! Hey, you add a one and two zeros to that or we walk! You're
            going to do his laundry? I've got to find a way to escape.
          </v-card-text>
        </div>
      </v-expand-transition>
      <!--  캘린더 열고 닫힘  -->
      <v-card-actions>
        <v-card-text color="orange" text> Calendar </v-card-text>

        <v-spacer></v-spacer>

        <v-btn icon @click="calendarShow = !calendarShow">
          <v-icon>{{
            calendarShow ? "mdi-chevron-up" : "mdi-chevron-down"
          }}</v-icon>
        </v-btn>
      </v-card-actions>

      <v-expand-transition>
        <div v-show="calendarShow">
          <v-divider></v-divider>
          <v-row class="fill-height">
            <v-col>
              <v-sheet height="64">
                <v-toolbar flat>
                  <v-btn
                    outlined
                    class="mr-4"
                    color="grey darken-2"
                    @click="setToday"
                  >
                    Today
                  </v-btn>
                  <v-btn fab text small color="grey darken-2" @click="prev">
                    <v-icon small> mdi-chevron-left </v-icon>
                  </v-btn>
                  <v-btn fab text small color="grey darken-2" @click="next">
                    <v-icon small> mdi-chevron-right </v-icon>
                  </v-btn>
                  <v-toolbar-title v-if="$refs.calendar">
                    {{ $refs.calendar.title }}
                  </v-toolbar-title>
                  <v-spacer></v-spacer>
                  <v-menu bottom right>
                    <template v-slot:activator="{ on, attrs }">
                      <v-btn
                        outlined
                        color="grey darken-2"
                        v-bind="attrs"
                        v-on="on"
                      >
                        <span>{{ typeToLabel[type] }}</span>
                        <v-icon right> mdi-menu-down </v-icon>
                      </v-btn>
                    </template>
                    <v-list>
                      <v-list-item @click="type = 'day'">
                        <v-list-item-title>Day</v-list-item-title>
                      </v-list-item>
                      <v-list-item @click="type = 'week'">
                        <v-list-item-title>Week</v-list-item-title>
                      </v-list-item>
                      <v-list-item @click="type = 'month'">
                        <v-list-item-title>Month</v-list-item-title>
                      </v-list-item>
                      <v-list-item @click="type = '4day'">
                        <v-list-item-title>4 days</v-list-item-title>
                      </v-list-item>
                    </v-list>
                  </v-menu>
                </v-toolbar>
              </v-sheet>
              <v-sheet height="600">
                <v-calendar
                  ref="calendar"
                  v-model="focus"
                  color="primary"
                  :events="events"
                  :event-color="getEventColor"
                  :type="type"
                  @click:event="showEvent"
                  @click:more="viewDay"
                  @click:date="viewDay"
                  @change="updateRange"
                ></v-calendar>
                <v-menu
                  v-model="selectedOpen"
                  :close-on-content-click="false"
                  :activator="selectedElement"
                  offset-x
                >
                  <v-card color="grey lighten-4" min-width="350px" flat>
                    <v-toolbar :color="selectedEvent.color" dark>
                      <v-btn icon>
                        <v-icon>mdi-pencil</v-icon>
                      </v-btn>
                      <v-toolbar-title
                        v-html="selectedEvent.name"
                      ></v-toolbar-title>
                      <v-spacer></v-spacer>
                      <v-btn icon>
                        <v-icon>mdi-heart</v-icon>
                      </v-btn>
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
                </v-menu>
              </v-sheet>
            </v-col>
          </v-row>
        </div>
      </v-expand-transition>
    </v-card>
    <v-btn depressed color="primary" @click="camerashow"> Primary </v-btn>
    <v-easy-camera
      v-model="picture"
      ref="picpreview"
      :startOnMounted="cameraon"
      output="blob"
    ></v-easy-camera>
    <v-btn @click="cameraAction('start')">Start</v-btn>
    <v-btn @click="cameraAction('snap')">Snap</v-btn>
    <v-btn @click="cameraAction('stop')">Stop</v-btn>
    <v-btn @click="cameraAction('close')">Close</v-btn>
  </div>
</template>
<script>
import EasyCamera from "easy-vue-camera";
export default {
  data: () => ({
    picture: "",
    show: false,
    calendarShow: false,
    cameraon: true,
    bURL: "blob:",
    focus: "",
    type: "month",
    typeToLabel: {
      month: "Month",
      week: "Week",
      day: "Day",
      "4day": "4 Days",
    },
    selectedEvent: {},
    selectedElement: null,
    selectedOpen: false,
    events: [],
    colors: [
      "blue",
      "indigo",
      "deep-purple",
      "cyan",
      "green",
      "orange",
      "grey darken-1",
    ],
    names: [
      "Meeting",
      "Holiday",
      "PTO",
      "Travel",
      "Event",
      "Birthday",
      "Conference",
      "Party",
    ],
  }),
  components: {
    "v-easy-camera": EasyCamera,
  },
  methods: {
    camerashow() {
      this.cameraon = !this.cameraon;
    },
    cameraAction(opt) {
      if (opt === "start") {
        console.log("start");
        this.$refs.picpreview.start();
      } else if (opt === "snap") {
        this.$refs.picpreview.snap();
        setTimeout(() => {
          this.processFile(this.picture);
        }, 2000);
      } else if (opt === "stop") {
        this.$refs.picpreview.stop();
      } else if (opt === "close") {
        this.$refs.picpreview.close();
      }
    },
    processFile(blob) {
      // read the files
      var reader = new FileReader();

      if (blob instanceof Blob) {
        reader.readAsArrayBuffer(blob);

        reader.onload = function (event) {
          // blob stuff
          var blob = new Blob([event.target.result]); // create blob...
          window.URL = window.URL || window.webkitURL;
          var blobURL = window.URL.createObjectURL(blob); // and get it's URL

          // helper Image object
          var image = new Image();
          image.src = blobURL;
          image.onload = function () {
            // have to wait till it's loaded
            var canvas = document.createElement("canvas");
            var width = image.width;
            var height = image.height;

            // calculate the width and height, constraining the proportions
            if (width > height) {
              if (width > 100) {
                //height *= max_width / width;
                height = Math.round((height *= 100 / width));
                width = 100;
              }
            } else {
              if (height > 100) {
                //width *= max_height / height;
                width = Math.round((width *= 100 / height));
                height = 100;
              }
            }

            // resize the canvas and draw the image data into it
            canvas.width = width;
            canvas.height = height;
            var ctx = canvas.getContext("2d");
            ctx.drawImage(image, 0, 0, width, height);

            canvas.toBlob(function (blob) {
              const newImg = document.createElement("img");
              const url = URL.createObjectURL(blob);

              console.log(blob);
              //send this blob to API

              newImg.onload = function () {
                // no longer need to read the blob so it's revoked
                URL.revokeObjectURL(url);
              };

              newImg.src = url;
              document.body.appendChild(newImg);
              console.log(newImg);
              console.log(typeof newImg);
            });
          };
        };
      } else {
        console.log(this.picture);
      }
    },

    // 달력과 관련된 methods
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
