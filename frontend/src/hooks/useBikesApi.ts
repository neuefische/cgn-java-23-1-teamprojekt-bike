import { useEffect, useState } from 'react'
import { Bike } from '../models/Bike'
import bikeApiService from '../services/bikesApiService'

function useBikesApi() {
   const [bikes, setBikes] = useState<Bike[]>([])
   const [loading, setLoading] = useState<boolean>(true)

   useEffect(() => {
      fetchBikes()
   }, [])

   function fetchBikes() {
      setLoading(true)
      bikeApiService()
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
      bikeApiService()
         .post(newBikeTitle)
         .then((incomingBike) => {
            setBikes([...bikes, incomingBike])
         })
         .finally(() => {
            setLoading(false)
         })
   }

   function editBike(updatedBike: Bike) {
      setLoading(true)
      bikeApiService()
         .put(updatedBike)
         .then((incomingBike: Bike) => {
            setBikes([...bikes.filter((bike) => bike.id !== incomingBike.id), incomingBike])
         })
         .finally(() => {
            setLoading(false)
         })
   }

   return { loading, bikes, addBike, editBike }
}

export default useBikesApi
