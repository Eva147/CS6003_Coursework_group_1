import React from 'react';
import { useEffect } from 'react';
import { useData } from '../../hooks/useData';
import { useParams } from 'react-router-dom';
import { NavLink } from 'react-router-dom';

// styles
import classes from './catalog.module.css';

export default function Catalog() {
    const { catalogId } = useParams<{ catalogId: string }>();
    const { products, isLoading, error, fetchProductsByCatalogById } = useData();

    useEffect(() => {
        if (catalogId) {
            fetchProductsByCatalogById(catalogId);
        }
      }, [catalogId]);

      if (isLoading) {
        return <div>Loading...</div>;
      }

      if (error) {
        return <div>{error}</div>;
      }

    return (
        <div className={classes.wrapper}>
        {products.map((product) => (
            <NavLink key={product.id} to={`/${product.id}`}>
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
