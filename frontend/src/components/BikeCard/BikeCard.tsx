import React from 'react'
import { Bike } from '../../models/Bike'

type BikeCardProps = {
   bike: Bike
}
function BikeCard(props: BikeCardProps) {
   return <div id={props.bike.id}>{props.bike.title}</div>
}

export default BikeCard
