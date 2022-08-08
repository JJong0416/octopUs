<template>
  <div>
    <v-container>
      <v-row>
        <v-col class="logo-img-wrapper">
          <v-img src="../assets/Logo.png" max-width="30%"></v-img>
        </v-col>
      </v-row>
      <!-- 검색창 -->
      <v-row>
        <v-col cols="4">
          <v-select :items="items" v-model="theme" label="검색 선택"></v-select>
        </v-col>
        <v-col cols="5">
          <v-text-field
            v-model="tofindsearch"
            hide-details
            single-line
          ></v-text-field>
        </v-col>
        <v-col cols="">
          <v-card-text>
            <v-btn icon @click="transmit">
              <v-icon> mdi-magnify </v-icon>
            </v-btn>
          </v-card-text>
        </v-col>
      </v-row>
      <!-- 여기서부터는 게시판 페이지와 거의 일치, 검색완료시에만 표가 나타나게 했고, 게시판 번호 표시 -->
      <v-row v-if="searchfinish === true">
        <!-- 검색 성공시 보여질 페이지 -->
      </v-row>
      <!-- 모달 창 -->
      <div class="text-center">
        <v-dialog v-model="dialog" width="500">
          <template v-slot:activator="{ on, attrs }">
            <v-btn color="red lighten-2" dark v-bind="attrs" v-on="on">
              방 상세보기
            </v-btn>
          </template>

          <v-card>
            <v-card-title class="text-h5 grey lighten-2">
              Theme <v-spacer></v-spacer> MissionTitle
            </v-card-title>

            <v-card-text>
              <div class="my-4 text-subtitle-1">룸 코드</div>
            </v-card-text>
            <v-card-text>
              <div class="my-4 text-subtitle-1">참가자 명단</div>
            </v-card-text>

            <v-card-title> 참여 시 필요한 포인트 5000P</v-card-title>

            <v-card-actions>
              <v-card-text color="orange" text> 방 설명 </v-card-text>

              <v-spacer></v-spacer>

              <v-btn icon @click="show = !show">
                <v-icon>{{
                  show ? "mdi-chevron-up" : "mdi-chevron-down"
                }}</v-icon>
              </v-btn>
            </v-card-actions>

            <v-expand-transition>
              <div v-show="show">
                <v-divider></v-divider>
                <v-card-title>방 설명 / 인증 방법</v-card-title>
                <v-card-text>
                  매일 아침 7시 기상 인증 미라클 모닝 방입니다
                  <br />
                  흐르는 물에 손을 씻는 장면을 찍어 인증합니다!
                </v-card-text>
                <v-card-title>인증 요일 / 시간</v-card-title>
                <v-card-text>
                  일주일에 5 번, 오전 6시 ~ 오전 7시 에 인증합니다.
                </v-card-text>
              </div>
            </v-expand-transition>
            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn color="primary" text @click="dialog = false">
                참가하기
              </v-btn>
            </v-card-actions>
          </v-card>
        </v-dialog>
      </div>
      <v-carousel
        cycle
        interval="1500"
        height="100"
        hide-delimiters
        hide-delimiter-background
        show-arrows-on-hover
      >
        <v-carousel-item v-for="(item, i) in hotmissions" :key="i">
          <v-sheet :color="colors[i]" height="100%">
            <v-row class="fill-height" align="center" justify="center">
              <div class="text-h2">
                <router-link
                  :to="{
                    name: 'before',
                    params: { missionNo: item.missionNo },
                  }"
                  style="text-decoration: none; color: white"
                  >{{ item.missionTitle }}</router-link
                >
              </div>
            </v-row>
          </v-sheet>
        </v-carousel-item>
      </v-carousel>
      <v-carousel
        cycle
        interval="1500"
        height="100"
        hide-delimiters
        hide-delimiter-background
        show-arrows-on-hover
      >
        <v-carousel-item v-for="(item, i) in newmissions" :key="i">
          <v-sheet :color="colors[i]" height="100%">
            <v-row class="fill-height" align="center" justify="center">
              <div class="text-h2">
                <router-link
                  :to="{
                    name: 'before',
                    params: { missionNo: item.missionNo },
                  }"
                  style="text-decoration: none; color: white"
                  >{{ item.missionTitle }}</router-link
                >
              </div>
            </v-row>
          </v-sheet>
        </v-carousel-item>
      </v-carousel>
    </v-container>
  </div>
</template>

<script>
import axios from "axios";
export default {
  // data 속성 전체 코드
  data() {
    return {
      colors: [
        "indigo",
        "warning",
        "pink darken-2",
        "red lighten-1",
        "deep-purple accent-4",
      ],
      theme: "",
      tofindsearch: "",
      slides: ["hot", "new", "mission", "is waiting for", "you"],
      searchtype: "",
      items: ["코드입력", "제목검색", "테마검색"],
      hotmissions: [],
      newmissions: [],
      headers: [
        {
          text: "missionTitle",
          align: "start",
          sortable: false,
          value: "missionTitle",
        },
        { text: "missionCode", value: "missionCode" },
        { text: "missionNo", value: "missionNo" },
      ],
    };
  },
  created() {
    var vm = this;
    axios
      .get(`api/mission/new`, {
        headers: {
          "Access-Control-Allow-Origin": "*",
          "Content-Type": "application/json",
        },
      })
      .then(function (response) {
        console.log(response);

        console.log("여기는 뉴미션");
        console.log(response.data[0]);
        vm.newmissions = response.data;
        console.log(vm.newmissions);
      })
      .catch(function (err) {
        console.log(err);
      })
      .finally({});
    axios
      .get(`api/mission/hot`, {
        headers: {
          "Access-Control-Allow-Origin": "*",
          "Content-Type": "application/json",
        },
      })
      .then(function (response) {
        console.log(response);

        console.log("여기는 hotmission");
        console.log(response.data[0]);
        vm.hotmissions = response.data;
        console.log(vm.newmissions);
      })
      .catch(function (err) {
        console.log(err);
      });
  },
  computed: {},
  methods: {
    transmit() {
      if (this.theme === "코드입력") {
        this.searchtype = "code";
      } else if (this.theme === "제목검색") {
        this.searchtype = "title";
      } else if (this.theme === "테마검색") {
        this.searchtype = "type";
      }
      this.serachMission(this.searchtype);
    },
    serachMission(search) {
      const find = this.tofindsearch;
      axios
        .get(`api/mission/search/${search}/${find}`, {
          headers: {
            "Access-Control-Allow-Origin": "*",
            "Content-Type": "application/json",
          },
        })
        .then(function (response) {
          console.log(response);

          console.log("여기는 hotmission");
          console.log(response.data[0]);
        })
        .catch(function (err) {
          console.log(err);
        })
        .finally(function () {
          console.log(search);
          console.log(find);
        });
    },
  },
};
</script>

<style></style>
