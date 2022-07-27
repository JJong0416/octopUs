<template>
  <v-card :loading="loading" class="mx-auto my-12" max-width="374">
    <template slot="progress">
      <v-progress-linear
        color="deep-purple"
        height="10"
        indeterminate
      ></v-progress-linear>
    </template>

    <v-img height="250" src="../assets/img/1.png"></v-img>

    <v-card-title>나는 문어</v-card-title>

    <v-card-text>
      <div>내 정보가 들어갈 곳</div>
    </v-card-text>

    <v-divider class="mx-4"></v-divider>

    <v-card-title>꿈을 꾸는 문어</v-card-title>
    <v-img style="max-width: 70px" src="../assets/kakaopay.png"></v-img>
  </v-card>
</template>
<script></script>
