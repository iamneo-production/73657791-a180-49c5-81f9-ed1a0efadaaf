
import React from "react";
import { Route,  Navigate } from "react-router-dom";
const ProtectedRoute = ({ auth, elem: element, ...rest }) => {
  return <Route {...rest} render={(props) => {
    if(auth) return <element {...props}/>;
    if(!auth) return <Navigate to={{path:"/",state:{from:props.location}}}/>
  }} />;
};

export default ProtectedRoute;
