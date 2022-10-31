import React, { useEffect, useState } from "react";
import AuthService from "../services/auth-service";
import user_controller_service from "../services/user_controller_service";
import { Navigate, useNavigate } from "react-router-dom";
const UpdateProfile = () => {
  const currentUser = AuthService.getCurrentUser();
  const navigate = useNavigate();
  const [username, setUsername] = useState("");
  const [email, setEmail] = useState("");
  const [mobilenum, setMobilenum] = useState("");
  if (currentUser) {
    console.log("user is defined");
  } else {
    return <Navigate to="/login" replace={true} />;
  }

 
  const logOut = () => {
    //console.log("on here")
    AuthService.logout();
  };
  const saveNow=(e)=>{
    e.preventDefault();

    var request={username,
        email,mobilenum};
    user_controller_service.EditUser(currentUser.id,request).then((res)=>{
        console.log("updated user");
        logOut();//remove user from localStorage
        //navigate("/login",{replace:true});
        window.location.reload();
    }).catch((err)=>console.log(err));
    
  }
  return (
    <div>
      <div className="container">
        <div className="row">
          <div className="card w-55 col-6 mx-auto">
            <h2 className="text-center">Edit Profile</h2>;
            <div className="card-body">
              <form>
                <div className="form-group mb-2">
                  <label className="form-label">User Name:</label>
                  <input
                    type="text"
                    placeholder="Enter User Name"
                    name="username"
                    className="form-control"
                    value={username}
                    onChange={(e) => {
                      setUsername(e.target.value);
                    }}
                  />
                </div>
                <div className="form-group mb-2">
                  <label className="form-label">Email:</label>
                  <input
                    type="email"
                    placeholder="Enter Email-ID"
                    name="email"
                    className="form-control"
                    value={email}
                    onChange={(e) => {
                      setEmail(e.target.value);
                    }}
                  />
                </div>
                <div className="form-group mb-2">
                  <label className="form-label">Mobile No:</label>
                  <input
                  //pause
                    type="tel"
                    placeholder="Enter User Name"
                    name="username"
                    className="form-control"
                    value={mobilenum}
                    onChange={(e) => {
                      setMobilenum(e.target.value);
                    }}
                    pattern="[0-9]{10}"
                    size="10"
                  />
                </div>
                <button
                  className="btn btn-success"
                  onClick={(e) => {saveNow(e)}}
                >
                  Submit
                </button>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default UpdateProfile;
