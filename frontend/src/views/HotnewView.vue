<template>
  <v-container>
    <br /><br /><br /><br />
    여기는 뜰까?
    <v-simple-table>
      <template v-slot:default>
        <thead>
          <tr>
            <th class="text-left">Name</th>
            <th class="text-left">Calories</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(newmission, index) in hotmissions" :key="index">
            <td>왜 안뜨는거지????</td>
            <td>{{ newmission }}</td>
            <td>{{ newmission.missionTitle }}</td>
          </tr>
        </tbody>
      </template>
    </v-simple-table>
  </v-container>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      hotmissions: [],
      newmissions: [],
    };
  },

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
        vm.hotmissions = response.data;
        console.log(vm.hotmissions);
      })
      .catch(function (err) {
        console.log(err);
      });
  },
};
</script>

<style></style>
