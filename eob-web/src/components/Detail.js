import React from 'react';
import Dataview from './Dataview';
import UserInput from './UserInput';
import SearchBar from './SearchBar' ;

import aaa from "../assets/json_file/userOrder.json"

// profile and data view's parent

const url = "http://localhost:8085/eOrderButler/getAllShoppingOrders";
const url2 = "http://localhost:8085/eOrderButler/getShoppingOrderById/1";

const localDir = "../assets/json_file/userOrder.json";
const proxyurl = "https://cors-anywhere.herokuapp.com/";

// var proxyUrl = 'https://cors-anywhere.herokuapp.com/'
//
// fetch(proxyUrl + API)
//     .then(blob => blob.json())
//     .then(data => {
//         console.table(data);
//         document.querySelector("pre").innerHTML = JSON.stringify(data, null, 2);
//         return data;
//     })
//     .catch(e => {
//         console.log(e);
//         return e;
//     });

// fetch(API)
//     .then(response => response.json())
//     .then((resp) => resp.json())
//     .then(function(data) {
//         console.log(data);
//     }).catch(function(error) {
//     alert("HTTP-Error");
// });

class Detail extends React.Component {
    constructor (props) {
        super (props);
        this.state = {
            PostData: [],
        };
    }


    async componentDidMount() {

        await this.setState({PostData: aaa});
        console.log(this.state)
        //debugger
    }


    render () {
        //debugger;
        return (
            <div className ="detail" >
                <SearchBar />
                <div className ="dataview" >
                    <Dataview PostData = { this.state.PostData }/>
                </div>
            </div>
        ) ;
    }
}


export default Detail;