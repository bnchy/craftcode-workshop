import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatDividerModule } from '@angular/material/divider';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatDividerModule
  ],
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {
  userObj: any = {
    username: '',
    password: ''
  }

  constructor(private router: Router, private http: HttpClient) {}

  login() {
    const body = new HttpParams()
      .set('username', this.userObj.username)
      .set('password', this.userObj.password);

    const headers = new HttpHeaders({
      'Content-Type': 'application/x-www-form-urlencoded'
    });

    this.http.post('http://localhost:8080/login', body.toString(), { headers, observe: 'response' })
      .subscribe(response => {
        if (response.status === 200) {
          console.log('Login successful');
          localStorage.setItem('user', JSON.stringify(this.userObj));
          this.router.navigate(['/home']); // Navigate to home or another route
        }
      }, error => {
        console.log('Login failed', error);
      });
  }
}