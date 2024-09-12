import { Routes } from '@angular/router';
import { LoginComponent } from './pages/login/login.component';
import { HomeComponent } from './pages/home/home.component';
import { AuthGuard } from './auth/auth.guard';
import { BreweriesComponent } from './pages/breweries/breweries.component';
import { ClassificationsComponent } from './pages/classifications/classifications.component';

export const routes: Routes = [
    {path: "", redirectTo: "/home", pathMatch: "full"},
    {path: "login", component: LoginComponent},
    {path: "home", component: HomeComponent, canActivate: [AuthGuard]},
    {path: "breweries", component: BreweriesComponent, canActivate: [AuthGuard]},
    {path: "classifications", component: ClassificationsComponent, canActivate: [AuthGuard]}

];

