<template>
  <div>
    <router-link :to="before"></router-link>
    <router-view></router-view>
  </div>
</template>

<script>
export default {
  data: () => ({
    before: "/mission/before",
    proceeding: "/mission/proceeding",
    finished: "/mission/finished",
    address: "",
  }),
  created: {
    //방에 status를 확인하는 api호출
  },
  methods: {
    addressChange() {
      this.address = this.before;
    },
  },
};
</script>

<style></style>
