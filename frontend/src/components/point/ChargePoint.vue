<template>
  <div>
    <v-dialog v-model="charge" width="200">
      <template v-slot:activator="{ on, attrs }">
        <v-btn
          color="yellow"
          v-bind="attrs"
          v-on="on"
          style="padding: 10px 12px 10px 12px; margin-top: 15px"
        >
          충전
        </v-btn>
      </template>

      <v-card>
        <v-card-title class="text-h6">
          <h5>포인트 충전하기</h5>
        </v-card-title>
        <v-dialog
          v-model="kakaopay"
          fullscreen
          hide-overlay
          transition="dialog-bottom-transition"
        >
          <template v-slot:activator="{ on, attrs }">
            <v-card-actions>
              <v-row>
                <v-col>
                  <v-btn
                    class="px-1"
                    color="#fdffb6"
                    v-bind="attrs"
                    v-on="on"
                    @click="getPrice(1000)"
                  >
                    <v-icon color="#143559">mdi-currency-usd</v-icon>
                    1000 Points
                  </v-btn>
                </v-col>
                <v-col>
                  <v-btn
                    class="px-1"
                    color="#fdffb6"
                    v-bind="attrs"
                    v-on="on"
                    @click="getPrice(3000)"
                  >
                    <v-icon color="#143559">mdi-currency-usd</v-icon>
                    3000 Points
                  </v-btn>
                </v-col>
                <v-col>
                  <v-btn
                    class="px-1"
                    color="#fdffb6"
                    v-bind="attrs"
                    v-on="on"
                    @click="getPrice(5000)"
                  >
                    <v-icon color="#143559">mdi-currency-usd</v-icon>
                    5000 Points
                  </v-btn>
                </v-col>
              </v-row>
            </v-card-actions>
          </template>
          <v-card>
            <v-img
              src="../../assets/img/account/kakaopay.jpg"
              @click="[(kakaopay = false), paySuccess()]"
            ></v-img>
          </v-card>
        </v-dialog>
        <v-divider></v-divider>

        <v-card-actions>
          <v-spacer></v-spacer>

          <v-btn color="#fa183e" outlined @click="charge = false"> 취소 </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </div>
</template>

<script>
import axios from "axios";
export default {
  data() {
    return {
      charge: false,
      kakaopay: false,
      price: null,
    };
  },

  created() {
    axios.defaults.headers.common[
      "Authorization"
    ] = `Bearer ${sessionStorage.getItem("token")}`;
  },
  methods: {
    getPrice(price) {
      this.price = price;
      console.log(this.price);
    },
    paySuccess() {
      // 해당 금액 입금 api호출
      const pointReq = { userId: "", point: this.price };
      axios
        .patch(`api/user/modification/point/charge`, pointReq)
        .then((response) => {
          console.log(response);

          this.charge = false;
          this.$emit("success");
          // this.$router.push("Mypage");
        })
        .catch(function (err) {
          console.log(err);
        });
    },
  },
};
</script>

<style></style>
