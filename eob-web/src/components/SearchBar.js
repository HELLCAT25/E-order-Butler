import React , {Component} from 'react' ;
import { AutoComplete , Input , Icon } from 'antd' ;

import axios, * as others from 'axios';
import authHeader from '../services/auth-header';

const Option = AutoComplete. Option ;
const url_search = "http://localhost:8085/eOrderButler/search/";

// 用ant design的search bar
// {/*<div className = "search-bar">*/}
// {/*    <Input placeholder="This is search bar" />*/}
// {/*</div>*/}

class SearchBar extends Component {
    constructor (props) {
        super (props) ;
        this.state = { userInput : '' } ;
        this.handleSearch = this.handleSearch.bind(this) ;
        this.handleInputChange = this.handleInputChange.bind(this) ;
        this.handleEnter = this.handleEnter.bind(this) ;
    }

    handleSearch = (value) => {
        // 每输入一个char就update result，双向绑定
        axios.get(url_search + value, { headers: authHeader() })
            .then((response) => {
                console.log(response);
                this.setState ({
                    // clean response data
                    dataSource : !value ?
                        [] : response.data.map ((postDetail, index) => ({
                            itemMerchant : postDetail.merchant ,
                            orderId : postDetail.orderId ,
                        }))
                }).catch((error)=>{
                    console.log(error);
                });
            })
    }

    handleInputChange (e) {
        this.setState({ userInput : e.target.value }) ;
        console.log(this.state.userInput)
    }

    handleEnter () {
        const text = this.state.userInput ;
        if (text) {
            this.props.handlePressEnter(text) ;
        }
        this.setState({ userInput : '' }) ;
    }

    render () {
        // const { dataSource } = this.state ;
        // const options = dataSource. map ((postDetail) => (
        //     <Option key = {postDetail.merchant} value = {postDetail.orderId}
        //             className ="player-option" >
        //         <span className ="player-option-label" > {postDetail.orderId } </span>
        //     </Option>
        // ));
        return (
            <div className = "search-bar">
                <Input onChange={this.handleInputChange} value={this.state.userInput} onPressEnter={this.handleEnter} type="text"
                       className="form-control" placeholder="Search Item!"
                       aria-label="Recipient's username" aria-describedby="basic-addon2"/>
                {/*<Input onPressEnter={this.onPressEnter} placeholder="This is search bar" />*/}
            </div>

        ) ;
    }
}



// class SearchBar extends Component {
//     render () {
//         return (
//             <form className="form-inline d-flex justify-content-center md-form form-sm active-cyan active-cyan-2 mt-2">
//                 <i className="fas fa-search" aria-hidden="true"></i>
//                 <input className="form-control form-control-sm ml-3 w-75, search-bar" type="text" placeholder="Search"
//                        aria-label="Search"/>
//             </form>
//         );
//     }
// }


// {/*<AutoComplete*/}
// {/*    className ="search-bar"*/}
// {/*    dataSource = {options}*/}
// {/*    onKeyDown = {this.onPressEnter}*/}
// {/*    // onSearch = { this.handleSearch }*/}
// {/*    placeholder ="Search Your Item"*/}
// {/*    size ="large"*/}
// {/*    optionLabelProp ="value"*/}
// {/*>*/}
// {/*    <Input suffix = { <Icon type ="search" className ="certain-category-icon"*/}
// {/*    /> } />*/}
// {/*</AutoComplete>*/}

export default SearchBar;