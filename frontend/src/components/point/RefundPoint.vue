<template>
  <div>
    <v-dialog
            hide-overlay
            transtion="dialog-bottom-transition"
            scrollable
          >
            <template v-slot:activator="{ on, attrs }">
              <v-btn color="#D5D5D5" v-bind="attrs" v-on="on" style="padding : 10px 12px 10px 12px; margin-top : 15px">환급</v-btn>
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
                  </v-container>
                </v-form>

                <v-card-actions class="justify-end">
                  <v-btn text @click="dialog.value = false">Close</v-btn>
                </v-card-actions>
              </v-card>
            </template>
          </v-dialog>
  </div>
</template>

<script>
export default {
  data(){
    return{
      account: false,
    }
  }
}
</script>

<style>

</style>