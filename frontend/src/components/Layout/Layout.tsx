import React, { ReactNode } from 'react'
import Header from '../Header/Header'
import './Layout.css'

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
         <footer className="footer">Imprint - 2023</footer>
      </>
   )
}

export default Layout
