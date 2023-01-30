import React from 'react'
import AuthService from '../services/auth-service';
import { Navigate } from 'react-router-dom';
export const RequiredAuth = ({children}) => {
    const currentUser=AuthService.getCurrentUser();
    if(!currentUser && !currentUser.roles.includes("ROLE_USER")){
        return <Navigate to="/login"/>
    }
  return children
  
}
