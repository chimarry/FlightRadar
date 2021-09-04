import { HttpClient, HttpParams, HttpResponse } from '@angular/common/http';
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
    console.log(this.restUtil.getHeaders());
    return this.httpClient.get<FlightReservation[]>(RestUtilService.buildUrl("reservations"),
      { params: params, headers: this.restUtil.getHeaders() });
  }

  public getDetails(flightReservationId: number, type: FlightType): Observable<any> {
    let params = new HttpParams().set("flightType", type);
    return this.httpClient.get(RestUtilService.buildUrl("reservations/") + flightReservationId,
      { params: params, headers: this.restUtil.getHeaders() });
  }

  public downloadFile(flightReservationId: number) {
    this.httpClient.get(RestUtilService.buildUrl("reservations/download/") + flightReservationId, {
      responseType: 'blob', observe: 'response', headers: this.restUtil.getHeaders()
    }
    ).subscribe(response => {
      this.downLoadFile(response, response.headers.get('Content-Type') ?? '', response.headers.get('File-name') ?? '');
    });
  }

  downLoadFile(data: any, type: string, filename: string) {
    let blob = new Blob([data], { type: type });
    let downloadLink = document.createElement('a');
    downloadLink.href = window.URL.createObjectURL(blob);
    downloadLink.setAttribute('download', filename);
    document.body.appendChild(downloadLink);
    downloadLink.click();
  }

  public cancelReservation(flightReservationId: number): Observable<Boolean> {
    return this.httpClient.put<Boolean>(RestUtilService.buildUrl("reservations/cancel/") + flightReservationId, null,
      { headers: this.restUtil.getHeaders() });
  }

  public confirmReservation(flightReservationId: number): Observable<Boolean> {
    return this.httpClient.put<Boolean>(RestUtilService.buildUrl("reservations/confirm/") + flightReservationId, null,
      { headers: this.restUtil.getHeaders() });
  }
}
