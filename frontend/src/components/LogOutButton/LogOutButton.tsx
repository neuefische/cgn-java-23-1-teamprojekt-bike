import React, { ReactNode } from 'react'
import useAuth from '../../hooks/useAuth'
import axios from 'axios'

function LogOutButton(props: { children: ReactNode }) {
   const user = useAuth(false)

   // const location = useLocation()

   function handleLogOut() {
      axios.post('/api/users/logout').then(() => {
         // window.sessionStorage.setItem('signInRedirect', location.pathname || '/') // TODO: This is a subject of discussion!
         window.location.href = '/login'
      })
   }

   return user ? <div onClick={handleLogOut}>{props.children}</div> : null
}

export default LogOutButton
