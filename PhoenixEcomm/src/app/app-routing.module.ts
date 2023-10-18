import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LandingPageComponent } from './Pages/landing-page/landing-page.component';
import { LoginPageComponent } from './Pages/login-page/login-page.component';
import { RegistrationPageComponent } from './Pages/registration-page/registration-page.component';

const routes: Routes = [
  {
    component:LandingPageComponent,
    path:'',
  },
  {
    component:LoginPageComponent,
    path:'login',
  },
  {
    component:RegistrationPageComponent,
    path:'signup',
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
