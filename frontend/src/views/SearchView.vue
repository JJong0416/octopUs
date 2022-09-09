<template>
  <div>
<<<<<<< HEAD
    <v-container>
      <v-row>
        <v-col class="logo-img-wrapper">
          <v-img src="../assets/Logo.png" max-width="30%"></v-img>
        </v-col>
      </v-row>
      <!-- 검색창 -->
      <v-row>
        <v-col cols="4">
          <v-select :items="items" label="검색 선택"></v-select>
        </v-col>
        <v-col cols="5">
          <v-text-field hide-details single-line></v-text-field>
        </v-col>
        <v-col cols="">
          <v-card-text>
            <v-btn icon>
              <v-icon> mdi-magnify </v-icon>
            </v-btn>
          </v-card-text>
        </v-col>
      </v-row>
      <!-- 여기서부터는 게시판 페이지와 거의 일치, 검색완료시에만 표가 나타나게 했고, 게시판 번호 표시 -->
      <v-row v-if="searchfinish === true">
        <!-- 검색 성공시 보여질 페이지 -->
      </v-row>
      <!-- 모달 창 -->
      <div class="text-center">
        <v-dialog v-model="dialog" width="500">
          <template v-slot:activator="{ on, attrs }">
            <v-btn color="red lighten-2" dark v-bind="attrs" v-on="on">
              방 상세보기
            </v-btn>
          </template>

          <v-card>
            <v-card-title class="text-h5 grey lighten-2">
              Theme <v-spacer></v-spacer> MissionTitle
            </v-card-title>

            <v-card-text>
              <div class="my-4 text-subtitle-1">룸 코드</div>
            </v-card-text>
            <v-card-text>
              <div class="my-4 text-subtitle-1">참가자 명단</div>
            </v-card-text>

            <v-card-title> 참여 시 필요한 포인트 5000P</v-card-title>

            <v-card-actions>
              <v-card-text color="orange" text> 방 설명 </v-card-text>

              <v-spacer></v-spacer>

              <v-btn icon @click="show = !show">
                <v-icon>{{
                  show ? "mdi-chevron-up" : "mdi-chevron-down"
                }}</v-icon>
              </v-btn>
            </v-card-actions>

            <v-expand-transition>
              <div v-show="show">
                <v-divider></v-divider>
                <v-card-title>방 설명 / 인증 방법</v-card-title>
                <v-card-text>
                  매일 아침 7시 기상 인증 미라클 모닝 방입니다
                  <br />
                  흐르는 물에 손을 씻는 장면을 찍어 인증합니다!
                </v-card-text>
                <v-card-title>인증 요일 / 시간</v-card-title>
                <v-card-text>
                  일주일에 5 번, 오전 6시 ~ 오전 7시 에 인증합니다.
                </v-card-text>
              </div>
            </v-expand-transition>
            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn color="primary" text @click="dialog = false">
                참가하기
              </v-btn>
            </v-card-actions>
          </v-card>
        </v-dialog>
      </div>
      <v-carousel
        cycle
        interval="1500"
        height="100"
        hide-delimiters
        hide-delimiter-background
        show-arrows-on-hover
      >
        <v-carousel-item v-for="(slide, i) in slides" :key="i">
          <v-sheet :color="colors[i]" height="100%">
            <v-row class="fill-height" align="center" justify="center">
              <div class="text-h2">{{ slide }}</div>
            </v-row>
          </v-sheet>
        </v-carousel-item>
      </v-carousel>
      <v-carousel
        cycle
        interval="1500"
        height="100"
        hide-delimiters
        hide-delimiter-background
        show-arrows-on-hover
      >
        <v-carousel-item v-for="(slide, i) in slides" :key="i">
          <v-sheet :color="colors[i]" height="100%">
            <v-row class="fill-height" align="center" justify="center">
              <div class="text-h2">{{ slide }}</div>
            </v-row>
          </v-sheet>
        </v-carousel-item>
      </v-carousel>
    </v-container>
=======
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
>>>>>>> ac048782d308840093efdf3b8146c990ec2fcf95
  </div>
</template>

<script>
<<<<<<< HEAD
export default {
  // data 속성 전체 코드
  data() {
    return {
      colors: [
        "indigo",
        "warning",
        "pink darken-2",
        "red lighten-1",
        "deep-purple accent-4",
      ],
      slides: ["hot", "new", "mission", "is waiting for", "you"],
      items: ["코드 입력", "제목 검색", "테마 검색"],
      missionList: null,
    };
  },
  computed: {},
  methods: {},
=======
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
>>>>>>> ac048782d308840093efdf3b8146c990ec2fcf95
};
</script>

<style></style>
