import { HttpClient } from '@angular/common/http';
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

  fetchData() : Observable<any> {
    return this.http.get<Beer[]>(this.beersUrl);
  }
}
