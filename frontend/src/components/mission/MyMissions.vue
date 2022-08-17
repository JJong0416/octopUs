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
    
      </div>
      
    </div>

  </div>
</template>

<script>
import MissionCard from './MissionCard.vue';
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
