import React from 'react'
import { Bike } from '../../models/Bike'
import { Link } from 'react-router-dom'
import './BikeCard.css'

type BikeCardProps = {
   bike: Bike
   deleteBike: (id: string) => void
}

function BikeCard(props: BikeCardProps) {
    function handleDelete() {
        props.deleteBike(props.bike.id)
    }

    return (
      <div className="gallery__card" id={props.bike.id}>
         <h2 className="gallery__card--title">{props.bike.title}</h2>
         <Link className="gallery__card--button" to={'details/' + props.bike.id}>
            Go to details
         </Link>
          <button onClick={handleDelete}>Delete this bike</button>
      </div>
   )
}

export default BikeCard
