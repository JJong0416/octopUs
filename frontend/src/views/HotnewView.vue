<template>
  <div class="wrapper">
    <header-view></header-view>
    <v-card>
<br>

    <v-bottom-navigation
      style="box-shadow: none !important;  height : 100%"
    
      color="pink darken-1"
      grow
    >
      <v-btn text @click="clickevent('Hot')" style="display:block; height : 100%">
        <span>HOT</span>

        <v-img  :src="require(`../assets/img/Hot.png`)"   style="height:auto;"></v-img>
      </v-btn>

      <v-btn text @click="clickevent('New')" style="display:block;height : 100%">
        <span>NEW</span>

         <v-img  :src="require(`../assets/img/New.png`)"  style="height:auto;"></v-img>
      </v-btn>

      
    </v-bottom-navigation>
<v-divider></v-divider>
    <v-divider></v-divider>
<br>


<div class="wrapper" v-if="hotnewSelected =='Hot'">
      <div class="card" v-for="(hotmission, index) in hotmissions" :key="index">
        <div v-if="`${hotmissions.length}` == 0">
          <v-card-text
        style="display: flex; justify-content: center; align-items: center"
      >
     현재 모집 중인 미션이 없습니다.
      </v-card-text>
        </div>
        <div v-else>
          <v-card class="mx-auto" max-width="344" outlined>
                      <v-list-item three-line>
                        <v-list-item-content>
                          <div class="text-overline mb-4">
                            {{ hotmission.missionNo }}
                          </div>
                          <v-list-item-title class="text-h5 mb-1">
                            {{ hotmission.missionTitle }}
                          </v-list-item-title>
                          <v-list-item-subtitle
                            >{{ hotmission.missionContent }}
                          </v-list-item-subtitle>
                        </v-list-item-content>
                  <v-list-item-avatar tile size="80">
                    <v-img
                      :src="
                        require(`../assets/img/Ocsoon/Pet/${hotavatars[index][3]}.png`)
                      "
                    >
                      <v-img
                        :src="
                          require(`../assets/img/Ocsoon/Character/${hotavatars[index][0]}.png`)
                        "
                      >
                        <v-img
                          :src="
                            require(`../assets/img/Ocsoon/Face/${hotavatars[index][1]}.png`)
                          "
                        >
                          <v-img
                            :src="
                              require(`../assets/img/Ocsoon/Hat/${hotavatars[index][2]}.png`)
                            "
                          ></v-img
                        ></v-img>
                      </v-img> </v-img
                  ></v-list-item-avatar>
                </v-list-item>

                <v-card-actions>
                  <v-btn
                    outlined
                    rounded
                    text
                    color="secondary"
                    @click="mvdetail(hotmission)"
                  >
                    자세히보기
                  </v-btn>
                </v-card-actions>
              </v-card>
        </div>
        

    
        <br />
        <!-- <a v-bind:href="post.link" target="_blank">
        <img v-bind:src="post.img"/>
        <small>posted by: {{ post.author }}</small> 
        {{ post.title }}n
      </a> -->
      </div>
      
    </div>

<div class="wrapper" v-if="hotnewSelected =='New'">
      <div class="card" v-for="(newmission, index) in newmissions" :key="index">
        <div v-if="`${newmissions.length}` == 0">
          <v-card-text
        style="display: flex; justify-content: center; align-items: center"
      >
      검색 조건에 맞는 미션이 없습니다.
      </v-card-text>
        </div>
        <div v-else>
          <v-card class="mx-auto" max-width="344" outlined>
                      <v-list-item three-line>
                        <v-list-item-content>
                          <div class="text-overline mb-4">
                            {{ newmission.missionNo }}
                          </div>
                          <v-list-item-title class="text-h5 mb-1">
                            {{ newmission.missionTitle }}
                          </v-list-item-title>
                          <v-list-item-subtitle
                            >{{ newmission.missionContent }}
                          </v-list-item-subtitle>
                        </v-list-item-content>
                  <v-list-item-avatar tile size="80">
                    <v-img
                      :src="
                        require(`../assets/img/Ocsoon/Pet/${newavatars[index][3]}.png`)
                      "
                    >
                      <v-img
                        :src="
                          require(`../assets/img/Ocsoon/Character/${newavatars[index][0]}.png`)
                        "
                      >
                        <v-img
                          :src="
                            require(`../assets/img/Ocsoon/Face/${newavatars[index][1]}.png`)
                          "
                        >
                          <v-img
                            :src="
                              require(`../assets/img/Ocsoon/Hat/${newavatars[index][2]}.png`)
                            "
                          ></v-img
                        ></v-img>
                      </v-img> </v-img
                  ></v-list-item-avatar>
                </v-list-item>

                <v-card-actions>
                  <v-btn
                    outlined
                    rounded
                    text
                    color="secondary"
                    @click="mvdetail(newmission)"
                  >
                    자세히보기
                  </v-btn>
                </v-card-actions>
              </v-card>
        </div>
        

    
        <br />
        <!-- <a v-bind:href="post.link" target="_blank">
        <img v-bind:src="post.img"/>
        <small>posted by: {{ post.author }}</small> 
        {{ post.title }}n
      </a> -->
      </div>
      
    </div>


     
    </v-card>
    <footer-view></footer-view>
  </div>
