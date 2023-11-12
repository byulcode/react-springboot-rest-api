import React, {useEffect, useState} from 'react';
import {Link, useNavigate} from 'react-router-dom';
import {getAllOrders} from '../../services/ProductService';

const OrderList = () => {
    const [orders, setOrders] = useState([]);
    const navigate = useNavigate();

    useEffect(() => {
        getAllOrders()
            .then(async (data) => {
                setOrders(data);
            })
            .catch(error => console.error('Error fetching orders:', error));
    }, []);

    return (
        <div className="container">
            <h2>Order List</h2>
            <table className="table table-bordered">
                <thead>
                <tr>
                    <th>Order ID</th>
                    <th>Order Status</th>
                    <th>Address</th>
                    <th>Postcode</th>
                    <th>Total Price</th>
                    <th>Created At</th>
                </tr>
                </thead>
                <tbody>
                {orders.map(order => (
                    <tr key={order.orderId}>
                        <td>{order.orderId}</td>
                        <td>{order.orderStatus}</td>
                        <td>{order.address}</td>
                        <td>{order.postcode}</td>
                        <td>{order.totalPrice}</td>
                        <td>{order.createdAt}</td>
                    </tr>
                ))}
                </tbody>
            </table>
            <Link to="/" style={{float: 'right'}}>
                <button>주문</button>
            </Link>
        </div>
    );
};

export default OrderList;
