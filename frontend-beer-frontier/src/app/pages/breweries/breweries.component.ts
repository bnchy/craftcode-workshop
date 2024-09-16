import { Component, OnInit } from '@angular/core';
import { BreweryService } from '../../services/brewery.service';
import { Brewery } from '../../api';
import { MatCardModule } from '@angular/material/card';
import { MatTableModule } from '@angular/material/table';
import { CommonModule } from '@angular/common';
import { MatButtonModule } from '@angular/material/button';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-breweries',
  standalone: true,
  imports: [
    CommonModule,
    MatCardModule,
    MatTableModule,
    MatButtonModule,
    RouterLink,
  ],
  templateUrl: './breweries.component.html',
  styleUrl: './breweries.component.scss',
})
export class BreweriesComponent implements OnInit {
  breweries: Brewery[] = [];
  displayedBreweryColumns: string[] = ['id', 'name', 'location'];

  constructor(private breweryService: BreweryService) {}

  ngOnInit() {
    this.breweryService.fetchAllBreweries().subscribe(data => {
      this.breweries = data;
    });
  }
}
