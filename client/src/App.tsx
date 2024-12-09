import React from 'react'
import { BrowserRouter, Route, Routes } from 'react-router-dom'
import Navbar from './components/navbar'
import Footer from './components/footer'
import Chat from './components/chat'
import Catalog from './pages/catalog/Catalog'
import ProductPage from './pages/product/ProductPage'
import Cart from './pages/cart/cart';
import Login from './pages/auth/login';

// page components
import Home from './pages/home/Home'

// styles
import classes from './app.module.css'

function App() {
// run the backend: mvn spring-boot:run
  return (
      <BrowserRouter>
          <div className={classes.app}>
            <a href="#main" className={classes.skipLink}>
              Skip to main content
            </a>
            <header role="banner">
              <Navbar />
            </header>
            <main id="main" className={classes.content}>
              <Routes>
                  <Route path="/" element={<Home />} />
                  <Route path=":catalogId" element={<Catalog />} />
                  <Route path=":catalogId/:productId" element={<ProductPage />} />
                  <Route path="/cart" element={<Cart />} />
                  <Route path="/login" element={<Login />} />
              </Routes>
            </main>
            <Chat />
            <Footer />
          </div>
      </BrowserRouter>
  );
}

export default App
