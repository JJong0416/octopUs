<template>
  <div>
    <v-dialog
      v-model="passwordDialog"
      persistent
      max-width="600px"
    >
      <template v-slot:activator="{ on, attrs }">

        <button  v-bind="attrs"
          v-on="on"
          text
          class="pa-0" style="color : #1C69C1">비밀번호 수정 ></button>
      </template>
      <v-card>
        <v-card-title class="pl-8 pt-5">
          <span class="text-h5">비밀번호 변경하기</span>
        </v-card-title>
        <v-card-text>
          <v-container>
            <v-row>
              <v-col
                cols="12"
                sm="6"
                md="4"
              >
              <v-text-field
                 v-model="currentPassword"
                :append-icon="show1 ? 'mdi-eye' : 'mdi-eye-off'"
                :rules="[rules.required]"
                :type="show1 ? 'text' : 'password'"
                label="현재 비밀번호 입력"
                
                counter
                @click:append="show1 = !show1"
              ></v-text-field>

              <v-text-field
                 v-model="newPassword"
                :append-icon="show2 ? 'mdi-eye' : 'mdi-eye-off'"
                :rules="[rules.required, rules.min]"
                :type="show2 ? 'text' : 'password'"
                label="새로운 비밀번호 입력"
                hint="최소 8글자 이상으로 설정해주세요."
                counter
                @click:append="show2 = !show2"
              ></v-text-field>

              <v-text-field
                 v-model="chkPassword"
                :append-icon="show3 ? 'mdi-eye' : 'mdi-eye-off'"
              :rules="[rules.required, rules.min]"
                :type="show3 ? 'text' : 'password'"
                label="새로운 비밀번호 확인"
                hint="최소 8글자 이상으로 설정해주세요."
                counter
                @click:append="show3 = !show3"
              ></v-text-field>

              <h6 v-if="sameChk(chkPassword)" class="mb-5 teal--text accent-3">
                
              </h6>
              <h6 v-else class="mb-5 red--text lighten-2">
                비밀번호가 같지 않습니다. 다시 한 번 확인해주세요.
              </h6>
               </v-col>
            </v-row>
          </v-container>
        </v-card-text>
        <v-card-actions>
          <v-btn
            color="#143559"
            text
            @click="passwordDialog = false"
          >
            취소
          </v-btn>
          <v-spacer></v-spacer>
          <v-btn
            color="#fa183e"
            text
            @click="changePassword"
          >
            변경하기
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </div>
</template>

<script>
import axios from "axios";
export default {
    data(){
        return{
            passwordDialog : false,
            currentPassword : "",
            newPassword : "",
            rules: {
                required: (value) => !!value || "필수 항목입니다.",
                min: (v) => v.length >= 8 || "최소 8글자 이상으로 설정해주세요.",
            },
            show1: false,
            show2: false,
            show3: false,
            chkPassword: "",
            passwordValid :"",
        }
    },
    methods:{
        sameChk(password) {
            if (this.newPassword == password) return true;
            else {
                this.passwordValid = false;
                return false;
            }
        },
        changePassword(){
            if(this.newPassword === this.chkPassword){
                const UserUpdatePasswordReq = {
                currentPassword : this.currentPassword,
                newPassword : this.newPassword
                }
                axios
                .patch(`api/user/modification/password`, UserUpdatePasswordReq
                )
                .then(() => {
                    alert("비밀번호 변경에 성공했습니다.")
                
                })
                .catch((error) => {
                    console.log(error);
                    alert("비밀번호 변경에 실패했습니다.")
                })
                this.passwordDialog = false;
                this.$router.push("mypage");
            }else{
                alert("새로운 비밀번호가 일치하지 않습니다.")
            }
        },
    }

}
</script>

<style>

</style>