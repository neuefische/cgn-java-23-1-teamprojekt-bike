import React from 'react';
import { Routes, Route } from 'react-router-dom';
import './App.scss';

import BikeGallery from '../BikeGallery/BikeGallery';

function App() {
   const mockBikes = [{ id: 'some ID', title: 'Trend-a-bikes MARK II' }];

   return (
      <div id="app">
         <header className="header">Frontend will be here</header>
         <main className="main">
            <Routes>
               <Route path="/" element={<BikeGallery bikes={mockBikes} />} />
            </Routes>
         </main>
         <footer className="footer">Footer - Impressum</footer>
      </div>
   );
}

export default App;
