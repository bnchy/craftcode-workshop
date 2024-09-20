import { Component, inject, OnInit, signal } from '@angular/core';
import { Beer, Brewery } from '../../../api';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
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
import { MatDialog } from '@angular/material/dialog';
import { DialogOverviewComponent } from '../../../shared/dialog/dialog-overview/dialog-overview.component';

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
  edit = false;

  readonly beerId = signal('');
  readonly dialog = inject(MatDialog);

  constructor(
    private breweryService: BreweryService,
    private beerService: BeerService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit() {
    const id = this.route.snapshot.paramMap.get('id');
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
    this.beerService.fetchBeersByBreweryId(breweryId).subscribe(
      (data: Beer[] | null) => {
        this.beers = data || [];
        if (this.beers.length === 0) {
          console.log('No more beers in the brewery');
        }
      },
      error => {
        console.error('Error fetching beers', error);
      }
    );
  }

  openDialog(beerName: string, breweryName: string, beerId: number): void {
    const dialogRef = this.dialog.open(DialogOverviewComponent, {
      data: { itemName: beerName, removeFrom: breweryName },
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result === true) {
        this.unlinkBeer(beerId);
      }
    });
  }

  unlinkBeer(beerId: number) {
    if (this.brewery.id !== undefined) {
      this.breweryService
        .unlinkBeerFromBrewery(this.brewery.id, beerId)
        .subscribe(() => {
          this.fetchBeersForBrewery(this.brewery.id!);
        });
    }
  }
  deleteBrewery() {
    if (!this.brewery.id) {
      return;
    }
    this.breweryService.deleteBrewery(this.brewery.id).subscribe(() => {
      this.router.navigate(['/breweries']);
    });
  }
}
