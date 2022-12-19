import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent  {

  email: string;
  password: string;
  loginError: boolean;

  constructor(
    private router: Router,
  ) { }

  onSubmit() {
    this.router.navigate(['/home']);
    console.log(`Email: ${this.email}, Pass: ${this.password}`);
  }

  cancelar(){
    this.router.navigate(['/home']);
  }

}
