import React from 'react'
import BikeCard from '../BikeCard/BikeCard'
import './BikeGallery.css'
import useAuth from '../../hooks/useAuth'
import {useNavigate} from 'react-router-dom'
import AddBike from "../AddBike/AddBike";
import useBikesApi from "../../hooks/useBikesApi";
import Layout from "../Layout/Layout";

type BikeGalleryProps = {
   addBikeInputRef: React.MutableRefObject<HTMLInputElement>
}

function BikeGallery(props: BikeGalleryProps) {
   const currentUser = useAuth(false)
    const { bikes, addBike, editBike, deleteBike, loading } = useBikesApi()
    const navigate = useNavigate()
    !currentUser && navigate("/login")
   return (
       <Layout addBikeInputRef={props.addBikeInputRef}> {!loading? ( <>
         <section className="gallery">
            {bikes?.map((bike) => (
               <BikeCard key={bike.id} bike={bike} editBike={editBike} deleteBike={deleteBike} />
            ))}
         </section>
          <aside className="add-form">
             <AddBike addBikeInputRef={props.addBikeInputRef} addBike={addBike}/>
          </aside>
       </>
       ):<div>Loading...</div>}
       </Layout>
   )
}

export default BikeGallery
