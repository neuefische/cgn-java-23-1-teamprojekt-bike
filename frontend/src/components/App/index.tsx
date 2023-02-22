import React from 'react'
import './App.scss'

import BikeGallery from '../BikeGallery/'

function App() {
   return (
      <div id="app">
         Frontend will be here
         <BikeGallery
            bikes={[{ id: 'some ID', title: 'Trend-a-bikes MARK II' }]}
         />
      </div>
   )
}

export default App
