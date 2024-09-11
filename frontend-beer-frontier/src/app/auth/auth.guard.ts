import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot } from '@angular/router';
import { map, Observable, tap } from 'rxjs';
import { AuthService } from '../services/auth.service';
import { get } from 'http';

@Injectable({
    providedIn: 'root'
  })

export class AuthGuard implements CanActivate{
    constructor(private authService: AuthService, private router: Router) {}

    canActivate() : boolean {
        const token =  this.getToken();

        if (!token) {
            this.router.navigate(['/login']);
            return false;
        }
        
        if (this.authService.isLoggedIn(token)) {
            return true;
        } else {
            this.router.navigate(['/login']);
            return false;
        }
        
    }
    
    private getToken(): string | null {
        if (typeof window !== 'undefined' && typeof localStorage !== 'undefined') {
            return localStorage.getItem('user');
        }
        return null;
    }
}

