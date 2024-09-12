import { Component } from '@angular/core';
import { BreweryService } from '../../services/brewery.service';
import { Brewery } from '../../api';
import { MatCardModule } from '@angular/material/card';
import { MatTableModule } from '@angular/material/table';
import { CommonModule } from '@angular/common';
import { MatButtonModule } from '@angular/material/button';

@Component({
  selector: 'app-breweries',
  standalone: true,
  imports: [CommonModule, MatCardModule, MatTableModule, MatButtonModule],
  templateUrl: './breweries.component.html',
  styleUrl: './breweries.component.scss'
})
export class BreweriesComponent {

  breweries: Brewery[] = [];
  displayedBreweryColumns : string[] = ['id','name', 'location'];

  constructor(private breweryService: BreweryService) {}

  ngOnInit(){
    this.breweryService.fetchData().subscribe( data => {
      this.breweries = data;
    })
  }

}
