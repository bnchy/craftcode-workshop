import { Component, OnInit } from '@angular/core';
import { BeerService } from '../../services/beer.service';
import { CommonModule } from '@angular/common';
import { MatCardModule } from '@angular/material/card';
import { Beer } from '../../api';
import { RouterLink } from '@angular/router';
import { MatDividerModule } from '@angular/material/divider';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { FormsModule } from '@angular/forms';

interface BeerWithPlaceholder extends Beer {
  placeholderImage?: string;
}

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    MatCardModule,
    MatIconModule,
    MatDividerModule,
    RouterLink,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    MatButtonModule,
  ],
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
})
export class HomeComponent implements OnInit {
  beers: BeerWithPlaceholder[] = [];
  searchTerm = '';

  beerPlaceholderImages = [
    '/assets/images/beer-placeholder1.jpg',
    '/assets/images/beer-placeholder2.jpg',
    '/assets/images/beer-placeholder3.jpg',
    '/assets/images/beer-placeholder4.png',
  ];

  constructor(private beerService: BeerService) {}

  ngOnInit() {
    this.getAllBeers();
  }

  getAllBeers(): void {
    this.beerService.fetchAllBeers().subscribe({
      next: data => {
        this.beers = data.map(beer => ({
          ...beer,
          placeholderImage: this.getRandomPlaceholder(), // Assign random placeholder image
        }));
      },
    });
  }

  searchBeers(): void {
    if (this.searchTerm.trim()) {
      this.beerService.fetchBeersBySearch(this.searchTerm).subscribe({
        next: data => {
          this.beers = data.map(beer => ({
            ...beer,
            placeholderImage: this.getRandomPlaceholder(),
          }));
        },
      });
    } else {
      this.getAllBeers();
    }
  }

  getRandomPlaceholder(): string {
    const randomIndex = Math.floor(
      Math.random() * this.beerPlaceholderImages.length
    );
    return this.beerPlaceholderImages[randomIndex];
  }
}
