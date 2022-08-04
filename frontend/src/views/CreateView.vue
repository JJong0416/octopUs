<template>
  <div>
    <v-card>
      <v-container fluid>
        <v-row align="center">
          <v-col cols="12">
            <v-autocomplete
              v-model="values"
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
    <v-text-field :rules="rules"></v-text-field>
    <v-card flat>
      <v-card-text>
        <v-container fluid>
          <v-row>
            <v-col cols="12" sm="4" md="4">
              <v-checkbox
                label="공개방"
                color="#ff2456"
                hide-details
              ></v-checkbox>
            </v-col>
          </v-row>
        </v-container>
      </v-card-text>
    </v-card>
    <v-text-field
      label="인원수를 1~8사이의 숫자로 입력해주세요"
      :rules="personrules"
      type="number"
      min="1"
      hide-details="auto"
      oniput="javascript: this.value= this.value.replace(/[ㄱ-ㅎ|ㅏ-ㅣ|가-힣|a-z]/g,'');"
      :value="personnum"
    ></v-text-field>
    <v-form>
      <v-container>
        <v-row>
          <v-col cols="12" sm="6">
            <v-text-field
              v-model="description"
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
      oniput="javascript: this.value= this.value.replace(/[ㄱ-ㅎ|ㅏ-ㅣ|가-힣|a-z]/g,'');"
      :value="pointnum"
    ></v-text-field>
    <br />
    <v-btn>Create</v-btn>
  </div>
</template>

<script>
export default {
  data: () => ({
    pointnum: "",
    personnum: "",
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
    values: ["기타"],
    value: null,
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
};
</script>

<style></style>
