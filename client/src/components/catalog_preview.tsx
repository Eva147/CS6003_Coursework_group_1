import React from "react";
import { NavLink } from "react-router-dom";

import classes from './catalog_preview.module.css';

type CatalogPreviewProps = {
    id: string;
    name: string;
    imageUrl: string;
};

export default function CatalogPreview(props: CatalogPreviewProps) {
    const { id, name, imageUrl } = props;

    return (
        <article className={classes.item}>
            <NavLink to={`/${id}`} className={classes.wrapper} aria-label={`Browse ${name} catalog`}>
                <div
                    data-test={`catalog-preview-item-${id}`}
                    className={classes.image}
                    style={{ backgroundImage: `url(${imageUrl})` }}
                    role="img"
                    aria-label={`${name} catalog image`}
                />
                <h2 className={classes.title}>{name}</h2>
            </NavLink>
        </article>
    );
}