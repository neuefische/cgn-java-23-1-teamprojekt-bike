import { useEffect, useState } from 'react'
import { Bike } from '../models/Bike'
import bikesApiService from '../services/bikesApiService'

function useBikesApi() {
   const [bikes, setBikes] = useState<Bike[]>([])
   const [loading, setLoading] = useState<boolean>(true)

   useEffect(() => {
      fetchBikes()
   }, [])

   function fetchBikes() {
      setLoading(true)
      bikesApiService
         .get()
         .then((incomingBikes) => {
            setBikes(incomingBikes)
         })
         .finally(() => {
            setLoading(false)
         })
   }

   function addBike(newBikeTitle: string) {
      setLoading(true)
      bikesApiService
         .post(newBikeTitle)
         .then((incomingBike) => {
            setBikes([...bikes, incomingBike])
         })
         .finally(() => {
            setLoading(false)
         })
   }
   function deleteBike(id: string): void {
      bikesApiService
          .deleteBike(id)
          .then((bikeToDelete): Bike[] => {
             return bikes.filter(bike => (bike !== bikeToDelete))
          })
          .then(newBikeArray => setBikes(newBikeArray))
   }

   return { loading, bikes, addBike, deleteBike }
}

export default useBikesApi
