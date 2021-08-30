import { Injectable } from '@angular/core';
import { FlightReservation } from 'src/app/models/flight-reservation';
import { FlightReservationStatus } from 'src/app/models/flight-reservation-status';
import { FlightType } from 'src/app/models/flight-type';

@Injectable({
  providedIn: 'root'
})
export class ReservationService {

  public data: Array<FlightReservation> = [new FlightReservation("marija", 1, "2021-12-12 20:00", "Banja Luka",
    "BiH", "Moscow", "Russia", "2021-12-12 10:00", FlightReservationStatus.New, FlightType.Passenger),
  new FlightReservation("marko", 2, "2021-11-12 09:00", "Banja Luka",
    "BiH", "Belgrade", "Serbia", "2021-11-11 12:00", FlightReservationStatus.Confirmed, FlightType.Passenger),
  new FlightReservation("vanja", 3, "2021-11-12 09:00", "Banja Luka",
    "BiH", "Belgrade", "Serbia", "2021-11-12 08:00", FlightReservationStatus.Canceled, FlightType.Transport)
  ];

  constructor() { }

  public getAll() {
    return this.data;
  }
}
