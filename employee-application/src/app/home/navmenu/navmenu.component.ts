import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { ActivatedRoute } from '@angular/router';
import { FlightsComponent } from 'src/app/flights/flights/flights.component';

@Component({
  selector: 'app-navmenu',
  templateUrl: './navmenu.component.html',
  styleUrls: ['./navmenu.component.css']
})
export class NavmenuComponent implements OnInit {

  constructor(private router: Router, private route: ActivatedRoute, private dialog: MatDialog) { }

  ngOnInit(): void {
  }

  public showFlights() {
    this.dialog.open(FlightsComponent, {
      width: '600px'
    })
      .afterClosed();
  }

  public showReservations() {
    this.router.navigate(['reservations'], { relativeTo: this.route });
  }

  public showMessages() {
    this.router.navigate(['messages'], { relativeTo: this.route });
  }
}
