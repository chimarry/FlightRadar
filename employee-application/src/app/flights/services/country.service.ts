import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Country } from 'src/app/models/country';

@Injectable({
  providedIn: 'root'
})
export class CountryService {

  constructor(private httpClient: HttpClient) { }

  public getAll(): Observable<Country[]> {
    return this.httpClient.get<Country[]>("http://localhost:8080/api/v0.1/countries");
  }
}
