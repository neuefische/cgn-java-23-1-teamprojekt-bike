import {ChangeEvent, FormEvent, useState} from "react";

export default function Login(){
    const [username, setUsername] = useState("")
    const [password, setPassword] = useState("")

    function handleUsernameChange(event: ChangeEvent<HTMLInputElement>) {
        setUsername(event.target.value)
    }
    function handlePasswordChange(event: ChangeEvent<HTMLInputElement>) {
        setPassword(event.target.value)
    }

    function submitHandler(event: FormEvent<HTMLFormElement>) {
        event.preventDefault()

    }

    return (
        <form onSubmit={submitHandler}>
            <input type={"text"} value={username} onChange={handleUsernameChange}/>
            <input type={"password"} value={password} onChange={handlePasswordChange}/>
            <button type={"submit"}>Submit</button>
        </form>

        )
}