import React from "react";
import TodoItem from './TodoItem';

function Dataview(props) {
    return (
        <div className ="data-view" >
            {props.todos.map ((todo, i) => (
                < TodoItem
                    key= {todo.id }
                    todo= {todo}
                    index= {i}
                    handleDeleteTodo= {props.handleDeleteTodo }
                />
            ))}
        </div>
    ) ;
}

export default Dataview;