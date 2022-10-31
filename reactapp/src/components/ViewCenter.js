import React, { useState, useEffect } from "react";
import serviceCenterProvider from "../services/serviceCenterProvider";
import { Link } from "react-router-dom";
const ViewCenter = () => {
  const [services, setServices] = useState([]);
  const delay = (ms) => new Promise((resolve) => setTimeout(resolve, ms));
  const handleClick = async () => {
    await delay(200);
    window.location.reload();
  };
  useEffect(() => {
    getAllServices();
    //console.log(services)
  }, []);
  const getAllServices = () => {
    serviceCenterProvider.getServices().then((res) => {
        setServices(res.data);
        //console.log(res.data);
      })
      .catch((err) => console.log(err));
      //console.log(services)
  };
  const deleteNow=(id)=>{
    serviceCenterProvider.deleteService(id).then(res=>{
      //console.log("deleted")
      getAllServices();
    }).catch(err=>console.log(err));
  }
  return (
    <div className="container">
      <h2 className="text-center">ViewCenter</h2>
      <table className="table table-bordered table-striped">
        <thead>
          <th>Service Name</th>
          <th>Address</th>
          <th>mail-id</th>
          <th>phone no</th>
          <th>description</th>
          <th>image URL</th>
          <th>Actions</th>
        </thead>
        <tbody>
          {services.map((service) => (
            <tr key={service.sid}>
              <td>{service.name}</td>
              <td>{service.address}</td>
              <td>{service.mailId}</td>
              <td>{service.phone}</td>
              <td>{service.description}</td>
              <td><a href={`${service.img_url}`}  target="_blank" rel="noreferrer">{service.img_url.substring(0, 5)} ...{" "}
        {service.img_url.substr(service.img_url.length - 10)}</a></td>
              <td>
                <Link
                  className=" btn btn-info"
                  to={"/editcenter"}
                  onClick={() =>{
                    //handleClick()
                    serviceCenterProvider.setCurrentService(service);
                    //localStorage.setItem("service", JSON.stringify(service));
                    //console.log(JSON.parse(localStorage.getItem("service")))
                  }}
                >
                  Update
                </Link>
                <button
                  className="btn btn-danger ms-2"
                  onClick={() => {
                    deleteNow(service.sid)
                  }}
                >
                  Delete
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default ViewCenter;
