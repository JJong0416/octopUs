<template>
  <div>
    <v-container align="center" justify="center">
      <v-row class="pt-4 pl-4">
        <v-col cols="4">
          <!-- 뒤로 가기 버튼 -->
          <v-btn @click="goback" icon>
            <v-icon> mdi-arrow-left</v-icon>
          </v-btn>
        </v-col>
        <v-col cols="5">
          <h3>옥수니상점</h3>
        </v-col>
        <v-col cols="3"></v-col>
      </v-row>
      <v-row justify="center">
        <v-col class="px-10 pt-3 pb-0" cols="4">
          <v-icon large @click="toOriginal">mdi-refresh</v-icon>
        </v-col>
        <v-col cols="4"></v-col>
        <v-col class="px-10 pt-3 pb-0" cols="4">
          <v-icon
            large
            v-if="
              initColor === avatarColor &&
              initFace === avatarFace &&
              initHat === avatarHat &&
              initPet === avatarPet
            "
            @click="saveAvatar_not"
            >mdi-check</v-icon
          >
          <v-icon large v-else @click="avatarDialog = true">mdi-check</v-icon>
        </v-col>
      </v-row>

      <v-row class="logo-img-wrapper">
        <v-col>
          <v-img
            :src="require(`../assets/img/Ocsoon/Pet/${this.avatarPet}.png`)"
            width="30vh"
          >
            <v-img
              :src="
                require(`../assets/img/Ocsoon/Character/${this.avatarColor}.png`)
              "
              width="30vh"
            >
              <v-img
                :src="
                  require(`../assets/img/Ocsoon/Face/${this.avatarFace}.png`)
                "
                width="30vh"
              >
                <v-img
                  :src="
                    require(`../assets/img/Ocsoon/Hat/${this.avatarHat}.png`)
                  "
                  width="30vh"
                ></v-img
              ></v-img>
            </v-img> </v-img
        ></v-col>
      </v-row>
      <v-btn-toggle v-model="toggle_exclusive">
        <v-row>
          <v-col cols="12" sm="6" class="py-1">
            <v-btn color="#fa183e" @click="changeColor(0)" />
            <v-btn color="#ffbbed" @click="changeColor(1)" />
            <v-btn color="#ffec00" @click="changeColor(2)" />
            <v-btn color="#b9ffb2" @click="changeColor(3)" />
            <v-btn color="#003fff" @click="changeColor(4)" />
            <v-btn color="#d2a1ff" @click="changeColor(5)" />
            <v-btn color="#8500ff" @click="changeColor(6)" />
          </v-col>
          <v-col cols="12" sm="6" class="pb-5 pt-0">
            <v-btn color="#a5bbff" @click="changeColor(7)" />
            <v-btn color="#fff9b5" @click="changeColor(8)" />
            <v-btn rounded height="48" width="48">
              <v-img
                height="48"
                width="48"
                src="../assets/img/Btn/glitter.jpeg"
                @click="changeColor(9)"
              ></v-img
            ></v-btn>
            <v-btn rounded height="48" width="48">
              <v-img
                height="48"
                width="48"
                src="../assets/img/Btn/cow.jpeg"
                @click="changeColor(10)"
              ></v-img
            ></v-btn>
            <v-btn rounded height="48" width="48">
              <v-img
                height="48"
                width="48"
                src="../assets/img/Btn/tiger.jpeg"
                @click="changeColor(11)"
              ></v-img
            ></v-btn>
            <v-btn rounded height="48" width="48">
              <v-img
                height="48"
                width="48"
                src="../assets/img/Btn/universe.jpeg"
                @click="changeColor(12)"
              ></v-img
            ></v-btn>
            <v-btn rounded height="48" width="48">
              <v-img
                height="48"
                width="48"
                src="../assets/img/Btn/pudding.jpeg"
                @click="changeColor(13)"
              ></v-img
            ></v-btn>
          </v-col>
        </v-row>
        <br /><br />
      </v-btn-toggle>

      <template>
        <v-card>
          <v-tabs v-model="tab" background-color="#eefaec" color="#143559" grow>
            <v-tab v-for="(item, index) in items" :key="index">
              {{ item.kind }}
            </v-tab>
          </v-tabs>

          <v-tabs-items v-model="tab">
            <v-tab-item v-for="(item, index) in items" :key="index">
              <v-container fluid>
                <v-row>
                  <v-col v-for="i in item.size" :key="i" cols="3" md="4">
                    <v-img
                      :src="
                        require(`../assets/img/Ocsoon/${item.kind}/${
                          i - 1
                        }.png`)
                      "
                      aspect-ratio="1"
                      :class="{
                        'grey lighten-2': selectItemKind[item.kind] == i - 1,
                        'grey lighten-4': selectItemKind[item.kind] != i - 1,
                      }"
                      @click="changeAvatar(item.kind, i - 1)"
                    ></v-img>
                  </v-col>
                </v-row>
              </v-container>
            </v-tab-item>
          </v-tabs-items>
        </v-card>
      </template>
    </v-container>
    <!-- 아바타 Dialog -->
    <v-dialog v-model="avatarDialog" max-width="500px">
      <v-card>
        <v-card-title>
          <h5>아바타 변경</h5>
        </v-card-title>
        <v-card-title>
          <h6>아바타 변경시 500 포인트가 차감됩니다. 변경하시겠습니까?</h6>
        </v-card-title>
        <v-card-actions>
          <v-btn color="#fa183e" text @click="saveAvatar"> 확인 </v-btn>
          <v-spacer></v-spacer>
          <v-btn color="#fa183e" text @click="avatarDialog = false">
            취소
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
    <!-- 아바타 -->
    <v-dialog v-model="avaChangeDialog" max-width="500px">
      <v-card>
        <v-card-title>
          <h5>아바타 변경</h5>
        </v-card-title>
        <v-card-title>
          <h6>{{ this.avatarMsg }}</h6>
        </v-card-title>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="#fa183e" text @click="refresh"> 확인 </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
    <footer-view></footer-view>
  </div>
