import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { FlightReservation } from 'src/app/models/flight-reservation';

@Component({
  selector: 'app-passenger-reservation',
  templateUrl: './passenger-reservation.component.html',
  styleUrls: ['./passenger-reservation.component.css']
})
export class PassengerReservationComponent implements OnInit {

  constructor(private dialogRef: MatDialogRef<PassengerReservationComponent>, @Inject(MAT_DIALOG_DATA) public reservation: FlightReservation) {
  }

  ngOnInit() {
  }

  close() {
    this.dialogRef.close();
  }
}
