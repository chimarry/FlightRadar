import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Country } from 'src/app/models/country';
import { RestUtilService } from 'src/app/util/rest-util.service';

@Injectable({
  providedIn: 'root'
})
export class CountryService {

  constructor(private httpClient: HttpClient, private restUtil: RestUtilService) {
  }

  public getAll(): Observable<Country[]> {
    return this.httpClient.get<Country[]>(RestUtilService.buildUrl("countries"), { headers: this.restUtil.getHeaders() });
  }
}
