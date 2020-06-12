import React from 'react';
import Dataview from './Dataview';
import UserInput from './UserInput';
import SearchBar from './SearchBar' ;
import UserService from "../services/user.service";

import axios, * as others from 'axios';
import authHeader from '../services/auth-header';


const url_getall = "http://localhost:8085/eOrderButler/getAllShoppingOrders";
const url_add = "http://localhost:8085/eOrderButler/addShoppingOrder";

// const localDir = "../assets/json_file/userOrder.json";



class Main extends React.Component {
    constructor (props) {
        super (props);
        this.state = {
            PostData: [],
        };
        this.handleAddTodo = this.handleAddTodo.bind(this);
    }


    handleAddTodo (text) {
        console.log(text);
        const text1 = "http://ship.sephora.com/tracking/itemvisibility/v1/sephora/orders/25273302644?dzip=63112-1114&locale=en_US&order_number=25273302644&tracking_numbers=1Z5R68990310574080&tracking_url=http%3A%2F%2Fship.sephora.com%2Ftracking%2Fsephora%2Fups%3Fdzip%3D63112-1114%26locale%3Den_US%26order_number%3D25273302644%26tracking_numbers%3D1Z5R68990310574080%20Request%20Method:%20GET"
        axios.post(url_add, text1, { headers: authHeader() })
            .then((response) => {
                console.log(response);
            })
            .catch((error)=>{
                console.log(error);
            });
        // redirect
    }

    async componentDidMount() {
        await axios.get(url_getall, { headers: authHeader() })
            .then((response) => {
                console.log(response);
                this.setState({PostData: response.data})
            })
            .catch((error)=>{
                console.log(error);
            });
    }


    render () {
        //debugger;
        return (
            <div className ="main" >
                <SearchBar />
                <div className ="dataview" >
                    <Dataview PostData = { this.state.PostData }/>
                </div>
                <div className ="add" >
                    <UserInput handleAddTodo= { this.handleAddTodo } />
                </div>
            </div>
        ) ;
    }
}


export default Main;