import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { FlightReservation } from 'src/app/models/flight-reservation';
import { TransportFlightReservation } from 'src/app/models/transport-flight-reservation';
import { ReservationService } from '../services/reservation.service';

@Component({
  selector: 'app-transport-reservation',
  templateUrl: './transport-reservation.component.html',
  styleUrls: ['./transport-reservation.component.css']
})
export class TransportReservationComponent implements OnInit {

  public reservation: TransportFlightReservation = new TransportFlightReservation();

  constructor(private dialogRef: MatDialogRef<TransportReservationComponent>,
    private reservationService: ReservationService,
    @Inject(MAT_DIALOG_DATA)
    public inputData: FlightReservation) {
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
