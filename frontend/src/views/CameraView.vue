<template>
  <div>
    <web-cam
      ref="webcam"
      :device-id="deviceId"
      width="100%"
      @started="onStarted"
      @stopped="onStopped"
      @error="onError"
      @cameras="onCameras"
      @camera-change="onCameraChange"
    />
    <v-btn type="button" class="btn btn-primary" @click="onCapture">
      찍기
    </v-btn>
    <v-spacer></v-spacer>
    미리보기
    <v-img src="../assets/img/picture.jpg">
      <figure class="figure">
        <v-img :src="img" width="70%"></v-img>
      </figure>
    </v-img>
    <v-btn v-if="img != null" @click="onSave">올리기</v-btn>
    <v-btn v-if="img != null" @click="onRestart">다시 찍기</v-btn>
  </div>
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
