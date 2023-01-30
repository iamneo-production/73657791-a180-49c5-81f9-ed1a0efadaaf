import axios from "axios";
import authHeader from "./auth-header";
import { user_service_URL } from "./URLprovider";
//const API_URL = "http://localhost:8081/api/test/user/";
const API_URL = user_service_URL
class AppointmentProvider{
    setCurrentAppointment=(appointment)=>{
        localStorage.setItem("appointment", JSON.stringify(appointment));
    }
    getCurrentAppointment=()=>{
        return JSON.parse(localStorage.getItem("appointment"));
    }
    removeCurrentAppointment=()=>{
        localStorage.removeItem("appointment");
      }
    createAppointment=(product)=>{
        return axios.post(API_URL+"addAppo",product,{headers:authHeader()});
    }
    getAppointments(){
        return axios.get(API_URL+"viewAppo",{headers:authHeader()});
    }
    getAppointmentById(Id){
        return axios.get(API_URL+"getAppo/"+Id,{headers:authHeader()});
    }
    editAppointmentById(Id,product){
        return axios.put(API_URL+"editAppo/"+Id,product,{headers:authHeader()});
    }
    deleteAppointment(Id){
        return axios.delete(API_URL+"delAppo/"+Id,{headers:authHeader()});
    }

}
export default new AppointmentProvider();