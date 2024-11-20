import React from 'react';
import { useData } from '../../hooks/useData';
import CatalogPreview from '../../components/catalog_preview';


// styles
import classes from './home.module.css';

export default function Home() {
    const { data, isLoading, error } = useData();

    if (isLoading) {
        return <div>Loading...</div>;
    }

    if (error) {
        return <div>{error}</div>;
    }

    return (
        <>
            <div className={classes.title}>Catalogs</div>
            <div className={classes.wrapper}>
                {data && Array.isArray(data) && data.map((catalog) => (
                    <div key={catalog.id} className={classes.catalogs}>
                        <CatalogPreview id={catalog.id} name={catalog.category} imageUrl={catalog.image} />
                    </div>
                ))}
            </div>
        </>
    );
}
