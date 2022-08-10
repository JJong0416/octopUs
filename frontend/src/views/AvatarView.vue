<template>
  <div align="center" justify="center">
    <v-row>
      <v-col cols="8"></v-col>
      <v-col cols="4">
        <v-btn @click="toOriginal">원래대로</v-btn>
      </v-col>
      <v-col cols="4">
        <v-btn @click="saveAvatar">저장하기(500p 차감)</v-btn>
      </v-col>
    </v-row>
    <v-img :src="require(`../assets/img/Ocsoon/Pet/${this.avatarPet}.png`)" width="30vh">
          <v-img :src="require(`../assets/img/Ocsoon/Character/${this.avatarColor}.png`)" width="30vh">
          <v-img :src="require(`../assets/img/Ocsoon/Face/${this.avatarFace}.png`)" width="30vh">
          <v-img :src="require(`../assets/img/Ocsoon/Hat/${this.avatarHat}.png`)" width="30vh"></v-img></v-img>
          </v-img>
      </v-img>
 
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
        <v-col cols="12" sm="6" class="py-1">
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
      <v-card color="basil">
        <v-card-title class="text-center justify-center py-6">
          <h3 class="font-weight-bold basil--text">custom your ocsoon</h3>
        </v-card-title>

        <v-tabs v-model="tab" background-color="transparent" color="basil" grow>
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
                      require(`../assets/img/Ocsoon/${item.kind}/${i - 1}.png`)
                    "
                    aspect-ratio="1"
                    class="grey lighten-4"
                    @click="changeAvatar(item.kind, i - 1)"
                  ></v-img>
                </v-col>
              </v-row>
            </v-container>
          </v-tab-item>
        </v-tabs-items>
      </v-card>
    </template>
  </div>
</template>

<script>
import axios from "axios";
export default {
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
      avatarColor: 0,
      avatarFace: 0,
      avatarHat: 0,
      avatarPet: 0,
      avatarInfo :[]
    };
  },
  created() {
    (this.avatarColor = this.$route.params.avatarColor),
      (this.avatarFace = this.$route.params.avatarFace),
      (this.avatarHat = this.$route.params.avatarHat),
      (this.avatarPet = this.$route.params.avatarPet);
      console.log(this.avatarColor)
  },
  methods: {
    toOriginal() {
      (this.avatarColor = this.$route.params.avatarColor),
        (this.avatarFace = this.$route.params.avatarFace),
        (this.avatarHat = this.$route.params.avatarHat),
        (this.avatarPet = this.$route.params.avatarPet);
    },
    changeColor(value) {
      this.avatarColor = value;
    },
    changeAvatar(kind, i) {
      console.log(kind + i);
      if (kind === "Face") {
        this.avatarFace = i;
      } else if (kind === "Hat") {
        this.avatarHat = i;
      } else if (kind === "Pet") {
        this.avatarPet = i;
      }
    },
    saveAvatar(){
     
    const avatarReq = {
      avatarColor : this.avatarColor,
      avatarFace : this.avatarFace,
      avatarHat : this.avatarHat,
      avatarPet : this.avatarPet
    }
      axios
      .patch(`/api/user/modification/avatar`,
        avatarReq
      )
      .then((response) => {
        console.log(response);
        alert("아바타 변경이 완료되었습니다.")
      })
      .catch(function (err) {
        alert("포인트가 부족합니다.")
        console.log(err);
      });
      this.$router.push("Mypage")
    }
  },
};
</script>

<style>
/* Helper classes */
.basil {
  background-color: #fffbe6 !important;
}
.basil--text {
  color: #356859 !important;
}
</style>
