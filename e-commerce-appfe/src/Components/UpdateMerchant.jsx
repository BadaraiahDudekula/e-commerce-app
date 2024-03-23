import { useState } from "react"
import axios from "axios"
import { useEffect } from "react"
import '../styles/UpdateMerchant.css'
const UpdateMerchant= () => {
    const [email, setEmail] = useState("")
    const [gst_number, setgst_number] = useState("")
    const [name, setName] = useState("")
    const [phone, setPhone] = useState("")
    const [password, setpassword] = useState("")
    const [id, setId] = useState("")

    useEffect(()=>{
        const user=JSON.parse(localStorage.getItem("merchant"))
        if(user){
            setId(user.id)
            setName(user.name)
            setEmail(user.email)
            setPhone(user.phone)
            setpassword(user.password)
            setgst_number(user.gst_number)
        }
    },[]);
    const updateMerchant =(e)=>{
        e.preventDefault();
        const data={name,email,phone,password,gst_number,id}
        axios.put(`http://localhost:8080/merchants`,data)
        .then((res)=>{
             console.log(res);
             alert("Merchant updated")
 
        })
        .catch((err)=>{
             console.log(err);
             alert("Data Not Added")
 
        })
    }

    return(
        <div className="merchantupdate">
         <form onSubmit={updateMerchant} action="">
           
                <label htmlFor="">Id</label>
                <input required value={id} onChange={(e) => { setId(e.target.value) }} type="text" placeholder='Enter the id' />
                <label htmlFor="">Name</label>
                <input required value={name} onChange={(e) => { setName(e.target.value) }} type="text" placeholder='Enter the name' />
                <label htmlFor="">Email</label>
                <input required value={email} onChange={(e) => { setEmail(e.target.value) }} type="email" placeholder='Enter the email' />
                <label htmlFor="">Password</label>
                <input required value={password} onChange={(e) => { setpassword(e.target.value) }} type="text" placeholder='Enter the password' />
               <label htmlFor="">Phone No</label>
                <input required value={phone} onChange={(e) => { setPhone(e.target.value) }} type="tel" pattern='[0-9]{10}' placeholder='Enter the phone number' />
                <label htmlFor="">gstnumber</label>
                <input required value={gst_number} onChange={(e) => { setgst_number(e.target.value) }} type="text" placeholder='Enter the gst_number' />
                <button onClick={updateMerchant} className='btn btn-outline-info'>Update</button>
            </form>
        </div>
    )
}
export default UpdateMerchant