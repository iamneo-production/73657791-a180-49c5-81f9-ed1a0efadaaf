import React, { useEffect, useState } from "react";
import AuthService from "../services/auth-service";
//import { useNavigate } from "react-router-dom";
import {Navigate,useNavigate} from 'react-router-dom';
import { Link } from "react-router-dom";
import {toast} from 'react-toastify';
import UserControllerService from "../services/user_controller_service";
const Profile = () => {
  //------------------------------------------------------
  //let navigate = useNavigate();
  const currentUser = AuthService.getCurrentUser();
  const navigate=useNavigate();
  if(currentUser){
    console.log("user is defined")
  }else{
    return(<Navigate to="/login" replace={true} />);
  }
  const logOut = () => {
    //console.log("on here")
    AuthService.logout();
  };
  const delay = ms => new Promise(
    resolve => setTimeout(resolve, ms)
  );
  const deleteNow=async()=> {
    UserControllerService.deleteUser(currentUser.id).then(async(res)=>{
      console.log("deleted user");
      logOut();//remove user from localStorage
      //await delay(200);
       //navigate("/login",{replace:true});
       window.location.reload();
     }).catch(err=>console.log(err))
  }
    
  

  //------------------------------------------------------
  return (
    <>
    <div className="container">
      <header className="jumbotron">
        <h3>
          <strong>{currentUser.username}</strong> Profile
        </h3>
      </header>
      <p>
        <strong>Token:</strong> {currentUser.accessToken.substring(0, 20)} ...{" "}
        {currentUser.accessToken.substr(currentUser.accessToken.length - 20)}
      </p>
      <p>
        <strong>Id:</strong> {currentUser.id}
      </p>
      <p>
        <strong>Email:</strong> {currentUser.email}
      </p>
      {/*-------------------------------------------------------------------------*/}
      <p>
        <strong>Mobile Number:</strong> {currentUser.mobilenum}
      </p>
      {/*-------------------------------------------------------------------------*/}
      <strong>Authorities:</strong>
      <ul>
        {currentUser.roles &&
          currentUser.roles.map((role, index) => <li key={index}>{role}</li>)}
      </ul>
    </div>
    <div>
    <Link className=" btn btn-info mx-2" to="/updateProfile" onClick={()=>{ //console.log("Update button")

  }}>Update</Link>
    <button className="btn btn-danger ms-2" onClick={()=>deleteNow()}>Delete</button>
    </div>
    
    </>
  );
};

export default Profile;
