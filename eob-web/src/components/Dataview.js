/*eslint-env jquery*/

import TodoItem from './TodoItem';
import React, {Component} from 'react';
import PostData from "../assets/json_file/userOrder.json"


// var json_file = fetch("../assets/jdon_file/userOrder.json")
//     .then(response => response.json())
//     .then((resp) => resp.json())
//     .then(function(data) {
//         console.log(data);
//         // let authors = data.results;
//         // return authors.map(function(author) {
//         //     let li = createNode('li'),
//         //         img = createNode('img'),
//         //         span = createNode('span');
//         //     img.src = author.picture.medium;
//         //     span.innerHTML = `${author.name.first} ${author.name.last}`;
//         //     append(li, img);
//         //     append(li, span);
//         //     append(ul, li);
//         // })
//     });

// let response = fetch("../assets/jdon_file/userOrder.json");
// if (response.ok) { // if HTTP-status is 200-299
//                    // get the response body (the method explained below)
//     let json = response.json();
// } else {
//     alert("HTTP-Error: " + response.status);
// }



class Dataview extends Component {
    render() {
        return(
        //     <div>
        //         {PostData.map((postDetail, index) => {
        //             return <div>
        //             {/*<tr key={postDetail.index}>*/}
        //                 <p>{postDetail.orderNumber}</p>
        //                 <p>{postDetail.totalPrice}</p>
        //             </div>
        //         })}
        //     </div>
        //
            <table className="table">
                <thead>
                <tr>
                    <th>Date</th>
                    <th>Merchant</th>
                    <th>Order Number</th>
                    <th>Total</th>
                    <th>Status</th>
                </tr>
                </thead>

                <tbody>
                    {PostData.map((postDetail, index) => {
                    return (
                        <tr key={index}>
                            <td>{new Date(postDetail.date).toISOString().slice(0,10)}</td>
                            <td>{postDetail.merchant}</td>
                            <td>{postDetail.orderNumber}</td>
                            <td>{postDetail.totalPrice}</td>
                            <td>{postDetail.status}</td>
                        </tr>
                    )
                })}
                </tbody>
            </table>
        )
    }
}


export default Dataview;

