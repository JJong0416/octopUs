<template>
  <div>
    <header-view></header-view>
    <v-container>
      <v-row justify="center">
        <v-img src="../assets/Logo.png" max-width="25%"></v-img>
      </v-row>
      <!-- 검색창 -->
      <v-row>
        <v-col class="py-0" cols="4">
          <v-select :items="items" v-model="theme" label="검색 선택"></v-select>
        </v-col>
        <v-col class="py-0" cols="8" v-if="theme != `테마`">
          <v-text-field
            v-model="tofindsearch"
            @keyup.enter="transmit"
            hide-details
            single-line
          ></v-text-field>
        </v-col>
        <v-col class="py-0" cols="8" v-if="theme == `테마`">
          <v-autocomplete
            v-model="selectedtheme"
            :items="themes"
            dense
            chips
            label="선택된 테마"
            @change="transmit"
          ></v-autocomplete>
        </v-col>
      </v-row>

      <!-- 검색 성공시 보여질 페이지 -->
      <v-row v-if="searchres">
        <v-card
          class="mx-auto"
          max-width="344"
          outlined
          v-for="(item, index) in searchres"
          v-bind:key="index"
        >
          <v-list-item three-line>
            <v-list-item-content>
              <div class="text-overline mb-4">
                참가 포인트 {{ item.missionPoint }}
              </div>
              <v-list-item-title class="text-h5 mb-1">
                {{ item.missionTitle }}
              </v-list-item-title>
              <v-list-item-subtitle>{{
                item.missionContent
              }}</v-list-item-subtitle>
            </v-list-item-content>

            <v-list-item-avatar tile size="80">
              <v-img
                :src="require(`../assets/img/Theme/${item.missionType}.jpg`)"
              >
              </v-img
            ></v-list-item-avatar>
          </v-list-item>

          <v-card-actions>
            <v-btn
              outlined
              rounded
              text
              @click="beforeJoinCheck(item.missionNo)"
            >
              입장하기
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-row>
      <!-- all missions -->
      <v-row v-if="!searchres">
        <v-card
          class="mx-auto"
          max-width="344"
          outlined
          v-for="(item, index) in allmissions"
          v-bind:key="index"
        >
          <v-list-item three-line>
            <v-list-item-content>
              <div class="text-overline mb-4">
                참가 포인트 {{ item.missionPoint }}
              </div>
              <v-list-item-title class="text-h5 mb-1">
                {{ item.missionTitle }}
              </v-list-item-title>
              <v-list-item-subtitle>{{
                item.missionContent
              }}</v-list-item-subtitle>
            </v-list-item-content>

            <v-list-item-avatar tile size="80">
              <v-img
                :src="require(`../assets/img/Theme/${alltypes[index]}.jpg`)"
              >
              </v-img
            ></v-list-item-avatar>
          </v-list-item>

          <v-card-actions>
            <v-btn outlined rounded text @click="mvdetail(item.missionNo)">
              입장하기
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-row>
    </v-container>
    <footer-view></footer-view>
  </div>
</template>

<script>
import HeaderView from "../components/common/HeaderView.vue";
import FooterView from "../components/common/FooterView.vue";
import axios from "axios";
export default {
  components: {
    HeaderView,
    FooterView,
  },
  // data 속성 전체 코드
  data() {
    return {
      theme: "",
      tofindsearch: "",
      selectedtheme: "",
      userId: "",
      searchtype: "",
      items: ["코드", "제목", "테마"],
      themes: ["생활", "운동", "공부", "모임", "기타"],
      allmissions: [],
      alltypes: [],
      searchtypes: [],
      searchres: null,
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
  created() {
    var vm = this;
    axios.defaults.headers.common[
      "Authorization"
    ] = `Bearer ${sessionStorage.getItem("token")}`;
    axios
      .get(`api/mission/all`, {
        headers: {
          "Access-Control-Allow-Origin": "*",
          "Content-Type": "application/json",
        },
      })
      .then(function (response) {
        vm.allmissions = response.data;
        for (let index = 0; index < vm.allmissions.length; index++) {
          var allmission = vm.allmissions[index];
          vm.alltypes[index] = allmission.missionType;
        }
      })
      .catch(function (error) {
        console.log(error);
      });
    axios
      .get(`api/user/info`, {
        headers: {
          "Access-Control-Allow-Origin": "*",
          "Content-Type": "application/json",
        },
      })
      .then(function (response) {
        vm.userId = response.data.userId;
      })
      .catch(function (err) {
        console.log(err);
      });
  },
  computed: {},
  methods: {
    transmit() {
      if (this.theme === "코드") {
        this.searchtype = "code";
      } else if (this.theme === "제목") {
        this.searchtype = "title";
      } else if (this.theme === "테마") {
        this.searchtype = "type";
      } else {
        this.$router.push("/search");
        return;
      }
      this.serachMission(this.searchtype);
    },
    changeToType(theme) {
      var missionType;
      if (theme === "생활") {
        missionType = "LIFE";
      } else if (theme === "운동") {
        missionType = "EXERCISE";
      } else if (theme === "공부") {
        missionType = "STUDY";
      } else if (theme === "모임") {
        missionType = "MEETING";
      } else if (theme === "기타") {
        missionType = "ETC";
      } else {
        this.$router.push("/search");
        return;
      }
      return missionType;
    },
    serachMission(search) {
      var vm = this;
      var find = this.tofindsearch;
      if (search === "type") find = this.changeToType(vm.selectedtheme);
      axios
        .get(`api/mission/search/${search}/${find}`, {
          headers: {
            "Access-Control-Allow-Origin": "*",
            "Content-Type": "application/json",
          },
        })
        .then(function (response) {
          vm.searchres = response.data;
          for (let index = 0; index < vm.searchres.length; index++) {
            var searchedmission = vm.searchres[index];
            vm.searchtypes[index] = searchedmission.missionType;
          }
        })
        .catch(function (err) {
          console.log(err);
        });
    },
    mvdetail(missionNo) {
      this.$router.push({
        name: "before",
        params: { missionNo: missionNo },
      });
    },
  },
};
</script>

<style></style>
