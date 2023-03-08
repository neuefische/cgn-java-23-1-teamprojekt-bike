import { ChangeEvent, FormEvent, useState } from 'react'
import axios from 'axios'
import {Link, useNavigate} from 'react-router-dom'

function Login() {
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
            navigate('/')
         })
         .catch((error) => {
            console.log(error)
         })
   }

   return (
      <form onSubmit={submitHandler}>
         <input type={'text'} value={username} onChange={handleUsernameChange} />
         <input type={'password'} value={password} onChange={handlePasswordChange} />
         <button type={'submit'}>Log in</button>
         <p>
            <Link to={'/signup'}>New to Bike Master 9000? Sign up here!</Link>
         </p>
      </form>
   )
}

export default Login
