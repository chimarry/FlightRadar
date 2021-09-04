import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { RestUtilService } from 'src/app/util/rest-util.service';

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  constructor(private router: Router, private httpClient: HttpClient) {
  }

  login(username: string, password: string) {
    return this.httpClient.post(RestUtilService.buildUrl('authentication'), { username, password })
      .subscribe(responseData => this.saveToken(responseData), error => console.log("Failed login"));
  }

  private saveToken(responseData: any) {
    if (responseData.token != undefined) {
      localStorage.setItem("token", responseData.token);
    }
  }

  logout() {
    localStorage.removeItem("token");
  }

  isLoggedIn() {
    return this.getToken() != null;
  }

  isLoggedOut() {
    return !this.isLoggedIn();
  }

  getToken(): string | null {
    return localStorage.getItem("token");
  }
}
