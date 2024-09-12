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


  fetchAllBeers(): Observable<Beer[]> {
    const headers = this.authService.getHeaders();
    return this.http.get<Beer[]>(this.beersUrl, { headers });
  }

  fetchBeerById(id: number): Observable<Beer> {
    const headers = this.authService.getHeaders();
    return this.http.get<Beer>(`${this.beersUrl}/${id}`, { headers});
  }
 }
