import React from 'react'
import { useNavigate, useParams } from 'react-router-dom'
import { Bike } from '../../models/Bike'
import './BikeDetails.css'
import Layout from '../Layout/Layout'
import useBikesApi from '../../hooks/useBikesApi'
import useAuth from '../../hooks/useAuth'
import { Helmet } from 'react-helmet'

type BikeDetailsProps = {
   addBikeRef: React.MutableRefObject<HTMLFormElement>
   galleryRef: React.MutableRefObject<HTMLDivElement>
}

function BikeDetails(props: BikeDetailsProps) {
   const user = useAuth(true)
   const navigate = useNavigate()
   !user && navigate('/login')

   const { id } = useParams<{ id: string }>()
   const { bikes, loading } = useBikesApi()
   const bike = (!!id && (bikes.find((bike) => bike.id === id) as Bike)) || null

   return (
      <Layout addBikeRef={props.addBikeRef} galleryRef={props.galleryRef}>
         <Helmet>
            <title>Bike Master 9000{bike?.title ? ` - ${bike?.title}` : ''}</title>
         </Helmet>
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
            <div className="suspense">Loading...</div>
         )}
      </Layout>
   )
}

export default BikeDetails
