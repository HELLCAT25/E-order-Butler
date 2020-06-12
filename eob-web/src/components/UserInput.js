import React, {Component} from 'react';

class UserInput extends React.Component {
    constructor (props) {
        super (props) ;
        this.state = { userInput : '' } ;
        this.handleInputChange = this.handleInputChange.bind(this) ;
        this.handleAdd = this.handleAdd.bind(this) ;
    }

    handleInputChange (e) {
        this.setState({ userInput : e.target.value }) ;
        console.log(this.state.userInput)
    }

    handleAdd () {
        const text = this.state.userInput ;
        if (text) {
            this.props.handleAddTodo(text) ;
        }
        this.setState({ userInput : '' }) ;
        // redirect page
    }

    render () {
        return (
            <div className="input-group mb-3">
                <input onChange={this.handleInputChange} value={this.state.userInput} type="text" className="form-control" placeholder="Add Order URL"
                       aria-label="Recipient's username" aria-describedby="basic-addon2"/>
                    <div className="input-group-append">
                        <button onClick={this.handleAdd} className="btn btn-outline-secondary add-button" type="button">Add</button>
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