import axios from "axios";
import authHeader from "./auth-header";

const API_URL = "https://localhost:8080/api/";
class UserService {
  editOneSubscription(
    id: string,
    subscription: {
      name: string;
      link: string;
      price: string;
      currency: string;
      regularity: string;
      nextRenewal: string;
      autoRenewal: string;
    }
  ) {
    return axios.patch(API_URL + "subscription" + "/" + id, subscription, {
      headers: authHeader(),
    });
  }

  getOneSub(subid:string){
    return axios.get(API_URL + 'subscription' + '/' + subid,{headers: authHeader()})
  }
  deleteOneSub(subid: any) {
    return axios.delete(API_URL + "subscription" + "/" + subid, {
      headers: authHeader(),
    });
  }
  deleteAllSubs() {
    return axios.delete(API_URL + "subscriptions", { headers: authHeader() });
  }
  postOneSubscription(data: any) {
    return axios.put(API_URL + "subscription/", data, {
      headers: authHeader(),
    });
  }

  getSubscriptions() {
    return axios.get(API_URL + "subscriptions", { headers: authHeader() });
  }
}

export default new UserService();
