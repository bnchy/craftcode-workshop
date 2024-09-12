import { Component } from '@angular/core';
import { BeerService } from '../../../services/beer.service';
import { Beer } from '../../../api';
import { ActivatedRoute } from '@angular/router';
import { CommonModule } from '@angular/common';
import { MatCardModule } from '@angular/material/card';
import { MatDividerModule } from '@angular/material/divider';

@Component({
  selector: 'app-beer',
  standalone: true,
  imports: [CommonModule, MatCardModule, MatDividerModule],
  templateUrl: './beer.component.html',
  styleUrl: './beer.component.scss'
})
export class BeerComponent {
  beer!: Beer;

  constructor(private beerService: BeerService, private route: ActivatedRoute){}

  ngOnInit(){
    const id = this.route.snapshot.paramMap.get('id');
    this.beerService.fetchBeerById(+id!).subscribe(data => {
      this.beer = data;
    })
  }
}
