<template>
  <div>
    <!-- 왕문어 -->
    <v-container>
      <v-row>
        <v-col class="logo-img-wrapper">
          <v-img src="../assets/Logo.png" max-width="60%"></v-img>
        </v-col>
      </v-row>
      <router-link :to="`/proceeding`">proceeding</router-link>
      <!-- 검색창 -->
      <v-row>
        <v-col cols="4">
          <v-select :items="items" label="검색 선택"></v-select>
        </v-col>
        <v-col cols="5">
          <v-text-field hide-details single-line></v-text-field>
        </v-col>
        <v-col cols="">
          <v-card-text>
            <v-btn icon>
              <v-icon> mdi-magnify </v-icon>
            </v-btn>
          </v-card-text>
        </v-col>
      </v-row>

      <!-- login -->
      <v-row>
        <v-col cols="2"></v-col>
        <v-col cols="4">
          <v-card-actions
            ><div class="login">
              <router-link :to="`/login`">
                <v-btn color="#ff809d" dark large block>Login</v-btn>
              </router-link>
            </div>
          </v-card-actions></v-col
        >
        <!-- 회원가입 -->
        <v-col cols="4">
          <v-card-actions>
            <div class="signup">
              <router-link :to="`/signup`">
                <v-btn color="#ff809d" dark large block
                  >Sign Up</v-btn
                ></router-link
              >
            </div>
          </v-card-actions></v-col
        >
        <v-col cols="2"></v-col>
      </v-row>
      <v-carousel
        cycle
        interval="1500"
        height="400"
        hide-delimiter-background
        hide-delimiters
        show-arrows-on-hover
      >
        <v-carousel-item v-for="(slide, i) in slides" :key="i">
          <v-sheet :color="colors[i]" height="100%">
            <v-row class="fill-height" align="center" justify="center">
              <div class="text-h2">{{ slide }} Slide</div>
            </v-row>
          </v-sheet>
        </v-carousel-item>
      </v-carousel>
    </v-container>
  </div>
</template>

<script>
export default {
  data: () => ({
    items: ["코드 입력", "제목 검색", "테마 검색"],
    colors: [
      "indigo",
      "warning",
      "pink darken-2",
      "red lighten-1",
      "deep-purple accent-4",
    ],
    slides: ["First", "Second", "Third", "Fourth", "Fifth"],
  }),
};
</script>

<style>
.login a {
  text-decoration: none;
  color: black;
}
.login a:hover {
  text-decoration: none;
  color: black;
}
.signup a {
  text-decoration: none;
  color: black;
}
.signup a:hover {
  text-decoration: none;
  color: black;
}
body {
  background-color: #ebebeb;
}
.more a {
  text-decoration: none;
  color: black;
}
.more a:hover {
  text-decoration: none;
  color: black;
}
.login a {
  text-decoration: none;
  color: white;
}
.login a:hover {
  text-decoration: none;
  color: white;
}
.signUp a {
  text-decoration: none;
  color: white;
}
.signUp a:hover {
  text-decoration: none;
  color: white;
}
.logo-img-wrapper {
  display: flex;
  justify-content: center;
  align-items: center;
}
</style>
