import React from 'react'
import './Header.css'
import logo from '../../assets/logo.png'
import title from '../../assets/title.png'
import { useMediaQuery } from 'react-responsive'
import {Link} from "react-router-dom";

function Header() {
   const isTabletOrMobile = useMediaQuery({ query: '(max-width: 900px)' })
   return (
      <header className="header">
         <div className={'header__container' + (isTabletOrMobile ? '' : ' horizontal')}>
            <div className="header__container--third">
               <img className={"header__logo"+ (isTabletOrMobile ? '' : ' squared')} src={logo} alt="" />
            </div>
            <div className="header__container--third">
               <img className="header__logo" src={title} alt="Mega bikes 9000" />
            </div>
            <nav className="header__container--third navigation">
               <Link to={"/"}>Home</Link>
            </nav>
         </div>
      </header>
   )
}

export default Header
