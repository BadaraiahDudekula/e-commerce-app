import React, { useState } from 'react'
import Form from 'react-bootstrap/Form';
import axios from 'axios';
import '../styles/UserLogin.css';
import { Link, useNavigate } from 'react-router-dom';

const UserLogin=()=>{
  let[email,setemail]=useState("")
  let[password,setpassword]=useState("")
  let navigate=useNavigate()
  function verifyUser(e){
    e.preventDefault();
    axios.post(`http://localhost:8080/users/verify?email=${email}&password=${password}`)
    
   .then(res => {
        console.log(res.data);
        navigate('/userhomepage')
        alert("Verified")
      })
    
   .catch(err => {
        console.log(err);
        alert("Not Verified")
      })
  }
  return (
    <div className='UserLogin'>
      <h2 className='head'>User</h2>
      <Form>
        <Form.Group className="mb-3" controlId="formGroupEmail">
          <Form.Label>Email address</Form.Label>
          <Form.Control value={email} onChange={(e)=>{setemail(e.target.value)}} type="email" placeholder="Enter email" />
        </Form.Group>
        <Form.Group className="mb-3" controlId="formGroupPassword">
          <Form.Label>Password</Form.Label>
          <Form.Control value={password} onChange={(e)=>{setpassword(e.target.value)}} type="password" placeholder="Enter password" />
        </Form.Group>
        <Form.Group >
          <button onClick={verifyUser} className='btn btn-success mx-5'>Sign In</button>
          <button className='btn btn-danger mx-5' > <Link className='link1' to="/usersignup" >Sign Up</Link> </button> 
    
        </Form.Group>
      </Form>
    </div>
  )
}

export default UserLogin