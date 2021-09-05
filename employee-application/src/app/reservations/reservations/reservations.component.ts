import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatTableDataSource } from '@angular/material/table';
import { FlightReservation } from 'src/app/models/flight-reservation';
import { FlightReservationStatus } from 'src/app/models/flight-reservation-status';
import { FlightType } from 'src/app/models/flight-type';
import { ConfirmCancelComponent } from '../confirm-cancel/confirm-cancel.component';
import { PassengerReservationComponent } from '../passenger-reservation/passenger-reservation.component';
import { ReservationService } from '../services/reservation.service';
import { TransportReservationComponent } from '../transport-reservation/transport-reservation.component';

@Component({
  selector: 'app-reservations',
  templateUrl: './reservations.component.html',
  styleUrls: ['./reservations.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class ReservationsComponent implements OnInit {
  displayedColumns: string[] = ['username', 'arrivalCityName', 'arrivalCountryName', 'departureCityName',
    'departureCountryName', 'airportDateTime', 'createdOn', 'see', 'confirm', 'delete'];
  dataSource = new MatTableDataSource<FlightReservation>();

  public availableFilters: FlightReservationStatus[] = [FlightReservationStatus.Canceled,
  FlightReservationStatus.New,
  FlightReservationStatus.Confirmed];

  public selectedFilters: FlightReservationStatus[] = [];

  constructor(private service: ReservationService,
    private dialog: MatDialog,
    private snackBar: MatSnackBar,) {
  }

  ngOnInit() {
    this.refresh();
  }

  applyFilter(status: FlightReservationStatus): void {
    const index = this.availableFilters.indexOf(status);
    if (index >= 0)
      this.availableFilters.splice(index, 1);
    this.selectedFilters.push(status);
    this.refresh();
  }

  removeFilter(status: FlightReservationStatus): void {
    const index = this.selectedFilters.indexOf(status);
    if (index >= 0)
      this.selectedFilters.splice(index, 1);

    this.availableFilters.push(status);
    this.refresh();
  }

  showDetails(element: FlightReservation) {
    if (element.flightType === FlightType.Passenger)
      this.dialog.open(PassengerReservationComponent, {
        data: element
      });
    else
      this.dialog.open(TransportReservationComponent, {
        data: element
      });
  }

  confirm(element: FlightReservation) {
    this.service.confirmReservation(element.flightReservationId ?? 1).subscribe(() => { this.refresh() });
  }

  cancel(element: FlightReservation) {
    this.dialog.open(ConfirmCancelComponent, {
      width: '300px'
    })
      .afterClosed()
      .subscribe((result: string | null) => {
        if (result != null) {
          this.service.cancelReservation(element.flightReservationId ?? 1, result).subscribe(() => { this.refresh() });
          this.refresh();
        } else {
          this.snackBar.open("Reservation was not cancelled.", undefined, {
            duration: 2000
          })
        }
      });
  }

  refresh() {
    this.service.getAll(this.selectedFilters)
      .subscribe(responseData => this.dataSource.data = responseData);
  }
}