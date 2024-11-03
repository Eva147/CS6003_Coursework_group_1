import React from 'react'
import { BrowserRouter, Route, Routes } from 'react-router-dom'
import Navbar from './components/navbar'
import Footer from './components/footer'
import Chat from './components/chat'

// page components
import Home from './pages/home/Home'

// styles
import classes from './app.module.css'

function App() {

  return (
      <BrowserRouter>
          <div className={classes.app}>
              <Navbar />
              <div className={classes.content}>
                <Routes>
                    <Route path="/" element={<Home />} />
                </Routes>
              </div>
              <Chat />
              {/* <Footer /> */}
          </div>
      </BrowserRouter>
  );
}

export default App
