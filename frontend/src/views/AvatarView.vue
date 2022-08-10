<template>
  <div align="center" justify="center">
    <v-img src="../assets/img/Ocsoon/Character/0.png" width="200px">
      <v-img src="../assets/img/Ocsoon/Face/0.png"></v-img>
    </v-img>
    <v-btn-toggle v-model="toggle_exclusive">
      <v-row>
        <v-col cols="12" sm="6" class="py-1">
          <v-btn color="#fa183e"/>
          <v-btn color="#ffbbed"/>
          <v-btn color="#8500ff"/>
          <v-btn color="#15ff00"/>
          <v-btn color="#003fff"/>
          <v-btn color="#d2a1ff"/>
          <v-btn color="#b9ffb2"/>
        </v-col>
        <v-col cols="12" sm="6" class="py-1">
          <v-btn color="#a5bbff"/>
          <v-btn color="#fff9b5"/>
          <v-btn rounded height="48" width="48" >
          <v-img height="48" width="48" src="../assets/img/Btn/glitter.jpeg"></v-img></v-btn>
          <v-btn rounded height="48" width="48" >
          <v-img height="48" width="48" src="../assets/img/Btn/cow.jpeg"></v-img></v-btn>
          <v-btn rounded height="48" width="48" >
          <v-img height="48" width="48" src="../assets/img/Btn/tiger.jpeg"></v-img></v-btn>
          <v-btn rounded height="48" width="48" >
          <v-img height="48" width="48" src="../assets/img/Btn/universe.jpeg"></v-img></v-btn>
          <v-btn rounded height="48" width="48" >
          <v-img height="48" width="48" src="../assets/img/Btn/pudding.jpeg"></v-img></v-btn>
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
    };
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
