import React, { ChangeEvent, FormEvent, useState } from 'react'
import './AddBike.css'

type AddBikeProps = {
   addBike: (newBikeTitle: string) => void
}

export default function AddBike(props: AddBikeProps) {
   const [inputTitle, setInputTitle] = useState<string>('')

   function handleSubmit(event: FormEvent<HTMLFormElement>) {
      event.preventDefault()
      props.addBike(inputTitle)
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
