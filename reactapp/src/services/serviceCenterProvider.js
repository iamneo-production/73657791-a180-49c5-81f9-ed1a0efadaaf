import axios from "axios";
import authHeader from "./auth-header";
const API_URL = "http://localhost:8081/api/test/admin/";
class ServiceCenterProvider{
  setCurrentService=(service)=>{
    localStorage.setItem("service", JSON.stringify(service));
  }
  getCurrentService=()=>{
    return JSON.parse(localStorage.getItem("service"));
  }
    AddService=(service)=>{
      return axios.post(API_URL+"addServ",service,{ headers: authHeader() });
    }
    getServices() {
        return axios.get(API_URL+"viewServ",{ headers: authHeader() });
      }
    getServiceById(Id,service) {
        return axios.get(API_URL+"getServ/"+Id, {headers: authHeader() });
      }
      EditService=(Id,service)=>{
        return axios.put(API_URL+"editServ/"+Id,service,{ headers: authHeader() });
      }
    deleteService(Id){
      return axios.delete(API_URL+"delServ/"+Id,{ headers: authHeader() });
    }
}
export default new ServiceCenterProvider();