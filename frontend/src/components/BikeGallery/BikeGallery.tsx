import React from 'react';
import { Bike } from '../../models/Bike';
import BikeCard from '../BikeCard/BikeCard';
import AddBike from "../AddBike/AddBike";

type BikeGalleryProps = {
   bikes: Bike[];

   addBike: (newBikeTitle: string) => Promise<void>
};

function BikeGallery(props: BikeGalleryProps) {
   return (
      <div className="gallery">
         {props.bikes.map((bike) => (
            <BikeCard key={bike.id} bike={bike} />
         ))}
      </div>
   );
}
export default BikeGallery;
