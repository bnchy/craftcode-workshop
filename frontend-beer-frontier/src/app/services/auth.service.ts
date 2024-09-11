import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../environment/environment';
import { catchError, map, Observable, of } from 'rxjs';
import { PassThrough } from 'stream';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) { }

  isLoggedIn(token:string): Observable<boolean>{

    if(!token) {
      return of(false);
    }

    const headers = new HttpHeaders({
      'Content-Type': 'application/json'
    })

    return this.http.post(`${environment.apiUrl}/login`, JSON.parse(token) , { headers, observe: 'response' })
      .pipe(
        map(response => response.status === 200),
        catchError(() => of(false))
      );
  }
}
