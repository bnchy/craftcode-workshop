import { Component } from '@angular/core';
import { BeerService } from '../../services/beer.service';
import { CommonModule } from '@angular/common';
import { MatCardModule } from '@angular/material/card';
import { Beer } from '../../api';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule, MatCardModule],
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss'
})

export class HomeComponent {
  data: Beer[] = [];

  constructor(private beerService: BeerService) {
  }

  ngOnInit() {
    this.beerService.fetchData().subscribe(data => {
      this.data = data;
    });
  }
}
