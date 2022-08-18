!
<template>
  <v-card class="mx-auto" max-width="344" outlined>
    <v-list-item three-line>
      <v-list-item-content>
        <div class="text-overline mb-4">
          {{ mission.missionType }}
          &nbsp;&nbsp;<v-chip
            right
            :color="getColor(mission.missionPoint)"
            dark
          >
            {{ mission.missionPoint }}
          </v-chip>
        </div>

        <v-list-item-title class="text-h5 mb-1">
          {{ mission.missionTitle }}
        </v-list-item-title>
        <v-list-item-subtitle>{{
          mission.missionContent
        }}</v-list-item-subtitle>
      </v-list-item-content>

      <v-list-item-avatar tile size="80" color="grey">
        <v-img
          :src="require(`../../assets/img/Theme/${mission.missionType}.jpg`)"
        ></v-img
      ></v-list-item-avatar>
    </v-list-item>

    <v-card-actions>
      <v-spacer></v-spacer>

      <v-btn depressed outlined v-if="mission.missionStatus === `UNACTIVATED`">
        <router-link
          :to="{
            name: 'unactivated',
            params: { missionNo: mission.missionNo },
          }"
        >
          미션 활성화하기
        </router-link>
      </v-btn>
      <v-btn outlined depressed v-else-if="mission.missionStatus === `OPEN`">
        <router-link
          :to="{
            name: 'before',
            params: { missionNo: mission.missionNo },
          }"
        >
          모집중인 미션 입장하기
        </router-link>
      </v-btn>
      <v-btn outlined depressed v-else-if="mission.missionStatus === `ONGOING`">
        <router-link
          :to="{
            name: 'proceeding',
            params: { missionNo: mission.missionNo },
          }"
        >
          진행중인 미션 입장하기
        </router-link>
      </v-btn>
      <v-btn outlined depressed v-else>
        <router-link
          :to="{
            name: 'proceeding',
            params: { missionNo: mission.missionNo },
          }"
        >
          끝난방 들어가기
        </router-link></v-btn
      >
    </v-card-actions>
  </v-card>
</template>

<script>
export default {
  props: ["mission"],
  methods: {
    getColor(point) {
      if (point > 1000) return "red";
      else if (point > 500) return "orange";
      else return "green";
    },
  },
};
</script>

<style></style>
