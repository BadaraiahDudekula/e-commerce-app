import { useState } from "react"
import axios from "axios"
import { useEffect } from "react"
import '../styles/UpdateUser.css'
const UpdateUser = () => {
    const [email, setEmail] = useState("")
    const [password, setPassword] = useState("")
    const [name, setName] = useState("")
    const [phone, setPhone] = useState("")
    const [gender, setGender] = useState("")
    const [age, setAge] = useState("")
    const [id, setId] = useState("")

    useEffect(()=>{
        const user=JSON.parse(localStorage.getItem("User"))
        if(user){
            setId(user.id)
            setName(user.name)
            setEmail(user.email)
            setPhone(user.phone)
            setGender(user.gender)
            setAge(user.age)
            setPassword(user.password)
        }
    },[]);
    const updateUser =(e)=>{
        e.preventDefault();
        const data={name,email,phone,gender,age,password,id}
        axios.put(`http://localhost:8080/User`,data)
        .then((res)=>{
             console.log(res);
             alert("User updated")
 
        })
        .catch((err)=>{
             console.log(err);
             alert("Data Not Added")
 
        })
    }

    return(
        <div className="userupdate">
         <form onSubmit={updateUser} action="">
                <label htmlFor="">Id</label>
                <input required value={id} onChange={(e) => { setId(e.target.value) }} type="text" placeholder='Enter the id' />
                <label htmlFor="">Name</label>
                <input required value={name} onChange={(e) => { setName(e.target.value) }} type="text" placeholder='Enter the name' />
                <label htmlFor="">Email</label>
                <input required value={email} onChange={(e) => { setEmail(e.target.value) }} type="email" placeholder='Enter the email' />
                <label htmlFor="">Gender</label>
                <input required value={gender} onChange={(e) => { setGender(e.target.value) }} type="text" placeholder='Enter the GST number' />
                <label htmlFor="">Age</label>
                <input required value={age} onChange={(e) => { setAge(e.target.value) }} type="text" placeholder='Enter the age' />
                <label htmlFor="">Phone No</label>
                <input required value={phone} onChange={(e) => { setPhone(e.target.value) }} type="tel" pattern='[0-9]{10}' placeholder='Enter the phone number' />
                <label htmlFor="">Password</label>
                <input required value={password} onChange={(e) => { setPassword(e.target.value) }} type="text" placeholder='Enter the password' />
                <button onClick={updateUser} className='btn btn-outline-info'>Update</button>
            </form>
        </div>
    )
}
export default UpdateUser