import React from 'react'
import axios from 'axios'
import { Bike } from '../models/Bike'

const apiUrlSlug = '/api/bikes'

function get(setBikes: React.Dispatch<React.SetStateAction<Bike[]>>) {
   return axios
      .get(apiUrlSlug)
      .then((response) => {
         setBikes(response.data)
      })
      .catch((error) => {
         console.log('Error while fetching bikes: ', error)
      })
}

export default {
   get,
}
