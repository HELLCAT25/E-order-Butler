import React, { Component }  from 'react';
import Dataview from './Dataview';
import UserInput from './UserInput';
import SearchBar from './SearchBar' ;
import UserService from "../services/user.service";
import ReactTable from 'react-table';
import Demo from './demo';

import axios, * as others from 'axios';
import authHeader from '../services/auth-header';
import { data, columns, subComponent } from './tableSetup';

const url_getall = "http://localhost:8085/eOrderButler/getAllShoppingOrders";
const url_search = "http://localhost:8085/eOrderButler/search/";

class Detail extends React.Component {
    constructor (props) {
        super (props);
        this.state = {
            PostData: [],
        };

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

    handlePressEnter = (name) => {
        this.loadInfo(name);
    }

    loadInfo = (txt) => {
        axios.get(url_search + txt, { headers: authHeader() })
            .then((response) => {
                this.setState({PostData: response.data})
            })
            .catch((error)=>{
                console.log(error);
            });
    }

    render () {

        return (
            <div className ="main">
                <SearchBar
                    handlePressEnter = { this.handlePressEnter } />
                <div >
                    <Demo PostData = { this.state.PostData }/>
                </div>
            </div>
        );
    }
}


export default Detail;