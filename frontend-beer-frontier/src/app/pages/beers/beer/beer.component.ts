import { Component, OnInit } from '@angular/core';
import { BeerService } from '../../../services/beer.service';
import { Beer } from '../../../api';
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

@Component({
  selector: 'app-beer',
  standalone: true,
  imports: [    CommonModule,
    FormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatDividerModule,
    MatIconModule,
    MatCardModule,
    RouterLink],
  templateUrl: './beer.component.html',
  styleUrls: ['./beer.component.scss']
})
export class BeerComponent implements OnInit {
  edit: boolean = false;
  beer: Beer | undefined;

  constructor(private beerService: BeerService, private route: ActivatedRoute) {}

  ngOnInit() {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.beerService.fetchBeerById(+id).subscribe(data => {
        this.beer = data;
      });
    }
  }
  transformEnum(value: string | undefined) : string {
    if (!value) {
      return '';
    }
    return transformEnumValue(value);
  }
}