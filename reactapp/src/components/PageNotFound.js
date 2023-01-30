import React, { useEffect } from 'react'

const PageNotFound = () => {
    useEffect(()=>{
        console.log("page not found");
    })
  return (
    <div>
        <h1>404 Error Page</h1>
        <h2>PageNotFound</h2></div>
  )
}

export default PageNotFound