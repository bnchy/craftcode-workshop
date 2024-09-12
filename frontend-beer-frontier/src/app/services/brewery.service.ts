import { Injectable } from '@angular/core';
import { environment } from '../../environment/environment';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Brewery } from '../api/models';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class BreweryService {
  private breweryUrl = `${environment.apiUrl}/breweries`;
  constructor(private http: HttpClient, private authService: AuthService) { }

  fetchData(): Observable<Brewery[]> {
    let user = this.authService.getUser();

    const headers = new HttpHeaders({
      'Content-Type' : 'application/json',
      'Authorization': 'Basic ' + btoa(`${user.username}:${user.password}`)
    })

    return this.http.get<Brewery[]>(this.breweryUrl, {headers});
  }
}
