<template>
  <v-container class="mypagewrap">
    <v-card style="background-color: #ffdede">
      <v-img height="250" src="../assets/img/Ocsoon/Character/1.png">
        <!-- 이미지 수정 버튼 -->
        <v-row>
          <v-col cols="8"></v-col>
          <v-col cols="2">
            <v-btn small class="ma-2" outlined fab color="indigo">
              <v-icon>mdi-pencil</v-icon>
            </v-btn></v-col
          >
        </v-row>
      </v-img>
      <!-- Nickname -->
      <v-row>
        <v-col cols="10">
          <v-card-title>{{ userInfo.userNickname }}</v-card-title></v-col
        >
        <v-col cols="2">
          <v-btn small class="ma-2">
            <v-icon>mdi-pencil</v-icon>
          </v-btn></v-col
        >
      </v-row>
      <!-- id, Email -->
      <v-card-text>
        <div>Id : {{ userInfo.userId }}</div>
        <div>Email : {{ userInfo.userEmail }}</div>
      </v-card-text>

      <v-divider class="mx-4"></v-divider>

      <v-row>
        <v-col cols="6">
          <v-card-title>{{ userInfo.userPoint }} Point</v-card-title>
        </v-col>
        <v-col cols="3">
          <v-dialog v-model="charge" width="500">
            <template v-slot:activator="{ on, attrs }">
              <v-btn color="red lighten-2" dark v-bind="attrs" v-on="on">
                충전하기
              </v-btn>
            </template>

            <v-card>
              <v-card-title class="text-h5 yellow lighten-2">
                포인트 충전
              </v-card-title>
              <v-dialog
                v-model="kakaopay"
                fullscreen
                hide-overlay
                transition="dialog-bottom-transition"
              >
                <template v-slot:activator="{ on, attrs }">
                  <v-btn color="amber" v-bind="attrs" v-on="on">
                    <v-icon color="yellow">mdi-currency-usd</v-icon>
                    1000Points
                  </v-btn>
                  <v-divider></v-divider>
                  <v-btn color="amber" v-bind="attrs" v-on="on">
                    <v-icon color="yellow">mdi-currency-usd</v-icon>
                    3000Points
                  </v-btn>

                  <v-btn color="amber" v-bind="attrs" v-on="on">
                    <v-icon color="yellow">mdi-currency-usd</v-icon>
                    5000Points
                  </v-btn>
                </template>
                <v-card>
                  <v-img
                    src="../assets/img/account/kakaopay.jpg"
                    @click="kakaopay = false"
                  ></v-img>
                </v-card>
              </v-dialog>
              <v-divider></v-divider>

              <v-card-actions>
                <v-spacer></v-spacer>

                <v-btn color="primary" text @click="charge = false">
                  close
                </v-btn>
              </v-card-actions>
            </v-card>
          </v-dialog>
        </v-col>
        <v-col cols="3">
          <v-img
            style="max-width: 70px"
            src="../assets/img/Kakao/kakaopay.png"
          ></v-img>
        </v-col>
        <v-col cols="auto">
          <v-dialog
            hide-overlay
            transtion="dialog-bottom-transition"
            scrollable
          >
            <template v-slot:activator="{ on, attrs }">
              <v-btn color="primary" v-bind="attrs" v-on="on">환급받기</v-btn>
            </template>
            <template v-slot:default="dialog">
              <v-card>
                <v-toolbar max-height="100px" color="primary" dark
                  ><div class="text-h4">보내기</div></v-toolbar
                >
                <v-dialog v-model="account" width="500">
                  <template v-slot:activator="{ on, attrs }">
                    <v-container>
                      <v-btn width="300" outlined v-bind="attrs" v-on="on">
                        은행 선택
                      </v-btn>
                    </v-container>
                  </template>

                  <v-card>
                    <v-img
                      @click="account = false"
                      src="../assets/img/account/bank.jpg"
                    ></v-img>
                    <v-img
                      @click="account = false"
                      src="../assets/img/account/bank2.jpg"
                    ></v-img>
                  </v-card>
                </v-dialog>
                <v-form>
                  <v-container>
                    <v-text-field label="계좌번호 입력" outlined></v-text-field>
                  </v-container>
                </v-form>

                <v-card-actions class="justify-end">
                  <v-btn text @click="dialog.value = false">Close</v-btn>
                </v-card-actions>
              </v-card>
            </template>
          </v-dialog>
        </v-col>
      </v-row>

      <br />
      비밀번호 수정하기
      <v-btn small class="ma-2">
        <v-icon>mdi-pencil</v-icon>
      </v-btn>
      <v-divider class="mx-4"></v-divider>
      <br />
      <v-data-table
        :headers="Missionheaders"
        :items="openedMissions"
        :items-per-page="5"
        class="elevation-1"
        :single-expand="singleExpand"
        :expanded.sync="openexpanded"
        item-key="missionCode"
        show-expand
        @item-expanded="clickExpand"
      >
        <!-- 표 상단의 해더 문구 -->
        <template v-slot:top>
          <v-toolbar flat>
            <v-toolbar-title>참여중인 방</v-toolbar-title>
            <v-spacer></v-spacer>
            <!-- <v-switch
                v-model="singleExpand"
                label="하나만 펼치기"
                class="mt-2"
              ></v-switch> -->
          </v-toolbar>
        </template>

        <template v-slot:[`item.missionPoint`]="{ item }">
          <v-chip :color="getColor(item.missionPoint)" dark>
            {{ item.missionPoint }}
          </v-chip>
        </template>
        <!-- <template v-slot:[`item.data-table-expand`]="{ item }">
            <tr @click="clickExpand(item.missionNo)">
              <td>{{item.data-table-expand}}</td>
            </tr>
            나오냐?
          </template> -->

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

              <v-list-item two-line>
                <v-list-item-content>
                  <v-list-item-title>인증 사진</v-list-item-title>
                  <img :src="URL" style="height: 120px" class="mx-4" />
                </v-list-item-content>
              </v-list-item>
            </template>

            <!-- 내부 내용 끝 -->
            <router-link
              :to="{
                name: 'proceeding',
                params: { missionNo: item.missionNo },
              }"
            >
              자세히보기
            </router-link>
          </td>
        </template>
      </v-data-table>

      <br />
      <v-data-table
        :headers="Missionheaders"
        :items="closedMissions"
        :items-per-page="5"
        class="elevation-1"
        :single-expand="singleExpand"
        :expanded.sync="closeexpanded"
        item-key="missionCode"
        show-expand
        @item-expanded="clickExpand"
      >
        <!-- 표 상단의 해더 문구 -->
        <template v-slot:top>
          <v-toolbar flat>
            <v-toolbar-title>종료된 방</v-toolbar-title>
            <v-spacer></v-spacer>
          </v-toolbar>
        </template>

        <template v-slot:[`item.missionPoint`]="{ item }">
          <v-chip :color="getColor(item.missionPoint)" dark>
            {{ item.missionPoint }}
          </v-chip>
        </template>
        <template v-slot:expanded-item="{ headers, item }">
          <td :colspan="headers.length">
            <!--  -->
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

              <v-list-item two-line>
                <v-list-item-content>
                  <v-list-item-title>인증 사진</v-list-item-title>
                  <img :src="URL" style="height: 120px" class="mx-4" />
                </v-list-item-content>
              </v-list-item>
            </template>

            <!--  -->
            <router-link
              :to="{ name: 'after', params: { missionNo: item.missionNo } }"
            >
              자세히보기
            </router-link>
          </td>
        </template>
      </v-data-table>
      <br />
    </v-card>
  </v-container>
