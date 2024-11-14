import { TypeAv } from './../model/TypeAv.model';
import { AuthService } from './auth.service';
import { Injectable } from '@angular/core';
import { Avion } from '../model/avion.model';
import { Observable, tap } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { TypeAvWrapper } from '../model/TypeAvWrapped.model';
import { apiURL } from '../config';
import { Image } from '../model/image.model';


const httpOptions = {
  headers: new HttpHeaders( {'Content-Type': 'application/json'} )
  };

// const httpOptions = {
//   headers: new HttpHeaders({ 'Content-Type': 'appliavion/json' }),
// };

@Injectable({
  providedIn: 'root',
})
export class AvionService {
  private apiURL: string = 'http://localhost:8089/avions/api';
  private apiURLTyp: string = 'http://localhost:8089/avions/typ';



  constructor(private http: HttpClient, private authService: AuthService) {}


  listeAvion(): Observable<Avion[]>{
    let jwt = this.authService.getToken();
    jwt = "Bearer "+jwt;
    let httpHeaders = new HttpHeaders({"Authorization":jwt})
    return this.http.get<Avion[]>(this.apiURL+"/all",{headers:httpHeaders});
    }
    



  ajouterAvion( avio: Avion):Observable<Avion>{
  let jwt = this.authService.getToken();
  jwt = "Bearer "+jwt;
  let httpHeaders = new HttpHeaders({"Authorization":jwt})
  return this.http.post<Avion>(this.apiURL+"/addavio", avio, {headers:httpHeaders});
  }
  supprimerAvion(id : number) {
  const url = `${this.apiURL}/delavio/${id}`;
  let jwt = this.authService.getToken();
  jwt = "Bearer "+jwt;
  let httpHeaders = new HttpHeaders({"Authorization":jwt})
  return this.http.delete(url, {headers:httpHeaders});
  }
  consulterAvion(id: number): Observable<Avion> {
  const url = `${this.apiURL}/getbyid/${id}`;
  let jwt = this.authService.getToken();
  jwt = "Bearer "+jwt;
  let httpHeaders = new HttpHeaders({"Authorization":jwt})
  return this.http.get<Avion>(url,{headers:httpHeaders});
  }
  updateAvion(prod :Avion) : Observable<Avion> {
  let jwt = this.authService.getToken();
  jwt = "Bearer "+jwt;
  let httpHeaders = new HttpHeaders({"Authorization":jwt})
  return this.http.put<Avion>(this.apiURL+"/updateavio", prod, {headers:httpHeaders});
  }

  listeTypes():Observable<TypeAvWrapper>{
    let jwt = this.authService.getToken();
    jwt = "Bearer "+jwt;
    let httpHeaders = new HttpHeaders({"Authorization":jwt})
    return this.http.get<TypeAvWrapper>(this.apiURLTyp,{headers:httpHeaders}
    );
    }
    rechercherParTypeAv(idav: number): Observable<Avion[]> {
    const url = `${this.apiURL}/aviotyp/${idav}`;
    return this.http.get<Avion[]>(url);
    }
    rechercherParMatricule(nom: string):Observable< Avion[]> {
    const url = `${this.apiURL}/aviosByName/${nom}`;
    return this.http.get<Avion[]>(url);
    }
    ajouterTypeAv( av: TypeAv):Observable<TypeAv>{
    //return this.http.post<TypeAv>(this.apiURLTyp, av, httpOptions);
    return this.http.post<TypeAv>(this.apiURLTyp, av);
    }

    supprimerTypeAv(id: number) {
    
      let jwt = this.authService.getToken();
      jwt = "Bearer "+jwt;
      let httpHeaders = new HttpHeaders({"Authorization": jwt});
      return this.http.delete(`${this.apiURLTyp}/${id}`, { headers: httpHeaders });
    }
    updateTypeAv(typ: TypeAv): Observable<TypeAv> {
      let jwt = this.authService.getToken();
      jwt = "Bearer " + jwt;
      let httpHeaders = new HttpHeaders({ "Authorization": jwt });
      const url = `${this.apiURLTyp}/${typ.idAv}`; // Update by ID

      return this.http.put<TypeAv>(url, typ, { headers: httpHeaders });
  }

  
  uploadImage(file: File, filename: string): Observable<Image>{ 
    const imageFormData = new FormData(); 
    imageFormData.append('image', file, filename); 
    const url = `${apiURL + '/image/upload'}`; 
    return this.http.post<Image>(url, imageFormData); 
 } 

  loadImage(id: number): Observable<Image> { 
    const url = `${apiURL + '/image/get/info'}/${id}`; 
    return this.http.get<Image>(url); 
  } 


  uploadImageAvion(file: File, filename: string, idAvio:number): Observable<any>{ 
    const imageFormData = new FormData(); 
    imageFormData.append('image', file, filename); 
    const url = `${apiURL + '/image/uplaodImageAvio'}/${idAvio}`; 
    return this.http.post(url, imageFormData); 
  } 

  supprimerImage(id : number) {
    const url = `${apiURL}/image/delete/${id}`;
    return this.http.delete(url, httpOptions);
    }
 
    uploadImageFS(file: File, filename: string, idProd: number): Observable<any> {
      const imageFormData = new FormData();
      imageFormData.append('image', file, filename);
      const url = `${this.apiURL + '/image/uploadFS'}/${idProd}`;
      return this.http.post(url, imageFormData);
    }

}