</template>

<script>
import axios from "axios";
import HeaderView from "../components/common/HeaderView.vue";
import FooterView from "../components/common/FooterView.vue";
export default {
  components: {
    HeaderView,
    FooterView,
  },
  data() {
    return {
      tab: null,
      hotmissions: [],
      newmissions: [],
      hotavatars: [],
      newavatars: [],
      hotnewSelected : 'Hot',
      headers: [
        {
          text: "missionTitle",
          align: "start",
          sortable: false,
          value: "missionTitle",
        },
        { text: "missionCode", value: "missionCode" },
        { text: "missionNo", value: "missionNo" },
      ],
    };
  },
  methods: {
    mvdetail(mission) {
      this.$router.push({
        name: "before",
        params: { missionNo: mission.missionNo },
      });
    },
    clickevent(value){
      this.hotnewSelected = value;
      console.log(this.hotnewSelected);
    }
  },

  created() {
    var vm = this;
    axios.defaults.headers.common[
      "Authorization"
    ] = `Bearer ${sessionStorage.getItem("token")}`;
    axios
      .get(`api/mission/new`, {
        headers: {
          "Access-Control-Allow-Origin": "*",
          "Content-Type": "application/json",
        },
      })
      .then(function (response) {
        console.log(response);

        console.log(response.data[0]);
        vm.newmissions = response.data;
        for (let index = 0; index < vm.newmissions.length; index++) {
          const newmission = vm.newmissions[index];
          var list = newmission.missionLeaderAvatar.split(", ");
          var list2 = [];
          list2[0] = parseInt(list[0]);
          list2[1] = parseInt(list[1]);
          list2[2] = parseInt(list[2]);
          list2[3] = parseInt(list[3]);
          if(list2[2] == 0){
            list2[2] = "0_nothing"
          }
          if(list2[3] == 0){
            list2[3] = "0_nothing"
          }
          vm.newavatars[index] = list2;
        }
        console.log(vm.newmissions);
        console.log(vm.newavatars);
      })
      .catch(function (err) {
        console.log(err);
      });
    axios
      .get(`api/mission/hot`, {
        headers: {
          "Access-Control-Allow-Origin": "*",
          "Content-Type": "application/json",
        },
      })
      .then(function (response) {
        console.log(response);

        console.log(response.data[0]);
        vm.hotmissions = response.data;
        for (let index = 0; index < vm.hotmissions.length; index++) {
          const hotmission = vm.hotmissions[index];
          var list = hotmission.missionLeaderAvatar.split(", ");
          var list2 = [];
          list2[0] = parseInt(list[0]);
          list2[1] = parseInt(list[1]);
          list2[2] = parseInt(list[2]);
          list2[3] = parseInt(list[3]);
    if(list2[2] == 0){
            list2[2] = "0_nothing"
          }
          if(list2[3] == 0){
            list2[3] = "0_nothing"
          }
          vm.hotavatars[index] = list2;
        }
        console.log(vm.hotmissions);
      })
      .catch(function (err) {
        console.log(err);
      });
  
  },
};
</script>

<style>
.wrapper{
  height: 100%;
}
</style>
