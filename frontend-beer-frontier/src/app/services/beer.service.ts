import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../environment/environment';
import { Beer } from '../api';


@Injectable({
  providedIn: 'root'
})

export class BeerService {
  private beersUrl = `${environment.apiUrl}/beers`

  constructor(private http: HttpClient) { 
  }
  fetchData(): Observable<Beer[]> {
    let user = { username: '', password: '' };
    if (typeof localStorage !== 'undefined') {
      const storedUser = localStorage.getItem('user');
      if (storedUser) {
        user = JSON.parse(storedUser);
      }
    }

    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': 'Basic ' + btoa(`${user.username}:${user.password}`)
    });

    return this.http.get<Beer[]>(this.beersUrl, { headers });
  }
}
