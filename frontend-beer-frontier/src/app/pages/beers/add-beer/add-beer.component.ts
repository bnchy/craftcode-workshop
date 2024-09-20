import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatDividerModule } from '@angular/material/divider';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { Router, RouterLink } from '@angular/router';
import { Beer, BeerBeerTypeEnum } from '../../../api';
import { transformEnumValue } from '../../../utils/string-utils';
import { BeerService } from '../../../services/beer.service';

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
export class AddBeerComponent {
  beer: Beer = {
    name: '',
    alcoholPercentage: undefined,
    beerType: undefined,
    brewery: undefined,
  };

  beerTypes = Object.values(BeerBeerTypeEnum);

  constructor(
    private beerService: BeerService,
    private router: Router
  ) {}

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
}
