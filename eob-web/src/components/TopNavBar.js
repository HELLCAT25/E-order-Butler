import React , {Component} from 'react' ;
import icon from "../assets/images/logo.svg" ;
import eob from "../assets/images/EOB.svg" ;
import ReactModalLogin from "react-modal-login";
import Modal from "react-responsive-modal";
import { facebookConfig, googleConfig } from "./social-config";
import LoginModal from "react-login-modal-sm";

class TopNavBar extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            showModal: false,
            loading: false,
            error: null,
            sign: false,
            login: false,
        };
    }

    toggleModal = () => {
        this.setState({ showModal: !this.state.showModal });
    };

    handleLoginWithFacebook = () => {
        // Do something when 'Login with Facebook' is clicked
        console.log("Login with Facebook...");
    };

    handleSignupByEmail = (email, username, password) => {
        // Do something when 'Signup by email' is clicked
        console.log("Sign up by email...");
    };

    handleLoginByEmail = (email, password) => {
        // Do something when 'Signup by email' is clicked
        console.log("Log in by email...");
    };


    render() {
        const { login, sign } = this.state;
        const customUsernameRegex = /^[a-zA-Z0-9_]{5,}/;

        return (
        <div className="container">
            <nav className="selfnav navbar navbar-expand-md bg-light sticky-top">
                <img src={eob} className="App-logo" alt="logo" />
                <a className="navbar-brand text-dark" href="#">EOrderButler</a>
                <button className="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse"
                        data-target="#navb" aria-expanded="true">
                    <span className="navbar-toggler-icon"></span>
                </button>
                <div id="navb" className="navbar-collapse collapse">
                    <ul className="navbar-nav">
                        <li className="nav-item active text-dark">
                            <a className="nav-link" href="#">Home</a>
                        </li>
                        <li className="nav-item text-dark">
                            <a className="nav-link" href="#">Details</a>
                        </li>
                    </ul>

                    <ul className="nav navbar-nav ml-auto">
                        <li className="nav-item">
                            <div className="log-in">
                                <LoginModal
                                    showModal={this.state.showModal}
                                    toggleModal={this.toggleModal}
                                    onLoginFacebook={this.handleLoginWithFacebook}
                                    onLoginEmail={this.handleLoginByEmail}
                                    onSignupEmail={this.handleSignupByEmail}
                                    usernameRegex={customUsernameRegex}
                                />

                                <button className="test-btn btn btn-primary btn-lg" onClick={this.toggleModal}>
                                    Login
                                </button>
                            </div>
                        </li>
                    </ul>

                </div>
            </nav>
        </div>
        );
    }
}




export default TopNavBar ;
