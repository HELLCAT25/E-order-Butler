import React from 'react';
import Dataview from './Dataview';
import UserInput from './UserInput';
import SearchBar from './SearchBar' ;

// profile and data view's parent

class Main extends React.Component {
    constructor (props) {
        super (props)
        this.state = {
            todos : [] ,
        } ;
        this . handleAddTodo = this . handleAddTodo . bind ( this ) ;
    }
    handleAddTodo (text) {
        this.setState(prevState => ({ todos : [...prevState. todos , text] })) ;
    }

    handleDeleteTodo (index) {
        this .setState((prevState) => {
            return {
                todos : [...prevState. todos . slice ( 0 , index) ,
                    ...prevState. todos . slice (index + 1 , prevState. todos . length )] ,
            } ;
        }) ;
    }

    componentDidMount() {
    }
    render () {
        return (
            <div className ="main" >
                <SearchBar />
                <div className ="dateview" >
                    <Dataview todos= { this.state.todos }
                              handleDeleteTodo= { this.handleDeleteTodo } />
                </div>
                <div className ="add" >
                    <UserInput handleAddTodo= { this.handleAddTodo } />
                </div>
            </div>
        ) ;
    }
}


export default Main;