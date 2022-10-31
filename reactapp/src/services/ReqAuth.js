import React from 'react'
import{Navigate} from 'react-router-dom'
import AuthService from './auth-service'
export const ReqAuth = ({children}) => {
  const auth=AuthService.getCurrentUser();
  if(!auth.user){
    
     return <Navigate to='/login'/>
      
  }
  
}
