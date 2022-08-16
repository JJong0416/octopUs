<template>
  <div>
    <v-toolbar flat>
      <v-spacer></v-spacer>
      <v-text-field
        v-model="search"
        append-icon="mdi-magnify"
        label="검색"
        single-line
        hide-details
      ></v-text-field>
    </v-toolbar>
    <br />
    <v-divider></v-divider>
    <v-bottom-navigation
      style="box-shadow: none !important"
      v-model="statusSearch"
      color="pink darken-1"
      grow
    >
      <v-btn value="UNACTIVATED">
        <span>비활성화</span>

        <v-icon>mdi-battery-alert-variant-outline</v-icon>
      </v-btn>

      <v-btn value="OPEN">
        <span>모집중</span>

        <v-icon>mdi-battery-low</v-icon>
      </v-btn>

      <v-btn value="ONGOING">
        <span>진행중</span>

        <v-icon>mdi-battery-medium</v-icon>
      </v-btn>
      <v-btn value="CLOSE">
        <span>종료</span>

        <v-icon>mdi-battery-high</v-icon>
      </v-btn>
    </v-bottom-navigation>

    <v-divider></v-divider>
    <br />

    <div class="wrapper">
      <div class="card" v-for="(mission, index) in filteredList" :key="index">
        <div v-if="`${search.length}` == 0">
          <mission-card :mission=mission></mission-card>
        </div>
        <div v-else-if="mission.missionTitle.includes(search)">
          <mission-card :mission=mission></mission-card>
        </div>
        

        <div v-else><v-card-text
        style="display: flex; justify-content: center; align-items: center"
      >
      검색 조건에 맞는 미션이 없습니다.
      </v-card-text></div>
        <br />
        <!-- <a v-bind:href="post.link" target="_blank">
        <img v-bind:src="post.img"/>
        <small>posted by: {{ post.author }}</small> 
        {{ post.title }}n
      </a> -->
      </div>
      
    </div>

  </div>
</template>

<script>
import axios from "axios";
import MissionCard from './MissionCard.vue';

//import MissionCard from "../MissionCard.vue";
export default {
  components: { MissionCard },
  props: ["missions"],

  data() {
    return {
      search: "",
      statusSearch: "",
      openexpanded: [],
      closeexpanded: [],
      singleExpand: true,
      Missionheaders: [
        {
          text: `방 이름`,
          align: `start`,
          softable: false,
          value: `missionTitle`,
        },
        { text: `미션상태`, value: `missionStatus` },
        { text: `포인트`, value: `missionPoint` },
        { text: ``, value: `data-table-expand` },
      ],
      URL: null,
      userPictures: [],
    };
  },
  methods: {
    clickExpand({ item }) {
      var vm = this;
      console.log(item);
      axios
        .get(`api/mission/${item.missionNo}/picture`, {
          headers: {
            "Access-Control-Allow-Origin": "*",
            "Content-Type": "application/json",
          },
        })
        .then(function (response) {
          console.log(response);
          // 내부에 받은 이미지 확인필요
          console.log(response.data[0].userPictures[0].pictureUrl);
          console.log(response.data[0].userPictures);
          vm.userPictures = response.data[0].userPictures;
          // vm.URL = response.data[0].userPictures[0].pictureUrl;
          // console.log(vm.URL)
        })
        .catch(function (err) {
          console.log(err);
        });
    },
    getColor(point) {
      if (point > 1000) return "red";
      else if (point > 500) return "orange";
      else return "green";
    },
  },
  computed: {
    filteredList() {
      return this.missions.filter((mission) => {
        return mission.missionStatus
          .toLowerCase()
          .includes(this.statusSearch.toLowerCase());
      });
    },
  },
};
</script>

<style>
.logo {
  text-decoration: none;
}
</style>
