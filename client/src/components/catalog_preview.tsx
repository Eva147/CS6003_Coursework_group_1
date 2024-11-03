import React from "react";
import { NavLink } from "react-router-dom";

import classes from './catalog_preview.module.css';

type CatalogPreviewProps = {
    catalogId: string;
    name: string;
    imageUrl: string;
};

export default function CatalogPreview(props: CatalogPreviewProps) {
    const { catalogId, name, imageUrl } = props;

    return (
        <NavLink to={`/${catalogId}`} className={classes.wrapper} style={{ backgroundImage: `url(${imageUrl})` }}>
            <div className={classes.title}>{name}</div>
        </NavLink>
    );
}