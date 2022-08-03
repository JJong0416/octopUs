<template>
  <div>
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
  </div>
</template>

<script>
import axios from "axios";
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
      searchkeyword: "", // 검색키워드
      searchfinish: false, // 검색완료시 true로 바뀌고, 이때부터 표 생성
      searchoption: ["코드입력", "작성자"], // 검색옵션
      searchoptionselected: "코드입력", // 검색옵션값 받아오기, 기본값은 제목으로 지정
      searchcnt: 0, // 검색된 게시글 갯수
      contentlist: [], // 게시글 리스트
      dialog: false,
      show: false,
    };
  },
  methods: {
    // method 속성 추가 코드
    movetocontent(boardnum, id) {
      // 검색된 게시글 클릭시 해당 게시글로 이동
      window.location.href =
        "http://127.0.0.1:8080/board/" + boardnum + "/content?id=" + id;
    },
    searchstart() {
      // 검색버튼 눌렀을때 실행
      if (this.searchkeyword == "") {
        alert("키워드가 없습니다!");
      } else {
        axios({
          url: "http://127.0.0.1:52273/content/search/",
          method: "POST",
          data: {
            // 선택된 검색옵션과 검색키워드 넘겨줌
            searchoption: this.searchoptionselected,
            searchkeyword: this.searchkeyword,
          },
        })
          .then((res) => {
            this.contentlist = res.data;
            this.searchcnt =
              this.contentlist[Object.keys(this.contentlist).length - 1].cnt;
            this.contentlist.pop();
            alert("검색완료!");
            this.searchfinish = true;
            this.searchkeyword = "";
          })
          .catch((err) => {
            alert(err);
          });
      }
    },
  },
};
</script>

<style></style>
