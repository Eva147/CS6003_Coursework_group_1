import React, { useEffect } from "react";
import { useData } from "../../hooks/useData";
import { useState } from "react";
import { useParams } from 'react-router-dom';
import classes from './productPage.module.css';
import { Product} from '../../dataTypes';

export default function ProductPage() {
    const { catalogId, productId } = useParams<{
        catalogId: string;
        productId: string;
    }>();
    // fetch the products if came from another page, otherwise get the product from the context
    const { products, isLoading, error, fetchProductsByCatalogById, getProductById } = useData();
    const [product, setProduct] = useState<Product | undefined>(products?.find((product: Product) => product.productId === productId));
    const [quantity, setQuantity] = useState(1);
    const [alert, setAlert] = useState(false);
    const [added, setAdded] = useState(false);

    useEffect(() => {
        if (catalogId) {
            fetchProductsByCatalogById(catalogId);
        }
    }, [catalogId]);

    useEffect(() => {
        if (productId) {
            const foundProduct = getProductById(productId);
            setProduct(foundProduct);
        }
    }, [productId, products, getProductById]);

    if (isLoading) return <div>Loading...</div>;
    if (error) return <div>Error loading product</div>;
    if (!product) return <div>Product not found</div>;

    function handleClick() {
        if(!product || !quantity) return;

        if(quantity > product?.quantity) {
            setAlert(true);
            return;
        } else {
            setAdded(true);
        }
        // add product to the cart
    }

    return (
        <article aria-labelledby="product-title">
            <h1 className={classes.title}>{product?.name}</h1>
            <div className={classes.wrapper}>
                <img src={product?.image} alt={product?.name} className={classes.image} />
                <div className={classes.info} aria-label="Product details">
                    <div className={classes.description} aria-label="Description">{`Description: ${product?.description}`}</div>
                    <div className={classes.price} aria-label="Price">{`Price: £${product?.price}`}</div>
                    <div className={classes.quantity} aria-label="Availability">{`Availability: ${product?.quantity}`}</div>


                    <div className={classes.quantitySelector}>
                        <input
                            data-test="product-quantity-input"
                            type="number"
                            value={quantity}
                            onChange={(event: React.ChangeEvent<HTMLInputElement>) => {
                                setQuantity(Number(event.target.value))
                                setAlert(false)
                                setAdded(false)
                            }}
                            className={classes.input}
                            min="1"
                            max={product?.quantity}
                            aria-label="Select quantity"
                        />
                    </div>

                    <button data-test="add-to-cart-btn" className={classes.button} onClick={handleClick} aria-label={`Add ${product.name} to cart`}>Add to Basket</button>
                    {alert && <div className={classes.alert}>{`Please choose maximum ${product?.quantity} items`}</div>}
                    {added && <div data-test="product-added-message" className={classes.message}>{`Product${quantity > 1 ? "s" : ""} added to your Basket`}</div>}
                </div>
            </div>
        </article>
    )

}