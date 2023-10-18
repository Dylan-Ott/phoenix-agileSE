import { Component } from '@angular/core';

@Component({
  selector: 'app-registration-page',
  templateUrl: './registration-page.component.html',
  styleUrls: ['./registration-page.component.css']
})
export class RegistrationPageComponent {

  ngOnInit(): void {}
  signUp(data:object){
    //This is where we'll send the data to the DB
    console.warn(data)
  }

}
