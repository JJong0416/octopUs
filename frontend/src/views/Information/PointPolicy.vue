<template>
  <div class="wrap">
    <v-container>
      <v-row>
        <v-col>
          <!-- 뒤로 가기 버튼 -->
          <v-btn @click="goback" icon>
            <v-icon> mdi-arrow-left</v-icon>
          </v-btn>
        </v-col>
      </v-row>
      <v-row>
        <v-card
          v-scroll.self="onScroll"
          class="overflow-y-auto"
          height="700"
          width="100%"
        >
          <v-card-text>
            <div class="pa-0 ma-0">
              <v-img src="../../assets/img/Point.png"> </v-img>
            </div>
          </v-card-text>
        </v-card>
      </v-row>
    </v-container>
  </div>
</template>
<script>
export default {
  data: () => ({
    scrollInvoked: 0,
  }),

  methods: {
    onScroll() {
      this.scrollInvoked++;
    },
    goback() {
      this.$router.go(-1);
    },
  },
};
</script>
<style scoped>
.wrap {
  background-color: #f7f5fc;
}
</style>
