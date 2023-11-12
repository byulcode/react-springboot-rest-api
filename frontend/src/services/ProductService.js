import axios from 'axios';

export const getAllProducts = async () => {
    try {
        const response = await axios.get('http://localhost:8080/api/v1/products');
        return response.data;
    } catch (error) {
        throw error;
    }
};

export const getAllOrders = async () => {
    try {
        console.log('Fetching orders...');
        const response = await axios.get('http://localhost:8080/api/v1/orders');
        console.log('Received response:', response);
        return response.data;
    } catch (error) {
        console.error('Error fetching orders:', error);
        throw error;
    }
};
