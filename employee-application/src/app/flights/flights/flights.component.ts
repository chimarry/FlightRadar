import { JsonpClientBackend } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { City } from 'src/app/models/city';
import { Country } from 'src/app/models/country';
import { Flight } from 'src/app/models/flight';
import { FlightType } from 'src/app/models/flight-type';
import { FlightService } from '../services/flight.service';

@Component({
  selector: 'app-flights',
  templateUrl: './flights.component.html',
  styleUrls: ['./flights.component.css']
})
export class FlightsComponent implements OnInit {

  public form: FormGroup = new FormGroup({}); //forma
  public flight: Flight = new Flight();
  public countries: Array<Country> = [new Country(1, "BiH"), new Country(2, "Serbia")];
  public cities: Array<City> = [new City(1, 1, "Banja Luka"), new City(2, 2, "Beograd"), new City(3, 2, "Nis")];
  public flightTypes: Array<FlightType> = [FlightType.Passenger, FlightType.Transport];

  public dates: Array<Date> = [new Date()];

  constructor(public formBuilder: FormBuilder, //formBuilder sluzi za kreiranje forme
    private service: FlightService, //service koristimo za cuvanje podataka
    private snackBar: MatSnackBar) {
  }

  ngOnInit() {
    //prilikom ucitavanja stranice pravimo formu
    this.form = this.formBuilder.group({
      arrivalCity: [this.flight.arrivalCityId, Validators.required],
      departureCity: [this.flight.departureCityId, Validators.required],
      flightType: [this.flight.type, Validators.required],
      date: [this.flight.date, Validators.required],
      time: [this.flight.time, Validators.required],
      departureCountry: [this.countries[0], Validators.required],
      arrivalCountry: [this.countries[1], Validators.required]
    });
  }

  addDate(event: any): void {
    this.dates.push(event);
  }

  save({ value, valid }: { value: Flight, valid: boolean }) {
    if (valid) { //ako su OK
      this.form.reset(); //ponistimo prethodno unesene podatke
      this.snackBar.open("Podaci su sacuvani", undefined, { //i prikazemo poruku koja nestaje nakon 2s
        duration: 2000,
      });
    }
  }
  close() {
  }
}