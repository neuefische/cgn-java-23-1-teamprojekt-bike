import React from 'react'
import './Header.css'
import logo from '../../assets/logo.png'
import title from '../../assets/title.png'
import { useMediaQuery } from 'react-responsive'
import useAuth from '../../hooks/useAuth'
import LogOutButton from '../LogOutButton/LogOutButton'
import { useLocation, useNavigate } from 'react-router-dom'

type HeaderProps = {
   addBikeRef: React.MutableRefObject<HTMLFormElement>
   galleryRef: React.MutableRefObject<HTMLDivElement>
}

function Header(props: HeaderProps) {
   const isTabletOrMobile = useMediaQuery({ query: '(max-width: 900px)' })
   const user = useAuth(false)
   const { pathname } = useLocation()
   const isNotTheGallery = pathname !== '/'
   const navigate = useNavigate()
   const handleScrollIntoAddBikeView = () => props.addBikeRef.current.scrollIntoView({ behavior: 'smooth' })
   const handleScrollIntoGalleryView = () => props.galleryRef.current.scrollTo({ top: 0, behavior: 'smooth' })

   return (
      <header className="header">
         <div className={'header__container' + (isTabletOrMobile ? '' : ' horizontal')}>
            <div className="header__container--third">
               <img className={'header__logo' + (!isTabletOrMobile ? '' : ' squared')} src={logo} alt="" />
            </div>
            <div className="header__container--third">
               <img className="header__logo" src={title} alt="Mega bikes 9000" />
            </div>
            <nav className={'header__container--third navigation' + (!isTabletOrMobile ? ' vertical' : '')}>
               {!!user && (
                  <>
                     <div onClick={isNotTheGallery ? () => navigate('/') : handleScrollIntoGalleryView}>Gallery</div>
                     <div onClick={handleScrollIntoAddBikeView}>Add new bike</div>
                     <LogOutButton>Log out</LogOutButton>
                  </>
               )}
            </nav>
         </div>
      </header>
   )
}

export default Header
