import { Component, inject, OnInit } from '@angular/core';
import { BeerService } from '../../../services/beer.service';
import { Beer, Brewery } from '../../../api';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
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
import { MatDialog } from '@angular/material/dialog';
import { BreweryService } from '../../../services/brewery.service';
import { DialogOverviewComponent } from '../../../shared/dialog/dialog-overview/dialog-overview.component';

@Component({
  selector: 'app-beer',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    RouterLink,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatDividerModule,
    MatIconModule,
    MatCardModule,
    MatSelectModule,
  ],
  templateUrl: './beer.component.html',
  styleUrls: ['./beer.component.scss'],
})
export class BeerComponent implements OnInit {
  edit = false;
  beer!: Beer;
  breweries: Brewery[] = [];

  readonly dialog = inject(MatDialog);

  constructor(
    private beerService: BeerService,
    private breweryService: BreweryService,
    private route: ActivatedRoute,
    private router: Router
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
  deleteBeer() {
    if (!this.beer.id) {
      return;
    }
    this.beerService.deleteBeer(this.beer.id).subscribe(() => {
      this.router.navigate(['/beers']);
    });
  }

  openDialogDelete(): void {
    const dialogRef = this.dialog.open(DialogOverviewComponent, {
      data: {
        actionType: 'Delete',
        itemName: this.beer!.name,
        itemType: 'beer',
        hasChildren: false,
      },
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result === true) {
        this.deleteBeer();
      }
    });
  }
}
