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
          <v-toolbar max-height="20%" color="#ffadad"
            ><div class="text-h6">환급 받기</div></v-toolbar
          >
          <v-dialog v-model="account" width="500">
            <template v-slot:activator="{ on, attrs }">
              <v-container>
                <br>
                <v-btn width="100%" outlined color="#143559" v-bind="attrs" v-on="on">
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
              <v-text-field height="50%" label="계좌번호를 입력해주세요." outlined></v-text-field>
              <v-text-field
                height="50%"
                label="환급 받으실 포인트를 입력해주세요."
                outlined
                v-model="inputdata"
              ></v-text-field>
            </v-container>
          </v-form>

          <v-card-actions class="justify-end">
            <v-btn outlined color="#143559" @click="dialog.value = false">Close</v-btn>
            <v-spacer></v-spacer>
            <v-btn outlined color="#fa183e" @click="refundbtn">환급 받기</v-btn>
          </v-card-actions>
        </v-card>
      </template>
    </v-dialog>
    <!-- 환급완료Dialog -->
    <v-dialog v-model="refundFail" max-width="500px">
      <v-card>
        <v-card-title>
          <h5>환급</h5>
        </v-card-title>
        <v-card-title>
          <h6>잔액이 부족합니다.</h6>
        </v-card-title>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="#fa183e" text @click="refundFail = false"> 확인 </v-btn>
        </v-card-actions>
      </v-card>
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
      refundFail: false,
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
        this.refundFail = true;
        return;
      }
      const pointReq = { userId: "", point: vm.inputdata };
      console.log(pointReq);
      axios
        .patch(`api/user/modification/point/refund`, pointReq)
        .then((response) => {
          console.log(response);

          vm.refunddia = false;
          vm.$emit("success");
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
