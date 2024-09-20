import { Injectable } from '@angular/core';
import { environment } from '../../environment/environment';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Brewery } from '../api/models';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root',
})
export class BreweryService {
  private breweryUrl = `${environment.apiUrl}/breweries`;
  constructor(
    private http: HttpClient,
    private authService: AuthService
  ) {}

  fetchAllBreweries(): Observable<Brewery[]> {
    const headers = this.authService.getHeaders();
    return this.http.get<Brewery[]>(this.breweryUrl, { headers });
  }

  fetchABrewery(id: number): Observable<Brewery> {
    const headers = this.authService.getHeaders();
    return this.http.get<Brewery>(`${this.breweryUrl}/${id}`, { headers });
  }
  createABrewery(brewery: Brewery): Observable<Brewery> {
    const headers = this.authService.getHeaders();
    return this.http.post<Brewery>(this.breweryUrl, brewery, { headers });
  }

  updateABrewery(brewery: Brewery): Observable<Brewery> {
    const headers = this.authService.getHeaders();
    return this.http.put<Brewery>(`${this.breweryUrl}/${brewery.id}`, brewery, {
      headers,
    });
  }

  unlinkBeerFromBrewery(
    breweryId: number,
    beerId: number
  ): Observable<boolean> {
    const headers = this.authService.getHeaders();
    return this.http.delete<boolean>(
      `${this.breweryUrl}/${breweryId}/beers/${beerId}`,
      { headers }
    );
  }
  deleteBrewery(id: number): Observable<void> {
    const headers = this.authService.getHeaders();
    return this.http.delete<void>(`${this.breweryUrl}/${id}`, { headers });
  }
}
