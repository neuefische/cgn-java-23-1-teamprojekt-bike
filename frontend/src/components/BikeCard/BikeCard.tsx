import React from 'react'
import { Bike } from '../../models/Bike'
import { Link } from 'react-router-dom'

type BikeCardProps = {
   bike: Bike
}

function BikeCard(props: BikeCardProps) {
   return (
      <div className="gallery__card" id={props.bike.id}>
         <h2 className="gallery__card--title">{props.bike.title}</h2>
         <Link to={'details/' + props.bike.id}>
            <button className="gallery__card--button">Go to details of Bike No. {props.bike.id}</button>
         </Link>
      </div>
   )
}

export default BikeCard
