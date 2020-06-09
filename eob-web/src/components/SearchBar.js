import React , {Component} from 'react' ;
import ReactDOM from 'react-dom';
import 'antd/dist/antd.css';
import { Input } from 'antd';


// 用ant design的search bar
// {/*<div className = "search-bar">*/}
// {/*    <Input placeholder="This is search bar" />*/}
// {/*</div>*/}

class SearchBar extends Component {
    render () {
        return (
            <form className="form-inline d-flex justify-content-center md-form form-sm active-cyan active-cyan-2 mt-2">
                <i className="fas fa-search" aria-hidden="true"></i>
                <input className="form-control form-control-sm ml-3 w-75, search-bar" type="text" placeholder="Search"
                       aria-label="Search"/>
            </form>
        );
    }
}

export default SearchBar;