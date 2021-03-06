import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReservationsComponent } from './reservations/reservations.component';
import { AppMaterialModule } from '../app-material/app-material.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { PassengerReservationComponent } from './passenger-reservation/passenger-reservation.component';
import { TransportReservationComponent } from './transport-reservation/transport-reservation.component';
import { ConfirmCancelComponent } from './confirm-cancel/confirm-cancel.component';



@NgModule({
  declarations: [
    ReservationsComponent,
    PassengerReservationComponent,
    TransportReservationComponent,
    ConfirmCancelComponent
  ],
  imports: [
    CommonModule,
    AppMaterialModule,
    FormsModule,
    ReactiveFormsModule
  ]
})
export class ReservationsModule { }
