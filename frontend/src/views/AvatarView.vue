<template>
  <div align="center" justify="center">
    <v-img src="../assets/img/1.png" width="200px" />
    <v-btn-toggle v-model="toggle_exclusive">
      <v-row>
        <v-col cols="12" sm="6" class="py-1">
          <v-btn color="red"> </v-btn>
          <v-btn color="red lighten-2"> </v-btn>
          <v-btn color="pink lighten-2"></v-btn>
          <v-btn color="purple lighten-2"></v-btn>
          <v-btn color="deep-purple ligten-1"></v-btn>
          <v-btn color="deep-purple darken-1"></v-btn>
          <v-btn color="indigo"></v-btn>
        </v-col>
        <v-col cols="12" sm="6" class="py-1">
          <v-btn color="indigo lighten-1"></v-btn>
          <v-btn color="blue"></v-btn>
          <v-btn color="blue lighten-3"></v-btn>
          <v-btn color="cyan"></v-btn>
          <v-btn color="cyan lighten-3"></v-btn>
          <v-btn color="lime"></v-btn>
          <v-btn color="lime lighten-3"></v-btn>
        </v-col>
        <v-col cols="12" sm="6" class="py-1">
          <v-btn color="yellow darken-4"></v-btn>
          <v-btn color="yellow darken-3"></v-btn>
          <v-btn color="brown"></v-btn>
          <v-btn color="blue-grey darken-3"></v-btn>
          <v-btn color="blue-grey"></v-btn>
          <v-btn color="blue-grey lighten-2"></v-btn>
          <v-btn color="grey"></v-btn>
        </v-col>
      </v-row>
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
      items: ["Hat", "Accessory", "Shoes"],
      text: "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.",
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
