import axios from "axios";
import { auth_URL } from "./URLprovider";
//const API_URL = "http://localhost:8080/api/auth/";
//const API_URL = "http://localhost:8081/api/auth/";
const API_URL = auth_URL;

const register = (username, email,mobilenum, password) => {
 
  return axios.post(API_URL + "signup", {
    username,
    email,
    mobilenum,
    password,
   
  });
};

const login = (username, password) => {
  return axios
    .post(API_URL + "signin", {
      username,
      password,
    })
    .then((response) => {
      if (response.data.accessToken) {
        localStorage.setItem("user", JSON.stringify(response.data));
      }

      return response.data;
    });
};

const logout = () => {
  localStorage.removeItem("user");
};

const getCurrentUser = () => {
  return JSON.parse(localStorage.getItem("user"));
};

const AuthService = {
  register,
  login,
  logout,
  getCurrentUser,
};

export default AuthService;