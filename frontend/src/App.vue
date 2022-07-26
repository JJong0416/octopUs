<template>
  <div>
    <header-view></header-view>

    <router-view></router-view>

    <footer-view/>
  </div>
</template>

<script>
import FooterView from "./components/common/FooterView.vue";
import HeaderView from "./components/common/HeaderView.vue";

export default {
  name: "App",

  components: {
    HeaderView,
    FooterView,
  },
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
