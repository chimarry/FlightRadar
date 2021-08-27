import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Employee } from 'src/app/models/employee';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private users: Array<Employee> = [];
  public signedIn: boolean = false;
  public activeUser: Employee | null = null;

  constructor(private router: Router) {
    this.users.push(new Employee());
  }

  public login(username: string, password: string): boolean {
    this.activeUser = new Employee();
    this.signedIn = true;
    return true;;
  }

  public logout() {
    this.activeUser = null;
    this.signedIn = false;
    this.router.navigate(['/']);
  }
}
