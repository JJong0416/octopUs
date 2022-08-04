<template>
  <div class="header-nav-wrap">
    <v-card class="mx-auto overflow-hidden" height="100vh">
      <v-app-bar>
        <!-- 좌측 메뉴 바 -->
        <v-app-bar-nav-icon @click="drawer = true"></v-app-bar-nav-icon>
        <!-- 여백 -->
        <v-spacer></v-spacer>
        <!-- Home 화면 이동 링크 -->
        <div class="title">
          <router-link :to="`/`">
            <v-img
              src="../../assets/Title.png"
              max-width="150"
              max-height="80"
            ></v-img>
          </router-link>
        </div>
        <!-- 여백 -->
        <v-spacer></v-spacer>
        <!-- 새로운 방 만들기 -->
        <div class="create">
          <router-link :to="`/create`">
            <v-btn elevation="2">Create</v-btn>
          </router-link>
        </div>
      </v-app-bar>

      <v-navigation-drawer v-model="drawer" absolute temporary height="100vh">
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
              <v-list-item-title>My Page</v-list-item-title>
            </v-list-item>

            <v-list-item>
              <v-list-item-icon>
                <v-icon>mdi-logout</v-icon>
              </v-list-item-icon>
              <v-list-item-title>Logout</v-list-item-title>
            </v-list-item>

            <v-list-item>
              <v-list-item-icon>
                <v-icon>mdi-information-variant</v-icon>
              </v-list-item-icon>
              <v-list-item-title>About US</v-list-item-title>
            </v-list-item>
          </v-list-item-group>
        </v-list>
        <!-- 왼쪽 탭 닫기 버튼 -->
        <template v-slot:append>
          <div class="pa-2" @click="drawer = false">
            <v-btn block>
              <v-icon>mdi-close-circle</v-icon>
            </v-btn>
          </div>
        </template>
      </v-navigation-drawer>
    </v-card>
  </div>
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
.create a {
  text-decoration: none;
  color: none;
}
.create a:hover {
  text-decoration: none;
  color: none;
}
.header-nav-wrap {
  height: 6.2vh;
}
</style>
