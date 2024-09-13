import { Component } from '@angular/core';
import { Brewery } from '../../../api';
import { ActivatedRoute } from '@angular/router';
import { BreweryService } from '../../../services/brewery.service';
import { MatCardModule } from '@angular/material/card';
import { MatDividerModule } from '@angular/material/divider';

@Component({
  selector: 'app-brewery',
  standalone: true,
  imports: [MatCardModule , MatDividerModule],
  templateUrl: './brewery.component.html',
  styleUrl: './brewery.component.scss'
})
export class BreweryComponent {

  brewery!: Brewery;
  edit: boolean = false;

  constructor(private breweryService: BreweryService, private router: ActivatedRoute) {}

  ngOnInit(){
    const id = this.router.snapshot.paramMap.get('id');
    this.breweryService.fetchABrewery(+id!).subscribe( data => {
      this.brewery = data;
    } )
  }
  updateBrewery(brewery: Brewery) {
    this.breweryService.updateABrewery(brewery).subscribe(data => {
      this.edit = false;
    });
  }

}
