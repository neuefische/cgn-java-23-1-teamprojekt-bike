import React, { useRef } from 'react'
import { Route, Routes } from 'react-router-dom'
import axios from 'axios'
import Cookies from 'js-cookie'
import './App.css'

import BikeGallery from '../BikeGallery/BikeGallery'
import BikeDetails from '../BikeDetails/BikeDetails'
import Header from '../Header/Header'

import useBikesApi from '../../hooks/useBikesApi'
import AddBike from '../AddBike/AddBike'
import SignUp from '../SignUp/SignUp'
import Login from '../Login/Login'
import useAuth from '../../hooks/useAuth'

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
               <Route path="/login" element={<Login />} />
               <Route path="/signup" element={<SignUp />} />
               <Route path="/" element={<BikeGallery addBikeInputRef={addBikeInputRef}/>} />
               <Route path="/details/:id" element={<BikeDetails} />} />
            </Routes>

      </div>
   )
}

export default App
