import React, { useEffect, useState } from 'react';
import axios from 'axios';

const ProductList = () => {
    const [products, setProducts] = useState([]);
    const [categories, setCategories] = useState([]);

    useEffect(() => {
        // Fetch products data
        axios.get('http://localhost:8080/:category_id')
            .then(response => {
                setProducts(response.data);
            })
            .catch(error => console.error("There was an error fetching the products!", error));
        
        // Fetch categories data
        axios.get('http://localhost:8080/')
            .then(response => {
                setCategories(response.data);
            })
            .catch(error => console.error("There was an error fetching the categories!", error));
    }, []);
}
