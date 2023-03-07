import React, { useRef } from 'react'
import { Route, Routes } from 'react-router-dom'
import axios from 'axios'
import './App.css'

import BikeGallery from '../BikeGallery/BikeGallery'
import BikeDetails from '../BikeDetails/BikeDetails'
import Header from '../Header/Header'

import useBikesApi from '../../hooks/useBikesApi'
import AddBike from '../AddBike/AddBike'
import Login from '../Login/Login'

// @ts-ignore
axios.interceptors.request.use((config) => {
   return (
      fetch('/api/csrf').then((response) => {
         config.headers['X-XSRF-TOKEN'] = response.headers.get('XSRF-TOKEN')
         return config
      }),
      function (error: any) {
         return Promise.reject(error)
      }
   )
})

function App() {
   const { bikes, addBike, editBike, deleteBike, loading } = useBikesApi()

   const addBikeInputRef = useRef() as React.MutableRefObject<HTMLInputElement>

   return (
      <div id="app">
         <Header addBikeInputRef={addBikeInputRef} />
         <main className="main">
            <Routes>
               <Route path="/" element={<Login />} />
               <Route path="/gallery" element={!loading && <BikeGallery bikes={bikes} editBike={editBike} deleteBike={deleteBike} />} />
               <Route path="/details/:id" element={!loading && <BikeDetails bikes={bikes} />} />
            </Routes>
         </main>
         <aside className="add-form">
            <AddBike addBike={addBike} addBikeInputRef={addBikeInputRef} />
         </aside>
         <footer className="footer">Imprint - 2023</footer>
      </div>
   )
}

export default App
