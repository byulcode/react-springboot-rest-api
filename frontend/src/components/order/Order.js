import 'bootstrap/dist/css/bootstrap.css';
import '../../App.css';
import React, {useEffect, useState} from "react";
import {ProductList} from "../product/ProductList";
import axios from "axios";
import {Link, useNavigate} from 'react-router-dom';
import {Summary} from "../summary/Summary";


function Order() {
    const navigate = useNavigate();
    const [categoryField, setCategoryField] = useState('');

    const [products, setProducts] = useState([])
    const [items, setItems] = useState([]);
    const handleAddClicked = productId => {
        const product = products.find(v => v.id === productId);
        const found = items.find(v => v.id === productId);
        const updatedItems =
            found ? items.map(v => (v.id === productId) ? {...v, count: v.count + 1} : v) : [...items, {
                ...product,
                count: 1
            }]
        setItems(updatedItems);
    }

    useEffect(() => {
        axios.get('http://localhost:8080/api/v1/products')
            .then(v => setProducts(v.data))
    }, []);

    const handleOrderSubmit = (order) => {
        if (items.length === 0) {
            alert("아이템을 추가해 주세요!");
        } else {
            axios.post('http://localhost:8080/api/v1/orders/bulk-orders', {
                email: order.email,
                address: order.address,
                postcode: order.postcode,
                orderItems: items.map(v => ({
                    productId: v.id,
                    category: v.category,
                    price: v.price,
                    quantity: v.count
                }))
            }).then(
                v => {
                    alert("주문이 정상적으로 접수되었습니다.");
                    navigate('/orders')
                },
                e => {
                    alert("서버 장애");
                    console.error(e);
                }
            );
        }
    }

    const handleSearchClick = () => {
        // Check if a category is selected
        if (!categoryField) {
            alert("카테고리를 선택해 주세요!");
            return;
        }

        // Make an API call with the selected category
        axios.get(`http://localhost:8080/api/v1/products?category=${categoryField}`)
            .then(response => setProducts(response.data))
            .catch(error => {
                console.error("Error fetching products:", error);
                alert("상품을 불러오는 중에 오류가 발생했습니다.");
            });
    }


    return (
        <div className="container-fluid">
            <div className="row justify-content-center m-4">
                <h1 className="text-center">Products Order</h1>
            </div>

            <div className="card">
                <div>

                </div>
                <div className="d-flex justify-content-end">
                    <select
                        id="categoryField"
                        value={categoryField}
                        onChange={(e) => setCategoryField(e.target.value)}>
                        <option value="">카테고리</option>
                        <option value="TOY">장난감</option>
                        <option value="SAND">모래</option>
                        <option value="SNACK">간식</option>
                        <option value="FEED">사료</option>
                        <option value="SCRATCHER">스크래쳐</option>
                        {/* Add more categories as needed */}
                    </select>
                    <button onClick={handleSearchClick}>조회</button>
                    <Link to="/orders">
                        <button>주문 목록</button>
                    </Link>
                </div>
                <div className="row">
                    <div className="col-md-8 mt-4 d-flex flex-column align-items-start p-3 pt-0">
                        <ProductList products={products} onAddClick={handleAddClicked}/>
                    </div>
                    <div className="col-md-4 summary p-4">
                        <Summary items={items} onOrderSubmit={handleOrderSubmit}/>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default Order;
