<template>
  <div>
    <v-dialog
      hide-overlay
      transtion="dialog-bottom-transition"
      scrollable
      v-model="refunddia"
    >
      <template v-slot:activator="{ on, attrs }">
        <v-btn
          color="#D5D5D5"
          v-bind="attrs"
          v-on="on"
          style="padding: 10px 12px 10px 12px; margin-top: 15px"
          >환급</v-btn
        >
      </template>
      <template v-slot:default="dialog">
        <v-card>
          <v-toolbar max-height="100px" color="primary" dark
            ><div class="text-h4">보내기</div></v-toolbar
          >
          <v-dialog v-model="account" width="500">
            <template v-slot:activator="{ on, attrs }">
              <v-container>
                <v-btn width="300" outlined v-bind="attrs" v-on="on">
                  은행 선택
                </v-btn>
              </v-container>
            </template>

            <v-card>
              <v-img
                @click="account = false"
                src="../../assets/img/account/bank.jpg"
              ></v-img>
              <v-img
                @click="account = false"
                src="../../assets/img/account/bank2.jpg"
              ></v-img>
            </v-card>
          </v-dialog>
          <v-form>
            <v-container>
              <v-text-field label="계좌번호 입력" outlined></v-text-field>
              <v-text-field
                label="환급 포인트 입력"
                outlined
                v-model="inputdata"
              ></v-text-field>
            </v-container>
          </v-form>

          <v-card-actions class="justify-end">
            <v-btn text @click="refundbtn">환급하기</v-btn>
            <v-btn text @click="dialog.value = false">Close</v-btn>
          </v-card-actions>
        </v-card>
      </template>
    </v-dialog>
  </div>
</template>

<script>
import axios from "axios";
export default {
  props: ["point"],
  data() {
    return {
      account: false,
      inputdata: null,
      refunddia: false,
    };
  },
  created() {
    axios.defaults.headers.common[
      "Authorization"
    ] = `Bearer ${sessionStorage.getItem("token")}`;
  },
  methods: {
    refundbtn() {
      var vm = this;
      if (vm.point < vm.inputdata) {
        alert("포인트가 부족합니다.");
        return;
      }
      const pointReq = { userId: "", point: vm.inputdata };
      console.log(pointReq);
      axios
        .patch(`api/user/modification/point/refund`, pointReq)
        .then((response) => {
          console.log(response);
          alert(vm.inputdata + "point 환급 성공.");
          vm.refunddia = false;
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
