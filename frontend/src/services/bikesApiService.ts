import React from 'react'
import axios from 'axios'
import { Bike } from '../models/Bike'

const apiUrlSlug = '/api/bikes'

/*async*/ function get(setBikes: React.Dispatch<React.SetStateAction<Bike[]>>) {
   //const response = await (axios request here)
   return axios
      .get(apiUrlSlug)
      .then((response) => {
         setBikes(response.data)
      })
      .catch((error) => {
         console.log('Error while fetching bikes: ', error)
      })
}
// return only response

async function post(newBikeTitle: string) {
   return await axios.post(apiUrlSlug, {title: newBikeTitle})
}

export default {
   get, post
}
