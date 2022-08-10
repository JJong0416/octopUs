<template>
  <div align="center" justify="center">
    <v-img :src=avatarPet width="30vh">
          <v-img :src=avatarColor width="30vh">
          <v-img :src=avatarFace width="30vh">
          <v-img :src=avatarHat width="30vh"></v-img></v-img>
          </v-img>
      </v-img>
    <v-btn-toggle v-model="toggle_exclusive">
      <v-row>
        <v-col cols="12" sm="6" class="py-1">
          <v-btn color="#fa183e" @click="changeColor(0)"/>
          <v-btn color="#ffbbed" @click="changeColor(1)"/>
          <v-btn color="#8500ff" @click="changeColor(2)"/>
          <v-btn color="#15ff00" @click="changeColor(3)"/>
          <v-btn color="#003fff" @click="changeColor(4)"/>
          <v-btn color="#d2a1ff" @click="changeColor(5)"/>
          <v-btn color="#b9ffb2" @click="changeColor(6)"/>
        </v-col>
        <v-col cols="12" sm="6" class="py-1">
          <v-btn color="#a5bbff"  @click="changeColor(7)"/>
          <v-btn color="#fff9b5"  @click="changeColor(8)"/>
          <v-btn rounded height="48" width="48" >
          <v-img height="48" width="48" src="../assets/img/Btn/glitter.jpeg" @click="changeColor(9)"></v-img></v-btn>
          <v-btn rounded height="48" width="48" >
          <v-img height="48" width="48" src="../assets/img/Btn/cow.jpeg" @click="changeColor(10)"></v-img></v-btn>
          <v-btn rounded height="48" width="48" >
          <v-img height="48" width="48" src="../assets/img/Btn/tiger.jpeg" @click="changeColor(11)"></v-img></v-btn>
          <v-btn rounded height="48" width="48" >
          <v-img height="48" width="48" src="../assets/img/Btn/universe.jpeg" @click="changeColor(12)"></v-img></v-btn>
          <v-btn rounded height="48" width="48" >
          <v-img height="48" width="48" src="../assets/img/Btn/pudding.jpeg" @click="changeColor(13)"></v-img></v-btn>
        </v-col>
      </v-row>
      <br><br>
    </v-btn-toggle>
    <template>
      <v-card color="basil">
        <v-card-title class="text-center justify-center py-6">
          <h1 class="font-weight-bold text-h4 basil--text">
            Choose Your Items
          </h1>
        </v-card-title>

        <v-tabs v-model="tab" background-color="transparent" color="basil" grow>
          <v-tab v-for="item in items" :key="item">
            {{ item }}
          </v-tab>
        </v-tabs>

        <v-tabs-items v-model="tab">
          <v-tab-item v-for="item in items" :key="item">
            <v-card color="basil" flat>
              <v-card-text>{{ text }}</v-card-text>
            </v-card>
          </v-tab-item>
        </v-tabs-items>
      </v-card>
    </template>
  </div>
</template>

<script>
export default {
  data() {
    return {
      toggle_exclusive: 2,
      tab: null,
      items: ["Face", "Hat", "Pet"],
      text: "items",
      avatarColor : this.$route.params.avatarColor,
      avatarFace : this.$route.params.avatarFace,
      avatarHat : this.$route.params.avatarHat,
      avatarPet : this.$route.params.avatarPet,
      color : "",
    };
  },
  created(){
    console.log(this.avatarColor, this.avatarFace);
  },
  methods :{
    changeColor(value){
      console.log(value);
      this.avatarColor = require("../assets/img/Ocsoon/Character/" + value + ".png");
    }
  }
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
