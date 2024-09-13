import { Component } from '@angular/core';
import { BeerService } from '../../services/beer.service';
import { CommonModule } from '@angular/common';
import { MatCardModule } from '@angular/material/card';
import { Beer } from '../../api';
import { RouterLink } from '@angular/router';
import { MatDividerModule } from '@angular/material/divider';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule, MatCardModule, MatDividerModule, RouterLink],
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss'
})

export class HomeComponent {
  beers: Beer[] = [];

  constructor(private beerService: BeerService) {
  }

  ngOnInit() {
    this.beerService.fetchAllBeers().subscribe(data => {
      this.beers = data;
    });
  }
}
