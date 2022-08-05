import { apiInstance } from "./index.js";

const api = apiInstance();

async function getMissionInfo(success, fail) {
  await api
    .get(`api/mission`, {
      headers: {
        "Access-Control-Allow-Origin": "*",
        "Content-Type": "application/json",
      },
    })
    .then(success)
    .catch(fail);
}

async function getHot(success, fail) {
  await api
    .get(`api/mission/new`, {
      headers: {
        "Access-Control-Allow-Origin": "*",
        "Content-Type": "application/json",
      },
    })
    .then(success)
    .catch(fail);
}

export { getMissionInfo, getHot };
