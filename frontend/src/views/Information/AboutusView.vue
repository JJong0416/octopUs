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
  <v-img src="../../assets/img/aboutus/1.png"></v-img>
  <br><br>
  <v-img src="../../assets/img/aboutus/2.png"></v-img>
  <br><br>
  <v-img src="../../assets/img/aboutus/3.png"></v-img>
  <br><br>
   <v-img src="../../assets/img/aboutus/4.png"></v-img>
   <br><br>
    <v-img src="../../assets/img/aboutus/5.png"></v-img>
    <br><br>
     <v-img src="../../assets/img/aboutus/6.png"></v-img>
     <br><br>
     <v-img src="../../assets/img/aboutus/7.png" width="100%"></v-img>
    </v-container>
</div>
</template>

<script>

  export default {
     
    data: () => ({
      length: 3,
      window: 0,
    }),
    methods:{
      goback() {
      this.$router.go(-1);
    },
    }
  }
</script>

<style>.wrap {
  background-color: #f7f5fc;
}</style>
