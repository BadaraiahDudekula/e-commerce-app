import React, { useState } from 'react'
import '../styles/UserSignUp.css';
import axios from 'axios';

function UserSignUp() {
    let [name, setname] = useState("")
    let [phone, setphone] = useState("")
    let [email, setemail] = useState("")
    let [gender, setgender] = useState("")
    let [age, setage] = useState("")
    let [password, setpassword] = useState("")

    let data = { name, phone, email, gender, age, password }

    let addUser = (e) => {
        e.preventDefault();
        axios.post(`http://localhost:8080/users`, data)
            .then((res) => {
                console.log(res.data);
                alert("Added")
            })
            .catch((err) => {
                console.log(err);
                alert("Data Not Added")
            })
    }
    return (
        <div className='usersignup'>

            <form action="">
                <label htmlFor="">Name</label>
                <input required value={name} onChange={(e) => { setname(e.target.value) }} type="text" placeholder='Enter the name' />
                <label htmlFor="">Phone No</label>
                <input required value={phone} onChange={(e) => { setphone(e.target.value) }} type="tel" pattern='[0-9]{10}' placeholder='Enter the phone number' />
                <label htmlFor="">Email</label>
                <input required value={email} onChange={(e) => { setemail(e.target.value) }} type="email" placeholder='Enter the email' />
                <label htmlFor="">gender</label>
                <input required value={gender} onChange={(e) => { setgender(e.target.value) }} type="text" placeholder='Enter gender' />
                <label htmlFor="">Age</label>
                <input required value={age} onChange={(e) => { setage(e.target.value) }} type="text" placeholder='Enter the age' />
                <label htmlFor="">Password</label>
                <input required value={password} onChange={(e) => { setpassword(e.target.value) }} type="password" placeholder='Enter the password' />

                <button onClick={addUser} className='btn btn-outline-info'>Submit</button>
            </form>
        </div>
    )
}

export default UserSignUp