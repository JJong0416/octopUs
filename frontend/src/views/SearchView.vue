<template>
  <div>
    <header-view></header-view>
    <v-container>
      <v-row justify="center">
        <v-img src="../assets/Logo.png" max-width="25%"></v-img>
      </v-row>
      <!-- 검색창 -->
      <v-row>
        <v-col class="py-0" cols="4">
          <v-select :items="items" v-model="theme" label="검색 선택"></v-select>
        </v-col>
        <v-col class="py-0" cols="8" v-if="theme != `테마`">
          <v-text-field
            v-model="tofindsearch"
            @keyup.enter="transmit"
            hide-details
            single-line
          ></v-text-field>
        </v-col>
        <v-col class="py-0" cols="8" v-if="theme == `테마`">
          <v-autocomplete
            v-model="selectedtheme"
            :items="themes"
            dense
            chips
            label="선택된 테마"
            @change="transmit"
          ></v-autocomplete>
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
      <!-- all missions -->
      <v-sheet
        v-if="searchres == null"
        class="mx-auto"
        elevation="8"
        max-width="800"
      >
        <v-slide-group v-model="model" class="pa-4" show-arrows>
          <v-slide-item
            v-for="(item, index) in allmissions"
            :key="index"
            v-slot="{ active, toggle }"
          >
            <v-card
              :color="active ? 'warning' : 'amber'"
              class="ma-4"
              height="200"
              width="200"
              @click="toggle"
            >
              <v-card-title v-if="!active"
                ><h2 class="singleLine text-h5" style="width: 150px">
                  {{ item.missionTitle }}
                </h2></v-card-title
              >
              <v-card-text v-if="!active"
                ><h3>{{ item.missionLeaderId }}의 미션</h3></v-card-text
              >
              <v-row class="fill-height" align="center" justify="center">
                <v-scale-transition>
                  <v-button
                    v-if="active"
                    @click="beforeJoinCheck(item.missionNo)"
                  >
                    <v-icon
                      color="white"
                      size="48"
                      v-text="'mdi-location-enter'"
                    ></v-icon>
                  </v-button>
                </v-scale-transition>
              </v-row>
            </v-card>
          </v-slide-item>
        </v-slide-group>
      </v-sheet>

      <!-- hot,new -->
    </v-container>
    <footer-view></footer-view>
  </div>
</template>

<script>
import HeaderView from "../components/common/HeaderView.vue";
import FooterView from "../components/common/FooterView.vue";
import axios from "axios";
export default {
  components: {
    HeaderView,
    FooterView,
  },
  // data 속성 전체 코드
  data() {
    return {
      model: null,
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
      selectedtheme: "",
      dialog: false,
      userId: "",
      slides: ["hot", "new", "mission", "is waiting for", "you"],
      searchtype: "",
      items: ["코드", "제목", "테마"],
      themes: ["생활", "운동", "공부", "모임", "기타"],
      hotmissions: [],
      newmissions: [],
      allmissions: [],
      searchres: null,
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
    axios.defaults.headers.common[
      "Authorization"
    ] = `Bearer ${sessionStorage.getItem("token")}`;
    axios
      .get(`api/mission/all`, {
        headers: {
          "Access-Control-Allow-Origin": "*",
          "Content-Type": "application/json",
        },
      })
      .then(function (response) {
        console.log(response);
        vm.allmissions = response.data;
        console.log("미션전부받아오기 성공");
      })
      .catch(function (error) {
        console.log(error);
      });
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

        console.log("유저 ID를 저장성공");

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
    changeToType(theme) {
      var missionType;
      if (theme === "생활") {
        missionType = "LIFE";
      } else if (theme === "운동") {
        missionType = "EXERCISE";
      } else if (theme === "공부") {
        missionType = "STUDY";
      } else if (theme === "모임") {
        missionType = "MEETING";
      } else if (theme === "기타") {
        missionType = "ELSE";
      } else {
        this.$router.push("/search");
        return;
      }
      return missionType;
    },
    serachMission(search) {
      var vm = this;
      var find = this.tofindsearch;
      if (search === "type") find = this.changeToType(vm.selectedtheme);
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
