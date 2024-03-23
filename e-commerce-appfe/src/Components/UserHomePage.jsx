import React from 'react'
import { Routes, Route } from 'react-router-dom'
import UpdateUser from './UpdateUser'
import UserNavBar from './UserNavBar'
import ViewUser from './ViewUser'
function UserHomePage() {
  return (
    <div className='uhp'>
      
      <UserNavBar/>
      <Routes>
        <Route path="/viewuser" element={<ViewUser/>} />
        <Route path="/updateuser" element={<UpdateUser/>} />
      </Routes>
    </div>
  )
}
export default UserHomePage