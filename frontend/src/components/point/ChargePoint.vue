<template>
  <div>
    <v-dialog v-model="charge" width="500">
            <template v-slot:activator="{ on, attrs }">
              <v-btn color="yellow" v-bind="attrs" v-on="on">
                충전하기
              </v-btn>
            </template>

            <v-card>
              <v-card-title class="text-h5 yellow lighten-2">
                포인트 충전
              </v-card-title>
              <v-dialog
                v-model="kakaopay"
                fullscreen
                hide-overlay
                transition="dialog-bottom-transition"
              >
                <template v-slot:activator="{ on, attrs }">
                  <v-btn color="amber" v-bind="attrs" v-on="on">
                    <v-icon color="yellow">mdi-currency-usd</v-icon>
                    1000Points
                  </v-btn>
                  <v-divider></v-divider>
                  <v-btn color="amber" v-bind="attrs" v-on="on">
                    <v-icon color="yellow">mdi-currency-usd</v-icon>
                    3000Points
                  </v-btn>
                  <v-btn color="amber" v-bind="attrs" v-on="on">
                    <v-icon color="yellow">mdi-currency-usd</v-icon>
                    5000Points
                  </v-btn>
                </template>
                <v-card>
                  <v-img
                    src="../../assets/img/account/kakaopay.jpg"
                    @click="kakaopay = false"
                  ></v-img>
                </v-card>
              </v-dialog>
              <v-divider></v-divider>

              <v-card-actions>
                <v-spacer></v-spacer>

                <v-btn color="primary" text @click="charge = false">
                  close
                </v-btn>
              </v-card-actions>
            </v-card>
          </v-dialog>
  </div>
</template>

<script>
export default {
  data(){
    return{
      charge: false,
      kakaopay: false,
    }
  }

}
</script>

<style>

</style>