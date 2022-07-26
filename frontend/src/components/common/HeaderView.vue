<template>
  <v-card class="mx-auto overflow-hidden">
    <v-app-bar color="black" dark>
      <v-app-bar-nav-icon @click="drawer = true"></v-app-bar-nav-icon>
      <!-- Home 화면 이동 링크 -->
      <div class="title">
        <router-link :to="`/`">
          <v-toolbar-title>OctopUS</v-toolbar-title>
        </router-link>
      </div>
      <!-- 여백 -->
      <v-spacer></v-spacer>
      <!-- About Us 설명 창 이동 -->
      <router-link :to="`/aboutus`">
        <v-btn elevation="2">About US</v-btn>
      </router-link>
      <router-link :to="`/create`">
        <v-btn elevation="2">Create</v-btn>
      </router-link>
    </v-app-bar>

    <v-navigation-drawer v-model="drawer" absolute temporary height="180px">
      <v-list nav dense>
        <v-list-item-group
          v-model="group"
          active-class="deep-purple--text text--accent-4"
        >
          <v-list-item>
            <v-list-item-icon>
              <v-icon>mdi-home</v-icon>
            </v-list-item-icon>
            <v-list-item-title>Home</v-list-item-title>
          </v-list-item>

          <v-list-item>
            <v-list-item-icon>
              <v-icon>mdi-account</v-icon>
            </v-list-item-icon>
            <v-list-item-title>Account</v-list-item-title>
          </v-list-item>

          <v-list-item>
            <v-list-item-icon>
              <v-icon>mdi-logout</v-icon>
            </v-list-item-icon>
            <v-list-item-title>Logout</v-list-item-title>
          </v-list-item>
        </v-list-item-group>
      </v-list>

      <template v-slot:append>
        <div class="pa-2" @click="drawer = false">
          <v-btn block>
            <v-icon>mdi-close-circle</v-icon>
          </v-btn>
        </div>
      </template>
    </v-navigation-drawer>
  </v-card>
</template>

<script>
export default {
  data: () => ({
    drawer: false,
    group: null,
  }),
};
</script>

<style>
.title a {
  text-decoration: none;
  color: white;
}
.title a:hover {
  text-decoration: none;
  color: white;
}
</style>
