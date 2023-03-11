import axios from 'axios'
import { useEffect, useState } from 'react'
import { useLocation, useNavigate } from 'react-router-dom'

export type User = {
   id: string
   username: string
   role: string
}

function useAuth(redirectToSignIn?: boolean) {
   const [user, setUser] = useState<User | null>(null)
   const { pathname } = useLocation()
   const navigate = useNavigate()

   useEffect(() => {
      axios
         .get('/api/users/me')
         .then((res) => {
            setUser(res.data)
         })
         .catch((e) => {
            if (redirectToSignIn && e.response.status === 401) {
               window.sessionStorage.setItem('signInRedirect', pathname || '/')
               navigate('/login')
            }
         })
   }, [pathname, navigate, redirectToSignIn])

   return user
}

export default useAuth
