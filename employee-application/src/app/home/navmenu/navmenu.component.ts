import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-navmenu',
  templateUrl: './navmenu.component.html',
  styleUrls: ['./navmenu.component.css']
})
export class NavmenuComponent implements OnInit {

  constructor(private router: Router, private route: ActivatedRoute) { }

  ngOnInit(): void {
  }

  public showFlights() {
    this.router.navigate(['flights'], { relativeTo: this.route });
  }

  public showReservations() {
    this.router.navigate(['reservations'], { relativeTo: this.route });
  }

  public showMessages() {
    this.router.navigate(['messages'], { relativeTo: this.route });
  }
}
