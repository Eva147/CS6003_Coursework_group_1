import React from "react";

import classes from './product_preview.module.css';

export interface ProductData {
    product_id: string;
    name: string;
    description: string;
    price: string;
    quantity: number;
    catalog_id: string;
    image: string;
}

export default function ProductPreview() {
    return (
        <div>Info about the product</div>
    )

}