import React from 'react'
import { useState, useEffect } from 'react';
import {NavLink} from 'react-router-dom';
import ShoppingCartIcon from '@mui/icons-material/ShoppingCart';
import { useData } from '../hooks/useData';

//styles
import classes from './navbar.module.css';


export default function Navbar() {
    const { isAuthenticated, user, logoutUser } = useData();

    const userName = user?.name;

    const [isMobile, setIsMobile] = useState(window.matchMedia("(max-width: 768px)").matches);

    //  check the size of the screen to choose the logo:
    //  on small screens it will be CBC
    //  on larger screens it will be THE CLEAN BRIGHT COMPANY
    useEffect(() => {
        const mediaQuery = window.matchMedia("(max-width: 768px)");

        const handleResize = (e: MediaQueryListEvent) => {
          setIsMobile(e.matches);
        };

        mediaQuery.addEventListener("change", handleResize);

        return () => mediaQuery.removeEventListener("change", handleResize);
      }, []);

    function handleLogout() {
        logoutUser();
        console.log("Logged out");
    }

    return (
    <div className={classes.navbar}>
        <nav role="navigation" aria-label="Main navigation" className={classes.nav}>
            <NavLink to='/' className={classes.brand}>
                <h1>
                    {isMobile ? "CBC" : "THE CLEAN BRIGHT COMPANY"}
                </h1>
            </NavLink>
            <div className={classes.links}>
                {isAuthenticated && <p className={classes.user}>Hello, {userName}</p>}
                <NavLink to="/cart" className={classes.navItem}>
                    <ShoppingCartIcon className={classes.cartIcon}/>
                </NavLink>
                {isAuthenticated
                    ? <NavLink to="/" className={classes.loginBtn} onClick={handleLogout}>Logout</NavLink>
                    : <NavLink to="/login" className={classes.loginBtn}>Login</NavLink>
                }
            </div>
        </nav>
    </div>
    )
}
