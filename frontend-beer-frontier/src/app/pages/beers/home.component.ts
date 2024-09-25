import { Component, OnInit } from '@angular/core';
import { BeerService } from '../../services/beer.service';
import { CommonModule } from '@angular/common';
import { MatCardModule } from '@angular/material/card';
import { Beer, PageBeer } from '../../api';
import { RouterLink } from '@angular/router';
import { MatDividerModule } from '@angular/material/divider';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { FormsModule } from '@angular/forms';
import { debounceTime, distinctUntilChanged, Subject, switchMap } from 'rxjs';

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
  pageNr = 1;
  pageSize = 12;
  totalPages = 0;
  beers: BeerWithPlaceholder[] = [];
  searchTerm = '';
  searchTermSubject = new Subject<string>();

  beerPlaceholderImages = [
    '/assets/images/beer-placeholder1.jpg',
    '/assets/images/beer-placeholder2.jpg',
    '/assets/images/beer-placeholder3.jpg',
    '/assets/images/beer-placeholder4.png',
  ];

  constructor(private beerService: BeerService) {}

  ngOnInit() {
    this.getAllBeers();

    this.searchTermSubject
      .pipe(
        debounceTime(300),
        distinctUntilChanged(),
        switchMap(term =>
          term
            ? this.beerService.fetchBeersBySearch(
                term,
                this.pageNr,
                this.pageSize
              )
            : this.beerService.fetchAllBeers(this.pageNr, this.pageSize)
        )
      )
      .subscribe({
        next: (data: PageBeer) => {
          this.beers = data.content!.map(beer => ({
            ...beer,
            placeholderImage: this.getRandomPlaceholder(),
          }));
          this.totalPages = data.totalPages!;
        },
        error: err => {
          console.error('Error fetching beers:', err);
        },
      });
  }

  getAllBeers(): void {
    this.beerService.fetchAllBeers(this.pageNr, this.pageSize).subscribe({
      next: (data: PageBeer) => {
        this.beers = data.content!.map(beer => ({
          ...beer,
          placeholderImage: this.getRandomPlaceholder(),
        }));
      },
    });
  }

  searchBeers(): void {
    this.searchTermSubject.next(this.searchTerm);
  }

  getRandomPlaceholder(): string {
    const randomIndex = Math.floor(
      Math.random() * this.beerPlaceholderImages.length
    );
    return this.beerPlaceholderImages[randomIndex];
  }
}
