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
    const addBikeInputRef = useRef() as React.MutableRefObject<HTMLInputElement>


   return (
      <div id="app">

            <Routes>
               <Route path="/" element={<BikeGallery addBikeInputRef={addBikeInputRef}/>} />
               <Route path="/login" element={<Login  addBikeInputRef={addBikeInputRef}/>} />
               <Route path="/signup" element={<SignUp  addBikeInputRef={addBikeInputRef}/>} />
               <Route path="/details/:id" element={<BikeDetails addBikeInputRef={addBikeInputRef}/>} />
            </Routes>
      </div>
   )
}

export default App
