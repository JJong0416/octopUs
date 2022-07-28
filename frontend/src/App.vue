<template>
  <div>
    <div id="wrapper">
      <header-view></header-view>
      <router-view></router-view>
    </div>
    <footer>
      <footer-view></footer-view>
    </footer>
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
#wrapper {
  height: auto;
  min-height: 100%;
}
footer {
  width: 100%;
  position: absolute;
  bottom: 0;
  left: 0;
}
</style>
