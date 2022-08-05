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

      <v-card-title> {{ mission.missionTitle }} </v-card-title>

      <v-card-text>
        <div class="my-4 text-subtitle-1">{{ mission.missionCode }}</div>
      </v-card-text>
      <v-card-text>
        <div class="my-4 text-subtitle-1">
          참가자 명단
          {{ mission.missionUsers }}
        </div>
      </v-card-text>
      <v-card-title
        >방 인원제한 : {{ mission.missionLimitPersonnel }}</v-card-title
      >
      <v-card-title
        >미션 참가시 필요한 포인트 : {{ mission.missionPoint }}</v-card-title
      >

      <v-card-actions>
        <v-card-text color="orange" text> 방 설명 </v-card-text>

        <v-spacer></v-spacer>

        <v-btn icon @click="show = !show">
          <v-icon>{{ show ? "mdi-chevron-up" : "mdi-chevron-down" }}</v-icon>
        </v-btn>
      </v-card-actions>

      <v-expand-transition>
        <div v-show="show">
          <v-divider></v-divider>
          <v-card-title>방 설명 / 인증 방법</v-card-title>
          <v-card-text>
            {{ mission.missionContent }}
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
        <v-btn color="deep-purple lighten-2" text @click="reserve">
          시작하기(방장만보이는버튼)
        </v-btn>
      </v-card-actions>
    </v-card>
  </div>
</template>

<script>
import axios from "axios";
export default {
  data: () => ({
    show: false,
    mission: null,
  }),
  props: {
    missionNo: { missionNo: String },
  },
  created() {
    var vm = this;

    console.log("missionNo는 뭘까요?");
    console.log("missionNo는 : " + this.$route.params.missionNo);
    axios
      .get(`api/mission/${this.$route.params.missionNo}`, {
        headers: {
          "Access-Control-Allow-Origin": "*",
          "Content-Type": "application/json",
        },
      })
      .then(function (response) {
        console.log(response);

        console.log("첫번째 데이터가 뭐게요");
        console.log(response.data[0]);
        vm.mission = response.data;
        console.log(vm.mission);
      })
      .catch(function (err) {
        console.log(err);
      });
  },

  methods: {},
};
</script>

<style></style>
