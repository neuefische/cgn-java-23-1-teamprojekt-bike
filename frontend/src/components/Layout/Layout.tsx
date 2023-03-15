import React, { ReactNode } from 'react'
import Header from '../Header/Header'
import './Layout.css'
import { Link, useLocation } from 'react-router-dom'
import { Helmet } from 'react-helmet'
import springBootImg from '../../assets/spring-boot.png'
import reactImg from '../../assets/react-transparent.png'
import cloudinaryImg from '../../assets/cloudinary.png'

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
               <div className="footer--powered">
                  Powered by
                  <div className="footer--logos">
                     <img className="footer--logo" src={springBootImg} alt="Spring Boot" />
                     <img className="footer--logo react" src={reactImg} alt="React" />
                     <img className="footer--logo cloudinary" src={cloudinaryImg} alt="Cloudinary" />
                  </div>
               </div>
            )}
         </footer>
      </>
   )
}

export default Layout
