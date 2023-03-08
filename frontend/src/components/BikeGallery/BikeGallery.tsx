import React from 'react'
import { Bike } from '../../models/Bike'
import BikeCard from '../BikeCard/BikeCard'
import './BikeGallery.css'
import useAuth from '../../hooks/useAuth'
import { useNavigate } from 'react-router-dom'
import AddBike from "../AddBike/AddBike";
import useBikesApi from "../../hooks/useBikesApi";
import Layout from "../Layout/Layout";

type BikeGalleryProps = {
   bikes: Bike[]
   editBike: (updatedBike: Bike) => void
   deleteBike: (id: string) => void
   addBikeInputRef: React.MutableRefObject<HTMLInputElement>
}

function BikeGallery(props: BikeGalleryProps) {
   const currentUser = useAuth(true)
   const navigate = useNavigate()
   const { bikes, addBike, editBike, deleteBike, loading } = useBikesApi()

   !currentUser && navigate('/login')
   return (
       <Layout>
         <section className="gallery">
            {props.bikes.map((bike) => (
               <BikeCard key={bike.id} bike={bike} editBike={props.editBike} deleteBike={props.deleteBike} />
            ))}
         </section>
          <aside className="add-form">
             <AddBike addBikeInputRef={props.addBikeInputRef} addBike={addBike}/>
          </aside>
       </Layout>
   )
}

export default BikeGallery
