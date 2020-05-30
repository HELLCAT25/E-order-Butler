import React, {Component} from 'react';

class UserInput extends React.Component {
    constructor (props) {
        super (props) ;
        this . state = { userInput : '' } ;
        this . handleInputChange = this . handleInputChange . bind ( this ) ;
        this . handleAdd = this . handleAdd . bind ( this ) ;
    }
    handleInputChange (e) {
        this .setState({ userInput : e. target . value }) ;
    }
    handleAdd () {
        const text = this . state . userInput ;
        if (text) {
            this .props. handleAddTodo (text) ;
        }
        this .setState({ userInput : '' }) ;
    }
    render () {
        return (
            <div className="input-group mb-3">
                <input type="text" className="form-control" placeholder="Add Order Number"
                       aria-label="Recipient's username" aria-describedby="basic-addon2"/>
                    <div className="input-group-append">
                        <button className="btn btn-outline-secondary add-button" type="button">Button</button>
                    </div>
            </div>
        );
    }
}

        // {/*<div className="add-item">*/}
        // {/*    <input onChange= { this . handleInputChange } value= { this . state . userInput } />*/}
        // {/*    <button onClick= { this . handleAdd } > Add </button>*/}
        // {/*</div>*/}

export default UserInput;