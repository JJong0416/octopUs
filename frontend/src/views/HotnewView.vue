<template>
  <div class="wrapper">
    <header-view></header-view>
    <v-card>
      <v-tabs v-model="tab">
        <v-tabs-slider></v-tabs-slider>
        <v-tab href="#tab-1">
          Hot
        </v-tab>
        <v-tab href="#tab-2">
          New
        </v-tab>
      </v-tabs>
      <v-tabs-items v-model="tab">
        <v-tab-item v-for="i in 2" :key="i" :value="'tab-'+ i">
          <v-container>
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
                            >{{ newmission.missionContent }}
                          </v-list-item-subtitle>
                        </v-list-item-content>
                  <v-list-item-avatar tile size="80">
                    <v-img
                      :src="
                        require(`../assets/img/Ocsoon/Pet/${newavatars[i][3]}.png`)
                      "
                    >
                      <v-img
                        :src="
                          require(`../assets/img/Ocsoon/Character/${newavatars[i][0]}.png`)
                        "
                      >
                        <v-img
                          :src="
                            require(`../assets/img/Ocsoon/Face/${newavatars[i][1]}.png`)
                          "
                        >
                          <v-img
                            :src="
                              require(`../assets/img/Ocsoon/Hat/${newavatars[i][2]}.png`)
                            "
                          ></v-img
                        ></v-img>
                      </v-img> </v-img
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
          </v-container>
        </v-tab-item>
      </v-tabs-items>
    </v-card>
    <footer-view></footer-view>
  </div>
</template>

<script>
import axios from "axios";
import HeaderView from "../components/common/HeaderView.vue";
import FooterView from "../components/common/FooterView.vue";
export default {
  components: {
    HeaderView,
    FooterView,
  },
  data() {
    return {
      tab: null,
      hotmissions: [],
      newmissions: [],
      hotavatars: [],
      newavatars: [],
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
    axios.defaults.headers.common[
      "Authorization"
    ] = `Bearer ${sessionStorage.getItem("token")}`;
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
        for (let index = 0; index < vm.newmissions.length; index++) {
          const newmission = vm.newmissions[index];
          var list = newmission.missionLeaderAvatar.split(", ");
          var list2 = [];
          list2[0] = parseInt(list[0]);
          list2[1] = parseInt(list[1]);
          list2[2] = parseInt(list[2]);
          list2[3] = parseInt(list[3]);

          vm.newavatars[index] = list2;
        }
        console.log(vm.newmissions);
        console.log(vm.newavatars);
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
        for (let index = 0; index < vm.hotmissions.length; index++) {
          const hotmission = vm.hotmissions[index];
          var list = hotmission.missionLeaderAvatar.split(", ");
          var list2 = [];
          list2[0] = parseInt(list[0]);
          list2[1] = parseInt(list[1]);
          list2[2] = parseInt(list[2]);
          list2[3] = parseInt(list[3]);

          vm.hotavatars[index] = list2;
        }
        console.log(vm.hotmissions);
      })
      .catch(function (err) {
        console.log(err);
      });
  },
};
</script>

<style>
.wrapper{
  height: 100%;
}
</style>
