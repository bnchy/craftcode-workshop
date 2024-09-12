import { Component } from '@angular/core';
import { ClassificationsService } from '../../services/classifications.service';
import { Classification } from '../../api';
import { MatTableModule } from '@angular/material/table';
import { MatButtonModule } from '@angular/material/button';

@Component({
  selector: 'app-classifications',
  standalone: true,
  imports: [MatTableModule, MatButtonModule],
  templateUrl: './classifications.component.html',
  styleUrl: './classifications.component.scss'
})
export class ClassificationsComponent {

  classifications: Classification[] = [];
  displayedClassificationColumns : string[] = ['id', 'country', 'usedGrainType', 'fermentationType', 'namesAndOrigins']

  constructor(private classification: ClassificationsService) {}

  ngOnInit(){
    this.classification.fetchAllClassifications().subscribe( data => {
      this.classifications = data;
    })
  }
}
