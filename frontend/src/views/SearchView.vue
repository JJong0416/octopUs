<template>
  <div>
    <v-simple-table>
      <tr>
        <th>
          <v-select
            :items="searchoption"
            v-model="searchoptionselected"
            :style="{ width: '100px' }"
          />
        </th>
        <th>
          <v-text-field
            v-model="searchkeyword"
            dense
            outlined
            label="검색키워드"
            :style="{ width: '200px', marginTop: '20px' }"
          />
        </th>
        <th>
          <v-btn
            @click="searchstart"
            :style="{ width: '5px', marginBottom: '7px', marginRight: '5px' }"
            ><v-icon>mdi-magnify</v-icon></v-btn
          >
        </th>
      </tr>
    </v-simple-table>
    <!-- 여기서부터는 게시판 페이지와 거의 일치, 검색완료시에만 표가 나타나게 했고, 게시판 번호 표시 -->
    <v-row v-if="searchfinish === true">
      <!-- 검색 성공시 보여질 페이지 -->
    </v-row>
  </div>
</template>

<script>
import axios from "axios";
export default {
  // data 속성 전체 코드
  data() {
    return {
      searchkeyword: "", // 검색키워드
      searchfinish: false, // 검색완료시 true로 바뀌고, 이때부터 표 생성
      searchoption: ["코드입력", "작성자"], // 검색옵션
      searchoptionselected: "코드입력", // 검색옵션값 받아오기, 기본값은 제목으로 지정
      searchcnt: 0, // 검색된 게시글 갯수
      contentlist: [], // 게시글 리스트
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
