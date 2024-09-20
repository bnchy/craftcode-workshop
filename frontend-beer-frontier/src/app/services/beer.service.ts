import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../environment/environment';
import { Beer } from '../api';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root',
})
export class BeerService {
  private beersUrl = `${environment.apiUrl}/beers`;

  constructor(
    private http: HttpClient,
    private authService: AuthService
  ) {}

  fetchAllBeers(): Observable<Beer[]> {
    const headers = this.authService.getHeaders();
    return this.http.get<Beer[]>(this.beersUrl, { headers });
  }

  fetchBeerById(id: number): Observable<Beer> {
    const headers = this.authService.getHeaders();
    return this.http.get<Beer>(`${this.beersUrl}/${id}`, { headers });
  }

  fetchBeersByBreweryId(breweryId: number): Observable<Beer[]> {
    const headers = this.authService.getHeaders();
    return this.http.get<Beer[]>(`${this.beersUrl}/by-brewery/${breweryId}`, {
      headers,
    });
  }

  updateBeer(beer: Beer): Observable<Beer> {
    const headers = this.authService.getHeaders();
    return this.http.put<Beer>(`${this.beersUrl}/${beer.id}`, beer, {
      headers,
    });
  }

  deleteBeer(id: number): Observable<void> {
    const headers = this.authService.getHeaders();
    return this.http.delete<void>(`${this.beersUrl}/${id}`, { headers });
  }
}
