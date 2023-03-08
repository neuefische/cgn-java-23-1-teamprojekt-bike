import React, { ChangeEvent, FormEvent, useState } from 'react'
import axios from 'axios'
import Layout from '../Layout/Layout'
import { Link, useNavigate } from 'react-router-dom'
import '../Login/Login.css'

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
            navigate('/login', { state: { username } })
            setUsername('')
            setPassword('')
         })
         .catch((error) => {
            console.log(error)
         })
   }

   return (
      <Layout addBikeInputRef={props.addBikeInputRef}>
         <form className="signup" onSubmit={submitHandler}>
            <h1 className="signup__title">Sign up</h1>

            <label className="signup__label">
               <span className="signup__label--title">Username:</span>
               <input className="signup__input" type={'text'} value={username} onChange={handleUsernameChange} />
            </label>

            <label className="signup__label">
               <span className="signup__label--title">Password:</span>
               <input className="signup__input" type={'password'} value={password} onChange={handlePasswordChange} />
            </label>

            <button className="signup__submit" type={'submit'}>
               Create User
            </button>

            <Link className="signup__link" to={'/login'}>
               Already have an account? Log in here!
            </Link>
         </form>
      </Layout>
   )
}

export default SignUp
