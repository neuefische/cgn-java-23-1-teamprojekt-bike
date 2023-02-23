import React from 'react'
import { useParams } from 'react-router-dom'
import { Bike } from '../../models/Bike'

type BikeDetailsProps = {
   bikes: Bike[]
}

function BikeDetails(props: BikeDetailsProps) {
   const { id } = useParams<{ id: string }>()
   const bike = (!!id && (props.bikes.find((bike) => bike.id === id) as Bike)) || null

   return (
      <div className="bike-details">
         <h1 className="bike-details--title">{bike?.title}</h1>
      </div>
   )
}

export default BikeDetails
