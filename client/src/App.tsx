import React from 'react'
import { BrowserRouter, Route, Routes } from 'react-router-dom'
import DataFetcher from './DataFetcher';
import Navbar from './components/navbar'
import Home from './pages/home/Home'


// page components

// styles
import classes from './app.module.css'

function App() {

  return (
    <div>
            <BrowserRouter>
                <div className={classes.container}>
                    <Navbar />
                    <DataFetcher />
                    <Routes>
                        <Route path="/" element={<Home />} />
                    </Routes>
                </div>
            </BrowserRouter>
    </div>
  );
}

export default App
