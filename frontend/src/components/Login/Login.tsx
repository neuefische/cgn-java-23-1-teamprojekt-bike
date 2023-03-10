import React, { ChangeEvent, FormEvent, useState } from 'react'
import axios from 'axios'
import { Link, useLocation, useNavigate } from 'react-router-dom'
import Layout from '../Layout/Layout'
import useAuth from '../../hooks/useAuth'
import './Login.css'

type Props = {
   addBikeRef: React.MutableRefObject<HTMLFormElement>
   galleryRef: React.MutableRefObject<HTMLDivElement>
}

function Login(props: Props) {
   const location = useLocation()
   const navigate = useNavigate()
   const currentUser = useAuth(false)
   !!currentUser && navigate('/')
   const [username, setUsername] = useState(location.state?.username || '')
   const [password, setPassword] = useState('')

   function handleUsernameChange(event: ChangeEvent<HTMLInputElement>) {
      setUsername(event.target.value)
   }

   function handlePasswordChange(event: ChangeEvent<HTMLInputElement>) {
      setPassword(event.target.value)
   }

   function submitHandler(event: FormEvent<HTMLFormElement>) {
      event.preventDefault()
      axios
         .post(
            '/api/users/login',
            {},
            {
               headers: {
                  Authorization: `Basic ${window.btoa(`${username}:${password}`)}`,
               },
            }
         )
         .then(() => {
            navigate(window.sessionStorage.getItem('signInRedirect') || '/')
         })
         .catch((error) => {
            console.log(error)
         })
   }

   return (
      <Layout addBikeRef={props.addBikeRef} galleryRef={props.galleryRef}>
         <form className="login" onSubmit={submitHandler}>
            <h1 className="login__title">Log in</h1>
            <label className="login__label">
               <span className="login__label--title">Username:</span>
               <input className="login__input" type={'text'} value={username} onChange={handleUsernameChange} />
            </label>

            <label className="login__label">
               <span className="login__label--title">Password:</span>
               <input className="login__input" type={'password'} value={password} onChange={handlePasswordChange} autoFocus={!!location.state?.username} />
            </label>
            <button className="login__submit" type={'submit'}>
               Log in
            </button>
            <Link className="login__link" to={'/signup'}>
               New to Bike Master 9000? Sign up here!
            </Link>
         </form>
      </Layout>
   )
}

export default Login
