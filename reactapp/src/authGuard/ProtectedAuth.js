import React from "react";
import AuthService from "../services/auth-service";
import { Navigate ,useNavigate} from "react-router-dom";

export const ProtectedAuth = ({ children }) => {
  const currentUser = AuthService.getCurrentUser();
  const navigate = useNavigate();
    if (!currentUser && !currentUser.roles.includes("ROLE_ADMIN")) {
    return <Navigate to="/login"/>;
  }
  return children;
};
