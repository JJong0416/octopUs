<template>
  <v-container>
    <!-- <input
      type="file"
      accept="image/*"
      onchange="previewFile()"
      id="capture"
      capture="camera"
    /><br />
    <img id="curImage" height="200" size="" src="" /><br />

    <button onclick="storeImage()">Store Captured Image Offline</button>
    <button onclick="loadImage()">Load Stored Image</button> -->
    <v-btn primary outlined @click="takepicture">사진찍기</v-btn>
    <input
      id="file"
      type="file"
      accept="image/*"
      style="display: none"
      capture
    />
    <v-btn @click="canvas(missionNo)">사진업로드</v-btn>
  </v-container>
</template>

<script>
import axios from "axios";
export default {
  data: () => ({
    missionNo: "",
  }),
  created() {
    var vm = this;
    vm.missionNo = this.$route.params.missionNo;
    axios.defaults.headers.common[
      "Authorization"
    ] = `Bearer ${sessionStorage.getItem("token")}`;
  },
  methods: {
    takepicture() {
      document.getElementById("file").click();
    },
    canvas(missionNo) {
      var input = document.querySelector("input[type=file]");
      var file = input.files[0];
      var reader = new FileReader();
      var data = "";
      let encodedImg = "";
      var vm = this;
      reader.onload = function (e) {
        var dataURL = e.target.result,
          c = document.querySelector("canvas"), // see Example 4
          ctx = c.getContext("2d"),
          img = new Image();

        img.onload = function () {
          c.width = img.width;
          c.height = img.height;
          ctx.drawImage(img, 0, 0);
        };
        console.log(img.src + "dfsafasfasdf");
        img.src = dataURL;
        data = dataURL;
      };
      reader.readAsDataURL(file);
      reader.onloadend = function () {
        encodedImg = reader.result;
        // console.log('Base64 String - ', encodedImg)
        axios
          .post(`../api/mission/${missionNo}/picture`, {
            headers: {
              "Access-Control-Allow-Origin": "*",
              "Content-Type": "application/json",
            },
            encodedImg,
          })
          .then((response) => {
            alert("정상적으로 등록되었습니다.");
            console.log(response);
            vm.$router.push({
              name: "proceeding",
              params: { missionNo: missionNo },
            });
          })
          .catch(() => {
            alert("사진전송실패");
          });
      };
      console.log("data는?? ");
      console.log(data);
      console.log("file은??");
      console.log(file);
      console.log("Base64 String - ", encodedImg);
    },
  },
};
</script>

<style></style>
