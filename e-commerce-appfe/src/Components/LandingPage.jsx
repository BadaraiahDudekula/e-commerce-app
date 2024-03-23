import '../styles/LandingPage.css';
import {Link} from 'react-router-dom';
const LandingPage = () => {
    return ( 
        <div className="landingpage">
           <Link to="/merchant">
            <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSB1o4IwyEY822wA5mbymjmI-VwPuYo0m33kg&usqp=CAU" alt="" /><br />
                Merchant</Link>

           <Link to="/user">
            <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSp3oYa9BljpcyhfIwVizBrEuo4HjsWq1mNzw&usqp=CAU" alt="" /><br />
          User </Link>
        </div>
     );
}
 
export default LandingPage;

