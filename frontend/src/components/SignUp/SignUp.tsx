import { ChangeEvent, FormEvent, useState } from 'react'
import axios from 'axios'

function SignUp() {
   const [username, setUsername] = useState('')
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
         .post('/api/users', {
            username,
            password,
         })
         .then((res) => {
            console.log(res)
            console.log(res?.data)
            setUsername('')
            setPassword('')
         })
         .catch((error) => {
            console.log(error)
         })
   }

   return (
      <form onSubmit={submitHandler}>
         <input type={'text'} value={username} onChange={handleUsernameChange} />
         <input type={'password'} value={password} onChange={handlePasswordChange} />
         <button type={'submit'}>Create User</button>
      </form>
   )
}

export default SignUp