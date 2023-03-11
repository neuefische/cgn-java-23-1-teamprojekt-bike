import React from 'react'
import { useParams } from 'react-router-dom'
import { Bike } from '../../models/Bike'
import './BikeDetails.css'
import Layout from "../Layout/Layout";
import useBikesApi from "../../hooks/useBikesApi";

type BikeDetailsProps = {
   addBikeInputRef: React.MutableRefObject<HTMLInputElement>
}

function BikeDetails(props: BikeDetailsProps) {
   const { id } = useParams<{ id: string }>()
   const { bikes, loading } = useBikesApi()
   const bike = (!!id && (bikes.find((bike) => bike.id === id) as Bike)) || null

   return (
       <Layout addBikeInputRef={props.addBikeInputRef}>
           {!loading?
         <div className="bike-details">
            <h1 className="bike-details--title">{bike?.title}</h1>
         </div>:
               <div>Loading...</div>
           }
       </Layout>
   )
}

export default BikeDetails
