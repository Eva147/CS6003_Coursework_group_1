import React from 'react';
// import { useData } from '../../hooks/useData';
import CatalogPreview from '../../components/catalog_preview';
import { catalogsPreviewData } from '../../data_helpers';


// styles
import classes from './home.module.css';

export default function Home() {
    // const { data, isLoading, error } = useData();

    return (
        <div className={classes.wrapper}>
            {catalogsPreviewData.map((catalog) => (
                <div key={catalog.id} className={classes.catalogs}>
                    <CatalogPreview catalogId={catalog.id} name={catalog.name} imageUrl={catalog.image} />
                </div>
            ))}
        </div>
    );
}
