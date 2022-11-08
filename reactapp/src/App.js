import React, { useState, useEffect } from "react";
import { Routes, Route, Link } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";
import "./App.css";

import AuthService from "./services/auth-service";

import Login from "./components/Login";
import Register from "./components/Register";
import Home from "./components/Home";
import Profile from "./components/Profile";
import BoardUser from "./components/BoardUser";
import Dashboard from "./components/Dashboard";
import Booking from "./components/Booking";
import AddCenter from "./components/AddCenter";
import ViewCenter from "./components/ViewCenter";
// import BoardModerator from "./components/BoardModerator";
import BoardAdmin from "./components/BoardAdmin";
import UpdateProfile from "./components/UpdateProfile";
import AddAppointment from "./components/AddAppointment";
import EditAppointment from "./components/EditAppointment";

//import { ReqAuth } from "./services/ReqAuth";
//import ProtectedRoute from "./services/ProtectedRoute";

const App = () => {
  // const [showModeratorBoard, setShowModeratorBoard] = useState(false);
  const [showAdminBoard, setShowAdminBoard] = useState(false);
  const [currentUser, setCurrentUser] = useState(undefined);
  const [showUserBoard, setShowUserBoard] = useState(false);

  useEffect(() => {
    const user = AuthService.getCurrentUser();

    if (user) {
      setCurrentUser(user);
      // setShowModeratorBoard(user.roles.includes("ROLE_MODERATOR"));
      setShowAdminBoard(user.roles.includes("ROLE_ADMIN"));

      setShowUserBoard(user.roles.includes("ROLE_USER"));
      // console.log(user.roles.includes("ROLE_ADMIN")," #1")
      // console.log(user.roles.includes("ROLE_USER")," #2")
    }
  }, []);

  const logOut = () => {
    AuthService.logout();
  };

  return (
    <div>
      <nav className="navbar navbar-expand navbar-dark bg-dark">
        <Link to={"/"} className="navbar-brand">
          Wooden Relics
        </Link>
        <div className="navbar-nav mr-auto">
          <li className="nav-item">
            <Link to={"/home"} className="nav-link">
              Home
            </Link>
          </li>

          {/* {showModeratorBoard && (
            <li className="nav-item">
              <Link to={"/mod"} className="nav-link">
                Moderator Board
              </Link>
            </li>
          )} */}

          {showAdminBoard && (
            <li className="nav-item">
              <Link to={"/admin"} className="nav-link">
                Admin Board
              </Link>
            </li>
          )}

          {/* {currentUser && (
            <li className="nav-item">
              <Link to={"/user"} className="nav-link">
                User
              </Link>
            </li>
          )} */}
          {showUserBoard && (
            <li className="nav-item">
              <Link to={"/user"} className="nav-link">
                User
              </Link>
            </li>
          )}

          {/***********************************************************************************/}
          {showUserBoard && (
            <li className="nav-item">
              <Link to={"/dashboard"} className="nav-link">
                Dashboard
              </Link>
            </li>
          )}

          {showUserBoard && (
            <li className="nav-item">
              <Link to={"/mybooking"} className="nav-link">
                MyBooking
              </Link>
            </li>
          )}

          {showAdminBoard && (
            <li className="nav-item">
              <Link to={"/addcenter"} className="nav-link">
                AddCenter
              </Link>
            </li>
          )}
          {showAdminBoard && (
            <li className="nav-item">
              <Link to={"/viewcenter"} className="nav-link">
                ViewCenter
              </Link>
            </li>
          )}
          {/***********************************************************************************/}
        </div>
        {currentUser ? (
          <div className="navbar-nav ml-auto">
            <li className="nav-item">
              <Link to={"/profile"} className="nav-link">
                {currentUser.username}
              </Link>
            </li>
            <li className="nav-item">
              <a href="/login" className="nav-link" onClick={logOut}>
                LogOut
              </a>
            </li>
          </div>
        ) : (
          <div className="navbar-nav ml-auto">
            <li className="nav-item">
              <Link to={"/login"} className="nav-link">
                Login
              </Link>
            </li>

            <li className="nav-item">
              <Link to={"/register"} className="nav-link">
                Sign Up
              </Link>
            </li>
          </div>
        )}
      </nav>

      <div className="container mt-3">
        <Routes>
{/*-------------------------User Controller---------------------------------------*/}
          <Route path="/" element={<Home />} />
          <Route path="/home" element={<Home />} />
          <Route path="/login" element={<Login />} />
          <Route path="/register" element={<Register />} />
          <Route path="/profile" element={<Profile />} />
          <Route path="/updateProfile" element={<UpdateProfile />} />
{/*----------------------------------------------------------------*/}
          <Route path="/user" element={<BoardUser />} />
          <Route path="/dashboard" element={<Dashboard />} />
          <Route path="/mybooking" element={<Booking />} />
          {/* <Route path="/mod" element={<BoardModerator/>} /> */}
{/*----------------------------Service Center------------------------------------*/}
          <Route path="/admin" element={<BoardAdmin />} />
          <Route path="/addcenter" element={<AddCenter />} />
          <Route path="/editcenter" element={<AddCenter />} />
          <Route path="/viewcenter" element={<ViewCenter />} />
{/*----------------------------Appointment------------------------------------*/}
          <Route path="/addAppointment" element={<AddAppointment/>}></Route>
          <Route path="/editAppointment" element={<EditAppointment/>}></Route>
        
        </Routes>
      </div>
    </div>
  );
};

export default App;
