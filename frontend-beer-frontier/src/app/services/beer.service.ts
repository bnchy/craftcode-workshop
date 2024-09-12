import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../environment/environment';
import { Beer } from '../api';
import { AuthService } from './auth.service';


@Injectable({
  providedIn: 'root'
})

export class BeerService {
  private beersUrl = `${environment.apiUrl}/beers`;

  constructor(private http: HttpClient, private authService: AuthService) { 
  }
  fetchData(): Observable<Beer[]> {
    let user = this.authService.getUser();

    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': 'Basic ' + btoa(`${user.username}:${user.password}`)
    });

    return this.http.get<Beer[]>(this.beersUrl, { headers });
  }
}
