import React from 'react'
// import './Credits.css'
import { Link } from 'react-router-dom'
import Layout from '../Layout/Layout'

type Props = {
   addBikeRef: React.MutableRefObject<HTMLFormElement>
   galleryRef: React.MutableRefObject<HTMLDivElement>
}

function Credits(props: Props) {
   return (
      <Layout addBikeRef={props.addBikeRef} galleryRef={props.galleryRef}>
         <div className="credits">
            <h1>Credits</h1>
            <p>
               This website was created as a part of the neuefische's Java Fullstack Bootcamp as a team project by{' '}
               <Link to={'https://github.com/helenrendich'}>Helen</Link>,<Link to={'https://github.com/TonyGlimm'}> Tony</Link>,
               <Link to={'https://github.com/JohannesThorbergsson'}> Johannes</Link> and <Link to={'https://github.com/gadmel'}> Gleb</Link>
            </p>
            Special thanks to the team of neuefische and our coaches
            <Link to={'https://github.com'}>gadmel</Link> <Link to={'https://github.com'}>gadmel</Link> <Link to={'https://github.com'}>gadmel</Link> for their
            support and guidance.
         </div>
      </Layout>
   )
}

export default Credits
