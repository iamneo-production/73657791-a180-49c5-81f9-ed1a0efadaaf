import React, { useEffect,useState } from 'react'
import AppointmentProvider from '../services/AppointmentProvider'
import { Link } from "react-router-dom";
const Booking = () => {
  const[appointments,setAppointments]=useState([])
  const getAllAppointments=()=>{
    AppointmentProvider.getAppointments().then((res)=>{
      setAppointments(res.data);
      //console.log(res.data);
    }).catch((err)=>console.log(err));
  }
  useEffect(()=>{
    getAllAppointments();
  },[]);
  console.log(appointments);
  const deleteNow=(id)=>{
    AppointmentProvider.deleteAppointment(id).then((res)=>{
      getAllAppointments();
    }).catch((err)=>console.log(err));
  }
  return (
    <div className="container">
      <h2 className="text-center">My Bookings</h2>
      <table className="table table-bordered table-striped">
      <thead>
          <th>Service Name</th>
          <th>Product Name</th>
          <th>Booked Slot</th>
          <th>My Contact Number</th>
          <th>Service Phone No</th>
          <th>Actions</th>
        </thead>
        <tbody>
          {
            appointments.map((appointment)=>(
              <tr key={appointment.id}>
                <td>{appointment.serviceCenter.name}</td>
                <td>{appointment.productName}</td>
                <td>{appointment.availableSlots}</td>
                <td>{appointment.contactNumber}</td>
                <td>{appointment.serviceCenter.phone}</td>
                <td>
                  <Link className=" btn btn-primary mx-2" to="/editAppointment" 
                  onClick={()=>{
                    AppointmentProvider.setCurrentAppointment(appointment);
                    //console.log(AppointmentProvider.getCurrentAppointment())
                  }}>
                    Edit
                  </Link>
                  <button
                  className="btn btn-danger ms-2"
                  onClick={() => {
                    deleteNow(appointment.id);
                  }}
                >
                  Delete
                </button>
                </td>
              </tr>
            ))
          }
          </tbody>
       </table>
       </div>

  )
}

export default Booking