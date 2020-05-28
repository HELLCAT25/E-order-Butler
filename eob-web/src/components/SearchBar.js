import React , {Component} from 'react' ;
import ReactDOM from 'react-dom';
import 'antd/dist/antd.css';
import { Input } from 'antd';


// 用ant design的search bar

class SearchBar extends Component {

    render () {
        return (
            <div className = "search-bar">
            <Input placeholder="This is search bar" />
            </div>
        ) ;
    }
}

export default SearchBar ;