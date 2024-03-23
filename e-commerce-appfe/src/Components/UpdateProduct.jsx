import React, { useEffect, useState } from 'react'
import '../styles/UpdateProduct.css'
import axios from 'axios'
import { useParams } from 'react-router-dom'
function UpdateProduct() {
    let [name,setname] = useState("")
    let [brand,setbrand] = useState("")
    let [category,setcategory] = useState("")
    let [description,setdescription] = useState("")
    let [cost,setcost] = useState("")
    let [image_url,setuimageurl] = useState("")
    let [id,setid] = useState("")

    let param = useParams()
    
    useEffect(()=>{
        axios.get(`http://localhost:8080/products/find-by-id/${param.id}`)
        .then((res)=>{
            console.log(res.data.model);
            setname(res.data.model.name)
            setbrand(res.data.model.brand)
            setcategory(res.data.model.category)
            setdescription(res.data.model.description)
            setcost(res.data.model.cost)
            setuimageurl(res.data.model.image_url)
            setid(res.data.model.id)
        })
        .catch((err)=>{
            console.log(err);
        })
    },[])


    let data = {name,brand,category,description,cost,image_url,id}
    
    let editData = (e) =>{
        e.preventDefault();
        axios.put(`http://localhost:8080/products`,data)
        .then((res)=>{
            console.log(res);
            alert("Product Edited succesfull")
        })
        .catch((err)=>{
            console.log(err);
            alert("Something Went Wrong")
        })
    }
  return (
    <div className='UpdateProduct'>
        <h1>kjhgfdfgh</h1>
        <form onSubmit={editData} action="">
            <label htmlFor="">Name:</label>
            <input value={name} onChange={(e)=>{setname(e.target.value)}} type="text" required placeholder='Enter the name' />
            <label htmlFor="">Brand:</label>
            <input value={brand} onChange={(e)=>{setbrand(e.target.value)}} type="text" required placeholder='Enter the brand' />
            <label htmlFor="">Category:</label>
            <input value={category} onChange={(e)=>{setcategory(e.target.value)}} type="text" required placeholder='Enter the category' />
            <label htmlFor="">Description:</label>
            <input value={description} onChange={(e)=>{setdescription(e.target.value)}} type="text" required placeholder='Enter the propertiy' />
            <label htmlFor="">Cost:</label>
            <input value={cost} onChange={(e)=>{setcost(e.target.value)}} type="text" required placeholder='Enter the cost ' />
            <label htmlFor="">Image URL:</label>
            <input value={image_url} onChange={(e)=>{setuimageurl(e.target.value)}} type="text" required placeholder='Enter the image url' />
            <button className='btn btn-info'>Submit</button>
        </form>
    </div>
  )
}
export default UpdateProduct