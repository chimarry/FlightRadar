import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { City } from 'src/app/models/city';
import { RestUtilService } from 'src/app/util/rest-util.service';

@Injectable({
  providedIn: 'root'
})
export class CityService {

  constructor(private httpClient: HttpClient, private restUtil: RestUtilService) {
  }

  public getAll(countryId: number): Observable<City[]> {
    return this.httpClient.get<City[]>(RestUtilService.buildUrl("cities/") + countryId, { headers: this.restUtil.getHeaders() });
  }
}
