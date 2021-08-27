import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
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
    private router: Router
  ) { }

  ngOnInit() {
    this.form = this.formBuilder.group({
      username: [null, Validators.required],
      password: [null, Validators.required]
    });
  }

  public login(form: FormGroup) {
    this.router.navigate(['home']);
  }
}
