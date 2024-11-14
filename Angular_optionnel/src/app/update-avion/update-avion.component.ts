import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { AvionService } from '../services/avion.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Avion } from '../model/avion.model';
import { TypeAv } from '../model/TypeAv.model';
import { Image } from '../model/image.model';

@Component({
  selector: 'app-update-avion',
  templateUrl: './update-avion.component.html',
  styles: [],
})
export class UpdateAvionComponent implements OnInit {
  currentAvion = new Avion();
  types: TypeAv[] = [];
  updatedTypId!: number;
  myImage!: string;
  uploadedImage!: File;
  isImageUpdated: Boolean = false;

  constructor(
    private activatedRoute: ActivatedRoute,
    private router: Router,
    private avionService: AvionService
  ) {}

  ngOnInit(): void { 
    this.avionService.listeTypes(). 
    subscribe(typs => {this.types = typs._embedded.typeAvs; 
    }); 
 
   this.avionService.consulterAvion(this.activatedRoute.snapshot.params['id'])
 .subscribe( avio =>{ this.currentAvion = avio; 
        this.updatedTypId =   avio.typeAv.idAv; 
    } ) ; 
    } 


  // ngOnInit(): void {
  //   this.avionService.listeTypes().subscribe(
  //     (typs) => {
  //       console.log('API Response:', typs);
  //       this.types = typs._embedded?.typeAvs || [];
  //       console.log('types:', this.types);
  //     },
  //     (error) => {
  //       console.error('Error fetching types:', error);
  //     }
  //   );

  //   this.avionService
  //     .consulterAvion(this.activatedRoute.snapshot.params['id'])
  //     .subscribe((avio) => {
  //       this.currentAvion = avio;
  //       this.updatedTypId = this.currentAvion.typeAv.idAv;

  //       this.avionService
  //         .loadImage(this.currentAvion.image.idImage)
  //         .subscribe((img: Image) => {
  //           this.myImage = 'data:' + img.type + ';base64,' + img.image;
  //         });
  //     });
  // }

  // updateAvion() {
  //   this.currentAvion.typeAv = this.types.find(
  //     (typ) => typ.idAv == this.updatedTypId
  //   )!;

  //   this.avionService.updateAvion(this.currentAvion).subscribe(() => {
  //     this.router.navigate(['avions']);
  //   });
  // }

  // updateAvion() {
  //   this.currentAvion.typeAv = this.types.find(
  //     (typ) => typ.idAv == this.updatedTypId
  //   )!;

  //   if (this.isImageUpdated) {
  //     this.avionService
  //       .uploadImage(this.uploadedImage, this.uploadedImage.name)
  //       .subscribe((img: Image) => {
  //         this.currentAvion.image = img;

  //         this.avionService.updateAvion(this.currentAvion).subscribe((avio) => {
  //           this.router.navigate(['avions']);
  //         });
  //       });
  //   } else {
  //     this.avionService.updateAvion(this.currentAvion).subscribe((avio) => {
  //       this.router.navigate(['avions']);
  //     });
  //   }
  // }

  updateAvion() { 
    this.currentAvion.typeAv = this.types.find(typ => typ.idAv == 
    this.updatedTypId)!;         
          this.avionService 
            .updateAvion(this.currentAvion) 
            .subscribe((avio) => { 
              this.router.navigate(['avions']); 
            }); 
  } 


  onImageUpload(event: any) {
    if (event.target.files && event.target.files.length) {
      this.uploadedImage = event.target.files[0];
      this.isImageUpdated = true;
      const reader = new FileReader();
      reader.readAsDataURL(this.uploadedImage);
      reader.onload = () => {
        this.myImage = reader.result as string;
      };
    }
  }

  onAddImageAvion() { 
    this.avionService 
    .uploadImageAvion(this.uploadedImage, 
  this.uploadedImage.name, this.currentAvion.idAvion) 
    .subscribe( (img : Image)  => { 
            this.currentAvion.images.push(img); 
            }); 
   }

   supprimerImage(img: Image){ 
    let conf = confirm("Etes-vous sûr ?"); 
    if (conf) 
       this.avionService.supprimerImage(img.idImage).subscribe(() => { 
          //supprimer image du tableau currentAvion.images     
          const index = this.currentAvion.images.indexOf(img, 0); 
          if (index > -1) { 
            this.currentAvion.images.splice(index, 1); 
          } 
     }); 
   }

}