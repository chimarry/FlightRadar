import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Flight } from 'src/app/models/flight';
import { RestUtilService } from 'src/app/util/rest-util.service';

@Injectable({
  providedIn: 'root'
})
export class FlightService {

  constructor(private httpClient: HttpClient, private restUtil: RestUtilService) {
  }

  add(flight: Flight): Observable<Boolean> {
    return this.httpClient.post<Boolean>(RestUtilService.buildUrl("flights"), flight, { headers: this.restUtil.getHeaders() });
  }
}
