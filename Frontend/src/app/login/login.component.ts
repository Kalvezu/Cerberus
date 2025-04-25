import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-login',
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent implements OnInit {

  protected loginForm = new FormGroup({email: new FormControl(''), password: new FormControl(''),});

  constructor(private router: Router, private authService: AuthService) {
  }

  ngOnInit() {
    if (this.authService.isLoggedIn()){
      this.router.navigate(['/home']);
    }
  }

  warn: boolean = false;

  login() {
    this.warn = false;
    if (this.loginForm?.valid) {
      this.authService.login(this.loginForm.value)
        .subscribe((data: any) => {
          if (this.authService.isLoggedIn() && this.authService.getUser()) {
            this.router.navigate(['/home']);
          }else{
            this.warn =  true;
          }
        });
    }
  }
}