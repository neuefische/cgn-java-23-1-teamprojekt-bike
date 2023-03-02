import React from 'react'
import './Header.css'
import logo from '../../assets/logo.png'
import title from '../../assets/title.png'
import { useMediaQuery } from 'react-responsive'

function Header() {
   const isTabletOrMobile = useMediaQuery({ query: '(max-width: 900px)' })
   return (
      <header className="header">
         <div className={'header__container' + (isTabletOrMobile ? '' : ' horizontal')}>
            <div className="header__container--third">
               <img className="header__logo" src={logo} alt="" />
            </div>
            <div className="header__container--third">
               <img className="header__logo" src={title} alt="Mega bikes 9000" />
            </div>
            <div className="header__container--third"></div>
         </div>
      </header>
   )
}

export default Header
