import React from 'react';
import Dataview from './Dataview';
import UserInput from './UserInput';
import SearchBar from './SearchBar' ;
import UserService from "../services/user.service";
import { useHistory, withRouter } from "react-router-dom";
import Popup from "reactjs-popup";

import axios, * as others from 'axios';
import authHeader from '../services/auth-header';

const url_getall = "http://localhost:8085/eOrderButler/getAllShoppingOrders";
const url_add = "http://localhost:8085/eOrderButler/addShoppingOrder";
const url_search = "http://localhost:8085/eOrderButler/search/";

// const localDir = "../assets/json_file/userOrder.json";


class Popup_m extends React.Component {
    render() {
        return (
            <div className='popup'>
                <div className='popup_inner'>
                    <h1>{this.props.text}</h1>
                    <button className='closeit' onClick={this.props.closePopup}>Back to Home</button>
                </div>
            </div>
        );
    }
}

class Main extends React.Component {
    constructor (props) {
        super (props);
        this.state = {
            PostData: [],
            showPopup: false
        };
        this.handleAddTodo = this.handleAddTodo.bind(this);
        this.handlePressEnter = this.handlePressEnter.bind(this);
    }

    togglePopup() {
        this.setState({
            showPopup: !this.state.showPopup
        });
    }

    toggleClose = () => {
        axios.get(url_getall, { headers: authHeader() })
            .then((response) => {
                console.log(response);
                this.setState({PostData: response.data})
            })
            .catch((error)=>{
                console.log(error);
            });

        this.setState({
            showPopup: !this.state.showPopup
        });
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

    loadInfoAdd = (text) => {
        console.log("we are adding");
        console.log(text);
        const text1 = "https://ship.sephora.com/tracking/sephora/ups?dzip=63112-1114&locale=en_US&order_number=25275089654&tracking_numbers=1Z5R68920339139401"
        const text2 = "https://ship.sephora.com/tracking/sephora/ups?dzip=63112-1114&locale=en_US&order_number=25273301663&tracking_numbers=1Z5R68990310574080"
        axios({
            method: 'post',
            url: url_add,
            headers: {...authHeader(),'Content-Type': 'text/plain'},
            data: text, // This is the body part
        }).then((response) => {
            console.log(response);
            this.setState({showPopup: true})
        }).catch((error)=>{
            console.log(error);
        });
    }

    loadInfoSearch = (txt) => {
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
        this.loadInfoSearch(name);
    }

    handleAddTodo = (name) => {
        this.loadInfoAdd(name);
    }


    render () {
        //debugger;
        return (
            <div className ="main" >
                {/*<button onClick={this.togglePopup.bind(this)}>show popup</button>*/}
                <div className='app'>
                    {this.state.showPopup ?
                        <Popup_m
                            text='Congrats! You just added your order. (◕‿◕✿)'
                            closePopup={this.toggleClose.bind(this)}
                        />
                        : null
                    }
                </div>

                <SearchBar className ="search"
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