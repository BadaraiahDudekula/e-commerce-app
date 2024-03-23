import React, { useEffect, useState } from 'react'
import axios from 'axios'
import Dropdown from 'react-bootstrap/Dropdown';
import EditIcon from '@mui/icons-material/Edit';
import DeleteForeverIcon from '@mui/icons-material/DeleteForever';
import '../styles/ProductView.css'
import { useNavigate } from 'react-router-dom';

function ProductView() {
    let navigate = useNavigate()
    let [item, setitem] = useState([])
    let admin = JSON.parse(localStorage.getItem("Merchant"))
    // let [name,setname]=useState("")
    useEffect(() => {
        axios.get(`http://localhost:8080/products/find-by-merchant-id/${admin.id}`)
            .then((res) => {
                console.log(res.data.model);
                setitem(res.data.model)
            })
            .catch((err) => {
                console.log(err);
            })
    }, [])

    let removeitem = (name, id) => {
        axios.delete(`http://localhost:8080/products/${id}`)
            .then((res) => {
                console.log(res);
                alert(`${name} Product Deleted succesfully`)
            })
            .catch((err) => {
                console.log(err);
            })

    }

    let searchBybrand = (brand) => {
        axios.get(`http://localhost:8080/products/find-by-brand/${brand}`)
            .then((res) => {
                console.log(res.data.model);
                setitem(res.data.model)
            })
            .catch((err) => {
                console.log(err);
            })
    }



    let searchByCategory = (category) => {
        axios.get(`http://localhost:8080/products/find-by-category/${category}`)
            .then((res) => {
                console.log(res.data.model);
                setitem(res.data.model)
            })
            .catch((err) => {
                console.log(err);
            })
    }

    let editData = (id) => {
        navigate(`/merchanthomepage/updateproduct/${id}`)
    }
    return (

        <div className='disp'>

            {item.map((x) => {
                return (
                    <div className="search">
                        <div className="brand">
                            <Dropdown>
                                <Dropdown.Toggle variant="success" id="dropdown-basic">
                                    Search
                                </Dropdown.Toggle>

                                <Dropdown.Menu>
                                    <Dropdown.Item onClick={() => { searchBybrand(x.brand) }}>By Brand</Dropdown.Item>
                                    <Dropdown.Item onClick={() => { searchByCategory(x.category) }}>By Category</Dropdown.Item>
                                </Dropdown.Menu>
                            </Dropdown>
                        </div>
                        <div className="productview">
                            <div className="image">
                                <span id='category'>{x.category}</span>
                                <img src={x.image_url} alt="" />
                            </div>
                            <div className="desc">
                                <h4 id='name'>{x.name} || {x.brand}</h4>
                                <span id='cost'><sup><b>₹</b></sup>{x.cost}</span>
                                <br />
                                <span id='desc'>{x.description}</span>
                            </div>
                            <div className="icons">
                                <EditIcon onClick={() => { editData(x.id) }} />
                                <DeleteForeverIcon onClick={() => { removeitem(x.name, x.id) }} />
                            </div>
                        </div>
                    </div>

                )
            })}
        </div>

    )
}
export default ProductView