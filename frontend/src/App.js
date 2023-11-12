import React from 'react';
import {Route, Routes} from 'react-router-dom';
import ProductForm from "./components/product/ProductForm";
import ProductListAdmin from "./components/product/ProductListAdmin";
import ProductDetail from "./components/product/ProductDetail";
import Order from "./components/order/Order";
import OrderList from "./components/order/OrderList";

function App() {
    return (
        <div className="App">
            <Routes>
                <Route path="/admin" element={<ProductListAdmin/>}/>
                <Route path="/admin/create" element={<ProductForm/>}/>
                <Route path="/admin/:id" element={<ProductDetail />} />
                <Route path="/" element={<Order />}/>
                <Route path="/orders" element={<OrderList/>}/>
            </Routes>
        </div>
    );
}

export default App;
