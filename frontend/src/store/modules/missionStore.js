import { getMissionInfo, getHot } from "@/api/mission";
const misssionStore = {
  state: {
    missionInfo: null,
    hotmissionInfo: null,
  },
  getters: {},
  mutations: {
    SET_MISSION_INFO: (state, missionInfo) => {
      console.log(missionInfo);
      state.missionInfo = missionInfo;
    },
    SET_HOT_INFO: (state, hotInfo) => {
      console.log(hotInfo);
      state.hotmissionInfo = hotInfo;
    },
  },
  actions: {
    async missionInfo({ commit }) {
      await getMissionInfo(
        (response) => {
          console.log(response);
          console.log("Mission정보 받아옴");
          if (response.status === 200) {
            commit("SET_MISSION_INFO", response.data.missionInfo);
          }
        },
        (error) => {
          console.log(error);
        }
      );
    },
    async hotInfo({ commit }) {
      await getHot(
        (response) => {
          console.log(response);
          console.log("Hot정보 받아옴");
          if (response.status === 200) {
            commit("SET_MISSION_INFO", response.data);
          }
        },
        (error) => {
          console.log(error);
        }
      );
    },
  },
};

export default misssionStore;
