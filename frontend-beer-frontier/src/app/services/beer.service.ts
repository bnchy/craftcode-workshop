import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../environment/environment';
import { Beer, PageBeer } from '../api';
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

  fetchAllBeers(pageNr: number, pageSize: number): Observable<PageBeer> {
    const headers = this.authService.getHeaders();
    return this.http.get<PageBeer>(
      `${this.beersUrl}?pageNr=${pageNr}&pageSize=${pageSize}`,
      { headers }
    );
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

  fetchBeersBySearch(
    input: string,
    pageNr: number,
    pageSize: number
  ): Observable<PageBeer> {
    const headers = this.authService.getHeaders();
    return this.http.get<PageBeer>(
      `${this.beersUrl}/search?searchTerm=${input}&pageNr=${pageNr}&pageSize=${pageSize}`,
      {
        headers,
      }
    );
  }
  fetchBeersByPagination(pageNr: number, pageSize: number): Observable<Beer[]> {
    const headers = this.authService.getHeaders();
    return this.http.get<Beer[]>(
      `${this.beersUrl}/pagination?pageNr=${pageNr}&pageSize=${pageSize}`,
      {
        headers,
      }
    );
  }

  createBeer(beer: Beer): Observable<Beer> {
    const headers = this.authService.getHeaders();
    return this.http.post<Beer>(`${this.beersUrl}`, beer, { headers });
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
