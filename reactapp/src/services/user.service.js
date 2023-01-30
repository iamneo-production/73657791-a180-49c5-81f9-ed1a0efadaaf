import axios from "axios";
import authHeader from "./auth-header";
import { service_URL } from "./URLprovider";
//const API_URL = "http://localhost:8080/api/test/";
//const API_URL = "http://localhost:8081/api/test/";
const API_URL=service_URL
const getPublicContent = () => {
  return axios.get(API_URL + "all");
};

const getUserBoard = () => {
  return axios.get(API_URL + "user", { headers: authHeader() });
};

// const getModeratorBoard = () => {
//   return axios.get(API_URL + "mod", { headers: authHeader() });
// };

const getAdminBoard = () => {
  return axios.get(API_URL + "admin", { headers: authHeader() });
};

const UserService = {
  getPublicContent,
  getUserBoard,
  //getModeratorBoard,
  getAdminBoard,
};

export default UserService;