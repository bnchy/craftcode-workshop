import { Component } from '@angular/core';
import { Beer, Brewery } from '../../../api';
import { ActivatedRoute, RouterLink } from '@angular/router';
import { BreweryService } from '../../../services/brewery.service';
import { MatCardModule } from '@angular/material/card';
import { MatDividerModule } from '@angular/material/divider';
import { CommonModule } from '@angular/common';
import { BeerService } from '../../../services/beer.service';

@Component({
  selector: 'app-brewery',
  standalone: true,
  imports: [CommonModule,MatCardModule , MatDividerModule, RouterLink ],
  templateUrl: './brewery.component.html',
  styleUrl: './brewery.component.scss'
})
export class BreweryComponent {

  brewery!: Brewery;
  beers! : Beer[];
  edit: boolean = false;

  constructor(private breweryService: BreweryService, private beerService: BeerService, private router: ActivatedRoute) {}

  ngOnInit(){
    const id = this.router.snapshot.paramMap.get('id');
    this.breweryService.fetchABrewery(+id!).subscribe( data  => {
      this.brewery = data;
    } )
    this.fetchBeersForBrewery(+id!);
  }
  updateBrewery(brewery: Brewery) {
    this.breweryService.updateABrewery(brewery).subscribe(data => {
      this.edit = false;
    });
  }

  fetchBeersForBrewery(breweryId: number) {
    this.beerService.fetchBeersByBreweryId(breweryId).subscribe((data : Beer[]) => {
      this.beers = data;
    });
  }

}
