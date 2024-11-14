import { TypeAv } from './../model/TypeAv.model';
import { Component, OnInit } from '@angular/core';
import { Avion } from '../model/avion.model';
import { AvionService } from '../services/avion.service';
import { Router } from '@angular/router';
import { Image } from '../model/image.model';

@Component({
  selector: 'app-add-avion',
  templateUrl: './add-avion.component.html',
})
export class AddAvionComponent {
  types! : TypeAv[];
  newIdAvion! : number;
  newType! : TypeAv;
  newAvion = new Avion();
  uploadedImage!: File; 
  imagePath: any; 

  ajouterAvecSucces = false;
  constructor(private avionService:AvionService,private router :Router) { 

  }

  ngOnInit(): void {
  this.avionService.listeTypes().subscribe(typs => {this.types = typs._embedded.typeAvs;
    console.log(typs);});
  }


//  addAvion(){
//   this.newAvion.typeAv = this.types.find(typ => typ.idAv == this.newIdAvion)!;
//   this.avionService.ajouterAvion(this.newAvion)
//   .subscribe(avio => {
//   console.log(avio);
//   this.router.navigate(['avions']);
// });
// }

// addAvion(){ 
//   this.avionService 
//   .uploadImage(this.uploadedImage, this.uploadedImage.name) 
//   .subscribe((img: Image) => { 
//        this.newAvion.image=img; 
//        this.newAvion.typeAv = this.types.find(typ => typ.idAv == this.newIdAvion)!; 
    
//         this.avionService 
//           .ajouterAvion(this.newAvion) 
//           .subscribe(() => { 
//             this.router.navigate(['avions']); 
//           }); 
//   }); 
//   } 

addAvion(){ 
  
  this.newAvion.typeAv = this.types.find(typ => typ.idAv == this.newIdAvion)!;
  this.avionService 
  .ajouterAvion(this.newAvion) 
  .subscribe((avio) => { 
  this.avionService 
  .uploadImageFS(this.uploadedImage, 
  this.uploadedImage.name,avio.idAvion) 
  .subscribe((response: any) => {} 
  ); 
  this.router.navigate(['avions']); 
  }); 
  }


onImageUpload(event: any) { 
  this.uploadedImage = event.target.files[0]; 
   
  var reader = new FileReader(); 
  reader.readAsDataURL(this.uploadedImage); 
  reader.onload = (_event) => {  this.imagePath = reader.result;    } 
} 

}
