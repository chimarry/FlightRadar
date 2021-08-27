import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './auth/login/login.component';
import { FlightsComponent } from './flights/flights/flights.component';
import { HomeComponent } from './home/home/home.component';
import { MessagesComponent } from './messages/messages/messages.component';
import { ReservationsComponent } from './reservations/reservations/reservations.component';

const routes: Routes = [
  {
    path: '',
    component: LoginComponent
  },
  {
    path: 'home',
    component: HomeComponent,
    children: [
      {
        path: 'messages',
        component: MessagesComponent
      },
      {
        path: 'flights',
        component: FlightsComponent
      },
      {
        path: 'reservations',
        component: ReservationsComponent
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
