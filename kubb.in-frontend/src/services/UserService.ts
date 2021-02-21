import axios from "axios";
import authHeader from "./auth-header";

const API_URL = "https://kubb.in:8080/api/";
class UserService {
  deleteAllSubs() {
    return axios.delete(API_URL + "subscriptions", { headers: authHeader() });
  }
  postOneSubscription(data: any) {
    return axios.put(API_URL + "subscription/", data, { headers: authHeader() });
  }

  getSubscriptions() {
    return axios.get(API_URL + "subscriptions", { headers: authHeader() });
  }
}

export default new UserService();
