import React from 'react'
import { Route, Routes } from 'react-router-dom'
import './App.css'

import BikeGallery from '../BikeGallery/BikeGallery'
import BikeDetails from '../BikeDetails/BikeDetails'
import Header from "../Header/Header";

import useBikesApi from '../../hooks/useBikesApi'



function App() {
   const { bikes, addBike, editBike, deleteBike, loading } = useBikesApi()

   return (
      <div id="app">
          <Header/>
          <main className="main">
              <Routes>
                  <Route path="/" element={!loading &&
                      <BikeGallery bikes={bikes} addBike={addBike} editBike={editBike} deleteBike={deleteBike}/>}/>
                  <Route path="/details/:id" element={!loading && <BikeDetails bikes={bikes}/>}/>
              </Routes>
          </main>
          <footer className="footer">Footer - Impressum</footer>
      </div>
   )
}

export default App
