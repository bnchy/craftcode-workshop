import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../environment/environment';
import { catchError, map, Observable, of } from 'rxjs';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  constructor(
    private http: HttpClient,
    private router: Router
  ) {}

  getUser(): { username: string; password: string } {
    let user = { username: '', password: '' };

    if (typeof localStorage !== 'undefined') {
      const storedUser = localStorage.getItem('user');
      if (storedUser) {
        user = JSON.parse(storedUser);
      }
    }
    return user;
  }

  getHeaders(): HttpHeaders {
    const user = this.getUser();

    return new HttpHeaders({
      'Content-Type': 'application/json',
      Authorization: 'Basic ' + btoa(`${user.username}:${user.password}`),
    });
  }

  isLoggedIn(token: string): Observable<boolean> {
    if (!token) {
      return of(false);
    }

    const headers = this.getHeaders();

    return this.http
      .post(`${environment.apiUrl}/login`, JSON.parse(token), {
        headers,
        observe: 'response',
      })
      .pipe(
        map(response => response.status === 200),
        catchError(() => of(false))
      );
  }

  login(username: string, password: string): Observable<boolean> {
    const body = new HttpParams()
      .set('username', username)
      .set('password', password);

    const headers = new HttpHeaders({
      'Content-Type': 'application/x-www-form-urlencoded',
    });

    return this.http
      .post(`${environment.apiUrl}/login`, body.toString(), {
        headers,
        observe: 'response',
      })
      .pipe(
        map(response => {
          if (response.status === 200) {
            localStorage.setItem(
              'user',
              JSON.stringify({ username, password })
            );
            this.router.navigate(['/beers']);
            return true;
          } else {
            return false;
          }
        }),
        catchError(error => {
          console.log('Login failed', error);
          return of(false);
        })
      );
  }
}
