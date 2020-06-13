import React, { Component } from "react";
import eobpng from "../assets/images/EOB.png"


import UserService from "../services/user.service";


export default class Home extends Component {


  render() {
    return (
        <div>

            <div className = "eobpngstyle">
                <img src={eobpng} width = "1100" ></img>

                {/*<img src={eobpng} height = "300" align={"center"}></img>*/}
            </div>



            <div className="container">
                <header className="jumbotron">
                    <h2>Welcome to E-order Butler! </h2>
                    <h4>You can now easily track everything your purchase online!</h4>
                </header>
            </div>
        </div>

    );
  }
}
