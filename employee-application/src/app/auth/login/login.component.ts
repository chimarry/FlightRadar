import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { LoginService } from '../services/login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  public form: FormGroup = new FormGroup({});

  constructor(private loginService: LoginService, private formBuilder: FormBuilder,
    private router: Router, private snackBar: MatSnackBar
  ) { }

  ngOnInit() {
    this.form = this.formBuilder.group({
      username: [null, Validators.required],
      password: [null, Validators.required]
    });
  }

  public login(form: FormGroup) {
    this.loginService.login(form.value.username, form.value.password).add(() => {
      if (this.loginService.isLoggedIn())
        this.router.navigate(['home']);
      else {
        this.form.reset();
        this.snackBar.open("Username or password is invalid", undefined, {
          duration: 2000
        });
      }
    });
  }
}
