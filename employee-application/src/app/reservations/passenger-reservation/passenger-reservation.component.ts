import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { FlightReservation } from 'src/app/models/flight-reservation';
import { PassengerFlightReservation } from 'src/app/models/passenger-flight-reservation';
import { ReservationService } from '../services/reservation.service';

@Component({
  selector: 'app-passenger-reservation',
  templateUrl: './passenger-reservation.component.html',
  styleUrls: ['./passenger-reservation.component.css']
})
export class PassengerReservationComponent implements OnInit {

  public reservation: PassengerFlightReservation  = new PassengerFlightReservation();

  constructor(private dialogRef: MatDialogRef<PassengerReservationComponent>, 
    private reservationService: ReservationService,
    @Inject(MAT_DIALOG_DATA) public inputData: FlightReservation) {
  }

  ngOnInit() {
    if (this.inputData.flightReservationId != null && this.inputData.flightType != null)
      this.reservationService.getDetails(this.inputData.flightReservationId, this.inputData.flightType)
        .subscribe(response => { this.reservation = response });
  }

  close() {
    this.dialogRef.close();
  }
}
