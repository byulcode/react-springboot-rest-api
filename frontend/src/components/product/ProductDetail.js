import React, {useEffect, useState} from 'react';
import {Link, useNavigate, useParams} from 'react-router-dom';
import axios from 'axios';
import {generateImageUrl} from '../util/GenerateImageUrl'

function ProductDetail() {
    const {id} = useParams();
    const [product, setProduct] = useState(null);
    const [imageUrl, setImageUrl] = useState(null);
    const navigate = useNavigate();

    useEffect(() => {
        // 제품의 ID를 사용하여 백엔드에서 상세 정보 가져오기
        axios.get(`http://localhost:8080/api/v1/products/${id}`)
            .then(async (response) => {
                const data = response.data;
                setProduct(data);

                // 이미지 URL 생성
                if (data.imageId) {
                    const imageUrl = await generateImageUrl(data.imageId);
                    setImageUrl(imageUrl);
                }
            })
            .catch((error) => {
                console.error('Error fetching product data: ', error);
            });
    }, [id]);

    const handleDelete = () => {
        // 삭제 요청을 보내고 완료되면 홈 페이지로 이동
        axios.delete(`http://localhost:8080/api/v1/products/admin/${id}`)
            .then(() => {
                // 삭제 완료 후 홈 페이지로 이동
                alert('상품이 삭제되었습니다.');
                navigate('/admin');
            })
            .catch((error) => {
                console.error('Error deleting product: ', error);
            });
    };

    if (!product) {
        return <div>Loading...</div>;
    }

    return (
        <div>
            <h1 className="text-center">Product Details</h1>
            <div className="container-sm mt-1">

                {imageUrl && <img src={imageUrl} alt={product.name} style={{width: '100px', height: '100px'}}/>}
                <p>Name: {product.name}</p>
                <p>Description: {product.description}</p>
                <p>Price: {product.price}원</p>
                <p>Category: {product.category}</p>
                <p>Created At: {product.createdAt}</p>
                <button onClick={handleDelete}>Delete</button>
                <Link to="/">
                    <button>Home</button>
                    </Link>
            </div>
        </div>

    );
}

export default ProductDetail;
