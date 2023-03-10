import React, { useRef } from 'react'
import { Route, Routes } from 'react-router-dom'
import axios from 'axios'
import Cookies from 'js-cookie'
import './App.css'

import BikeGallery from '../BikeGallery/BikeGallery'
import BikeDetails from '../BikeDetails/BikeDetails'
import SignUp from '../SignUp/SignUp'
import Login from '../Login/Login'

// @ts-ignore
axios.interceptors.request.use(
   function (config) {
      return fetch('/api/csrf/').then(() => {
         config.headers['X-XSRF-TOKEN'] = Cookies.get('XSRF-TOKEN')
         return config
      })
   },
   function (error) {
      return Promise.reject(error)
   }
)

function App() {
   const addBikeRef = useRef() as React.MutableRefObject<HTMLFormElement>
   const galleryRef = useRef() as React.MutableRefObject<HTMLDivElement>

   return (
      <div id="app">
         <Routes>
            <Route path="/" element={<BikeGallery addBikeRef={addBikeRef} galleryRef={galleryRef} />} />
            <Route path="/login" element={<Login addBikeRef={addBikeRef} galleryRef={galleryRef} />} />
            <Route path="/signup" element={<SignUp addBikeRef={addBikeRef} galleryRef={galleryRef} />} />
            <Route path="/details/:id" element={<BikeDetails addBikeRef={addBikeRef} galleryRef={galleryRef} />} />
         </Routes>
      </div>
   )
}

export default App
