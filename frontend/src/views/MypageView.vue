<template>
  <v-container>
    <v-card style="background-color: #ffdede">
      <v-img height="250" src="../assets/img/1.png">
        <!-- 이미지 수정 버튼 -->
        <v-row>
          <v-col cols="8"></v-col>
          <v-col cols="2">
            <v-btn small class="ma-2" outlined fab color="indigo">
              <v-icon>mdi-pencil</v-icon>
            </v-btn></v-col
          >
        </v-row>
      </v-img>
      <!-- Nickname -->
      <v-row>
        <v-col cols="10"> <v-card-title>Nickname</v-card-title></v-col>
        <v-col cols="2">
          <v-btn small class="ma-2">
            <v-icon>mdi-pencil</v-icon>
          </v-btn></v-col
        >
      </v-row>
      <!-- id, Email -->
      <v-card-text>
        <div>Id</div>
        <div>Email</div>
      </v-card-text>

      <v-divider class="mx-4"></v-divider>

      <v-row>
        <v-col cols="6">
          <v-card-title>5000 P</v-card-title>
        </v-col>
        <v-col cols="3"> 충전하기 </v-col>
        <v-col cols="3">
          <v-img style="max-width: 70px" src="../assets/kakaopay.png"></v-img>
        </v-col>
      </v-row>
    </v-card>
    <br />
    비밀번호 수정하기
    <v-btn small class="ma-2">
      <v-icon>mdi-pencil</v-icon>
    </v-btn>
  </v-container>
</template>
<script></script>
