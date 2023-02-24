import React from 'react'
import { Bike } from '../../models/Bike'
import { Link } from 'react-router-dom'
import './BikeCard.css'

type BikeCardProps = {
   bike: Bike
}

function BikeCard(props: BikeCardProps) {
   return (
      <div className="gallery__card" id={props.bike.id}>
         <h2 className="gallery__card--title">{props.bike.title}</h2>
         <Link className="gallery__card--button" to={'details/' + props.bike.id}>
            Go to details
         </Link>
      </div>
   )
}

export default BikeCard
