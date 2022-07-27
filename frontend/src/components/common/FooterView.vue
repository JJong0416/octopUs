<template>
  <div>
  <v-card>
    <v-toolbar flat>
      <template v-slot:extension>
        <v-tabs
          v-model="tabs"
          fixed-tabs
        >
          <v-tabs-slider></v-tabs-slider>
          <v-tab
            href="#mobile-tabs-5-1"
            class="primary--text"
          >
            <v-icon>mdi-home</v-icon>
          </v-tab>

          <v-tab
            href="#mobile-tabs-5-2"
            class="primary--text"
          >
            <v-icon>mdi-fire</v-icon>
          </v-tab>

          <v-tab
            href="#mobile-tabs-5-3"
            class="primary--text"
          >
            <v-icon>mdi-magnify</v-icon>
          </v-tab>

          <v-tab
            href="#mobile-tabs-5-3"
            class="primary--text"
          >
            <v-icon>mdi-account</v-icon>
          </v-tab>
        </v-tabs>
      </template>
    </v-toolbar>

    <v-tabs-items v-model="tabs">
      <v-tab-item
        v-for="i in 3"
        :key="i"
        :value="'mobile-tabs-5-' + i"
      >
      </v-tab-item>
    </v-tabs-items>
  </v-card>
  </div>
</template>

<script>
  export default {
   
  }
</script>

<style>

</style>