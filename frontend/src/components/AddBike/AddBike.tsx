import React, { ChangeEvent, FormEvent, useState } from 'react'
import useBikesApi from '../../hooks/useBikesApi'

export default function AddBike() {
   const [inputTitle, setInputTitle] = useState<string>('')

   const { addBike } = useBikesApi()

   function handleSubmit(event: FormEvent<HTMLFormElement>) {
      event.preventDefault()
      addBike(inputTitle)
      setInputTitle('')
   }

   function handleTitleChange(event: ChangeEvent<HTMLInputElement>) {
      setInputTitle(event.target.value)
   }

   return (
      <form className="add-form" onSubmit={(event) => handleSubmit(event)}>
         <input className="add-form__input" type="text" value={inputTitle} onChange={handleTitleChange} />
         <button className="add-form__button">Add new Bike</button>
      </form>
   )
}
