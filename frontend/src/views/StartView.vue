<template>
  <div class="start">
    <v-container>
        <v-row>
            <v-col cols="12">
                <v-img src="../assets/Title.png"  />
            </v-col>
        </v-row>
        <v-row>
            <v-col class="animate__animated animate__lightSpeedInLeft">
                <v-img src="../assets/Logo.png"/>
            </v-col>
        </v-row>
<br><br>
        <!-- 비회원 -->
        <v-row>
                <v-card-actions>
                    <div class="nonejoined">
                        <router-link :to="`/main`">
                            <v-btn
                                color="#fdffb6"
                                block
                                large
                                elevation="6"
                                width="42vh"
                                >비회원으로 계속하기</v-btn>
                        </router-link>
                    </div>
                </v-card-actions>
        </v-row>

        <!-- login -->
        <v-row>
                <v-card-actions>
                    <div class="login">
                        <router-link :to="`/login`">
                            <v-btn
                                color="#ffadad"
                                block
                                large
                                elevation="6"
                                width="42vh"
                                >Login</v-btn>
                        </router-link>
                    </div>
                </v-card-actions>
        </v-row>
    </v-container>




  </div>
</template>

<script>
import { mapState } from "vuex";
import 'animate.css';
const memberStore = "memberStore";
export default {
computed: {
    ...mapState(memberStore, ["isLogin", "userInfo"]),
  },
}
</script>

<style>
.start{
    height: 90vh;
}
.login a {
  text-decoration: none;
  color: black;
}
.login a:hover {
  text-decoration: none;
  color: black;
}
.nonejoined a {
  text-decoration: none;
  color: black;
}
.nonejoined a:hover {
  text-decoration: none;
  color: black;
}
</style>