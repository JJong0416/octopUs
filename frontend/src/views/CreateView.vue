<template>
  <div>
    <v-container fluid>
      <v-row align="center">
        <v-col cols="12">
          <v-autocomplete
            v-model="mission.missionType"
            :items="items"
            dense
            chips
            small-chips
            label="테마"
            solo
            hint="미션과 가장 유사한 주제를 골라주세요!"
          ></v-autocomplete>
        </v-col>
      </v-row>
      <v-row align="center">
        <v-col cols="1"></v-col>
        <v-col cols="8" sm="6">
          <v-text-field
            v-model="mission.missionTitle"
            :rules="rules"
            label="미션제목을 정해주세요!"
          ></v-text-field>
        </v-col>
        <v-col cols="3">
          <v-container class="px-0" fluid>
            <v-checkbox
              v-model="mission.missionOpen"
              :label="`공개`"
            ></v-checkbox>
          </v-container>
        </v-col>
        <v-col cols="1"></v-col>
      </v-row>
      <v-row>
        <v-col cols="1"></v-col>
        <v-text-field
          v-model="mission.missionLimitPersonnel"
          label="최대 참가인원을 정해주세요!"
          :rules="personrules"
          type="number"
          min="1"
          hide-details="auto"
          oniput="javascript: this.value= this.value.replace(/[ㄱ-ㅎ|ㅏ-ㅣ|가-힣|a-z]/g,'');"
        ></v-text-field>
        <v-col cols="1"></v-col>
      </v-row>
      <v-row>
        <v-col cols="1"></v-col>
        <v-text-field
          v-model="mission.missionContent"
          :rules="descriprules"
          counter
          maxlength="300"
          hint="미션에 대한 설명, 달성조건, 사람을 모을 멘트까지!"
          label="미션에 대해 소개해주세요!"
        ></v-text-field>
        <v-col cols="1"></v-col>
      </v-row>
      <v-row>
        <v-col cols="1"></v-col>
        <v-text-field
          label="참가 포인트를 숫자로 입력해주세요"
          :rules="pointrules"
          type="number"
          min="1"
          hide-details="auto"
          v-model="mission.missionPoint"
          oniput="javascript: this.value= this.value.replace(/[ㄱ-ㅎ|ㅏ-ㅣ|가-힣|a-z]/g,'');"
        ></v-text-field>
        <v-col cols="1"></v-col>
      </v-row>
      <br />
      <v-row>
        <v-col cols="1"></v-col>
        <v-btn @click="createmission">Create</v-btn>
      </v-row>
    </v-container>
  </div>
</template>

<script>
import axios from "axios";
export default {
  data: () => ({
    mission: {
      missionTitle: null,
      missionType: null,
      missionPoint: null,
      missionLimitPersonnel: null,
      missionContent: null,
      missionOpen: true,
    },
    userInfo: [],
    pointrules: [
      (value) => !!value || "참여 포인트를 입력해주세요",
      (value) => (value && value.length >= 3) || "최소 100이상이어야 합니다.",
    ],
    personrules: [
      (value) => !!value || "숫자로 입력해주세요",
      (value) =>
        (value && value >= 2 && value <= 8) || "2~8사이의 숫자로 입력해주세요",
    ],
    items: ["LIFE", "EXERCISE", "STUDY", "MEETING", "기타"],

    rules: [
      (value) => !!value || "미션의 제목을 입력해주세요",
      (value) =>
        (value && (value || "").length >= 4 && (value || "").length <= 30) ||
        "4 ~ 30자 사이여야합니다.",
    ],
    slider: 8,
    title: "Preliminary report",
    description: "이방은 어쩌구 저쩌구 솰라솰라",
    descriprules: [
      (v) =>
        (v && v.length <= 300 && v.length >= 6) ||
        "6~300자까지 입력가능합니다.",
    ],
  }),
  created() {
    var vm = this;
    axios
      .get(`api/user/info`, {
        headers: {
          "Access-Control-Allow-Origin": "*",
          "Content-Type": "application/json",
        },
      })
      .then(function (response) {
        console.log(response);
        vm.userInfo = response.data;
      })
      .catch(function (err) {
        console.log(err);
      });
  },
  computed: {
    dateRangeText() {
      return this.dates.join(" ~ ");
    },
  },
  methods: {
    createmission() {
      if (
        !this.mission.missionTitle ||
        !this.mission.missionPoint ||
        !this.mission.missionLimitPersonnel ||
        !this.mission.missionContent
      ) {
        console.log(this.mission.missionTitle);
        console.log(this.mission.missionType);
        console.log(this.mission.missionPoint);
        console.log(this.mission.missionLimitPersonnel);
        console.log(this.mission.missionContent);
        console.log(this.mission.missionOpen);
        alert("정보를 다 입력해주세요");
        return;
      }
      axios
        .post(`api/mission`, {
          missionLeaderId: this.userInfo.userId,
          missionTitle: this.mission.missionTitle,
          missionType: this.mission.missionType,
          missionPoint: this.mission.missionPoint,
          missionLimitPersonnel: this.mission.missionLimitPersonnel,
          missionContent: this.mission.missionContent,
          missionOpen: this.mission.missionOpen ? 0 : 1,
        })
        .then(() => {
          alert("미션 성공여부를 위한 인증시간을 정하면 방이 활성화되요!");
          // this.$router.push({name="/before" ,params:missionNo});
        })
        .catch(() => {
          alert("미션생성실패");
          console.log(this.mission.missionTitle);
          console.log(this.mission.missionType);
          console.log(this.mission.missionPoint);
          console.log(this.mission.missionLimitPersonnel);
          console.log(this.mission.missionContent);
          console.log(this.mission.missionOpen);
        });
    },
  },
};
</script>

<style></style>
