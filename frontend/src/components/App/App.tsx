import React from 'react'
import { Route, Routes } from 'react-router-dom'
import './App.scss'

import BikeGallery from '../BikeGallery/BikeGallery'
import BikeDetails from '../BikeDetails/BikeDetails'
import useBikesApi from '../../hooks/useBikesApi'

function App() {
   const { bikes, addBike, loading } = useBikesApi()

   return (
      <div id="app">
         <header className="header">Frontend will be here</header>
         <main className="main">
            <Routes>
               <Route path="/" element={!loading && <BikeGallery bikes={bikes} addBike={addBike} />} />
               <Route path="/details/:id" element={!loading && <BikeDetails bikes={bikes} />} />
            </Routes>
         </main>
         <footer className="footer">Footer - Impressum</footer>
      </div>
   )
}

export default App
