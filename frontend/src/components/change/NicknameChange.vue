<template>
<div>
  <v-form ref="nicknameForm" lazy-validation>
    <v-dialog 
      v-model="nicknameDialog"
      persistent
      max-width="600px"
    >
      <template v-slot:activator="{ on, attrs }">
        <v-btn
          v-bind="attrs"
          v-on="on"
          @click='newNickname = ""'
          icon
        >
        <v-icon>mdi-pencil</v-icon>
        </v-btn>
      </template>

      <v-card>
        <v-card-title>
          <span class="text-h5">Nickname 바꾸기</span>
        </v-card-title>
        <v-card-text>
          <v-container>
            <v-row>
              <v-col
                cols="12"
                sm="6"
                md="4">
                <v-text-field
                    v-model = newNickname
                    :counter="10"
                    :rules="nameRules"
                    label="새 닉네임을 입력해주세요."
                    @change="userNickChk = false">
                </v-text-field>
              <!-- 닉네임 중복검사 추가 -->
                <span>
                    중복검사
                    <v-icon @click="nickcheck">mdi-check-bold</v-icon>
                </span>
               </v-col>
            </v-row>
          </v-container>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-row>
            <v-col class="pr-10 pl-0">
               <v-btn
                    color="#143559"
                    text
                    @click="nicknameDialog = false">
                    닫기
                </v-btn> 
            </v-col>
            <v-col class="pl-10">
               <v-btn
                    color="#fa183e"
                    text
                    @click="changeNickname">
                    저장하기
                </v-btn> 
            </v-col>
          </v-row>
        </v-card-actions>
      </v-card>
    </v-dialog>
</v-form>
</div>
</template>

<script>
import axios from "axios";
export default {
    data(){
        return{
            nicknameDialog : false,
            newNickname: "",
            nameRules: [
            (v) => !!v || "Name is required",
            (v) => (v && v.length <= 10) || "Name must be less than 10 characters",
            (v) => (v && v.length >= 4) || "Name must be at least 4 characters",
            ],
            rules: {
              required: (value) => !!value || "Required.",
              min: (v) => v.length >= 8 || "Min 8 characters",
            },
            userNickChk : false,
            userNickValid : false,
        }
    },
    methods:{
         nickcheck() {
            const validate = this.$refs.nicknameForm.validate();
            if(validate){
            axios
                .get(`api/register/check/nickname/${this.newNickname}`)
                .then(({ data }) => {
                let msg = "중복된 닉네임입니다. 다시 입력해주세요";
                if (data === false) {
                    msg = "사용가능한 닉네임입니다.";
                    this.userNickChk = true;
                    alert(msg);
                } else {
                    this.userNickChk = false;
                    alert(msg);
                }
                })
                .catch((error) => {
                console.log(error);
                alert("닉네임중복체크에 실패했습니다..");
                });
            }else{
                alert("닉네임이 유효하지 않습니다.")
            }
        },
        changeNickname(){
            const userNickname = this.newNickname;
            if(this.userNickChk && this.newNickname.length!=0){
                axios
                .patch(`api/user/modification/nickname`, {
                headers: {
                "Access-Control-Allow-Origin": "*",
                "Content-Type": "application/json",
                },
                userNickname
            })
                .then(() => {
                alert("닉네임 변경에 성공했습니다.")
                window.location.reload();
                })
                .catch((error) => {
                console.log(error);
                alert("닉네임 변경에 실패했습니다.")
                })
                this.nicknameDialog = false;
            // this.$router.push("main");
            }else{
                alert("닉네임이 유효하지 않습니다.")
            }
    },

    }
}
</script>

<style>

</style>