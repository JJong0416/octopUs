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
    <v-card>
      <v-card-text>
        <v-slider v-model="slider" step="8"></v-slider>
      </v-card-text>
    </v-card>
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
  </div>
</template>

<script>
export default {
  data: () => ({
    items: ["foo", "bar", "fizz", "buzz"],
    values: ["foo", "bar"],
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
};
</script>

<style></style>
