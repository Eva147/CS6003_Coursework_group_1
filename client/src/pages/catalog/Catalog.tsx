import React from 'react';
import { NavLink } from 'react-router-dom';
import { catalogData } from '../../data_helpers';

// styles
import classes from './catalog.module.css';

export interface ProductData {
    product_id: string;
    name: string;
    description: string;
    price: string;
    quantity: number;
    catalog_id: string;
    image: string;
}

export default function Catalog() {
    // const { data, isLoading, error } = useData();

    return (
        <div className={classes.wrapper}>
        {catalogData.map((product) => (
            <NavLink to={`/${product.catalog_id}/${product.product_id}`}>
                <div className={classes.item} style={{ backgroundImage: `url(${product.image})` }} />
                <div className={classes.info}>
                    <div className={classes.title}>{product.name}</div>
                    <div className={classes.price}>{product.price}</div>
                </div>
            </NavLink>
        ))}
    </div>
    );
}
