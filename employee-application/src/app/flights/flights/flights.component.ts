import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Flight } from 'src/app/models/flight';
import { FlightService } from '../services/flight.service';

@Component({
  selector: 'app-flights',
  templateUrl: './flights.component.html',
  styleUrls: ['./flights.component.css']
})
export class FlightsComponent implements OnInit {

  public form: FormGroup = new FormGroup({}); //forma
  public event: Flight = new Flight();

  constructor(public formBuilder: FormBuilder, //formBuilder sluzi za kreiranje forme
    private service: FlightService, //service koristimo za cuvanje podataka
    private snackBar: MatSnackBar) {
  }

  ngOnInit() {
    //prilikom ucitavanja stranice pravimo formu
    this.form = this.formBuilder.group({
      name: [this.event.name, Validators.required],
      description: [this.event.description, Validators.required],
      image: [this.event.image],
      date: [this.event.date, Validators.required],
      time: [this.event.time, Validators.required]
    });
  }

  save({ value, valid }: { value: Event, valid: boolean }) {
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