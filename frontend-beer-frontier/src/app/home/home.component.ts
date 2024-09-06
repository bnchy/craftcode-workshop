import { Component } from '@angular/core';
import { BeerService } from '../services/beer.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss'
})

export class HomeComponent {
  data:any;

  constructor(private beerService: BeerService) {


  }
  ngOnInit() {
    this.beerService.fetchData().subscribe(data => {
      this.data = data;
      console.log(this.data);
    });
  }
}
