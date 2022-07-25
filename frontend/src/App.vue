<template>
  <div>
    <header-view></header-view>

    <router-view></router-view>
  </div>
</template>

<script>
import HeaderView from "./components/HeaderView.vue";

export default {
  name: "App",

  components: {
    HeaderView,
  },

  data: () => ({
    //
  }),
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
</style>
