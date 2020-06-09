import React from 'react';
import Dataview from './Dataview';
import UserInput from './UserInput';
import SearchBar from './SearchBar' ;

import aaa from "../assets/json_file/userOrder.json"

// profile and data view's parent

const url = "http://localhost:8085/eOrderButler/getShoppingOrderById/1";
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

class Main extends React.Component {
    constructor (props) {
        super (props);
        this.state = {
            PostData: [],
        };
        this.handleAddTodo = this.handleAddTodo.bind(this);
    }

    handleAddTodo (text) {
        //this.setState(prevState => ({ todos : [...prevState. todos , text] })) ;
    }


    async componentDidMount() {

        await this.setState({PostData: aaa});

        // var myInit = {method: 'GET',
        //                 headers : {
        //                     'Content-Type': 'application/json',
        //                     'Accept': 'application/json',
        //                     'Origin':'http://localhost:3001'
        //                 },
        //                 mode: 'cors',
        //                 cache: 'default'
        // };
        //
        // let myRequest = new Request(url, myInit);
        //
        // await fetch(myRequest)
        //      .then(response => response)//.json())
        //     .then(data => {console.log(data)})
        //     //.then(data => {console.log(typeof data)})
        //      //.then(data => this.setState({PostData:data}))
        //     //.then(data => {this.setState({ PostData: data })})
        //     .catch(err => {alert("Error Reading data " + err);
        // });

        console.log(this.state)
        //debugger
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