</template>
<script>
import axios from "axios";
import cookie from "vue-cookies";

export default {
  data() {
    return {
      kakaopay: false,
      charge: false,
      account: false,
      userInfo: [],
      openedMissions: [],
      closedMissions: [],
      Missionheaders: [
        {
          text: `방 이름`,
          align: `start`,
          softable: false,
          value: `missionTitle`,
        },
        { text: `포인트`, value: `missionPoint` },
        { text: ``, value: `data-table-expand` },
      ],
      openexpanded: [],
      closeexpanded: [],
      singleExpand: true,
      URL: null,
      userPictures: [],
    };
  },

  methods: {
    getColor(point) {
      if (point > 400) return "red";
      else if (point > 200) return "orange";
      else return "green";
    },

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
  },
  created() {
    var vm = this;
    let token = cookie.get("token");
    console.log("쿠키는?" + token);

    axios
      .get(`api/user/info`, {
        headers: {
          "Access-Control-Allow-Origin": "*",
          "Content-Type": "application/json",
        },
      })
      .then(function (response) {
        console.log(response);
        vm.userInfo = response.data;
        // 참여중인 방 정보 받기
        axios
          .get(`api/user/mission/${vm.userInfo.userId}`, {
            headers: {
              "Access-Control-Allow-Origin": "*",
              "Content-Type": "application/json",
            },
          })
          .then(function (response) {
            console.log(response);
            response.data.forEach((mission) => {
              if (mission.missionStatus === "CLOSE") {
                vm.closedMissions.push(mission);
              } else {
                vm.openedMissions.push(mission);
              }
            });
            console.log("열린것" + vm.openedMissions);
            console.log("닫힌것" + vm.closedMissions);
          })
          .catch(function (err) {
            console.log(err);
          });
      })
      .catch(function (err) {
        console.log(err);
      });
  },
};
</script>
<style>
.mypagewrap {
  height: 90vh;
  background-color: "#f1faee";
}
</style>
