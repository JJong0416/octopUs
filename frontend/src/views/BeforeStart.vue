<template>
  <div>
    <v-card :loading="loading" class="mx-auto my-12" max-width="374">
      <template slot="progress">
        <v-progress-linear
          color="deep-purple"
          height="10"
          indeterminate
        ></v-progress-linear>
      </template>

      <v-card-title>
        MissionTitle
      </v-card-title>

      <v-card-text>
        <div class="my-4 text-subtitle-1">룸 코드</div>
      </v-card-text>
      <v-card-text>
        <div class="my-4 text-subtitle-1">참가자 명단</div>
      </v-card-text>

      <v-card-title> 참여 시 필요한 포인트 5000P</v-card-title>

      <v-card-actions>
        <v-card-text color="orange" text> 방 설명 </v-card-text>

        <v-spacer></v-spacer>

        <v-btn icon @click="show = !show">
          <v-icon>{{ show ? "mdi-chevron-up" : "mdi-chevron-down" }}</v-icon>
        </v-btn>
      </v-card-actions>

      <v-expand-transition>
        <div v-show="show">
          <v-divider></v-divider>
          <v-card-title>방 설명 / 인증 방법</v-card-title>
          <v-card-text>
            매일 아침 7시 기상 인증 미라클 모닝 방입니다
            <br>
            흐르는 물에 손을 씻는 장면을 찍어 인증합니다!
          </v-card-text>
          <v-card-title>인증 요일 / 시간</v-card-title>
          <v-card-text>
            일주일에 5 번, 오전 6시 ~ 오전 7시 에 인증합니다.
          </v-card-text>
        </div>
      </v-expand-transition>
      <v-card-actions>
        <v-btn color="deep-purple lighten-2" text @click="reserve">
          시작하기(방장만보이는버튼)
        </v-btn>
      </v-card-actions>
    </v-card>
  </div>
</template>

<script>
export default {
  data: () => ({
    show: false,
  }),
  methods: {},
};
</script>

<style></style>
