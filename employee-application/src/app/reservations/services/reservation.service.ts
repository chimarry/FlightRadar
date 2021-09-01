import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { FlightReservation } from 'src/app/models/flight-reservation';
import { FlightReservationStatus } from 'src/app/models/flight-reservation-status';
import { FlightType } from 'src/app/models/flight-type';
import { RestUtilService } from 'src/app/util/rest-util.service';

@Injectable({
  providedIn: 'root'
})
export class ReservationService {

  constructor(private httpClient: HttpClient, private restUtil: RestUtilService) { }

  public getAll(filters: FlightReservationStatus[]): Observable<FlightReservation[]> {
    let params = this.restUtil.getParams(filters);
    return this.httpClient.get<FlightReservation[]>("http://localhost:8080/api/v0.1/reservations", { params: params });
  }
}
