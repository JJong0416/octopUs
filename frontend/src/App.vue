<template>
  <div>
    <v-app id="app">
<<<<<<< HEAD
      <header-view></header-view>
      <router-view></router-view>
      <footer-view></footer-view>
=======
      <router-view></router-view>
>>>>>>> ac048782d308840093efdf3b8146c990ec2fcf95
    </v-app>
  </div>
</template>

<script>
<<<<<<< HEAD
import FooterView from "./components/common/FooterView.vue";
import HeaderView from "./components/common/HeaderView.vue";

export default {
  name: "App",

  components: {
    HeaderView,
    FooterView,
  },
=======
export default {
  name: "App",
>>>>>>> ac048782d308840093efdf3b8146c990ec2fcf95
};
</script>

<style>
/* Router Transition */
.routing-fade-enter-active,
.routing-fade-leave-active {
  transition: opacity 0.3s ease;
}
.routing-fade-enter, .routing-fade-leave-to
/* .routing-fade-leave-active below version 2.1.8 */ {
  opacity: 0;
}
<<<<<<< HEAD
.wrapper {
  height: 88.4vh;
}
=======

>>>>>>> ac048782d308840093efdf3b8146c990ec2fcf95
</style>
