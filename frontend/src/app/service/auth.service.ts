import { Injectable, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from  '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class AuthService implements OnInit {

  constructor(private http: HttpClient) { }

  isTokenSet: boolean = false;

  ngOnInit(): void {
    
  }

  checkToken() {
    if (localStorage.getItem("token")) {
      this.isTokenSet = true
    } else {
      this.isTokenSet = false
    }

    return this.isTokenSet
  }

  login() {
    this.http.post<any>("http://localhost:8080/api/auth/login", {"username": "MattiaSpag", "password": "mattia"}).subscribe(
    data => {
      localStorage.setItem("token", data.tokenType + data.accessToken);
      alert("Token: "+ data.accessToken)
    },
    error => {
      console.log(error)
    }
    )
  }

  getUser() {
    const headers: HttpHeaders = new HttpHeaders({'Authorization': ""+ localStorage.getItem("token") +""})
    this.http.get<any>("http://localhost:8080/api/user/all", { headers: headers }).subscribe(
      data => console.log(data),
      error => console.log(error)
    )
  }

}