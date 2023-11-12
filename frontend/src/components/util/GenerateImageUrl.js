import axios from 'axios';

const generateImageUrl = async (imageId) => {
    try {
        const response = await axios.get(`http://localhost:8080/api/v1/products/image/${imageId}`, {
            responseType: 'arraybuffer',
        });

        const url = URL.createObjectURL(new Blob([response.data], { type: 'image/png' }));
        return url;
    } catch (error) {
        console.error('Error generating image URL: ', error);
        return '';
    }
};

export { generateImageUrl };
