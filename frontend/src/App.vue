<template>
<div>
  <header-view></header-view>
  <main-view></main-view>
</div>
</template>

<script>
import HeaderView from './components/HeaderView.vue'
import MainView from './views/MainView.vue';

export default {
  name: 'App',

  components: {
    MainView,
    HeaderView,
  },

  data: () => ({
    //
  }),
};
</script>
