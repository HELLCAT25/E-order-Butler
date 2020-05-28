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
            <div className="add-item">
                <input onChange= { this . handleInputChange } value= { this . state . userInput } />
                <button onClick= { this . handleAdd } > Add </button>
            </div>
        ) ;
    }
}


export default UserInput;