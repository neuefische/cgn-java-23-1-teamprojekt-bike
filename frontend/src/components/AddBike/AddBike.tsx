import React, { ChangeEvent, FormEvent, useState } from 'react'
import './AddBike.css'

type AddBikeProps = {
   addBike: (newBikeTitle: string) => void
}

function AddBike(props: AddBikeProps) {
   const [inputTitle, setInputTitle] = useState<string>('')

   function handleSubmit(event: FormEvent<HTMLFormElement>) {
      event.preventDefault()
      props.addBike(inputTitle.trim())
      setInputTitle('')
   }

   function handleTitleChange(event: ChangeEvent<HTMLInputElement>) {
      setInputTitle(event.target.value)
   }

   return (
      <form onSubmit={(event) => handleSubmit(event)}>
         <input className="add-form__input" type="text" value={inputTitle} onChange={handleTitleChange} />
         <button className="add-form__button" disabled={inputTitle.trim().length === 0}>
            Add new Bike
         </button>
      </form>
   )
}

export default AddBike
