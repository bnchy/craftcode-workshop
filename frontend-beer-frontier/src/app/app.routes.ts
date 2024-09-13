import { Routes } from '@angular/router';
import { LoginComponent } from './pages/login/login.component';
import { HomeComponent } from './pages/beers/home.component';
import { AuthGuard } from './auth/auth.guard';
import { BreweriesComponent } from './pages/breweries/breweries.component';
import { ClassificationsComponent } from './pages/classifications/classifications.component';
import { BeerComponent } from './pages/beers/beer/beer.component';
import { ClassificationComponent } from './pages/classifications/classification/classification.component';
import { BreweryComponent } from './pages/breweries/brewery/brewery.component';

export const routes: Routes = [
    {path: "", redirectTo: "/beers", pathMatch: "full"},
    {path: "login", component: LoginComponent},
    {path: "beers", component: HomeComponent, canActivate: [AuthGuard]},
    {path: "beers/:id", component: BeerComponent, canActivate: [AuthGuard]},
    {path: "breweries", component: BreweriesComponent, canActivate: [AuthGuard]},
    {path: "breweries/:id", component: BreweryComponent, canActivate: [AuthGuard]},
    {path: "classifications", component: ClassificationsComponent, canActivate: [AuthGuard]},
    {path: "classifications/:id", component: ClassificationComponent, canActivate: [AuthGuard]}

];

