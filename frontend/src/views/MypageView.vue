<template>
  <v-container class="mypagewrap">
    <v-card style="background-color: #ffdede">
      <v-row>
          <v-col cols="8"></v-col>
          <v-col cols="2">
            <v-btn small class="ma-2" outlined fab color="indigo">
  
              <router-link :to ="{name: 'avatar', params : {avatarColor, avatarFace, avatarHat, avatarPet} }">
                <v-icon>mdi-pencil</v-icon>
                </router-link>
            </v-btn></v-col
          >
        </v-row>
      <v-img :src="require(`../assets/img/Ocsoon/Pet/${this.avatarPet}.png`)" >
          <v-img :src="require(`../assets/img/Ocsoon/Character/${this.avatarColor}.png`)">
          <v-img :src="require(`../assets/img/Ocsoon/Face/${this.avatarFace}.png`)">
          <v-img :src="require(`../assets/img/Ocsoon/Hat/${this.avatarHat}.png`)"></v-img></v-img>
          </v-img>
      </v-img>
      <!-- Nickname -->
      <v-row>
        <v-col cols="10">
          <v-card-title>{{ userInfo.userNickname }}</v-card-title></v-col
        >
        <!-- <v-col cols="2">
          <v-btn small class="ma-2">
            <v-icon>mdi-pencil</v-icon>
          </v-btn></v-col
        > -->
         <v-col cols="2">
          <v-form ref="nicknameForm" lazy-validation>
    <v-dialog 
      v-model="nicknameDialog"
      persistent
      max-width="600px"
    >
      <template v-slot:activator="{ on, attrs }">
        <v-btn
          dark
          v-bind="attrs"
          v-on="on"
          @click='newNickname = ""'
        >
        <v-icon>mdi-pencil</v-icon>
        </v-btn>
      </template>
      <v-card>
        <v-card-title>
          <span class="text-h5">Change Nickname</span>
        </v-card-title>
        <v-card-text>
          <v-container>
            <v-row>
              <v-col
                cols="12"
                sm="6"
                md="4"
              >

              <v-text-field
                v-model = newNickname
                :counter="10"
                :rules="nameRules"
                label="new NickName*"
                required
                @change="userNickChk = false"
                
              ></v-text-field>
              <!-- 닉네임 중복검사 추가 -->
              <span>중복검사
              <v-icon @click="nickcheck">mdi-check-bold</v-icon></span>
               </v-col>
            </v-row>
          </v-container>
          <small>*indicates required field</small>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn
            color="blue darken-1"
            text
            @click="nicknameDialog = false"
          >
            Close
          </v-btn>
          <v-btn
            color="blue darken-1"
            text
            @click="changeNickname"
          >
            Save
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
          </v-form>
    </v-col
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
   
    <v-dialog
      v-model="passwordDialog"
      persistent
      max-width="600px"
    >
      <template v-slot:activator="{ on, attrs }">
        <v-btn
          small class="ma-2"
          v-bind="attrs"
          v-on="on"
        
        >
        <v-icon>mdi-pencil</v-icon>
        </v-btn>
      </template>
      <v-card>
        <v-card-title>
          <span class="text-h5">Change Password</span>
        </v-card-title>
        <v-card-text>
          <v-container>
            <v-row>
              <v-col
                cols="12"
                sm="6"
                md="4"
              >
              <v-text-field
                 v-model="currentPassword"
                :append-icon="show ? 'mdi-eye' : 'mdi-eye-off'"
                :rules="[rules.required, rules.min]"
                :type="show ? 'text' : 'password'"
                label="Enter current Password*"
                hint="At least 8 characters"
                counter
                @click:append="show = !show"
              ></v-text-field>

              <v-text-field
                 v-model="newPassword"
                :append-icon="show ? 'mdi-eye' : 'mdi-eye-off'"
                :rules="[rules.required, rules.min]"
                :type="show ? 'text' : 'password'"
                label="Enter new Password*"
                hint="At least 8 characters"
                counter
                @click:append="show = !show"
              ></v-text-field>

              <v-text-field
                 v-model="chkPassword"
                :append-icon="show ? 'mdi-eye' : 'mdi-eye-off'"
              :rules="[rules.required, rules.min]"
                :type="show ? 'text' : 'password'"
                label="Enter new Password Again*"
                hint="At least 8 characters"
                counter
                @click:append="show = !show"
              ></v-text-field>

              <h6 v-if="sameChk(chkPassword)" class="mb-5 teal--text accent-3">
                Please create the two passwords identical.
              </h6>
              <h6 v-else class="mb-5 red--text lighten-2">
                Please create the two passwords identical.
              </h6>
               </v-col>
            </v-row>
          </v-container>
          <small>*indicates required field</small>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn
            color="blue darken-1"
            text
            @click="passwordDialog = false"
          >
            Close
          </v-btn>
          <v-btn
            color="blue darken-1"
            text
            @click="changePassword"
          >
            Save
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  
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
      nicknameDialog : false,
      passwordDialog : false,
      userNickChk : false,
      userNickValid : false,
      currentPassword : "",
      newPassword : "",
      newNickname : "",
      show: false,
      chkPassword: "",
      passwordValid :"",
      nameRules: [
      (v) => !!v || "Name is required",
      (v) => (v && v.length <= 10) || "Name must be less than 10 characters",
      (v) => (v && v.length >= 4) || "Name must be at least 4 characters",
     
      ],
      rules: {
      required: (value) => !!value || "Required.",
      min: (v) => v.length >= 8 || "Min 8 characters",
    },
      userInfo: [],
      avatarColor : 0,
      avatarHat : 0,
      avatarFace : 0,
      avatarPet :0,
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
    sameChk(password) {
      if (this.newPassword == password) return true;
      else {
        this.passwordValid = false;
        return false;
      }
    },
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
   
    nickcheck() {
      const validate = this.$refs.nicknameForm.validate();
      if(validate){
      axios
        .get(`api/register/check/nickname/${this.userInfo.usernickname}`)
        .then(({ data }) => {
          let msg = "중복된 닉네임입니다. 다시 입력해주세요";
          if (data === false) {
            msg = "사용가능한 닉네임입니다.";
            this.userNickChk = true;
            alert(msg);
          } else {
            this.userNickChk = false;
            alert(msg);
          }
        })
        .catch((error) => {
          console.log(error);
          alert("닉네임중복체크에 실패했습니다..");
        });
      }else{
        alert("닉네임이 유효하지 않습니다.")
      }
    },
    changeNickname(){
      const userNickname = this.newNickname;
      if(this.userNickChk && this.newNickname.length!=0){
        axios
        .patch(`api/user/modification/nickname`, {
        headers: {
          "Access-Control-Allow-Origin": "*",
          "Content-Type": "application/json",
        },
        userNickname
      })
        .then(() => {
          alert("닉네임 변경에 성공했습니다.")
         this.userInfo.userNickname = userNickname
        })
        .catch((error) => {
          console.log(error);
          alert("닉네임 변경에 실패했습니다.")
        })
        this.nicknameDialog = false;
       // this.$router.push("main");
      }else{
        alert("닉네임이 유효하지 않습니다.")
      }
    },
    changePassword(){
      if(this.newPassword === this.chkPassword){
const UserUpdatePasswordReq = {
        currentPassword : this.currentPassword,
        newPassword : this.newPassword
      }
      axios
        .patch(`api/user/modification/password`, UserUpdatePasswordReq
        )
        .then(() => {
          alert("비밀번호 변경에 성공했습니다.")
         
        })
        .catch((error) => {
          console.log(error);
          alert("비밀번호 변경에 실패했습니다.")
        })
       this.passwordDialog = false;
       this.$router.push("main");
    }else{
      alert("새로운 비밀번호가 일치하지 않습니다.")
    }
      }
      
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
        vm.userAvatar = vm.userInfo.userAvatar.split(", ");
        vm.avatarColor = parseInt(vm.userAvatar[0]);
        vm.avatarFace =   parseInt(vm.userAvatar[1]);
        vm.avatarHat =  parseInt(vm.userAvatar[2]);
        vm.avatarPet =   parseInt(vm.userAvatar[3]);
        console.log(vm.userAvatar);
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
