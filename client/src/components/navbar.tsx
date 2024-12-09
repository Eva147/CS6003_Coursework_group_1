import React from 'react'
import {NavLink} from 'react-router-dom';
import ShoppingCartIcon from '@mui/icons-material/ShoppingCart';
import { useData } from '../hooks/useData';

//styles
import classes from './navbar.module.css';


export default function Navbar() {
    const { isAuthenticated, user, logoutUser } = useData();
    const userName = user?.name;

    function handleLogout() {
        logoutUser();
        console.log('Logged out');
    }

    return (
    <div className={classes.navbar}>
        <nav role="navigation" aria-label="Main navigation" className={classes.nav}>
            <NavLink to='/' className={classes.brand}><h1>THE CLEAN BRIGHT COMPANY</h1></NavLink>
            <>
                {isAuthenticated && <p className={classes.user}>Hello, {userName}</p>}
                <NavLink to="/cart" className={classes.navItem}>
                    <ShoppingCartIcon className={classes.cartIcon}/>
                </NavLink>
                <NavLink to="/login" className={classes.loginBtn}>Login</NavLink>
                <NavLink to="/" className={classes.loginBtn} onClick={handleLogout}>Logout</NavLink>
            </>
        </nav>
    </div>
    )
}
