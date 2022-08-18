<template>
  <div class="wrapper">
    <header-view></header-view>
    <v-card>
      <br />
      <v-bottom-navigation
        style="box-shadow: none !important"
        width="100%"
        height="100%"
        color="#ffadad"
        grow
      >
        <v-btn
          text
          @click="clickevent('Hot')"
          style="display: block; height: 100%"
        >
          <span>HOT</span>
          <v-img
            :src="require(`../assets/img/Hot.png`)"
            style="height: auto"
          ></v-img>
        </v-btn>
        <v-btn
          text
          @click="clickevent('New')"
          style="display: block; height: 100%"
        >
          <span>NEW</span>
          <v-img
            :src="require(`../assets/img/New.png`)"
            style="height: auto"
          ></v-img>
        </v-btn>
      </v-bottom-navigation>
      <br />
      <div v-if="hotnewSelected == 'Hot'">
        <div
          class="card"
          v-for="(hotmission, index) in hotmissions"
          :key="index"
        >
          <div v-if="`${hotmissions.length}` == 0">
            <v-card-text
              style="
                display: flex;
                justify-content: center;
                align-items: center;
              "
            >
              현재 모집 중인 미션이 없습니다.
            </v-card-text>
          </div>

          <div v-else>
            <v-card class="px-3" height="120" outlined>
              <v-row>
                <v-col cols="3">
                  <v-list-item-avatar tile width="100" height="100">
                    <v-img
                      :src="
                        require(`../assets/img/Ocsoon/Pet/${hotavatars[index][3]}.png`)
                      "
                    >
                      <v-img
                        :src="
                          require(`../assets/img/Ocsoon/Character/${hotavatars[index][0]}.png`)
                        "
                      >
                        <v-img
                          :src="
                            require(`../assets/img/Ocsoon/Face/${hotavatars[index][1]}.png`)
                          "
                        >
                          <v-img
                            :src="
                              require(`../assets/img/Ocsoon/Hat/${hotavatars[index][2]}.png`)
                            "
                          ></v-img
                        ></v-img>
                      </v-img> </v-img
                  ></v-list-item-avatar>
                </v-col>
                <v-col class="pt-6" cols="6">
                  <v-list-item three-line>
                    <v-list-item-content>
                      <v-list-item-title class="text-h6">
                        {{ hotmission.missionTitle }}
                      </v-list-item-title>
                      <v-list-item-subtitle
                        >{{ hotmission.missionContent }}
                      </v-list-item-subtitle>
                    </v-list-item-content>
                  </v-list-item>
                </v-col>
                <v-col class="pt-8" cols="3">
                  <v-card-actions>
                    <v-btn
                      outlined
                      color="#fa183e"
                      @click="mvdetail(hotmission)"
                    >
                      자세히
                    </v-btn>
                  </v-card-actions>
                </v-col>
              </v-row>
            </v-card>
          </div>
        </div>
      </div>

      <div v-if="hotnewSelected == 'New'">
        <div
          class="card"
          v-for="(newmission, index) in newmissions"
          :key="index"
        >
          <div v-if="`${newmissions.length}` == 0">
            <v-card-text
              style="
                display: flex;
                justify-content: center;
                align-items: center;
              "
            >
              현재 모집 중인 미션이 없습니다.
            </v-card-text>
          </div>
          <div v-else>
            <v-card class="px-3" height="120" outlined>
              <v-row>
                <v-col cols="3">
                  <v-list-item-avatar tile width="100" height="100">
                    <v-img
                      :src="
                        require(`../assets/img/Ocsoon/Pet/${newavatars[index][3]}.png`)
                      "
                    >
                      <v-img
                        :src="
                          require(`../assets/img/Ocsoon/Character/${newavatars[index][0]}.png`)
                        "
                      >
                        <v-img
                          :src="
                            require(`../assets/img/Ocsoon/Face/${newavatars[index][1]}.png`)
                          "
                        >
                          <v-img
                            :src="
                              require(`../assets/img/Ocsoon/Hat/${newavatars[index][2]}.png`)
                            "
                          ></v-img
                        ></v-img>
                      </v-img> </v-img
                  ></v-list-item-avatar>
                </v-col>
                <v-col class="pt-6" cols="6">
                  <v-list-item three-line>
                    <v-list-item-content>
                      <v-list-item-title class="text-h6">
                        {{ newmission.missionTitle }}
                      </v-list-item-title>
                      <v-list-item-subtitle
                        >{{ newmission.missionContent }}
                      </v-list-item-subtitle>
                    </v-list-item-content>
                  </v-list-item>
                </v-col>
                <v-col class="pt-8" cols="3">
                  <v-card-actions>
                    <v-btn
                      outlined
                      color="#fa183e"
                      @click="mvdetail(newmission)"
                    >
                      자세히
                    </v-btn>
                  </v-card-actions>
                </v-col>
              </v-row>
            </v-card>
          </div>
        </div>
      </div>
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
      hotnewSelected: "Hot",
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
    clickevent(value) {
      this.hotnewSelected = value;
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
        vm.newmissions = response.data;
        for (let index = 0; index < vm.newmissions.length; index++) {
          const newmission = vm.newmissions[index];
          var list = newmission.missionLeaderAvatar.split(", ");
          var list2 = [];
          list2[0] = parseInt(list[0]);
          list2[1] = parseInt(list[1]);
          list2[2] = parseInt(list[2]);
          list2[3] = parseInt(list[3]);
          if (list2[2] == 0) {
            list2[2] = "0_nothing";
          }
          if (list2[3] == 0) {
            list2[3] = "0_nothing";
          }
          vm.newavatars[index] = list2;
        }
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
        vm.hotmissions = response.data;
        for (let index = 0; index < vm.hotmissions.length; index++) {
          const hotmission = vm.hotmissions[index];
          var list = hotmission.missionLeaderAvatar.split(", ");
          var list2 = [];
          list2[0] = parseInt(list[0]);
          list2[1] = parseInt(list[1]);
          list2[2] = parseInt(list[2]);
          list2[3] = parseInt(list[3]);
          if (list2[2] == 0) {
            list2[2] = "0_nothing";
          }
          if (list2[3] == 0) {
            list2[3] = "0_nothing";
          }
          vm.hotavatars[index] = list2;
        }
      })
      .catch(function (err) {
        console.log(err);
      });
  },
};
</script>

<style>
.wrapper {
  height: 100%;
}
</style>
