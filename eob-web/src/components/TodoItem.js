import React, {Component} from 'react';

class TodoItem extends React.Component {
    constructor (props) {
        super (props) ;
        this . state = { done : false } ;
        this . onClick = this . onClick . bind ( this ) ;
        this . onDelete = this . onDelete . bind ( this ) ;
    }
    onClick () {
        this .setState(prevState => ({ done : !prevState. done })) ;
    }
    onDelete () {
        this.props.handleDeleteTodo ( this.props.index ) ;
    }
    render () {
        const textStyle = { 'textDecoration' : this . state . done ? 'line-through' :
                'none' } ;
        return (
            <div>
                <button className= "done-btn" onClick= { this . onClick } >
                    { this . state . done ? 'Undo' : 'Done' }
                </button>
                <span className= "todo-label" style= {textStyle} >
{ this .props.todo. text }
</span>
                <button onClick= { this . onDelete } > x </button>
            </div>
        ) ;
    }
}

export default TodoItem;
