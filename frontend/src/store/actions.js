import { fetchUser } from "../api/index.js";

export default {
  FETCH_USER({ commit }, userId) {
    return fetchUser(userId).then((res) => commit("SET_USER", res.data));
  },
};
