import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AppMaterialModule } from './app-material/app-material.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HomeModule } from './home/home.module';
import { AuthModule } from './auth/auth.module';
import { ReservationsModule } from './reservations/reservations.module';
import { MessagesModule } from './messages/messages.module';
import { FlightsModule } from './flights/flights.module';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    AppMaterialModule,
    FormsModule,
    ReactiveFormsModule,
    AuthModule,
    HomeModule,
    ReservationsModule,
    MessagesModule,
    FlightsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
