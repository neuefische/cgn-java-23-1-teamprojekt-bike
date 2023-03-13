import React from 'react'
import './Credits.css'
import { useMediaQuery } from 'react-responsive'
import { Link } from 'react-router-dom'

import Layout from '../Layout/Layout'

type Props = {
   addBikeRef: React.MutableRefObject<HTMLFormElement>
   galleryRef: React.MutableRefObject<HTMLDivElement>
}

function Credits(props: Props) {
   const isTabletOrMobile = useMediaQuery({ query: '(max-width: 900px)' })
   return (
      <Layout addBikeRef={props.addBikeRef} galleryRef={props.galleryRef}>
         <div className="credits--wrapper">
            <div className={'credits' + (isTabletOrMobile ? ' mobile' : '')}>
               <h1>Credits</h1>
               <p>
                  This website was created as a part of the <Link to={'https://www.neuefische.de/'}>neufische</Link>
                  {"'s "}
                  <Link to={'https://www.neuefische.de/bootcamp/java-development'}>Java Development Bootcamp</Link> as a team project by{' '}
                  <Link to={'https://github.com/helenrendich'}>Helen</Link>, <Link to={'https://github.com/TonyGlimm'}>Tony</Link>,{' '}
                  <Link to={'https://github.com/JohannesThorbergsson'}>Johannes</Link> and <Link to={'https://github.com/gadmel'}> Gleb</Link>.
               </p>
               Special thanks to the team of neuefische and our coaches for their support and guidance.
            </div>
         </div>
      </Layout>
   )
}

export default Credits
