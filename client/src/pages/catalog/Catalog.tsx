import React from 'react';
import { useParams } from 'react-router-dom';
import { useEffect } from 'react';
import { useData } from '../../hooks/useData';
import { NavLink } from 'react-router-dom';

// styles
import classes from './catalog.module.css';

export default function Catalog() {
    const { catalogId } = useParams<{ catalogId: string }>();
    const { products, sortedProducts, isLoading, error, fetchProductsByCatalogById, getCatalogTitleById, getSortedProducts } = useData();
    const [catalogTitle, setCatalogTitle] = React.useState<string | undefined>(undefined);
    const [sortParameter, setSortParameter] = React.useState<"name" | "price">("price");

    useEffect(() => {
        if (catalogId) {
            fetchProductsByCatalogById(catalogId);
            setCatalogTitle(getCatalogTitleById(catalogId));
        }
    }, [catalogId]);

     useEffect(() => {
        if (catalogId) {
            getSortedProducts(catalogId, sortParameter);
        }
     }, [sortParameter]);

      if (isLoading) {
        return <div>Loading...</div>;
      }

      if (error) {
        return <div>{error}</div>;
      }

    return (
        <main>
            <div className={classes.pageTitle}>{catalogTitle}</div>
                <div className={classes.sortMenu} aria-label="Sort products">
                    <button className={classes.button} aria-label="Sort price" onClick={() => setSortParameter("price")}>Sort by price</button>
                    <button className={classes.button} aria-label="Sort by name" onClick={() => setSortParameter("name")}>Sort by name</button>
                </div>
            <div className={classes.wrapper} aria-label={`Products in ${catalogTitle}`}>
                {sortedProducts.map((product) => (
                    <NavLink data-test={`product-preview-${product.productId}`} key={product.productId} to={`${product.productId}`} aria-label={`${product.name}, Price: £${product.price}`}>
                        <article>
                            <div className={classes.item} style={{ backgroundImage: `url(${product.image})` }} aria-label={product.name} />
                            <div className={classes.info}>
                                <h2 className={classes.title}>{product.name}</h2>
                                <p className={classes.price} aria-label={`Price: £${product.price}`}>{`£${product.price}`}</p>
                            </div>
                        </article>
                    </NavLink>
                ))}
            </div>
            {products.length === 0 && (
                <p role="alert" className={classes.noProducts}>
                    No products found in this catalog
                </p>
            )}
        </main>
    );
}
