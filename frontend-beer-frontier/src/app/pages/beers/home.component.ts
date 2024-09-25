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
import { debounceTime, distinctUntilChanged, Subject } from 'rxjs';
import { MatPaginatorModule, PageEvent } from '@angular/material/paginator';

interface BeerWithPlaceholder extends Beer {
  placeholderImage?: string;
}

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    RouterLink,
    MatCardModule,
    MatIconModule,
    MatDividerModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    MatButtonModule,
    MatPaginatorModule,
  ],
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
})
export class HomeComponent implements OnInit {
  pageNr = 1;
  pageSize = 12;
  pageSizeOptions = [6, 12, 24, 36];
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
    this.fetchBeers();

    this.searchTermSubject
      .pipe(debounceTime(300), distinctUntilChanged())
      .subscribe(() => {
        this.fetchBeers();
      });
  }

  fetchBeers(): void {
    const fetch = this.searchTerm
      ? this.beerService.fetchBeersBySearch(
          this.searchTerm,
          this.pageNr,
          this.pageSize
        )
      : this.beerService.fetchAllBeers(this.pageNr, this.pageSize);

    fetch.subscribe({
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

  searchBeers(): void {
    this.searchTermSubject.next(this.searchTerm);
  }

  getRandomPlaceholder(): string {
    const randomIndex = Math.floor(
      Math.random() * this.beerPlaceholderImages.length
    );
    return this.beerPlaceholderImages[randomIndex];
  }

  onPageChange(event: PageEvent): void {
    this.pageNr = event.pageIndex + 1;
    this.pageSize = event.pageSize;
    this.fetchBeers();
  }
}
