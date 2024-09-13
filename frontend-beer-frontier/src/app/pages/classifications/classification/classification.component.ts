import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Classification } from '../../../api';
import { ClassificationsService } from '../../../services/classifications.service';
import { MatCardModule } from '@angular/material/card';
import { MatDividerModule } from '@angular/material/divider';

@Component({
  selector: 'app-classification',
  standalone: true,
  imports: [MatCardModule, MatDividerModule],
  templateUrl: './classification.component.html',
  styleUrl: './classification.component.scss'
})
export class ClassificationComponent {

  classification!: Classification;
  edit: boolean = false;
  constructor(private classificationService: ClassificationsService, private router: ActivatedRoute) {}

  ngOnInit() {
    const id = this.router.snapshot.paramMap.get('id');
    this.classificationService.fetchAClassification(+id!).subscribe(data => {
      this.classification = data;
    })
  }
  updateAClassification(classification: Classification) {
    this.classificationService.updateAClassification(classification).subscribe(data => {
      this.edit = false;
    });
  }
}
