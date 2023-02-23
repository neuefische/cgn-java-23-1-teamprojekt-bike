import React from 'react'
import { Routes, Route } from 'react-router-dom'
import './App.scss'

import BikeGallery from '../BikeGallery/BikeGallery'
import useBikesApi from '../../hooks/useBikesApi'
import AddBike from "../AddBike/AddBike";

function App() {
   const { bikes, loading, addBike } = useBikesApi()

   return (
      <div id="app">
         <header className="header">Frontend will be here</header>
         <main className="main">
            <Routes>
                <Route path="/" element={!loading && <BikeGallery bikes={bikes}  addBike={addBike}/>} />
            </Routes>
         </main>
         <footer className="footer">Footer - Impressum</footer>
      </div>
   )
}

export default App
