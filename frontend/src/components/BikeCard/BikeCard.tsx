import React, { ChangeEvent, useState } from 'react'
import { Bike } from '../../models/Bike'
import { Link } from 'react-router-dom'
import './BikeCard.css'
import axios from 'axios'

type BikeCardProps = {
   bike: Bike
   editBike: (updatedBike: Bike) => void
   deleteBike: (id: string) => void
}

function BikeCard(props: BikeCardProps) {
   const [editMode, setEditMode] = useState(false)
   const [updatedTitle, setUpdatedTitle] = useState(props.bike.title)

   const [file, setFile] = React.useState<File | null>(null)

   function handleEditButton() {
      setEditMode(!editMode)
   }

   function handleUpdateTitle(event: ChangeEvent<HTMLInputElement>) {
      setUpdatedTitle(event.target.value)
   }

   function handleSaveChanges() {
      if (file) {
         let url = ''
         const payload = new FormData()
         payload.set('file', file)
         axios
            .post('/api/bikes/images', payload)
            .then((res) => {
               url = res.data
            })
            .catch((err) => {
               console.error(err)
            })
            .finally(() => {
               props.editBike({ ...props.bike, title: updatedTitle, imageUrl: url })
            })
      } else {
         props.editBike({ ...props.bike, title: updatedTitle })
         setEditMode(false)
      }
   }

   function handleCancelEdit() {
      setEditMode(false)
      setUpdatedTitle(props.bike.title)
   }

   function handleDelete() {
      props.deleteBike(props.bike.id)
   }

   const handleFileChange = (event: React.ChangeEvent<HTMLInputElement>) => {
      if (event.target.files && event.target.files.length > 0) {
         setFile(event.target.files[0])
      }
   }

   return (
      <>
         {!editMode ? (
            <div className="card" id={props.bike.id}>
               <h2 className="card__title">{props.bike.title}</h2>
               {props.bike.imageUrl && <img src={props.bike.imageUrl} alt={props.bike.title} className="card__image" />}
               <div className="card__controls">
                  <Link className="card__button" to={'details/' + props.bike.id}>
                     Go to details
                  </Link>
                  <div className="card__controls--horizontal">
                     <button onClick={handleEditButton} className="card__button inline-half">
                        Edit
                     </button>
                     <button onClick={handleDelete} className="card__button inline-half">
                        Delete bike
                     </button>
                  </div>
               </div>
            </div>
         ) : (
            <div className="card">
               <h2 className="card__title">Edit bike</h2>
               <label className="card__edit-label">
                  <span className="card__edit-label--title">Title:</span>
                  <div className="card__edit-input--wrapper">
                     <input className="card__edit-input" value={updatedTitle} onChange={handleUpdateTitle} />
                  </div>
               </label>
               <div className="card__controls">
                  <label className="card__button" htmlFor="imageUpload">
                     Upload image
                  </label>
                  <input id="imageUpload" className="hidden" type="file" onChange={handleFileChange} accept={'image/jpeg, image/png'} />

                  <div className="card__controls--horizontal">
                     <button className="card__button inline-half" onClick={handleSaveChanges}>
                        Save changes
                     </button>
                     <button className="card__button inline-half" onClick={handleCancelEdit}>
                        Cancel
                     </button>
                  </div>
               </div>
            </div>
         )}
      </>
   )
}

export default BikeCard
