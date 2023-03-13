import React, { ReactNode } from 'react'
import useAuth from '../../hooks/useAuth'
import axios from 'axios'

function LogOutButton(props: { children: ReactNode }) {
   const user = useAuth(false)

   function handleLogOut() {
      axios.post('/api/users/logout').then(() => {
         window.location.href = '/login'
      })
   }

   return user ? <div onClick={handleLogOut}>{props.children}</div> : ''
}

export default LogOutButton
