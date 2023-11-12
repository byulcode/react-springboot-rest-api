import React, {useEffect, useState} from 'react';
import {getAllProducts} from '../../services/ProductService';
import {Link, useNavigate} from 'react-router-dom';
import {generateImageUrl} from '../util/GenerateImageUrl';

function ProductListUser() {
    const [products, setProducts] = useState([]);
    const navigate = useNavigate();

    useEffect(() => {
        getAllProducts()
            .then(async (data) => {
                const productsWithImages = [];
                for (const product of data) {
                    if (product.imageId) {
                        const imageUrl = await generateImageUrl(product.imageId);
                        const productWithImage = {...product, imageUrl};
                        productsWithImages.push(productWithImage);
                    } else {
                        productsWithImages.push(product);
                    }
                }
                setProducts(productsWithImages);
            })
            .catch((error) => console.error('Error fetching data: ', error));
    }, []);

    const handleOrderClick = (product) => {
        console.log(`Ordered: ${product.name}`);
    };

    const handleCartClick = (product) => {
        console.log(`Added to Cart: ${product.name}`);
    };

    return (
        <div className="container">
            <h1 className="text-center mt-4">Product List</h1>
            <Link to="/admin/create" style={{float: 'right'}}>
                <button>등록</button>
            </Link>
            <table className="table table-bordered" style={{backgroundColor: '#edd6ff', borderRadius: '10px'}}>
                <thead style={{backgroundColor: '#edd6ff'}}>
                <tr>
                    <th className="col-2">Image</th>
                    <th className="col-1">Name</th>
                    <th className="col-2">Description</th>
                    <th className="col-1">Price</th>
                    <th className="col-2">CreatedAt</th>
                </tr>
                </thead>
                <tbody>
                {products.map((product) => (
                    <tr>
                        <td>
                            <img src={product.imageUrl} alt={product.name} className="img-fluid"
                                 style={{maxWidth: '80px', maxHeight: '80px'}}/>
                        </td>
                        <td>
                            <Link to={`/admin/${product.id}`}>{product.name}</Link>
                        </td>
                        <td>{product.description}</td>
                        <td>{product.price}원</td>
                        <td>{product.createdAt}</td>
                    </tr>
                ))}
                </tbody>
            </table>

        </div>
    );
}

export default ProductListUser;
