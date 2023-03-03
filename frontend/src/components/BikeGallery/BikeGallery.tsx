import React from 'react'
import { Bike } from '../../models/Bike'
import BikeCard from '../BikeCard/BikeCard'
import './BikeGallery.css'

type BikeGalleryProps = {
   bikes: Bike[]
   editBike: (updatedBike: Bike) => void
   deleteBike: (id: string) => void
}

function BikeGallery(props: BikeGalleryProps) {
   return (
      <section className="gallery">
         {props.bikes.map((bike) => (
            <BikeCard key={bike.id} bike={bike} editBike={props.editBike} deleteBike={props.deleteBike} />
         ))}
      </section>
   )
}

export default BikeGallery
