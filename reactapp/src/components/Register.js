import React, { useState, useRef } from "react";
import Form from "react-validation/build/form";
import Input from "react-validation/build/input";
import CheckButton from "react-validation/build/button";
import { isEmail } from "validator";

import AuthService from "../services/auth-service";

const required = (value) => {
  if (!value) {
    return (
      <div className="alert alert-danger" role="alert">
        This field is required!
      </div>
    );
  }
};

const validEmail = (value) => {
  if (!isEmail(value)) {
    return (
      <div className="alert alert-danger" role="alert">
        This is not a valid email.
      </div>
    );
  }
};

const vusername = (value) => {
  if (value.length < 3 || value.length > 20) {
    return (
      <div className="alert alert-danger" role="alert">
        The username must be between 3 and 20 characters.
      </div>
    );
  }
};
{
  /*------------------------------------------------------------------------ */
}
const vmobilenum = (value) => {
  if (!(value.length >= 10 && value.length < 14)) {
    return (
      <div className="alert alert-danger" role="alert">
        This is not a valid mobilenum.
      </div>
    );
  }
};

// const vrole = (value) => {
//   if (!(value==="admin"||value==="user")) {
//     return (
//       <div className="alert alert-danger" role="alert">
//         Please enter a valid role.
//       </div>
//     );
//   }
// };
{
  /*------------------------------------------------------------------------ */
}
const vpassword = (value) => {
  if (value.length < 6 || value.length > 40) {
    return (
      <div className="alert alert-danger" role="alert">
        The password must be between 6 and 40 characters.
      </div>
    );
  }
};


const Register = () => {
  const form = useRef();
  const checkBtn = useRef();

  const [username, setUsername] = useState("");
  const [email, setEmail] = useState("");
  { /*------------------------------------------------------------------------ */}
  const [mobilenum, setMobilenum] = useState("");
  //  const [role, setRole] = useState("");
  //  const [assignRole,setAssignRole]=useState([]);
  {/*------------------------------------------------------------------------ */}
  const [password, setPassword] = useState("");
  const [successful, setSuccessful] = useState(false);
  const [message, setMessage] = useState("");

  const onChangeUsername = (e) => {
    const username = e.target.value;
    setUsername(username);
  };

  const onChangeEmail = (e) => {
    const email = e.target.value;
    setEmail(email);
  };
  {/*------------------------------------------------------------------------ */}
  const onChangeMobilenum = (e) => {
    const mobilenum = e.target.value;
    setMobilenum(mobilenum);
  };
  // const onChangeRole = (e) => {
  //   const role = e.target.value;
  //   setRole(role);
  //   //assignRole=[role];
  //   setAssignRole(role);
  //   console.log(assignRole);
    
  // };
  
  {
    /*------------------------------------------------------------------------ */
  }
  const onChangePassword = (e) => {
    const password = e.target.value;
    setPassword(password);
  };

  const handleRegister = (e) => {
    e.preventDefault();

    setMessage("");
    setSuccessful(false);

    form.current.validateAll();

    if (checkBtn.current.context._errors.length === 0) {
      //AuthService.register(username, email, password).then(
        AuthService.register(username, email,mobilenum, password).then(
        (response) => {
          setMessage(response.data.message);
          setSuccessful(true);
        },
        (error) => {
          const resMessage =
            (error.response &&
              error.response.data &&
              error.response.data.message) ||
            error.message ||
            error.toString();

          setMessage(resMessage);
          setSuccessful(false);
        }
      );
    }
  };

  return (
    <div className="col-md-12">
      <div className="card card-container">
        <img
          src="//ssl.gstatic.com/accounts/ui/avatar_2x.png"
          alt="profile-img"
          className="profile-img-card"
        />

        <Form onSubmit={handleRegister} ref={form}>
          {!successful && (
            <div>
              <div className="form-group">
                <label htmlFor="username">Username</label>
                <Input
                  type="text"
                  className="form-control"
                  name="username"
                  value={username}
                  onChange={onChangeUsername}
                  validations={[required, vusername]}
                />
              </div>

              <div className="form-group">
                <label htmlFor="email">Email</label>
                <Input
                  type="text"
                  className="form-control"
                  name="email"
                  value={email}
                  onChange={onChangeEmail}
                  validations={[required, validEmail]}
                />
              </div>
              {/*------------------------------------------------------------------------ */}
              <div className="form-group">
                <label htmlFor="mobilenumber">Mobile Number</label>
                <Input
                  type="text"
                  className="form-control"
                  name="mobilenumber"
                  value={mobilenum}
                  onChange={onChangeMobilenum}
                  validations={[required, vmobilenum]}
                />
              </div>

              {/* <div className="form-group">
                <label htmlFor="roles">Role</label>
                <Input
                  type="text"
                  className="form-control"
                  name="role"
                  value={role}
                  onChange={onChangeRole}
                  validations={[required, vrole]}
                />
              </div> */}
              {/*------------------------------------------------------------------------ */}
              <div className="form-group">
                <label htmlFor="password">Password</label>
                <Input
                  type="password"
                  className="form-control"
                  name="password"
                  value={password}
                  onChange={onChangePassword}
                  validations={[required, vpassword]}
                />
              </div>

              <div className="form-group">
                <button className="btn btn-primary btn-block">Sign Up</button>
              </div>
            </div>
          )}

          {message && (
            <div className="form-group">
              <div
                className={
                  successful ? "alert alert-success" : "alert alert-danger"
                }
                role="alert"
              >
                {message}
              </div>
            </div>
          )}
          <CheckButton style={{ display: "none" }} ref={checkBtn} />
        </Form>
      </div>
    </div>
  );
};

export default Register;
