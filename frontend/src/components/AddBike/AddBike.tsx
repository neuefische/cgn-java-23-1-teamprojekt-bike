import React, {ChangeEvent, FormEvent, useState} from "react";
type inputProps = {
    handleSubmit(title: string): Promise<void>
}

export default function AddBike(props: inputProps){
    const [inputTitle, setTitle] = useState<string>("")

    function handleSubmit(event: FormEvent<HTMLFormElement>) {
        props.handleSubmit(inputTitle)
            .then(() =>setTitle(""))

    }
    function handleTitleChange(event: ChangeEvent<HTMLInputElement>){
        setTitle(event.target.value)
    }

    return (
        <form onSubmit={handleSubmit} className={"add-form"}>
            <input className={"input-title"} onChange={handleTitleChange} type={"text"}/>
            <button>Add new Bike</button>
        </form>
            )
}