<template>
  <div>
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
    width="360"
  >
    <v-card-text>
      <div class="mb-4">
        <h2 class="pl-7">[ OctopUS Point 관련 정책 ]</h2>
        
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
      onScroll () {
        this.scrollInvoked++
      },
      goback() {
      this.$router.go(-1);
    },
    },
  }
</script>