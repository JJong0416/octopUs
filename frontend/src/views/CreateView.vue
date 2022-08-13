<template>
  <div>
    <v-container>
      <v-row class="py-3 pl-3">
        <!-- 뒤로 가기 버튼 -->
        <v-btn @click="goback" icon>
          <v-icon> mdi-arrow-left</v-icon>
        </v-btn>
      </v-row>
      <v-row justify="center" align="center">
        <h3>새로운 Mission 생성하기</h3>
      </v-row>
      <v-row class="px-7 pt-7">
        <!-- 주제 -->
        <v-autocomplete
          v-model="mission.missionType"
          :items="items"
          dense
          chips
          small-chips
          label="Mission과 가장 유사한 주제 선택"
          solo
        ></v-autocomplete>
      </v-row>
      <v-row class="px-7 py-0">
        <v-text-field
          v-model="mission.missionTitle"
          :rules="rules"
          label="미션 제목을 입력해주세요 (4 ~ 30자)"
        ></v-text-field>
      </v-row>
      <v-row class="px-3 pt-3">
        <v-col cols="8">
          <p>공개방 여부를 선택해주세요.</p>
          <h5>비공개방은 코드로만 참가 가능합니다.</h5>
        </v-col>
        <v-col>
          <v-switch
            color="#457b9d"
            v-model="mission.missionOpen"
            :label="label[mission.missionOpen == true ? 0 : 1]"
          ></v-switch>
        </v-col>
      </v-row>
      <v-row class="px-4 pt-3">
        <v-col cols="5">
          참여 인원 : {{ mission.missionLimitPersonnel }}명
        </v-col>
        <v-slider
          v-model="mission.missionLimitPersonnel"
          :tick-labels="ticksLabels"
          :max="8"
          :min="2"
          step="1"
          ticks="always"
          tick-size="2"
          color="#457b9d"
        ></v-slider>
      </v-row>
      <v-row class="px-4 pt-3">
        <v-col class="pt-6" cols="6"> 미션 참가 포인트 : </v-col>
        <v-col class="px-0" cols="4">
          <v-text-field
            label="100 이상"
            class="point"
            :rules="pointrules"
            outlined
            type="number"
            min="1"
            hide-details="auto"
            v-model="mission.missionPoint"
            oniput="javascript: this.value= this.value.replace(/[ㄱ-ㅎ|ㅏ-ㅣ|가-힣|a-z]/g,'');"
          ></v-text-field>
        </v-col>
        <v-col class="px-0 pt-6"> Point </v-col>
      </v-row>
      <v-row class="px-4 pt-3">
        <v-textarea
          v-model="mission.missionContent"
          :rules="descriprules"
          maxlength="300"
          outlined
          label="미션 설명, 달성 조건 등을 기재해 주세요."
          height="120"
        ></v-textarea>
      </v-row>
      <v-row class="pr-4" justify="end">
        <v-btn outlined @click="createmission" color="#fa183e">
          생성하기
        </v-btn>
      </v-row>
    </v-container>
  </div>
</template>

<script>
import axios from "axios";
export default {
  data: () => ({
    ticksLabels: [2, 3, 4, 5, 6, 7, 8],
    label: ["공개", "비공개"],
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
      (value) => !!value || "필수 입력 값",
      (value) => (value && value.length >= 3) || "",
    ],
    items: ["LIFE", "EXERCISE", "STUDY", "MEETING", "ETC"],

    rules: [
      (value) => !!value || "필수 입력 값",
      (value) =>
        (value && (value || "").length >= 4 && (value || "").length <= 30) ||
        "",
    ],
    slider: 8,
    title: "Preliminary report",
    description: "해당 미션 설명",
    descriprules: [
      (v) =>
        (v && v.length <= 300 && v.length >= 6) ||
        "6 ~ 300자까지 입력 가능합니다.",
    ],
  }),
  created() {
    var vm = this;
    axios.defaults.headers.common[
      "Authorization"
    ] = `Bearer ${sessionStorage.getItem("token")}`;
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
    goback() {
      this.$router.go(-1);
    },
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
        alert("필요한 정보를 전부 입력해주세요.");
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
        .then((response) => {
          alert(
            "미션 성공 인증을 위한 시간을 설정하면 해당 미션이 활성화됩니다."
          );
          this.$router.push({
            name: "before",
            params: { missionNo: response.data },
          });
        })
        .catch(() => {
          alert("미션 생성에 실패하였습니다.");
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
<style>
.point {
  height: 10%;
  width: 100%;
}
</style>