</template>

<script>
import axios from "axios";
import FooterView from "../components/common/FooterView.vue";
export default {
  components: {
    FooterView,
  },
  data() {
    return {
      toggle_exclusive: 2,
      tab: null,
      items: [
        { kind: "Face", size: 12 },
        { kind: "Hat", size: 29 },
        { kind: "Pet", size: 8 },
      ],
      text: "items",
      avatarDialog: false,
      avaChangeDialog: false,
      avatarMsg: false,
      avatarColor: null,
      avatarFace: null,
      avatarHat: null,
      avatarPet: null,
      initColor: null,
      initFace: null,
      initHat: null,
      initPet: null,
      selectItem: null,
      selectItemKind: {
        Face: this.initFace,
        Hat: this.initHat,
        Pet: this.initPet,
      },
    };
  },

  created() {
    axios.defaults.headers.common[
      "Authorization"
    ] = `Bearer ${sessionStorage.getItem("token")}`;
    (this.avatarColor = this.$route.params.avatarColor),
      (this.avatarFace = this.$route.params.avatarFace),
      (this.avatarHat = this.$route.params.avatarHat),
      (this.avatarPet = this.$route.params.avatarPet),
      (this.initColor = this.avatarColor),
      (this.initFace = this.avatarFace),
      (this.initHat = this.avatarHat),
      (this.initPet = this.avatarPet);
    console.log(this.avatarColor);
  },
  methods: {
    toOriginal() {
      (this.avatarColor = this.initColor),
        (this.avatarFace = this.initFace),
        (this.avatarHat = this.initHat),
        (this.avatarPet = this.initPet);
      console.log(this.initPet);
      this.selectItemKind["Face"] = this.initFace;
      if (this.initHat == "0_nothing") {
        this.selectItemKind["Hat"] = 0;
      }
      if (this.initPet == "0_nothing") {
        this.selectItemKind["Pet"] = 0;
      }
    },
    changeColor(value) {
      this.avatarColor = value;
    },
    changeAvatar(kind, i) {
      this.selectItem = i;
      this.selectItemKind[kind] = i;
      console.log(this.selectItem);
      console.log(kind + i);
      if (kind === "Face") {
        this.avatarFace = i;
      } else if (kind === "Hat") {
        if (i == 0) {
          this.avatarHat = "0_nothing";
        } else {
          this.avatarHat = i;
        }
      } else if (kind === "Pet") {
        if (i == 0) {
          this.avatarPet = "0_nothing";
        } else {
          this.avatarPet = i;
        }
      }
    },
    saveAvatar() {
      var sendAvatarHat = this.avatarHat;
      var sendAvatarPet = this.avatarPet;
      if (this.avatarHat == "0_nothing") {
        sendAvatarHat = 0;
      }
      if (this.avatarPet == "0_nothing") {
        sendAvatarPet = 0;
      }
      const avatarReq = {
        avatarColor: this.avatarColor,
        avatarFace: this.avatarFace,
        avatarHat: sendAvatarHat,
        avatarPet: sendAvatarPet,
      };
      axios
        .patch(`/api/user/modification/avatar`, avatarReq)
        .then((response) => {
          console.log(response);
          this.avatarMsg = "아바타 변경이 완료되었습니다.";
        })
        .catch(function (err) {
          this.avatarMsg = "포인트가 부족합니다.";
          console.log(err);
        });
      this.avaChangeDialog = true;
    },
    refresh() {
      if (this.avatarMsg === "아바타 변경이 완료되었습니다.")
        window.location.href = "/Mypage";
      else this.avaChangeDialog = false;
    },
    saveAvatar_not() {
      window.location.href = "/Mypage";
    },
    goback() {
      this.$router.go(-1);
    },
  },
};
</script>

<style>
.basil {
  background-color: #fffbe6 !important;
}
.basil--text {
  color: #356859 !important;
}
.wrapper {
  height: 100%;
}
.logo-img-wrapper {
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 0;
}
</style>
