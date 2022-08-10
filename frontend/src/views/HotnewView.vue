<template>
  <div>
    <br />
    NewMissions!
    <br />
    <v-row>
      <v-col cols="1"></v-col>
      <v-col cols="10">
        <v-carousel
          cycle
          height="180"
          hide-delimiter-background
          show-arrows-on-hover
          hide-delimiters
        >
          <v-carousel-item v-for="(newmission, i) in newmissions" :key="i">
            <v-card class="mx-auto" max-width="344" outlined>
              <v-list-item three-line>
                <v-list-item-content>
                  <div class="text-overline mb-4">
                    {{ newmission.missionNo }}
                  </div>
                  <v-list-item-title class="text-h5 mb-1">
                    {{ newmission.missionTitle }}
                  </v-list-item-title>
                  <v-list-item-subtitle
                    >{{
                      newmission.missionContent
                    }}
                    missionContent</v-list-item-subtitle
                  >
                </v-list-item-content>

                <v-list-item-avatar
                  tile
                  size="80"
                  color="grey"
                ></v-list-item-avatar>
              </v-list-item>

              <v-card-actions>
                <v-btn
                  outlined
                  rounded
                  text
                  color="secondary"
                  @click="mvdetail(newmission)"
                >
                  자세히보기
                </v-btn>
              </v-card-actions>
            </v-card>
          </v-carousel-item>
        </v-carousel>
      </v-col>
      <v-col cols="1"></v-col>
    </v-row>

    <br />
    hotmission!
    <br />
    <v-row>
      <v-col cols="1"></v-col>
      <v-col cols="10">
        <v-carousel
          cycle
          height="180"
          hide-delimiter-background
          show-arrows-on-hover
          hide-delimiters
        >
          <v-carousel-item v-for="(hotmission, i) in hotmissions" :key="i">
            <v-card class="mx-auto" max-width="344" outlined>
              <v-list-item three-line>
                <v-list-item-content>
                  <div class="text-overline mb-4">
                    {{ hotmission.missionNo }}
                  </div>
                  <v-list-item-title class="text-h5 mb-1">
                    {{ hotmission.missionTitle }}
                  </v-list-item-title>
                  <v-list-item-subtitle
                    >{{
                      hotmission.missionContent
                    }}
                    missionContent</v-list-item-subtitle
                  >
                </v-list-item-content>

                <v-list-item-avatar
                  tile
                  size="80"
                  color="grey"
                ></v-list-item-avatar>
              </v-list-item>

              <v-card-actions>
                <v-btn
                  outlined
                  rounded
                  text
                  color="secondary"
                  @click="mvdetail(hotmission)"
                >
                  자세히보기
                </v-btn>
              </v-card-actions>
            </v-card>
          </v-carousel-item>
        </v-carousel>
      </v-col>
      <v-col cols="1"></v-col>
    </v-row>
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
  methods: {
    mvdetail(mission) {
      this.$router.push({
        name: "before",
        params: { missionNo: mission.missionNo },
      });
    },
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

        console.log(response.data[0]);
        vm.newmissions = response.data;
        console.log(vm.newmissions);
      })
      .catch(function (err) {
        console.log(err);
      });
    axios
      .get(`api/mission/hot`, {
        headers: {
          "Access-Control-Allow-Origin": "*",
          "Content-Type": "application/json",
        },
      })
      .then(function (response) {
        console.log(response);

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
