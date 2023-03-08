import React, { ReactNode } from 'react'
import Header from '../Header/Header'
import './Layout.css'

type Props = {
   children: ReactNode
   addBikeInputRef: React.MutableRefObject<HTMLInputElement>
}

function Layout(props: Props) {
   return (
      <>
         <Header addBikeInputRef={props.addBikeInputRef} />
         <main className="main">{props.children}</main>
         <footer className="footer">Imprint - 2023</footer>
      </>
   )
}

export default Layout
