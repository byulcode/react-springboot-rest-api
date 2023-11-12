import { generateImageUrl } from '../util/GenerateImageUrl';
import { useState, useEffect } from 'react';
import {Link, useNavigate} from 'react-router-dom';

export function Product(props) {
    const productId = props.id;
    const productName = props.name;
    const category = props.category;
    const price = props.price;
    const imageId = props.imageId;
    const [imageUrl, setImageUrl] = useState(null);
    const navigate = useNavigate();

    useEffect(() => {
        const fetchImageUrl = async () => {
            try {
                const url = await generateImageUrl(imageId);
                setImageUrl(url);
            } catch (error) {
                console.error('Error fetching image URL: ', error);
            }
        };

        fetchImageUrl();
    }, [imageId]);

    const handleAddBtnClick = (e) => {
        props.onAddClick(productId);
    };

    return (
        <>
            <div className="col-2">
                <img className="img-fluid" src={imageUrl} alt="" />
            </div>
            <div className=" col">
                <div className=" row text-muted">
                    <Link to={`/admin/${productId}`}>{productName}</Link>
                </div>
                <div className=" row">{category}</div>
            </div>
            <div className=" col text-center price">{price}원</div>
            <div className=" col text-end action">
                <button onClick={handleAddBtnClick} className=" btn btn-small btn-outline-dark">
                    추가
                </button>
            </div>
        </>
    );
}
