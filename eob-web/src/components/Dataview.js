/*eslint-env jquery*/

import TodoItem from './TodoItem';
import React, {Component} from 'react';
//import PostData from "../assets/json_file/userOrder.json"

class Dataview extends Component {

    render() {

        const PostData = this.props.PostData;

        // const {
        //     // something
        // } = this.props.PostData; // get the only info we need

        return(

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

