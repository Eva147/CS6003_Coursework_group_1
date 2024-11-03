import React from "react";

import classes from './catalog_preview.module.css';

type CatalogPreviewProps = {
    name: string;
    imageUrl: string;
};

export default function CatalogPreview(props: CatalogPreviewProps) {
    const { name, imageUrl } = props;

    return (
        <>
            <div className={classes.wrapper} style={{ backgroundImage: `url(${imageUrl})` }}>
                <div className={classes.title}>{name}</div>
            </div>
        </>
    );

}