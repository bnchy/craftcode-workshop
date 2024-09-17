import { Component, inject, OnInit, signal } from '@angular/core';
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
import { MatExpansionModule } from '@angular/material/expansion';
import { RemoveDialogComponent } from '../../../shared/remove-dialog/remove-dialog.component';
import { MatDialog } from '@angular/material/dialog';

@Component({
  selector: 'app-brewery',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    MatInputModule,
    MatExpansionModule,
    MatCardModule,
    MatDividerModule,
    MatIconModule,
    MatButtonModule,
    MatFormFieldModule,
    MatExpansionModule,
    RouterLink,
  ],
  templateUrl: './brewery.component.html',
  styleUrl: './brewery.component.scss',
})
export class BreweryComponent implements OnInit {
  brewery!: Brewery;
  beers!: Beer[];
  edit = true;
  readonly panelOpenState = signal(false);
  readonly dialog = inject(MatDialog);

  constructor(
    private breweryService: BreweryService,
    private beerService: BeerService,
    private router: ActivatedRoute,
    private removeDialog: RemoveDialogComponent
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
