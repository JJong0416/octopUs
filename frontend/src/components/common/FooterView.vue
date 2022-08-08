<template>
  <v-card>
    <v-footer fixed padless>
      <v-card flat tile width="100%" class="text-center">
        <v-card-text>
          <v-btn v-for="icon in icons" :key="icon" class="mx-4" icon>
            <router-link :to="icon[1]">
              <v-icon size="24px">
                {{ icon[0] }}
              </v-icon></router-link
            >
          </v-btn>
        </v-card-text>
      </v-card>
    </v-footer>
  </v-card>
</template>

<script>
export default {
  data: () => ({
    icons: [
      ["mdi-home", "/"],
      ["mdi-fire", "/hotnew"],
      ["mdi-magnify", "/search"],
      ["mdi-account", "mypage"],
    ],
  }),
};
</script>

<style>
.v-btn a {
  text-decoration: none;
  color: #ff2456;
}
.v-btn a:hover {
  text-decoration: none;
  color: #ff2456;
}
</style>
