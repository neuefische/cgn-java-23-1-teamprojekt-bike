import React, { ChangeEvent, FormEvent, useState } from 'react'
import axios from 'axios'
import { Link, useLocation, useNavigate } from 'react-router-dom'
import Layout from '../Layout/Layout'
import useAuth from '../../hooks/useAuth'

type Props = {
   addBikeInputRef: React.MutableRefObject<HTMLInputElement>
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
      <Layout addBikeInputRef={props.addBikeInputRef}>
         <form onSubmit={submitHandler}>
            <input type={'text'} value={username} onChange={handleUsernameChange} />
            <input type={'password'} value={password} onChange={handlePasswordChange} autoFocus={!!location.state?.username} />
            <button type={'submit'}>Log in</button>
            <p>
               <Link to={'/signup'}>New to Bike Master 9000? Sign up here!</Link>
            </p>
         </form>
      </Layout>
   )
}

export default Login
