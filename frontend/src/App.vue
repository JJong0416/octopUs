<template>
  <div>
    <v-app id="app">
      <router-view></router-view>
    </v-app>
  </div>
</template>

<script>
export default {
  name: "App",
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
