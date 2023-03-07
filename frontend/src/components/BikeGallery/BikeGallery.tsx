import React from 'react'
import { Bike } from '../../models/Bike'
import BikeCard from '../BikeCard/BikeCard'
import './BikeGallery.css'
import useAuth from '../../hooks/useAuth'
import { useNavigate } from 'react-router-dom'

type BikeGalleryProps = {
   bikes: Bike[]
   editBike: (updatedBike: Bike) => void
   deleteBike: (id: string) => void
}

function BikeGallery(props: BikeGalleryProps) {
   const currentUser = useAuth(true)
   const navigate = useNavigate()
   return currentUser ? (
      <section className="gallery">
         {props.bikes.map((bike) => (
            <BikeCard key={bike.id} bike={bike} editBike={props.editBike} deleteBike={props.deleteBike} />
         ))}
      </section>
   ) : (
      <>{navigate('/login')}</>
   )
}

export default BikeGallery
