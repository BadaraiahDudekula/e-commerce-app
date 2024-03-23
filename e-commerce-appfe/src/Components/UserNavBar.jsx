import React from 'react'
import { Link } from 'react-router-dom'
import Dropdown from 'react-bootstrap/Dropdown';
import AccountCircleIcon from '@mui/icons-material/AccountCircle';
import '../styles/UserNavBar.css';
function UserNavBar() {
  return (
   <nav className="navbar" >
    <div className='logo'>
        <h1>ShoppersCart</h1>
    </div>
    <div className="option">
        <Link className='link1' to="/userhomepage/userview">View User</Link>
    </div>
    <div className="account">
    <Dropdown>
      <Dropdown.Toggle variant="success" id="dropdown-basic">
      <AccountCircleIcon/>  Account
      </Dropdown.Toggle>

      <Dropdown.Menu>
        <Dropdown.Item href="/userhomepage/updateuser">Edit User Account</Dropdown.Item>
        <Dropdown.Item href="/">LogOut</Dropdown.Item>
        
      </Dropdown.Menu>
    </Dropdown>
    </div>
   </nav>
  )
}

export default UserNavBar