import { Component, OnInit } from '@angular/core';
import { Beer, Brewery } from '../../../api';
import { ActivatedRoute, RouterLink } from '@angular/router';
import { BreweryService } from '../../../services/brewery.service';
import { MatCardModule } from '@angular/material/card';
import { MatDividerModule } from '@angular/material/divider';
import { CommonModule } from '@angular/common';
import { BeerService } from '../../../services/beer.service';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { FormsModule } from '@angular/forms';
import { MatInputModule } from '@angular/material/input';

@Component({
  selector: 'app-brewery',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    MatInputModule,
    MatCardModule,
    MatDividerModule,
    MatIconModule,
    MatButtonModule,
    MatFormFieldModule,
    RouterLink,
  ],
  templateUrl: './brewery.component.html',
  styleUrl: './brewery.component.scss',
})
export class BreweryComponent implements OnInit {
  brewery!: Brewery;
  beers!: Beer[];
  edit = false;

  constructor(
    private breweryService: BreweryService,
    private beerService: BeerService,
    private router: ActivatedRoute
  ) {}

  ngOnInit() {
    const id = this.router.snapshot.paramMap.get('id');
    this.breweryService.fetchABrewery(+id!).subscribe(data => {
      this.brewery = data;
    });
    this.fetchBeersForBrewery(+id!);
  }
  updateBrewery() {
    this.breweryService.updateABrewery(this.brewery).subscribe(() => {
      this.edit = false;
    });
  }

  fetchBeersForBrewery(breweryId: number) {
    this.beerService
      .fetchBeersByBreweryId(breweryId)
      .subscribe((data: Beer[]) => {
        this.beers = data;
      });
  }
}
