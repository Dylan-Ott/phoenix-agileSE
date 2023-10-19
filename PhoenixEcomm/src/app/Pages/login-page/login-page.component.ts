import { Component } from '@angular/core';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css']
})
export class LoginPageComponent {

  ngOnInit(): void {}
  signUp(data:object){
    //This is where we'll send the data to the DB
    console.warn(data)
  }
}
