import React, { ReactNode } from 'react'
import Header from '../Header/Header'
import './Layout.css'
import { Link, useLocation } from 'react-router-dom'
import { Helmet } from 'react-helmet'

type Props = {
   children: ReactNode
   addBikeRef: React.MutableRefObject<HTMLFormElement>
   galleryRef: React.MutableRefObject<HTMLDivElement>
}

function Layout(props: Props) {
   const { pathname } = useLocation()
   const isCreditsPage = pathname === '/credits'
   return (
      <>
         <Helmet>
            <title>Bike Master 9000</title>
         </Helmet>
         <Header addBikeRef={props.addBikeRef} galleryRef={props.galleryRef} />
         <main className="main">{props.children}</main>
         <footer className="footer">
            {!isCreditsPage ? (
               <p>
                  <Link to={'/credits'}>Credits</Link> - 2023
               </p>
            ) : (
               <div>
                  Powered by
                  <div className="footer--logo">
                     <div className="footer--logo--neuefische">{/*<img src={} />*/}</div>
                  </div>
               </div>
            )}
         </footer>
      </>
   )
}

export default Layout
