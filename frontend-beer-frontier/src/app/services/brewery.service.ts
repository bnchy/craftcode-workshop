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

  fetchAllBreweries(): Observable<Brewery[]> {
    const headers = this.authService.getHeaders();
    return this.http.get<Brewery[]>(this.breweryUrl, {headers});
  }

  fetchABrewery(id: number): Observable<Brewery> {
    const headers = this.authService.getHeaders();
    return this.http.get<Brewery>(`${this.breweryUrl}/${id}`, {headers});
  }
}
