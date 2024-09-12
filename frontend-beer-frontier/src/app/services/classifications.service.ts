import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AuthService } from './auth.service';
import { environment } from '../../environment/environment';
import { Classification } from '../api';

@Injectable({
  providedIn: 'root'
})
export class ClassificationsService {

  constructor(private http: HttpClient, private authService: AuthService) { }

  fetchData() : Observable<Classification[]> {
    const user = this.authService.getUser();

    const headers = {
      'Content-Type': 'application/json',
      'Authorization': 'Basic ' + btoa(`${user.username}:${user.password}`)
    }
    
    return this.http.get<Classification[]>(`${environment.apiUrl}/classifications`, { headers})
  }
}
