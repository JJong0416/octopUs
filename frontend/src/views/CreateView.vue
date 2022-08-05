<template>
  <div>
    <v-card>
      <v-container fluid>
        <v-row align="center">
          <v-col cols="12">
            <v-autocomplete
              v-model="mission.missionType"
              :items="items"
              dense
              chips
              small-chips
              label="Solo"
              multiple
              solo
            ></v-autocomplete>
          </v-col>
        </v-row>
      </v-container>
    </v-card>
    <v-text-field v-model="mission.missionTitle" :rules="rules"></v-text-field>
    <v-container class="px-0" fluid>
      <v-checkbox
        v-model="mission.missionOpen"
        :label="`공개방 설정 여부: ${mission.missionOpen.toString()}`"
      ></v-checkbox>
    </v-container>
    <v-text-field
      v-model="mission.missionLimitPersonnel"
      label="인원수를 1~8사이의 숫자로 입력해주세요"
      :rules="personrules"
      type="number"
      min="1"
      hide-details="auto"
      oniput="javascript: this.value= this.value.replace(/[ㄱ-ㅎ|ㅏ-ㅣ|가-힣|a-z]/g,'');"
    ></v-text-field>
    <v-form>
      <v-container>
        <v-row>
          <v-col cols="12" sm="6">
            <v-text-field
              v-model="mission.missionContent"
              :rules="descriprules"
              counter
              maxlength="25"
              hint="This field uses maxlength attribute"
              label="Limit exceeded"
            ></v-text-field>
          </v-col>
        </v-row>
      </v-container>
    </v-form>
    <v-text-field
      label="참가하기 위한 Point를 숫자로 입력해주세요"
      :rules="pointrules"
      type="number"
      min="1"
      hide-details="auto"
      v-model="mission.missionPoint"
      oniput="javascript: this.value= this.value.replace(/[ㄱ-ㅎ|ㅏ-ㅣ|가-힣|a-z]/g,'');"
    ></v-text-field>
    <br />
    <!-- <v-row justify="center">
      <v-date-picker v-model="mission.missionOpen"></v-date-picker>
    </v-row> -->
    <v-btn @click="createmission">Create</v-btn>
  </div>
</template>

<script>
import axios from "axios";
export default {
  data: () => ({
    mission: {
      // missionType, missionOpen 수정필요
      missionTitle: null,
      missionType: "LIFE",
      missionPoint: null,
      missionLimitPersonnel: null,
      missionContent: null,
      missionOpen: "OPEN_ROOM",
    },
    pointrules: [
      (value) => !!value || "입력해주세요",
      (value) => (value && value.length >= 3) || "Min 3 characters",
    ],
    personrules: [
      (value) => !!value || "입력해주세요",
      (value) =>
        (value && value >= 1 && value <= 8) || "1~8사이의 숫자로 입력해주세요",
    ],
    items: ["미라클모닝", "공부", "코딩", "운동", "기타"],

    rules: [
      (value) => !!value || "제목을 입력해주세요",
      (value) => (value || "").length <= 20 || "Max 20 characters",
    ],
    slider: 8,
    title: "Preliminary report",
    description: "이방은 어쩌구 저쩌구 솰라솰라",
    descriprules: [(v) => v.length <= 25 || "Max 25 characters"],
  }),
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
          missionTitle: this.mission.missionTitle,
          missionType: this.mission.missionType,
          missionPoint: this.mission.missionPoint,
          missionLimitPersonnel: this.mission.missionLimitPersonnel,
          missionContent: this.mission.missionContent,
          missionOpen: this.mission.missionOpen,
        })
        .then(() => {
          alert("미션생성완료");
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
