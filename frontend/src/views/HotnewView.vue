<template>
  <div>
    <br /><br />
    <v-card
      v-for="item in newmissions"
      v-bind:key="item.missionNo"
      class="mx-auto"
      max-width="344"
      outlined
    >
      <v-list-item three-line>
        <v-list-item-content>
          <div class="text-overline mb-4">{{ item.missionNo }}</div>
          <v-list-item-title class="text-h5 mb-1">
            {{ item.missionTitle }}
          </v-list-item-title>
        </v-list-item-content>
        <v-list-item-avatar tile size="80" color="grey"></v-list-item-avatar>
      </v-list-item>

      <v-card-actions>
        <v-btn outlined rounded text>
          <router-link
            :to="{ name: 'before', params: { missionNo: item.missionNo } }"
          >
            자세히보기
          </router-link>
        </v-btn>
      </v-card-actions>
    </v-card>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      hotmissions: [],
      newmissions: [],
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
  methods: {},

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

        console.log("첫번째 데이터가 뭐게요");
        console.log(response.data[0]);
        vm.newmissions = response.data;
        console.log(vm.newmissions);
      })
      .catch(function (err) {
        console.log(err);
      });
  },
};
</script>

<style></style>
