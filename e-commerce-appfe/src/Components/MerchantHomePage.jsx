import { Route, Routes } from "react-router-dom";
import MerchantNavabr from "./MerchantNavBar";
import ProductView from "./ProductView";
import AddProducts from "./AddProducts";
import UpdateProduct from "./UpdateProduct";
import UpdateMerchant from "./UpdateMerchant";

const MechantHomePage = () => {
    return (
        <div className="mhp">
            <MerchantNavabr/>
           <Routes>
            <Route path="/productview" element={<ProductView/>}/>
            <Route path="/updatemerchant" element={<UpdateMerchant/>}/>
            <Route path="/addproduts" element={<AddProducts/>}/>
            <Route path="/updateproduct" element={<UpdateProduct/>}/>
           
           </Routes>
        </div>
      );
}
 
export default MechantHomePage;