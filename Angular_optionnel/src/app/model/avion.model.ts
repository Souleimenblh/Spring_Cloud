import { TypeAv } from './TypeAv.model';
import { Image } from './image.model';
export class Avion {
  idAvion!: number;
  matriculeAvion!: string;
  celometrageAvion!: number;
  dateFabrication!: Date;
  typeAv!: TypeAv;
  image! : Image;
  imageStr!:string; 
  images!: Image[]; 

}