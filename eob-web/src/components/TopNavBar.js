import React , {Component} from 'react' ;
import logo from "../assets/images/logo.svg" ;

class TopNavBar extends React.Component {
    render() {
        return (
            <header className="App-header">
                <img src={logo} className="App-logo" alt="logo" />
            </header>
        );
    }
}

export default TopNavBar ;
