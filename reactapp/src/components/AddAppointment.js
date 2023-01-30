import React, { useEffect, useState } from 'react'
import serviceCenterProvider from '../services/serviceCenterProvider';
import {Link,useNavigate} from "react-router-dom"
import {format} from 'date-fns';
import AppointmentProvider from '../services/AppointmentProvider';
import AuthService from "../services/auth-service";
const AddAppointment = () => {
    const [productName, setProductName] = useState("");
    const [productModelNo, setProductModelNo] = useState("");
    //const [dateOfPurchase, setDateOfPurchase] = useState("");
    const [dOP, setDOP] = useState("");
    const[contactNumber,setContactNumber]=useState("");
    const[problemDescription,setProblemDescription]=useState("");
    const[availableSlots,setAvailableSlots]=useState("");
    const[serviceCenter,setServiceCenter]=useState(serviceCenterProvider.getCurrentService());
    const navigate = useNavigate();
  
    

    function padTo2Digits(num) {
        return num.toString().padStart(2, '0');
      }
      function formatDate(date) {
        return [
          padTo2Digits(date.getDate()),
          padTo2Digits(date.getMonth() + 1),
          date.getFullYear(),
        ].join('/');
      }
    const saveAppointment=(e)=>{
        e.preventDefault();
        //console.log(dOP);
        let date = formatDate(new Date(dOP)).toString();
        let dateOfPurchase=date
        const product={
            productName,
            productModelNo,
            dateOfPurchase,
            contactNumber,
            problemDescription,
            availableSlots,
            serviceCenter
        }
        //console.log(product);
        AppointmentProvider.createAppointment(product).then((res)=>{
            //console.log(res.data)
            navigate("/mybooking",{replace:true});
        }).catch((err)=>console.log(err));
    }
    
  return (
    <div>
         <div className="container">
         <div className="row">
         <div className="card w-55 col-6 mx-auto">
         <h2 className="text-center"> Add Appointment for {serviceCenter.name}</h2>
         <div className="card-body">
         <form>
         <div className="form-group mb-2">
                  <label className="form-label">Product Name:</label>
                  <input
                    type="text"
                    placeholder="Enter Product Name"
                    name="productName"
                    className="form-control"
                    value={productName}
                    onChange={(e) => setProductName(e.target.value)}
                  />
                </div>
                <div className="form-group mb-2">
                  <label className="form-label">Product Model No:</label>
                  <input
                    type="text"
                    placeholder="Enter Product Model No"
                    name="productModelNo"
                    className="form-control"
                    value={productModelNo}
                    onChange={(e) => setProductModelNo(e.target.value)}
                  />
                </div>
                <div className="form-group mb-2">
                  <label className="form-label">Date Of Purchase:</label>
                  <input
                    type="date"
                    placeholder="dd/mm/yyyy"
                    name="dateOfPurchase"
                    className="form-control"
                    
                    value={dOP}
                    onChange={(e) => {
                       
                         setDOP(e.target.value);
                        
                        
                   }}
                  />
                </div>
                <div className="form-group mb-2">
                  <label className="form-label">Contact Number:</label>
                  <input
                    type="tel"
                    placeholder="Enter Contact No"
                    name="contactNumber"
                    className="form-control"
                    value={contactNumber}
                    onChange={(e) => setContactNumber(e.target.value)}
                  />
                </div>
                <div className="form-group mb-2">
                  <label className="form-label">Problem Description:</label>
                  <textarea rows="4" cols="50"
                    placeholder="Describe your product problem here..."
                    name="problemDescription"
                    className="form-control"
                    value={problemDescription}
                    onChange={(e) => {setProblemDescription(e.target.value);
                    }}
                  />
                </div>
                <div className="form-group mb-2">
                  <label className="form-label">Available Slots:</label>
                  <input
                    type="time"
                     name="availableSlots"
                    className="form-control"
                    value={availableSlots}
                    min="10:00" max="18:00"
                    onChange={async (e) => {
                         //console.log("#1 ",e.target.value)
                         setAvailableSlots(await e.target.value);
                         //pause
                    }}
                 />
                  <small>Service hours are 10am to 6pm</small>
                </div>
                <button
                  className="btn btn-success mx-2"
                  onClick={(e) => saveAppointment(e)}
                >
                  Submit
                </button>
                <Link
                  to="/dashboard"
                  className="btn btn-danger ms-1"
                  onClick={() => {}}
                >
                  Cancel
                </Link>
            </form>
            </div>
            </div>
         </div>
     </div>
    </div>
  )
}

export default AddAppointment
