import React from 'react';
import Dataview from './Dataview';
import UserInput from './UserInput';
import SearchBar from './SearchBar' ;
import UserService from "../services/user.service";

import axios, * as others from 'axios';
import authHeader from '../services/auth-header';


const url_getall = "http://localhost:8085/eOrderButler/getAllShoppingOrders";
const url_add = "http://localhost:8085/eOrderButler/addShoppingOrder";
const url_search = "http://localhost:8085/eOrderButler/search/";

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
        const text1 = "https://ship.sephora.com/tracking/sephora/ups?dzip=63112-1114&locale=en_US&order_number=25273301435&tracking_numbers=1Z5R68990310574080"
        axios({
            method: 'post',
            url: url_add,
            headers: authHeader(),
            data: text1, // This is the body part
        })
        //axios.post(url_add ,text1,  { headers: authHeader() })
            .then((response) => {
                console.log(response);
            })
            .catch((error)=>{
                console.log(error);
            });
        //window.location.reload();
    }

    async componentDidMount() {
        await axios.get(url_getall, { headers: authHeader() })
            .then((response) => {
                this.setState({PostData: response.data})
            })
            .catch((error)=>{
                console.log(error);
            });
    }

    loadInfo = (txt) => {
        axios.get(url_search + txt, { headers: authHeader() })
            .then((response) => {
                console.log(response);
                this.setState({PostData: response.data})
            })
            .catch((error)=>{
                console.log(error);
            });
    }

    handlePressEnter = (name) => {
        this.loadInfo(name);
    }

    render () {
        //debugger;
        return (
            <div className ="main" >

                <SearchBar
                    handlePressEnter = { this.handlePressEnter } />

                <div className ="dataview" >
                    <Dataview PostData = { this.state.PostData }/>
                </div>
                <br/>
                <br/>
                <div className ="add" >
                    <UserInput handleAddTodo= { this.handleAddTodo } />
                </div>
            </div>
        ) ;
    }
}


export default Main;