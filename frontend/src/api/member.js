import { apiInstance } from "./index.js";

const api = apiInstance();

async function login(user, success, fail) {
  await api
    .post(`/login/domain`, JSON.stringify(user))
    .then(success)
    .catch(fail);
}

async function findById(userid, success, fail) {
  api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
  await api.get(`/user/info/${userid}`).then(success).catch(fail);
}

// function logout(success, fail)

function deleteUser(userid, success, fail) {
  api.delete(`/user/${userid}`).then(success).catch(fail);
}

function userList(success, fail) {
  api.get(`/user`).then(success).catch(fail);
}

export { login, findById, deleteUser, userList };
