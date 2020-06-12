import React, { Component }  from 'react';
import Dataview from './Dataview';
import UserInput from './UserInput';
import SearchBar from './SearchBar' ;
import UserService from "../services/user.service";
import ReactTable from 'react-table';

import axios, * as others from 'axios';
import authHeader from '../services/auth-header';
import { data, columns, subComponent } from './tableSetup';

const url_getall = "http://localhost:8085/eOrderButler/getAllShoppingOrders";


class Detail extends React.Component {
    constructor (props) {
        super (props);
        this.state = {
            data: [],
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


    render () {
        //debugger;
        // return (
        //     <div className ="main" >
        //         <div className ="dataview" >
        //             <Dataview PostData = { this.state.PostData }/>
        //         </div>
        //     </div>
        // ) ;
        const defaultExpandedRows = data.map((element, index) => {return {index: true}});
        return (
            <div>
                <ReactTable
                    data={data}
                    // Use it here
                    defaultExpanded={defaultExpandedRows}
                    columns={columns}
                    defaultPageSize={10}
                    className="-striped -highlight"
                    SubComponent={subComponent}
                />
            </div>
        );
    }
}


export default Detail;