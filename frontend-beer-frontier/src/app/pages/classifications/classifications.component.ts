import { Component, OnInit } from '@angular/core';
import { ClassificationService } from '../../services/classification.service';
import { Classification } from '../../api';
import { MatTableModule } from '@angular/material/table';
import { MatButtonModule } from '@angular/material/button';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-classifications',
  standalone: true,
  imports: [MatTableModule, MatButtonModule, RouterLink],
  templateUrl: './classifications.component.html',
  styleUrl: './classifications.component.scss',
})
export class ClassificationsComponent implements OnInit {
  classifications: Classification[] = [];
  displayedClassificationColumns: string[] = [
    'id',
    'country',
    'usedGrainType',
    'fermentationType',
    'namesAndOrigins',
  ];

  constructor(private classification: ClassificationService) {}

  ngOnInit() {
    this.classification.fetchAllClassifications().subscribe(data => {
      this.classifications = data;
    });
  }
}
