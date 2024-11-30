/*

import React from 'react';
import { useParams } from 'react-router-dom';
import { useEffect } from 'react';
import { useData } from '../../hooks/useData';
import { NavLink } from 'react-router-dom';

// styles
import classes from './catalog.module.css';

export default function Catalog() {
    const { catalogId } = useParams<{ catalogId: string }>();
    const { products, isLoading, error, fetchProductsByCatalogById, getCatalogTitleById } = useData();
    const [catalogTitle, setCatalogTitle] = React.useState<string | undefined>(undefined);

    useEffect(() => {
        if (catalogId) {
            fetchProductsByCatalogById(catalogId);
            setCatalogTitle(getCatalogTitleById(catalogId));
        }
      }, [catalogId]);
      

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
                    <button className={classes.button} aria-label="Sort price">Sort by price</button>
                    <button className={classes.button} aria-label="Sort by name">Sort by name</button>
                </div>
            <div className={classes.wrapper} aria-label={`Products in ${catalogTitle}`}>
                {products.map((product) => (
                    <NavLink key={product.productId} to={`${product.productId}`} aria-label={`${product.name}, Price: £${product.price}`}>
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

*/

import React, { useState, useEffect } from "react";
import { useParams } from "react-router-dom";
import { useData } from "../../hooks/useData";
import { NavLink } from "react-router-dom";

// styles
import classes from "./catalog.module.css";

export default function Catalog() {
  const { catalogId } = useParams<{ catalogId: string }>();
  const {
    products, // fetched products
    isLoading,
    error,
    fetchProductsByCatalogById,
    getCatalogTitleById,
  } = useData();

  // Local state to manage sorted products
  const [sortedProducts, setSortedProducts] = useState(products || []);
  const [catalogTitle, setCatalogTitle] = useState<string | undefined>(undefined);

  // Fetch products when the catalog ID changes
  useEffect(() => {
    if (catalogId) {
      fetchProductsByCatalogById(catalogId);
      setCatalogTitle(getCatalogTitleById(catalogId));
    }
  }, [catalogId]);

  // Update sortedProducts whenever products are fetched
  useEffect(() => {
    setSortedProducts(products);
  }, [products]);

  
  // Sorting functions
  const sortByPrice = () => {
    const sorted = [...sortedProducts].sort(
      (a, b) => parseFloat(a.price) - parseFloat(b.price)
    );
    setSortedProducts(sorted);
  };

  const sortByName = () => {
    const sorted = [...sortedProducts].sort((a, b) =>
      a.name.localeCompare(b.name)
    );
    setSortedProducts(sorted);
  };

  

  // Handle loading and error states
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
        <button
          className={classes.button}
          aria-label="Sort price"
          onClick={sortByPrice} // Attach the sortByPrice function
        >
          Sort by price
        </button>
        <button
          className={classes.button}
          aria-label="Sort by name"
          onClick={sortByName} // Attach the sortByName function
        >
          Sort by name
        </button>
      </div>
      <div className={classes.wrapper} aria-label={`Products in ${catalogTitle}`}>
        {sortedProducts.map((product) => (
          <NavLink
            key={product.productId}
            to={`${product.productId}`}
            aria-label={`${product.name}, Price: £${product.price}`}
          >
            <article>
              <div
                className={classes.item}
                style={{ backgroundImage: `url(${product.image})` }}
                aria-label={product.name}
              />
              <div className={classes.info}>
                <h2 className={classes.title}>{product.name}</h2>
                <p className={classes.price} aria-label={`Price: £${product.price}`}>
                  {`£${product.price}`}
                </p>
              </div>
            </article>
          </NavLink>
        ))}
      </div>
      {sortedProducts.length === 0 && (
        <p role="alert" className={classes.noProducts}>
          No products found in this catalog
        </p>
      )}
    </main>
  );
}

