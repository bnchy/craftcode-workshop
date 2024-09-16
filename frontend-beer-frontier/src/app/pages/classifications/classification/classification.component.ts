import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Classification, ClassificationCountryEnum, ClassificationFermentationTypeEnum, ClassificationNamesAndOriginsEnum, ClassificationUsedGrainTypeEnum } from '../../../api';
import { ClassificationsService } from '../../../services/classifications.service';
import { MatCardModule } from '@angular/material/card';
import { MatDividerModule } from '@angular/material/divider';
import { CommonModule } from '@angular/common';
import { MatFormFieldModule } from '@angular/material/form-field';
import { transformEnumValue } from '../../../utils/string-utils';
import { FormsModule } from '@angular/forms';
import { MatInputModule } from '@angular/material/input';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatOptionModule } from '@angular/material/core';
import { MatSelectModule } from '@angular/material/select';

@Component({
  selector: 'app-classification',
  standalone: true,
  imports: [CommonModule, FormsModule, MatFormFieldModule, MatOptionModule, MatSelectModule, MatButtonModule, MatInputModule, MatCardModule, MatDividerModule, MatFormFieldModule, MatIconModule],
  templateUrl: './classification.component.html',
  styleUrl: './classification.component.scss',
})
export class ClassificationComponent implements OnInit {
  classification!: Classification;
  edit = false;

  fermentationTypes = Object.values(ClassificationFermentationTypeEnum);
  grainTypes = Object.values(ClassificationUsedGrainTypeEnum);
  countries = Object.values(ClassificationCountryEnum);
  namesAndOrigins = Object.values(ClassificationNamesAndOriginsEnum);

  constructor(
    private classificationService: ClassificationsService,
    private router: ActivatedRoute
  ) {}

  ngOnInit() {
    const id = this.router.snapshot.paramMap.get('id');
    this.classificationService.fetchAClassification(+id!).subscribe(data => {
      this.classification = data;
    });
  }
  updateAClassification() {
    this.classificationService
      .updateAClassification(this.classification)
      .subscribe(() => {
        this.edit = false;
      });
  }
  transformEnum(value: string | undefined): string {
    if (!value) {
      return '';
    }
    return transformEnumValue(value);
  }
}
