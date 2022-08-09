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
        <v-col cols="8">
          <v-text-field
            v-model="tofindsearch"
            @keyup.enter="transmit"
            hide-details
            single-line
          ></v-text-field>
        </v-col>
      </v-row>

      <!-- 검색 성공시 보여질 페이지 -->

      <v-card
        class="mx-auto"
        max-width="344"
        outlined
        v-for="(item, index) in searchres"
        v-bind:key="index"
      >
        <v-list-item three-line>
          <v-list-item-content>
            <div class="text-overline mb-4">
              참가 포인트 {{ item.missionPoint }}
            </div>
            <v-list-item-title class="text-h5 mb-1">
              {{ item.missionTitle }}
            </v-list-item-title>
            <v-list-item-subtitle>{{
              item.missionContent
            }}</v-list-item-subtitle>
          </v-list-item-content>

          <v-list-item-avatar tile size="80" color="grey"></v-list-item-avatar>
        </v-list-item>

        <v-card-actions>
          <v-btn outlined rounded text @click="beforeJoinCheck(item.missionNo)">
            입장하기
          </v-btn>
        </v-card-actions>
      </v-card>

      <!-- hot,new -->
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
      show: false,
      tofindsearch: "",
      dialog: false,
      userId: "",
      slides: ["hot", "new", "mission", "is waiting for", "you"],
      searchtype: "",
      items: ["코드", "제목", "테마"],
      hotmissions: [],
      newmissions: [],
      searchres: [],
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
    axios
      .get(`api/user/info`, {
        headers: {
          "Access-Control-Allow-Origin": "*",
          "Content-Type": "application/json",
        },
      })
      .then(function (response) {
        console.log(response);

        console.log("유저 ID를 저장성송");

        vm.userId = response.data.userId;
        console.log(vm.userId);
      })
      .catch(function (err) {
        console.log(err);
      });
  },
  computed: {},
  methods: {
    transmit() {
      if (this.theme === "코드") {
        this.searchtype = "code";
      } else if (this.theme === "제목") {
        this.searchtype = "title";
      } else if (this.theme === "테마") {
        this.searchtype = "type";
      } else {
        this.$router.push("/search");
        return;
      }
      this.serachMission(this.searchtype);
    },
    serachMission(search) {
      var vm = this;
      const find = this.tofindsearch;
      axios
        .get(`api/mission/search/${search}/${find}`, {
          headers: {
            "Access-Control-Allow-Origin": "*",
            "Content-Type": "application/json",
          },
        })
        .then(function (response) {
          console.log("여기는 search result");
          console.log(response);
          vm.searchres = response.data;
          console.log(vm.searchres);
        })
        .catch(function (err) {
          console.log(err);
        })
        .finally(function () {
          // console.log(search);
          // console.log(find);
        });
    },
    beforeJoinCheck(missionNum) {
      var vm = this;

      axios
        .get(`api/mission/${missionNum}/users`, {
          headers: {
            "Access-Control-Allow-Origin": "*",
            "Content-Type": "application/json",
          },
        })
        .then(function (response) {
          console.log(response);
          for (var i = 0; i < response.data.length; i++) {
            if (response.data[i] === vm.userId) {
              alert("이미 참가중인 방입니다");
              vm.$router.push({
                name: "proceeding",
                params: { missionNo: missionNum },
              });
              return;
            }
          }

          vm.joinMission(missionNum);
        })
        .catch(function (err) {
          console.log(err);
        })
        .finally(function () {
          // console.log(search);
          // console.log(find);
        });
    },
    joinMission(missionNum) {
      var vm = this;
      axios
        .post(`api/mission/${missionNum}/join`, {
          headers: {
            "Access-Control-Allow-Origin": "*",
            "Content-Type": "application/json",
          },
        })
        .then(function (response) {
          console.log(response);
          console.log("방참가 성공");
          vm.$router.push({
            name: "before",
            params: { missionNo: missionNum },
          });
        })
        .catch(function (err) {
          console.log(err);
        })
        .finally(function () {
          // console.log(search);
          // console.log(find);
        });
    },
  },
};
</script>

<style></style>
