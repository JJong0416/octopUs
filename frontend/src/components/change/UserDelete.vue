<template>
  <div>
    <v-dialog
      v-model="dialog"
      persistent
      max-width="600px"
    >
      <template v-slot:activator="{ on, attrs }">

      <v-btn  v-bind="attrs"
          v-on="on"
          text
          class="pa-0"> 회원 탈퇴하기</v-btn>
      </template>
      <v-card>
        <v-card-title>
          <span class="text-h5">회원 탈퇴하기</span>
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
                 v-model="password"
                :append-icon="show ? 'mdi-eye' : 'mdi-eye-off'"
                :type="show ? 'text' : 'password'"
                label="비밀번호를 입력하세요."
                counter
                @click:append="show = !show"
              ></v-text-field>

              </v-col>
            </v-row>
          </v-container>
        </v-card-text>
        <v-card-actions>
          <v-btn
            color="#143559"
            text
            @click="dialog = false"
          >
            취소하기
          </v-btn>
          <v-spacer></v-spacer>
          <v-btn
            color="#fa183e"
            text
            @click="deleteUser"
          >
            탈퇴하기
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </div>
</template>

<script>
import { mapMutations, mapState } from "vuex";
import cookie from "vue-cookies";
import axios from "axios";
const memberStore = "memberStore";
export default {
  computed: {
    ...mapState(memberStore, ["isLogin", "userInfo"]),
  },
    
    data(){
        return{
            dialog : false,
            password : "",
            show: false,
        }
    },
    methods:{
        ...mapMutations(memberStore, ["SET_IS_LOGIN", "SET_USER_INFO"]),

        deleteUser(){
            const currentPassword = this.password;
            if(confirm("정말로 탈퇴하시겠습니까?")){
                axios.post(`api/user`, { headers: {
                "Access-Control-Allow-Origin": "*",
                "Content-Type": "application/json",
                },
                currentPassword
            })
                .then(() => {
                    this.SET_IS_LOGIN(false);
                    this.SET_USER_INFO(null);
                    cookie.remove("token");
                    sessionStorage.removeItem("token");
                    alert("회원 탈퇴가 완료되었습니다.");
                    this.drawer = false;
                    window.location.replace("http://localhost:8080");
                })
                .catch((error) => {
                    console.log(error);
                    alert("비밀번호가 틀렸습니다. 다시 진행해주세요.")
                })
                 this.dialog = false;
            }else{
                this.dialog = false;
            }
            
        }
      
    }

}
</script>

<style>

</style>