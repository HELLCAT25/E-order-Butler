import React , {Component} from 'react' ;
import logo from "../assets/images/logo.svg" ;


// {/*<nav className="navbar navbar-expand-lg navbar-dark indigo mb-4">*/}
// {/*    <a className="navbar-brand" href="#">Navbar</a>*/}
//
// {/*    <div className="collapse navbar-collapse" id="navbarSupportedContent">*/}
//
// {/*        <form className="form-inline ml-auto">*/}
// {/*            <div className="md-form my-0">*/}
// {/*                <input className="form-control" type="text" placeholder="Search" aria-label="Search"/>*/}
// {/*            </div>*/}
// {/*            <button href="#!" className="btn btn-outline-white btn-md my-0 ml-sm-2" type="submit">Search</button>*/}
// {/*        </form>*/}
// {/*    </div>*/}
// {/*</nav>*/}

class TopNavBar extends React.Component {
    render() {
        return (
        <div className="container">
            <nav className="navbar navbar-expand-lg navbar-light bg-light">
                <img src={logo} className="App-logo" alt="logo" />
                <a className="navbar-brand" href="#">Navbar</a>
            </nav>
        </div>

        );
    }
}

export default TopNavBar ;
