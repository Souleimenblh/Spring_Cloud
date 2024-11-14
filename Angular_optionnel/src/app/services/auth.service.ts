import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { User } from '../model/user.model';
import { JwtHelperService } from '@auth0/angular-jwt';



@Injectable({
  providedIn: 'root'
})
export class AuthService {



 private helper = new JwtHelperService();

apiURL: string = 'http://localhost:8081/users';
token!:string;

public loggedUser!:string;
public isloggedIn: Boolean = false;
public roles!:string[];

public regitredUser : User = new User();

  constructor(private router : Router,
              private http : HttpClient
) { }



setRegistredUser(user : User){
this.regitredUser=user;
}
getRegistredUser(){
return this.regitredUser;
}

  login(user : User)
  {
  return this.http.post<User>(this.apiURL+'/login', user , {observe:'response'});
  }
 
 saveToken(jwt:string){
      localStorage.setItem('jwt',jwt);
      this.token = jwt;
      this.isloggedIn = true; 
      this.decodeJWT();
  }

  getToken():string {
    return this.token;
  }

  decodeJWT()
  {   if (this.token == undefined)
            return;
    const decodedToken = this.helper.decodeToken(this.token);
    this.roles = decodedToken.roles;
    this.loggedUser = decodedToken.sub;
  }

 



  isAdmin():Boolean{
    if (!this.roles) //this.roles== undefiened
    return false;
    return (this.roles.indexOf('ADMIN') >-1) ;
    ;
  }  


  logout() {
  this.loggedUser = undefined!;
  this.roles = undefined!;
  this.token= undefined!;
  this.isloggedIn = false;
  localStorage.removeItem('jwt');
  this.router.navigate(['/login']);
  }

  setLoggedUserFromLocalStorage(login: string) {
    this.loggedUser = login;
    this.isloggedIn = true;
   // this.getUserRoles(login);
  }

  loadToken() {
    this.token = localStorage.getItem('jwt')!;
    this.decodeJWT();
  }

  isTokenExpired(): Boolean
  {
    return  this.helper.isTokenExpired(this.token);   
  }


  registerUser(user :User){
    return this.http.post<User>(this.apiURL+'/register', user,
    {observe:'response'});
    }


    validateEmail(code : string){
      return this.http.get<User>(this.apiURL+'/verifyEmail/'+code);
      }
    
}