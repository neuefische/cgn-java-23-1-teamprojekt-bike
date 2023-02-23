import { useEffect, useState } from 'react'
import { Bike } from '../models/Bike'
import bikesApiService from '../services/bikesApiService'

function useBikesApi() {
   const [bikes, setBikes] = useState<Bike[]>([])
   const [loading, setLoading] = useState<boolean>(true)

   useEffect(() => {
      fetchBikes()
   }, [loading])

   function fetchBikes() {
      bikesApiService
         .get()
         .then((incomingBikes) => {
            setBikes(incomingBikes)
         })
         .finally(() => {
            setLoading(false)
         })
   }

   function addBike(newBikeTitle: string){
      return bikesApiService.post(newBikeTitle)
          .then((r)=> setBikes([...bikes, r.data.bike]))
   }

   return { loading, bikes, addBike }
}

export default useBikesApi
