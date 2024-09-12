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

  fetchAllClassifications() : Observable<Classification[]> {
    const headers = this.authService.getHeaders();
    return this.http.get<Classification[]>(`${environment.apiUrl}/classifications`, { headers})
  }

  fetchAClassification(id: number) : Observable<Classification> {
    const headers = this.authService.getHeaders();
    return this.http.get<Classification>(`${environment.apiUrl}/classifications/${id}`, { headers})
  }
 }
