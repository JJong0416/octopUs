import { apiInstance } from "./index.js";

const api = apiInstance();

async function create(missionInfo) {
  await api
    .post(`api/mission`, {
      missionTitle: missionInfo.MissionTitle,
      missionType: missionInfo.MissionType,
      missionPoint: missionInfo.MissionPoint,
      missionLimitPersonnel: missionInfo.MissionLimitPersonnel,
      missionContent: missionInfo.MissionContent,
      missionOpen: missionInfo.MissionOpen,
    })
    .then(() => {
      alert("미션생성완료");
    })
    .catch(() => {
      alert("미션생성실패");
      console.log(missionInfo.MissionTitle);
      console.log(missionInfo.MissionType);
      console.log(missionInfo.MissionPoint);
      console.log(missionInfo.MissionLimitPersonnel);
      console.log(missionInfo.MissionContent);
      console.log(missionInfo.MissionOpen);
    });
}

export { create };
