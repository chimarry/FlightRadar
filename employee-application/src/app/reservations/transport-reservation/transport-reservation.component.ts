import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { FlightReservation } from 'src/app/models/flight-reservation';

@Component({
  selector: 'app-transport-reservation',
  templateUrl: './transport-reservation.component.html',
  styleUrls: ['./transport-reservation.component.css']
})
export class TransportReservationComponent implements OnInit {

  constructor(private dialogRef: MatDialogRef<TransportReservationComponent>, @Inject(MAT_DIALOG_DATA)
  public reservation: FlightReservation) {
  }

  ngOnInit() {
  }

  close() {
    this.dialogRef.close();
  }
}
