import React from 'react'
import { Bike } from '../../models/Bike'
import BikeCard from '../BikeCard/BikeCard'
import { useParams } from 'react-router-dom'

type BikeGalleryProps = {
   bikes: Bike[]
}

function BikeGallery(props: BikeGalleryProps) {
   const { id } = useParams()

   return (
      <div className="gallery">
         {!id ? props.bikes.map((bike) => <BikeCard key={bike.id} bike={bike} />) : <BikeCard bike={props.bikes.find((bike) => bike.id === id) as Bike} />}
      </div>
   )
}

export default BikeGallery
