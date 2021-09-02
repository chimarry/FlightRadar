import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { City } from 'src/app/models/city';

@Injectable({
  providedIn: 'root'
})
export class CityService {

  constructor(private httpClient: HttpClient) {
  }

  public getAll(countryId: number): Observable<City[]> {
    return this.httpClient.get<City[]>("http://localhost:8080/api/v0.1/cities/" + countryId);
  }
}
