import React, { ReactNode } from 'react'
import Header from '../Header/Header'
import './Layout.css'
import { Link } from 'react-router-dom'

type Props = {
   children: ReactNode
   addBikeRef: React.MutableRefObject<HTMLFormElement>
   galleryRef: React.MutableRefObject<HTMLDivElement>
}

function Layout(props: Props) {
   return (
      <>
         <Header addBikeRef={props.addBikeRef} galleryRef={props.galleryRef} />
         <main className="main">{props.children}</main>
         <footer className="footer">
            <Link to={'/credits'}>Credits</Link> - 2023
         </footer>
      </>
   )
}

export default Layout
