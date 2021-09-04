import { HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { LoginService } from '../auth/services/login.service';

@Injectable({
  providedIn: 'root'
})
export class RestUtilService {

  public static API_PREFIX: string = "http://localhost:8080/api/v0.1/";

  constructor(private loginService: LoginService) { }

  public static buildUrl(url: string): string {
    return this.API_PREFIX + url;
  }

  public getParams(filters: any[]): HttpParams {
    let params = new HttpParams();
    filters.forEach(filter => {
      var output = filter.replace(/\w+/g, function (txt: string) {
        return txt.charAt(0).toUpperCase() + txt.substr(1);
      }).replace(/\s/g, '');
      params = params.append("filters", output)
    });
    return params;
  }

  public getHeaders(): HttpHeaders {
    let headers = new HttpHeaders();
    headers = headers.append('Authorization', 'Bearer ' +
      this.loginService.getToken());
    return headers;
  }
}
