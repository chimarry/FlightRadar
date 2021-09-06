import { formatDate } from '@angular/common';
import { JsonpClientBackend } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { City } from 'src/app/models/city';
import { Country } from 'src/app/models/country';
import { DateTime } from 'src/app/models/date-time';
import { Flight } from 'src/app/models/flight';
import { FlightType } from 'src/app/models/flight-type';
import { CityService } from '../services/city.service';
import { CountryService } from '../services/country.service';
import { FlightService } from '../services/flight.service';

@Component({
  selector: 'app-flights',
  templateUrl: './flights.component.html',
  styleUrls: ['./flights.component.css']
})
export class FlightsComponent implements OnInit {

  public form: FormGroup = new FormGroup({});
  public flight: Flight = new Flight();
  public countries: Array<Country> = [];
  public departureCities: Array<City> = [];
  public arrivalCities: Array<City> = [];
  public flightTypes: Array<FlightType> = [FlightType.Passenger, FlightType.Transport];

  public dates: Array<DateTime> = [new DateTime()];

  constructor(public formBuilder: FormBuilder,
    private service: FlightService,
    private countryService: CountryService,
    private cityService: CityService,
    private snackBar: MatSnackBar) {
  }

  ngOnInit() {
    this.loadCountries();
    this.form = this.formBuilder.group({
      arrivalCity: [this.flight.arrivalCityId, Validators.required],
      departureCity: [this.flight.departureCityId, Validators.required],
      flightType: [this.flight.type, Validators.required],
      departureCountry: [null, Validators.required],
      arrivalCountry: [null, Validators.required]
    });
  }

  addDate(event: any, i: number): void {
    this.dates[i].date = event.value;
    this.dates[i].selectable = true;
  }

  addTime(time: string, i: number) {
    if (time.length === 5) {
      this.dates[i].time = time;
      this.dates[i].selectable = false;
      let lastDate = this.dates[this.dates.length - 1].date;
      let lastTime = this.dates[this.dates.length - 1].time;
      if (lastDate != null && lastTime != null)
        this.dates.push(new DateTime());
    }
  }

  loadCountries() {
    this.countryService.getAll()
      .subscribe(responseData => {
        this.countries = responseData;
      }, error => console.log(error));
  }

  loadDepartureCities(country: any) {
    this.cityService.getAll(country.value.countryId)
      .subscribe(responseData => this.departureCities = responseData, error => console.log(error));
  }

  loadArrivalCities(country: any) {
    this.cityService.getAll(country.value.countryId)
      .subscribe(responseData => this.arrivalCities = responseData,
        error => console.log(error));
  }

  changeDepartureCityId(city: any) {
    this.flight.departureCityId = city.value;
  }

  changeArrivalCityId(city: any) {
    this.flight.arrivalCityId = city.value;
  }

  save({ value, valid }: { value: Flight, valid: boolean }) {
    if (valid && this.dates.length != 1) {
      this.dates.pop();
      this.flight.airportDateTimes = this.dates.map(x => x.getDateWithTime());
      this.service.add(this.flight)
        .subscribe(response => { console.log(response) });
      this.clear();
    }
  }

  clear() {
    this.form.reset();
    this.dates = [new DateTime()];
  }
}