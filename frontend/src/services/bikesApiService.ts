import axios from 'axios'
import { Bike } from '../models/Bike'
import Cookies from "js-cookie";

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
          .get("/api/csrf/")
          .then( () => axios
         .post(apiUrlSlug, { title: newBikeTitle }, {headers: {"X-XSRF-TOKEN": Cookies.get("XSRF-TOKEN")}})
         .then((response) => response.data)
         .catch((error) => console.error(error)))
   }

   async function put(bikeToUpdate: Bike) {
      return await axios
         .put(apiUrlSlug, bikeToUpdate)
         .then((response) => response.data)
         .catch((error) => console.error(error))
   }

   async function deleteBike(id: string) {
      return await axios
         .delete(apiUrlSlug+id)
         .then(response => response.data)
         .catch(error => console.error(error))
   }

   return { get, post, put, deleteBike }
}
