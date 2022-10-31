import axios from "axios";
import authHeader from "./auth-header";
const API_URL = "http://localhost:8081/api/test/user/";
class UserControllerService{
 deleteUser=(Id)=>{
    //localStorage.removeItem("user");
    //console.log(authHeader())
    return axios.delete(API_URL+"delete/"+Id,{ headers: authHeader() });
    //return axios.delete(API_URL+"delete/"+Id,{ headers: authHeader() }).then(r=>console.log("deleted")).catch(e=>console.log("not deleted"));
}
EditUser=(Id,request)=>{
    return axios.put(API_URL+"edit/"+Id,request,{ headers: authHeader() })
}
}
export default new UserControllerService();