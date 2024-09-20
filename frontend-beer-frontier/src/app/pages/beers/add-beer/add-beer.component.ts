import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatDividerModule } from '@angular/material/divider';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { Router, RouterLink } from '@angular/router';
import { Beer, BeerBeerTypeEnum, Brewery, Classification } from '../../../api';
import { transformEnumValue } from '../../../utils/string-utils';
import { BeerService } from '../../../services/beer.service';
import { BreweryService } from '../../../services/brewery.service';
import { ClassificationService } from '../../../services/classification.service';

@Component({
  selector: 'app-add-beer',
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
  templateUrl: './add-beer.component.html',
  styleUrl: './add-beer.component.scss',
})
export class AddBeerComponent implements OnInit {
  beer: Beer = {
    name: '',
    alcoholPercentage: undefined,
    beerType: undefined,
    brewery: undefined,
  };
  breweries: Brewery[] = [];
  classifications: Classification[] = [];

  beerTypes = Object.values(BeerBeerTypeEnum);

  constructor(
    private beerService: BeerService,
    private breweryService: BreweryService,
    private ClassificationService: ClassificationService,
    private router: Router
  ) {}

  ngOnInit() {
    this.fetchBreweries();
    this.fetchClassifications();
  }

  transformEnum(value: string | undefined): string {
    if (!value) {
      return '';
    }
    return transformEnumValue(value);
  }

  createBeer() {
    this.beerService.createBeer(this.beer).subscribe({
      next: response => {
        console.log('Beer created successfully:', response);
        this.router.navigate(['/beers']);
      },
      error: error => {
        console.error('Error creating beer:', error);
      },
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

  onClassificationChange(classificationId: number) {
    const selectedClassification = this.classifications.find(
      classification => classification.id === classificationId
    );
    if (selectedClassification) {
      this.beer!.classification = selectedClassification;
    }
  }

  fetchBreweries() {
    this.breweryService.fetchAllBreweries().subscribe(data => {
      this.breweries = data;
    });
  }

  fetchClassifications() {
    this.ClassificationService.fetchAllClassifications().subscribe(data => {
      this.classifications = data;
    });
  }
}
