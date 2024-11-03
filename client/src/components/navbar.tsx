import React from 'react'
import {NavLink} from 'react-router-dom'

//styles
import classes from './navbar.module.css';


export default function Navbar() {
    return (
    <div className={classes.navbar}>
        <nav>
            <NavLink to='/' className={classes.brand}><h1>THE CLEAN BRIGHT COMPANY</h1></NavLink>
              <>
                  <NavLink to="/login" className={classes.loginBtn}>Login</NavLink>
                  <NavLink to="/signup" className={classes.loginBtn}>Signup</NavLink>
              </>
        </nav>
    </div>
    )
}
