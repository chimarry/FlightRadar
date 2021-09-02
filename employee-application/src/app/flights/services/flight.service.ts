import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Flight } from 'src/app/models/flight';

@Injectable({
  providedIn: 'root'
})
export class FlightService {

  constructor(private httpClient: HttpClient) { }

  add(flight: Flight): Observable<Boolean> {
    return this.httpClient.post<Boolean>("http://localhost:8080/api/v0.1/flights", flight);
  }
}
