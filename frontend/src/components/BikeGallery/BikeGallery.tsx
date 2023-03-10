import React from 'react'
import BikeCard from '../BikeCard/BikeCard'
import './BikeGallery.css'
import './scroll.css'
import AddBike from '../AddBike/AddBike'
import useBikesApi from '../../hooks/useBikesApi'
import Layout from '../Layout/Layout'
import { useMediaQuery } from 'react-responsive'
import { useNavigate } from 'react-router-dom'
import useAuth from '../../hooks/useAuth'

type BikeGalleryProps = {
   addBikeRef: React.MutableRefObject<HTMLFormElement>
   galleryRef: React.MutableRefObject<HTMLDivElement>
}

function BikeGallery(props: BikeGalleryProps) {
   const currentUser = useAuth(false)
   const { bikes, addBike, editBike, deleteBike, loading } = useBikesApi()
   const navigate = useNavigate()
   !currentUser && navigate('/login')

   const isTabletOrMobile = useMediaQuery({ query: '(max-width: 900px)' })
   return (
      <Layout addBikeRef={props.addBikeRef} galleryRef={props.galleryRef}>
         {!loading ? (
            <section className={'gallery' + (isTabletOrMobile ? ' mobile-view' : '')} ref={props.galleryRef}>
               {bikes?.map((bike) => (
                  <BikeCard key={bike.id} bike={bike} editBike={editBike} deleteBike={deleteBike} />
               ))}
               <aside className="add-form">
                  <AddBike addBikeRef={props.addBikeRef} addBike={addBike} />
               </aside>
            </section>
         ) : (
            <div>Loading...</div>
         )}
      </Layout>
   )
}

export default BikeGallery
