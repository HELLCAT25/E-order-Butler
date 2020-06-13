/*eslint-env jquery*/


import React, {Component} from 'react';
import TableCell from "@material-ui/core/TableCell/TableCell";
//import PostData from "../assets/json_file/userOrder.json"

class Dataview extends Component {

    render() {

        const PostData = this.props.PostData;

        // const {
        //     // something
        // } = this.props.PostData; // get the only info we need

        return(

            <table className="myFormat table table-bordered table-striped table-wrapper-scroll-y my-custom-scrollbar" >
                <thead>
                <tr>
                    <th>Date</th>
                    <th>Merchant</th>
                    <th>Order Number</th>
                    <th>Total Price ($)</th>
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
                            <td>{!postDetail.totalPrice ? "N/A" : postDetail.totalPrice}</td>
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

