import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../environment/environment';


@Injectable({
  providedIn: 'root'
})

export class BeerService {
  private beersUrl = `${environment.apiUrl}/beers`

  constructor(private http: HttpClient) { 
  }

  fetchData() : Observable<any> {
    return this.http.get(this.beersUrl);
  }
}
