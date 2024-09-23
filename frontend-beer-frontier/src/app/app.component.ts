import { Component } from '@angular/core';
import { Router, RouterLink, RouterOutlet } from '@angular/router';
import { NavbarComponent } from './shared/navbar/navbar.component';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, NavbarComponent, RouterLink, CommonModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss',
})
export class AppComponent {
  title = 'beer-frontier';
  showNavbar = true;

  constructor(private router: Router) {
    this.router.events.subscribe(() => {
      const noNavbarRouters = ['/login'];
      this.showNavbar = !noNavbarRouters.includes(this.router.url);
    });
  }
}
