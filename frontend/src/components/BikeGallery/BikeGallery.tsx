import React from 'react'
import { Bike } from '../../models/Bike'
import BikeCard from '../BikeCard/BikeCard'
import AddBike from '../AddBike/AddBike'
import './BikeGallery.css'

type BikeGalleryProps = {
   bikes: Bike[]
   addBike: (newBikeTitle: string) => void
   editBike: (updatedBike: Bike) => void
   deleteBike: (id:string) => void
}

function BikeGallery(props: BikeGalleryProps) {
   return (
      <>
         <div className="gallery">
            {props.bikes.map((bike) => (
               <BikeCard key={bike.id} bike={bike} editBike={props.editBike} deleteBike={props.deleteBike}/>
            ))}
         </div>
         <AddBike addBike={props.addBike} />
      </>
   )
}

export default BikeGallery
