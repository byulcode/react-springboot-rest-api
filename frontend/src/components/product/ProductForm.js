import React, {useState} from 'react';
import axios from 'axios';
import {Link, useNavigate} from 'react-router-dom';
import './ProductForm.css';

function ProductForm() {
    const [nameField, setNameField] = useState('');
    const [contentField, setContentField] = useState('');
    const [priceField, setPriceField] = useState(0);
    const [categoryField, setCategoryField] = useState('SAND');
    const [image, setImage] = useState();
    const navigate = useNavigate();

    const onClickSubmitButton = async (e) => {
        e.preventDefault();

        const formData = new FormData();
        formData.append('image', image);

        const requestData = {
            name: nameField,
            description: contentField,
            price: priceField,
            category: categoryField,
        };

        formData.append('createProductRequest', new Blob([JSON.stringify(requestData)], {
            type: 'application/json',
        }));

        try {
            const response = await axios.post('http://localhost:8080/api/v1/products/admin', formData, {
                headers: {'Content-Type': 'multipart/form-data'},
            });

            console.log('Response:', response.data);
            alert('상품이 등록되었습니다.');
            navigate('/admin');
        } catch (error) {
            console.error('Error:', error);
        }
    };

    return (
        <div className="container mt-5">
            <div className="row justify-content-center">
                <div className="col-md-6">
                    <form className="form-container">
                        <div className="mb-3">
                            <label htmlFor="nameField" className="form-label">Name:</label>
                            <input
                                type="text"
                                className="form-control"
                                id="nameField"
                                placeholder="Name"
                                value={nameField}
                                onChange={(e) => setNameField(e.target.value)}/>
                        </div>
                        <div className="mb-3">
                            <label htmlFor="contentField" className="form-label">Description:</label>
                            <textarea
                                className="form-control"
                                id="contentField"
                                placeholder="Description"
                                value={contentField}
                                onChange={(e) => setContentField(e.target.value)}/>
                        </div>
                        <div className="mb-3">
                            <label htmlFor="priceField" className="form-label">Price:</label>
                            <input
                                type="number"
                                className="form-control"
                                id="priceField"
                                placeholder="Price"
                                value={priceField}
                                onChange={(e) => setPriceField(parseFloat(e.target.value))}/>
                        </div>
                        <div className="mb-3">
                            <label htmlFor="categoryField" className="form-label">Category:</label>
                            <select
                                className="form-select"
                                id="categoryField"
                                value={categoryField}
                                onChange={(e) => setCategoryField(e.target.value)}>
                                <option value="TOY">장난감</option>
                                <option value="SAND">모래</option>
                                <option value="SNACK">간식</option>
                                <option value="FEED">사료</option>
                                <option value="SCRATCHER">스크래쳐</option>
                                {/* Add more categories as needed */}
                            </select>
                        </div>
                        <div className="mb-3">
                            <label htmlFor="imageField" className="form-label">Image:</label>
                            <input
                                type="file"
                                className="form-control"
                                id="imageField"
                                onChange={(e) => setImage(e.target.files[0])}/>
                        </div>
                        <button type="submit" className="btn btn-primary" onClick={onClickSubmitButton}>Submit</button>
                        <Link to="/admin">
                            <button className="btn btn-primary">목록으로</button>
                        </Link>
                    </form>
                </div>
            </div>
        </div>
    );
}

export default ProductForm;
