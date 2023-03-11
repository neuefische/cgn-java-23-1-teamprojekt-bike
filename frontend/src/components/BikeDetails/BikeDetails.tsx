import React from 'react'
import { useParams } from 'react-router-dom'
import { Bike } from '../../models/Bike'
import './BikeDetails.css'
import Layout from '../Layout/Layout'
import useBikesApi from '../../hooks/useBikesApi'

type BikeDetailsProps = {
   addBikeRef: React.MutableRefObject<HTMLFormElement>
   galleryRef: React.MutableRefObject<HTMLDivElement>
}

function BikeDetails(props: BikeDetailsProps) {
   const { id } = useParams<{ id: string }>()
   const { bikes, loading } = useBikesApi()
   const bike = (!!id && (bikes.find((bike) => bike.id === id) as Bike)) || null

   return (
      <Layout addBikeRef={props.addBikeRef} galleryRef={props.galleryRef}>
         {!loading ? (
            <div className="bike-details--wrapper">
               <div className="bike-details">
                  <h1 className="bike-details--title">{bike?.title}</h1>
                  {bike?.imageUrl && (
                     <div className="bike-details--unit">
                        <img className="bike-details--image" src={bike.imageUrl} alt={bike?.title} />
                     </div>
                  )}
               </div>
            </div>
         ) : (
            <div>Loading...</div>
         )}
      </Layout>
   )
}

export default BikeDetails
