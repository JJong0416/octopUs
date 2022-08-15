<template>
  <div>
    <v-data-table
      :search="search"
      :headers="Missionheaders"
      :items="missions"
      :items-per-page="5"
      class="elevation-1"
      :single-expand="singleExpand"
      :expanded.sync="openexpanded"
      item-key="missionCode"
      show-expand
      multi-sort
      @item-expanded="clickExpand"
    >
      <!-- 표 상단의 해더 문구 -->
      <template v-slot:top>
        <v-toolbar flat>
          <v-toolbar-title
            >Missions
            <v-spacer></v-spacer>
            <v-text-field
              v-model="search"
              append-icon="mdi-magnify"
              label="검색"
              single-line
              hide-details
            ></v-text-field>
          </v-toolbar-title>
        </v-toolbar>
      </template>

      <template v-slot:[`item.missionPoint`]="{ item }">
        <v-chip :color="getColor(item.missionPoint)" dark>
          {{ item.missionPoint }}
        </v-chip>
      </template>
      <template v-slot:expanded-item="{ headers, item }">
        <td :colspan="headers.length">
          <!-- 내부 내용 -->
          <template>
            <v-list-item>
              <v-list-item-content>
                <v-list-item-title>미션 내용</v-list-item-title>
                <v-list-item-subtitle>{{
                  item.missionContent
                }}</v-list-item-subtitle>
              </v-list-item-content>
            </v-list-item>

            <v-list-item two-line>
              <v-list-item-content>
                <v-list-item-title>입장 코드</v-list-item-title>
                <v-list-item-subtitle>{{
                  item.missionCode
                }}</v-list-item-subtitle>
              </v-list-item-content>
            </v-list-item>

            <v-list-item two-line>
              <v-list-item-content>
                <v-list-item-title>분야</v-list-item-title>
                <v-list-item-subtitle>
                  {{ item.missionType }}</v-list-item-subtitle
                >
              </v-list-item-content>
            </v-list-item>

            <v-list-item
              two-line
              v-if="
                item.missionStatus === `ONGOING` ||
                item.missionStatus === `CLOSE`
              "
            >
              <v-list-item-content>
                <v-list-item-title>인증 사진</v-list-item-title>
                <img :src="URL" style="height: 120px" class="mx-4" />
              </v-list-item-content>
            </v-list-item>
          </template>

          <!-- 내부 내용 끝 -->
          <router-link
            v-if="item.missionStatus === `UNACTIVATED`"
            :to="{
              name: 'unactivated',
              params: { missionNo: item.missionNo },
            }"
          >
            미션 활성화하기
          </router-link>
          <router-link
            v-if="item.missionStatus === `OPEN`"
            :to="{
              name: 'before',
              params: { missionNo: item.missionNo },
            }"
          >
            열린 미션 입장하기
          </router-link>
          <!-- 열린미션, 진행중미션 이동위치 확인 -->
          <router-link
            v-if="item.missionStatus === `ONGOING`"
            :to="{
              name: 'proceeding',
              params: { missionNo: item.missionNo },
            }"
          >
            진행중인 미션 입장하기
          </router-link>
          <router-link
            v-if="item.missionStatus === `CLOSE`"
            :to="{
              name: 'proceeding',
              params: { missionNo: item.missionNo },
            }"
          >
            끝난방 들어가기
          </router-link>
        </td>
      </template>
    </v-data-table>
  </div>
</template>

<script>
import axios from "axios";
export default {
  props: ["missions"],

  data() {
    return {
      search: "",
      openexpanded: [],
      closeexpanded: [],
      singleExpand: true,
      Missionheaders: [
        {
          text: `방 이름`,
          align: `start`,
          softable: false,
          value: `missionTitle`,
        },
        { text: `미션상태`, value: `missionStatus` },
        { text: `포인트`, value: `missionPoint` },
        { text: ``, value: `data-table-expand` },
      ],
      URL: null,
      userPictures: [],
    };
  },
  methods: {
    clickExpand({ item }) {
      var vm = this;
      console.log(item);
      axios
        .get(`api/mission/${item.missionNo}/picture`, {
          headers: {
            "Access-Control-Allow-Origin": "*",
            "Content-Type": "application/json",
          },
        })
        .then(function (response) {
          console.log(response);
          // 내부에 받은 이미지 확인필요
          console.log(response.data[0].userPictures[0].pictureUrl);
          console.log(response.data[0].userPictures);
          vm.userPictures = response.data[0].userPictures;
          // vm.URL = response.data[0].userPictures[0].pictureUrl;
          // console.log(vm.URL)
        })
        .catch(function (err) {
          console.log(err);
        });
    },
    getColor(point) {
      if (point > 1000) return "red";
      else if (point > 500) return "orange";
      else return "green";
    },
  },
};
</script>

<style></style>
