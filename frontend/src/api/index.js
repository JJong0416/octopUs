import axios from "axios";

// 1. HTTP Request & Response와 관련된 기본설정
const config = {
  baseUrl: "http://localhost:8080/",
};

// 2. 데이터 받아오는 API들 정리
function fetchHome() {
  return axios.get(`${config.baseUrl}/home`);
}

function fetchUser(id) {
  return axios.get(`${config.baseUrl}/users/${id}`);
}

function fetchUserMission(id) {
  return axios.get(`${config.baseUrl}/users/${id}/mission`);
}

function fetchMission() {
  return axios.get(`${config.baseUrl}/mission`);
}

// 3. 데이터를 전송하는 API들 정리

export { fetchHome, fetchUser, fetchMission, fetchUserMission };
