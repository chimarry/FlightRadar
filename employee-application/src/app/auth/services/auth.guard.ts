import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { LoginService } from './login.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {
  constructor(private loginService: LoginService,
    private router: Router) { }

  canActivate(): boolean {
    if (this.loginService.isLoggedIn())
      return true;
    else {
      this.router.navigate(['']);
      return false;
    }
  }
}
