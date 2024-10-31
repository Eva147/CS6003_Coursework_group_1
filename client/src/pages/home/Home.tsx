import React from 'react';
import Catalogs from '../../components/catalogs';

// styles
import classes from './home.module.css'

export default function Home() {

    return (
        <div className={classes.home}>
           <Catalogs />
        </div>
    );
}
