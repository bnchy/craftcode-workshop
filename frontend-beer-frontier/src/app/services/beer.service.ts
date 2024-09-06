import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})

export class BeerService {
  private beersUrl = "http://localhost:8080/beers"

  constructor(private http: HttpClient) { 
  }

  fetchData() : Observable<any> {
    return this.http.get(this.beersUrl);
  }
}
