import { Component, OnInit } from '@angular/core';
import { BeerService } from '../../../services/beer.service';
import { Beer, Brewery } from '../../../api';
import { ActivatedRoute, RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';
import { MatCardModule } from '@angular/material/card';
import { MatDividerModule } from '@angular/material/divider';
import { transformEnumValue } from '../../../utils/string-utils';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatFormFieldModule } from '@angular/material/form-field';
import { FormsModule } from '@angular/forms';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { BreweryService } from '../../../services/brewery.service';

@Component({
  selector: 'app-beer',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatDividerModule,
    MatIconModule,
    MatCardModule,
    MatSelectModule,
    RouterLink,
  ],
  templateUrl: './beer.component.html',
  styleUrls: ['./beer.component.scss'],
})
export class BeerComponent implements OnInit {
  edit = false;
  beer: Beer | undefined;
  breweries: Brewery[] = [];

  constructor(
    private beerService: BeerService,
    private breweryService: BreweryService,
    private route: ActivatedRoute
  ) {}

  ngOnInit() {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.beerService.fetchBeerById(+id).subscribe(data => {
        this.beer = data;
      });
    }
    this.breweryService.fetchAllBreweries().subscribe(data => {
      this.breweries = data;
    });
  }
  transformEnum(value: string | undefined): string {
    if (!value) {
      return '';
    }
    return transformEnumValue(value);
  }

  updateBeer() {
    this.beerService.updateBeer(this.beer!).subscribe(() => {
      this.edit = false;
    });
  }

  onBreweryChange(breweryId: number) {
    const selectedBrewery = this.breweries.find(
      brewery => brewery.id === breweryId
    );
    if (selectedBrewery) {
      this.beer!.brewery = selectedBrewery;
    }
  }
}
