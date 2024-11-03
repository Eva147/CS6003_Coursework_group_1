import React from 'react'
import imageUrl from './../images/ad_3.jpg'

//styles
import classes from './footer.module.css';


export default function Navbar() {
    return (
    <div className={classes.footer}>
        <div className={classes.ad} style={{ backgroundImage: `url(${imageUrl})` }}></div>
    </div>
    )
}
