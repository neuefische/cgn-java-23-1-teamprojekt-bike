import axios from 'axios'
import { Bike } from '../models/Bike'

const apiUrlSlug = '/api/bikes/'

export default function bikeApiService() {
   async function get() {
      return await axios
         .get(apiUrlSlug)
         .then((response) => response.data)
         .catch((error) => {
            console.error(error)
         })
   }

   async function post(newBikeTitle: string) {
      return await axios
         .post(apiUrlSlug, { title: newBikeTitle })
         .then((response) => response.data)
         .catch((error) => console.error(error))
   }

   async function put(bikeToUpdate: Bike) {
      return await axios
         .put(apiUrlSlug, bikeToUpdate)
         .then((response) => response.data)
         .catch((error) => console.error(error))
   }

   return { get, post, put }
}
