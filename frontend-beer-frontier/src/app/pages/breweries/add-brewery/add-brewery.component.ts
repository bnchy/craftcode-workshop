import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { MatDividerModule } from '@angular/material/divider';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { Brewery } from '../../../api';
import { MatCardModule } from '@angular/material/card';
import { MatIcon } from '@angular/material/icon';
import { Router, RouterLink } from '@angular/router';
import { BreweryService } from '../../../services/brewery.service';
import { FormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';

@Component({
  selector: 'app-add-brewery',
  standalone: true,
  imports: [
    CommonModule,
    MatDividerModule,
    MatFormFieldModule,
    MatInputModule,
    MatInputModule,
    MatCardModule,
    MatButtonModule,
    MatIcon,
    RouterLink,
    FormsModule,
  ],
  templateUrl: './add-brewery.component.html',
  styleUrl: './add-brewery.component.scss',
})
export class AddBreweryComponent {
  brewery: Brewery = {
    name: undefined,
    location: undefined,
  };

  constructor(
    private breweryService: BreweryService,
    private router: Router
  ) {}

  createBrewery() {
    this.breweryService.createABrewery(this.brewery).subscribe({
      next: response => {
        console.log('Beer created successfully:', response);
        this.router.navigate(['/beers']);
      },
      error: error => {
        console.error('Error creating beer:', error);
      },
    });
  }
}
