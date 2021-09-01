import { HttpClient, HttpParams } from '@angular/common/http';
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

  public getDetails(flightReservationId: number, type: FlightType): Observable<any> {
    let params = new HttpParams().set("flightType", type);
    return this.httpClient.get("http://localhost:8080/api/v0.1/reservations/" + flightReservationId, { params: params });
  }

  public downloadFile(flightReservationId: number) {
    window.location.href = "http://localhost:8080/api/v0.1/reservations/download/" + flightReservationId;
  }

  public cancelReservation(flightReservationId: number): Observable<Boolean> {
    return this.httpClient.put<Boolean>("http://localhost:8080/api/v0.1/reservations/cancel/" + flightReservationId, null);
  }

  public confirmReservation(flightReservationId: number): Observable<Boolean> {
    return this.httpClient.put<Boolean>("http://localhost:8080/api/v0.1/reservations/confirm/" + flightReservationId, null);
  }
}
