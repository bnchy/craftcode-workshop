import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatDividerModule } from '@angular/material/divider';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { Router, RouterLink } from '@angular/router';
import {
  Classification,
  ClassificationCountryEnum,
  ClassificationFermentationTypeEnum,
  ClassificationNamesAndOriginsEnum,
  ClassificationUsedGrainTypeEnum,
} from '../../../api';
import { ClassificationService } from '../../../services/classification.service';
import { transformEnumValue } from '../../../utils/string-utils';

@Component({
  selector: 'app-add-classification',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatDividerModule,
    MatIconModule,
    MatCardModule,
    MatSelectModule,
    RouterLink,
  ],
  templateUrl: './add-classification.component.html',
  styleUrl: './add-classification.component.scss',
})
export class AddClassificationComponent {
  classification: Classification = {
    fermentationType: undefined,
    country: undefined,
    usedGrainType: undefined,
    namesAndOrigins: undefined,
  };

  fermentationTypes = Object.values(ClassificationFermentationTypeEnum);
  usedGrainTypes = Object.values(ClassificationUsedGrainTypeEnum);
  namesAndOrigins = Object.values(ClassificationNamesAndOriginsEnum);
  countries = Object.values(ClassificationCountryEnum);

  constructor(
    private classificationService: ClassificationService,
    private router: Router
  ) {}

  createClassification() {
    this.classificationService
      .createAClassification(this.classification)
      .subscribe(() => {
        this.router.navigate(['/classifications']);
      });
  }
  transformEnum(value: string | undefined): string {
    if (!value) {
      return '';
    }
    return transformEnumValue(value);
  }

  onGrainTypeChange(value: ClassificationUsedGrainTypeEnum | undefined) {
    if (!value) {
      return;
    }
    this.classification.usedGrainType = value;
  }

  onFermentationTypeChange(
    value: ClassificationFermentationTypeEnum | undefined
  ) {
    if (!value) {
      return;
    }
    this.classification.fermentationType = value;
  }

  onCountryChange(value: ClassificationCountryEnum | undefined) {
    if (!value) {
      return;
    }
    this.classification.country = value;
  }

  onNamesAndOriginsChange(
    value: ClassificationNamesAndOriginsEnum | undefined
  ) {
    if (!value) {
      return;
    }
    this.classification.namesAndOrigins = value;
  }
}
