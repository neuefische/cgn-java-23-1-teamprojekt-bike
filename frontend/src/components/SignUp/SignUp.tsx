import React, { ChangeEvent, FormEvent, useState } from 'react'
import axios from 'axios'
import Layout from "../Layout/Layout";
import {useNavigate} from "react-router-dom";

type Props = {
   addBikeInputRef: React.MutableRefObject<HTMLInputElement>
}
function SignUp(props: Props) {
   const [username, setUsername] = useState('')
   const [password, setPassword] = useState('')
   const navigate = useNavigate()

   function handleUsernameChange(event: ChangeEvent<HTMLInputElement>) {
      setUsername(event.target.value)
   }

   function handlePasswordChange(event: ChangeEvent<HTMLInputElement>) {
      setPassword(event.target.value)
   }

   function submitHandler(event: FormEvent<HTMLFormElement>) {
      event.preventDefault()
      axios
         .post('/api/users', {
            username,
            password,
         })
         .then(() => {
            navigate("/login", {state: {username}})
            setUsername('')
            setPassword('')
         })
         .catch((error) => {
            console.log(error)
         })
   }

   return (
       <Layout addBikeInputRef={props.addBikeInputRef}>
         <form onSubmit={submitHandler}>
            <input type={'text'} value={username} onChange={handleUsernameChange} />
            <input type={'password'} value={password} onChange={handlePasswordChange} />
            <button type={'submit'}>Create User</button>
         </form>
       </Layout>
   )
}

export default SignUp
