import React, { useEffect, useState } from "react";
import serviceCenterProvider from "../services/serviceCenterProvider";
import { Link ,Navigate,useNavigate} from "react-router-dom";
//import { useNavigate } from "react-router-dom";
import AuthService from "../services/auth-service";
const AddCenter = () => {
  const [name, setName] = useState("");
  const [address, setAddress] = useState("");
  const [mailId, setMailId] = useState("");
  const [phone, setPhone] = useState("");
  const [description, setDescription] = useState("");
  const [imgUrl, setImgUrl] = useState("");
  //---------------for update--------------------------
  const[sid,setSid]=useState(-1);
  //---------------------------------------------------
  const navigate = useNavigate();
  const currentUser = AuthService.getCurrentUser();
  if(currentUser.roles.includes("ROLE_ADMIN")){
    console.log("admin is defined")
  }else{
    navigate("/login",{replace:true});
  }
  const delay = (ms) => new Promise((resolve) => setTimeout(resolve, ms));
  const handleClick = async () => {
    await delay(200);

    window.location.reload();
  };

  const title = () => {
    if (sid!=-1) {
      return <h2 className="text-center">UPDATE Service Center</h2>;
    } else {
    return <h2 className="text-center">ADD Service Center</h2>;
    }
  };
  const saveNow=(e)=>{
    e.preventDefault();
    var img_url=imgUrl
    const service = {
      name,address,mailId,phone,description,img_url
    };
    if (sid!=-1) {
      serviceCenterProvider.EditService(sid,service).then((res)=>{
        //console.log(res.data)
        //console.log("update done")
        localStorage.removeItem("service");
        navigate("/viewcenter",{replace:true});
      }).catch((err)=>console.log(err))
    }else{
      serviceCenterProvider.AddService(service).then(res=>{
        //console.log("service added");
        navigate("/viewcenter",{replace:true});
      }).catch(err=>console.log(err))
    }
    
    
    
    //console.log(service)
  }
  useEffect(() => {
  
    //const service=localStorage.getItem("service");
    const service=serviceCenterProvider.getCurrentService()
    //console.log(service.sid);
    if(service){
      serviceCenterProvider.getServiceById(service.sid).then((res)=>{
        setName(res.data.name)
        setPhone(res.data.phone)
        setMailId(res.data.mailId)
        setAddress(res.data.address)
        setDescription(res.data.description)
        setImgUrl(res.data.img_url)
        setSid(res.data.sid)
      }) .catch((err) => console.log(err));
    }
    

  },[])
  return (
    <div>
      <div className="container">
        <div className="row">
          <div className="card w-55 col-6 mx-auto">
            {title()}
            <div className="card-body">
              <form>
                <div className="form-group mb-2">
                  <label className="form-label">Center Name:</label>
                  <input
                    type="text"
                    placeholder="Enter Center Name"
                    name="sname"
                    className="form-control"
                    value={name}
                    onChange={(e) => {
                      setName(e.target.value);
                    }}
                  />
                </div>
                <div className="form-group mb-2">
                  <label className="form-label">Address:</label>
                  <input
                    type="text"
                    placeholder="Enter Address"
                    name="address"
                    className="form-control"
                    value={address}
                    onChange={(e) => setAddress(e.target.value)}
                  />
                </div>
                <div className="form-group mb-2">
                  <label className="form-label">Mail Id:</label>
                  <input
                    type="email"
                    placeholder="Enter Mail Id"
                    name="mailId"
                    className="form-control"
                    value={mailId}
                    onChange={(e) => setMailId(e.target.value)}
                  />
                </div>
                <div className="form-group mb-2">
                  <label className="form-label">Phone No:</label>
                  <input
                    type="tel"
                    placeholder="Enter Phone No"
                    name="phone"
                    className="form-control"
                    value={phone}
                    onChange={(e) => setPhone(e.target.value)}
                    pattern="[0-9]{10}"
                  />
                </div>

                <div className="form-group mb-2">
                  <label className="form-label">Description:</label>
                  <input
                    type="text"
                    placeholder="Enter Description"
                    name="description"
                    className="form-control"
                    value={description}
                    onChange={(e) => setDescription(e.target.value)}
                  />
                </div>
                <div className="form-group mb-2">
                  <label className="form-label">Image Url:</label>
                  <input
                    type="url"
                    placeholder="Enter Image Url"
                    name="imgUrl"
                    className="form-control"
                    value={imgUrl}
                    onChange={(e) => setImgUrl(e.target.value)}
                  />
                </div>
                <button
                  className="btn btn-success"
                  onClick={(e) => saveNow(e)}
                >
                  Submit
                </button>
                <Link
                  to="/viewcenter"
                  className="btn btn-danger ms-1"
                  onClick={() =>{
                    serviceCenterProvider.removeCurrentService();
                  }}
                >
                  Cancel
                </Link>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default AddCenter;
