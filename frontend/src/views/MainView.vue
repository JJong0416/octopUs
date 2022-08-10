<template>
  <div>
    <v-container class="mainwrap">
      <v-row>
        <v-col class="logo-img-wrapper">
          <v-img src="../assets/img/Ocsoon/Pet/Pegion.png">
          <v-img src="../assets/img/Ocsoon/Character/1.png">
          <v-img src="../assets/img/Ocsoon/Face/Cartoon.png">
          <v-img src="../assets/img/Ocsoon/Hat/Bid.png"></v-img></v-img>
          </v-img></v-img>
        </v-col>
      </v-row>
      <!-- 검색창 -->
      <v-row>
        <v-col cols="4">
          <v-select :items="items" label="검색 선택"></v-select>
        </v-col>
        <v-col cols="8">
          <v-text-field hide-details single-line></v-text-field>
        </v-col>
      </v-row>
<br><br>
      <!-- Hot 실시간 반영 -->
      <v-row>
        <v-col cols="3"><v-img src="../assets/img/Hot.png"></v-img></v-col>
        <v-col cols="6">
          <v-carousel
            cycle
            interval="1500"
            height="80"
            hide-delimiter-background
            hide-delimiters
            show-arrows-on-hover
          >
            <v-carousel-item v-for="(slide, i) in slides" :key="i">
              <v-sheet height="100%">
                <v-row class="fill-height" align="center" justify="center">
                  <div class="text-h4">{{ slide }}</div>
                </v-row>
              </v-sheet>
            </v-carousel-item>
          </v-carousel>
        </v-col>

        <!-- Hot 더보기 -->
        <v-col cols="3"> <v-btn color="red lighten-1" @click="moveHot()">more</v-btn></v-col>
      </v-row>
      <!-- New 실시간 반영 -->
      <v-row>
        <v-col cols="3"><v-img src="../assets/img/New.png"></v-img></v-col>
        <v-col cols="6">
          <v-carousel
            cycle
            interval="1500"
            height="80"
            hide-delimiter-background
            hide-delimiters
            show-arrows-on-hover
          >
            <v-carousel-item v-for="(slide, i) in slides" :key="i">
              <v-sheet height="100%">
                <v-row class="fill-height" align="center" justify="center">
                  <div class="text-h4">{{ slide }}</div>
                </v-row>
              </v-sheet>
            </v-carousel-item>
          </v-carousel></v-col
        >
        <!-- New 더보기 -->
        <v-col cols="3"> <v-btn color="warning" @click="moveNew()">more</v-btn></v-col>
      </v-row>
    </v-container>
  </div>
</template>

<script>
import { mapState } from "vuex";
const memberStore = "memberStore";

export default {
  data: () => ({
    items: ["코드 입력", "제목 검색", "테마 검색"],
    slides: ["First", "Second", "Third", "Fourth", "Fifth"],
  }),
  computed: {
    ...mapState(memberStore, ["isLogin", "userInfo"]),
  },

  methods:{
    moveHot(){
      this.$router.push('/hotnew')
    },
    moveNew(){
      this.$router.push('/hotnew')
    }
  }
};
</script>

<style>

.signup a {
  text-decoration: none;
  color: black;
}
.signup a:hover {
  text-decoration: none;
  color: black;
}
body {
  background-color: #ebebeb;
}
.more a {
  text-decoration: none;
  color: black;
}
.more a:hover {
  text-decoration: none;
  color: black;
}
.logo-img-wrapper {
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 0;
}
.mainwrap {
  height: 90vh;
}
</style>
