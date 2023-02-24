import React, {ChangeEvent, useState} from 'react'
import { Bike } from '../../models/Bike'
import { Link } from 'react-router-dom'
import './BikeCard.css'

type BikeCardProps = {
   bike: Bike
   editBike: (updatedBike: Bike) => void
}

function BikeCard(props: BikeCardProps) {
    const [editMode, setEditMode] = useState(false)
    const [updatedTitle, setUpdatedTitle] = useState(props.bike.title)
    function handleEditButton(){
        setEditMode(!editMode)
    }
    function handleUpdateTitle(event: ChangeEvent<HTMLInputElement>){
        setUpdatedTitle(event.target.value)
    }
    function handleSaveChanges(){
        props.editBike({...props.bike, title: updatedTitle})
    }
    function handleCancelEdit(){
        setEditMode(false)
        setUpdatedTitle(props.bike.title)
    }
   return (
       <>
       {!editMode?
          <div className="gallery__card" id={props.bike.id}>
             <h2 className="gallery__card--title">{props.bike.title}</h2>
              <Link className="gallery__card--button" to={'details/' + props.bike.id}>
                Go to details
             </Link>
              <button onClick={handleEditButton} className={"gallery__card--button"}>Edit</button>
          </div>:
           <div className={"gallery__card"}>
               Title: <input className={"add-form__input"} value={updatedTitle} onChange={handleUpdateTitle}/>
               <button className="gallery__card--button" onClick={handleSaveChanges}>Save Changes</button>
               <button className="gallery__card--button" onClick={handleCancelEdit}>Save Cancel</button>
           </div>
       }
       </>
   )}

export default BikeCard