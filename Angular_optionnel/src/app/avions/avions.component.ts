import { Component, OnInit } from '@angular/core';
import { Avion } from '../model/avion.model';
import { AvionService } from '../services/avion.service';
import { AuthService } from '../services/auth.service';
import { Router } from '@angular/router';
import { Image } from '../model/image.model';

@Component({
  selector: 'app-avions',
  templateUrl: './avions.component.html',
})
export class AvionsComponent implements OnInit {
  avions?: Avion[]; //un tableau des avions

  apiurl: string = 'http://localhost:8085/avions/api';

  constructor(
    private avionService: AvionService,
    private router: Router,
    public authService: AuthService
  ) {}

  ngOnInit(): void {
    this.chargerAvions();
  }

  // chargerAvions() {
  //   this.avionService.listeAvion().subscribe((avios) => {
  //     console.log(avios);
  //     this.avions = avios;

  //     this.avions.forEach((avio) => {
  //       if (avio.image && avio.image.idImage) {
  //         this.avionService.loadImage(avio.image.idImage).subscribe((img: Image) => {
  //           avio.imageStr = 'data:' + img.type + ';base64,' + img.image;
  //         }, (error) => {
  //           console.error('Error loading image:', error);
  //         });
  //       } else if (avio.images && avio.images.length > 0) {
  //         // If the avion has multiple images, load the first one
  //         this.avionService.loadImage(avio.images[0].idImage).subscribe((img: Image) => {
  //           avio.imageStr = 'data:' + img.type + ';base64,' + img.image;
  //         }, (error) => {
  //           console.error('Error loading image:', error);
  //         });
  //       } else {
  //         console.warn('Avion image is undefined or idImage is missing:', avio);
  //       }
  //     });
  //   }, (error) => {
  //     console.error('Error fetching avions:', error);
  //   });
  // }

  chargerAvions() {
    this.avionService.listeAvion().subscribe((avios) => {
      this.avions = avios;
    });
  }

  supprimerAvion(a: Avion) {
    let conf = confirm('Etes-vous sûr ?');
    if (conf) {
      this.avionService.supprimerAvion(a.idAvion).subscribe(() => {
        console.log('avion supprimé');
        this.chargerAvions();
      });
    }
  }
}
