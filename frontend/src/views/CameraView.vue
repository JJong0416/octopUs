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
      <input type="file" accept="image/*" capture />
    <v-btn @click="canvas"></v-btn>
    <v-row>
      <v-col
        style="display: flex; justify-content: center; align-items: center"
      >
        <h4>인증 사진 찍기</h4></v-col
      > </v-row
    ><v-row>
      <v-col
        style="display: flex; justify-content: center; align-items: center"
      >
        <h6>미션 인증 방법을 잘 숙지한 후 찍어주세요.</h6>
      </v-col>
    </v-row>
    <v-row>
      <web-cam
        ref="webcam"
        :device-id="deviceId"
        width="100%"
        height="50%"
        @started="onStarted"
        @stopped="onStopped"
        @error="onError"
        @cameras="onCameras"
        @camera-change="onCameraChange"
    /></v-row>
    <v-row>
      <v-col
        style="display: flex; justify-content: center; align-items: center"
      >
        <v-btn
          outlined
          color="#fa183e"
          class="btn btn-primary"
          @click="onCapture"
        >
          찍기
        </v-btn></v-col
      ></v-row
    >

    <br />

    <h5 style="display: flex; justify-content: center; align-items: center">
      미리보기
    </h5>
    <v-row>
      <v-col class="px-0 pt-3">
        <figure class="figure">
          <v-img :src="img" width="100%"></v-img>
        </figure>
      </v-col>
    </v-row>
    <v-row class="px-4">
      <v-btn outlined color="#143559" v-if="img != null" @click="onRestart"
        >다시 찍기</v-btn
      >
      <v-spacer></v-spacer>
      <v-btn outlined color="#fa183e" v-if="img != null" @click="onSave"
        >올리기</v-btn
      >
    </v-row>
  </v-container>
</template>

<script>
import { WebCam } from "vue-web-cam";
import { find, head } from "lodash";
import axios from "axios";
export default {
  components: {
    WebCam,
  },
  data: () => ({
    missionNo: "",
    cameraon: true,
    img: null,
    camera: null,
    deviceId: null,
    devices: [],
  }),
  created() {
    var vm = this;
    vm.missionNo = this.$route.params.missionNo;
    axios.defaults.headers.common[
      "Authorization"
    ] = `Bearer ${sessionStorage.getItem("token")}`;
  },
  computed: {
    device() {
      return find(this.devices, (n) => n.deviceId == this.deviceId);
    },
  },
  watch: {
    camera: function (id) {
      this.deviceId = id;
    },
    devices: function () {
      // Once we have a list select the first one
      let first = head(this.devices);
      if (first) {
        this.camera = first.deviceId;
        this.deviceId = first.deviceId;
      }
    },
  },
  methods: {
    // storeImage() {
    //   var userImage = document.getElementById("capture");

    //   if (userImage) {
    //     localStorage.setItem(
    //       "imgData",
    //       document.getElementById("curImage").src
    //     );
    //   }
    // },

    // loadImage() {
    //   var imageData = localStorage.getItem("imgData");
    //   document.getElementById("curImage").src = imageData;
    // },

    // previewFile() {
    //   var preview = document.querySelector("img");
    //   var file = document.querySelector("input[type=file]").files[0];
    //   var reader = new FileReader();

    //   reader.onloadend = function () {
    //     preview.src = reader.result;
    //   };

    //   if (file) {
    //     reader.readAsDataURL(file);
    //   } else {
    //     preview.src = "";
    //   }
    // },
    init() {
      var takePicture = document.querySelector("#take-picture"),
        showPicture = document.querySelector("#show-picture");

      if (takePicture && showPicture) {
        // 이벤트 설정
        takePicture.onchange = function (event) {
          // 찍은 사진이나 파일에 대한 참조 얻기
          var files = event.target.files,
            file;
          if (files && files.length > 0) {
            file = files[0];
            console.log(
              files[0].toString +
                "================================================================="
            );
            try {
              // window.URL 객체 얻기
              var URL = window.URL || window.webkitURL;

              // ObjectURL 생성
              var imgURL = URL.createObjectURL(file);

              // src에 ObjectURL 지정
              showPicture.src = imgURL;
              console.log("------------------------------------" + imgURL);
              // Revoke ObjectURL
              URL.revokeObjectURL(imgURL);
            } catch (e) {
              try {
                // createObjectURL을 지원하지 않는 경우 대안
                var fileReader = new FileReader();
                fileReader.onload = function (event) {
                  showPicture.src = event.target.result;
                };
                fileReader.readAsDataURL(file);
              } catch (e) {
                //
                var error = document.querySelector("#error");
                if (error) {
                  error.innerHTML =
                    "Neither createObjectURL or FileReader are supported";
                }
              }
            }
          }
        };
      }
    },
    canvas() {
      var input = document.querySelector("input[type=file]");
      var file = input.files[0];
      var reader = new FileReader();
      var data = "";
      let encodedImg ="";
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
      reader.onloadend = function(){
       encodedImg = reader.result;
      // console.log('Base64 String - ', encodedImg)
      }
      console.log("data는?? ");
      console.log(data);
      console.log("file은??");
      console.log(file);
 console.log('Base64 String - ', encodedImg)
      axios
        .post(`../api/mission/${this.missionNo}/picture`, {
          headers: {
            "Access-Control-Allow-Origin": "*",
            "Content-Type": "application/json",
          },
          encodedImg
        })
        .then((response) => {
          alert("정상적으로 등록되었습니다.");
          console.log(response);
        })
        .catch(() => {
          alert("사진전송실패");
        });
    },
    onCapture() {
      this.img = this.$refs.webcam.capture();
    },
    onStarted(stream) {
      console.log("On Started Event", stream);
    },
    onStopped(stream) {
      console.log("On Stopped Event", stream);
    },
    onStop() {
      this.$refs.webcam.stop();
    },
    onStart() {
      this.$refs.webcam.start();
    },
    onError(error) {
      console.log("On Error Event", error);
    },
    onCameras(cameras) {
      this.devices = cameras;
      console.log("On Cameras Event", cameras);
    },
    onCameraChange(deviceId) {
      this.deviceId = deviceId;
      this.camera = deviceId;
      console.log("On Camera Change Event", deviceId);
    },
    onRestart() {
      this.$refs.webcam.stop();
      this.img = null;
      this.$refs.webcam.start();
    },
    onSave() {
      console.log(this.img);
      this.$refs.webcam.stop();
      this.cameraShow = false;
      console.log(this.img);
      const encodedImg = this.img;
      // const api = apiInstance();
      axios
        .post(`../api/mission/${this.missionNo}/picture`, {
          headers: {
            "Access-Control-Allow-Origin": "*",
            "Content-Type": "application/json",
          },
          encodedImg,
        })
        .then((response) => {
          console.log(response);
        });

      alert("정상적으로 등록되었습니다.");
      this.$router.push({
        name: "proceeding",
        params: { missionNo: this.missionNo },
      });
    },
  },
};
</script>

<style></style>
