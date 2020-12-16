import { AuthGuardService } from './services/auth-guard.service';
import { ViewStockComponent } from './view-stock/view-stock.component';
import { RegisterComponent } from './register/register.component';
import { SearchComponent } from './search/search.component';
import { LoginComponent } from './login/login.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ProfileComponent } from './profile/profile.component';
import { HomeComponent } from './home/home.component';

const routes: Routes = [
  { path: 'login', component: LoginComponent},
  { path: 'profile', component: ProfileComponent, canActivate: [AuthGuardService]},
  { path: 'search', component: SearchComponent, canActivate: [AuthGuardService]},
  { path: 'home', component: HomeComponent, canActivate: [AuthGuardService]},
  { path: 'register', component: RegisterComponent},
  { path : 'stocks/:id', component: ViewStockComponent, canActivate: [AuthGuardService]},
  { path: '', redirectTo:'/login', pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
