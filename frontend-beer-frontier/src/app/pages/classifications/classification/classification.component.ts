import { Component, inject, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import {
  Classification,
  ClassificationCountryEnum,
  ClassificationFermentationTypeEnum,
  ClassificationNamesAndOriginsEnum,
  ClassificationUsedGrainTypeEnum,
} from '../../../api';
import { ClassificationService } from '../../../services/classification.service';
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
import { BeerService } from '../../../services/beer.service';
import { MatDialog } from '@angular/material/dialog';
import { DialogOverviewComponent } from '../../../shared/dialog/dialog-overview/dialog-overview.component';

@Component({
  selector: 'app-classification',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    MatFormFieldModule,
    MatOptionModule,
    MatSelectModule,
    MatButtonModule,
    MatInputModule,
    MatCardModule,
    MatDividerModule,
    MatFormFieldModule,
    MatIconModule,
  ],
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

  readonly dialog = inject(MatDialog);

  constructor(
    private classificationService: ClassificationService,
    private beerService: BeerService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit() {
    const id = this.route.snapshot.paramMap.get('id');
    this.classificationService.fetchAClassification(+id!).subscribe(data => {
      this.classification = data;
    });
  }

  getBeer() {
    //TODO backend
    /*     this.beerService.fetchBeerById(this.classification.beerId).subscribe(data => {
      this.classification.beer = data;
    }) */
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

  deleteClassification() {
    console.log(this.classification.id);
    if (!this.classification.id) {
      return;
    }
    this.classificationService
      .deleteClassification(this.classification.id)
      .subscribe(() => {
        this.router.navigate(['/classifications']);
      });
  }

  openDialogDelete(): void {
    const dialogRef = this.dialog.open(DialogOverviewComponent, {
      data: {
        actionType: 'Delete',
        itemName: this.classification!.id,
        itemType: 'classification',
        hasChildren: true,
        childType: 'beers',
      },
    });
    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.deleteClassification();
      }
    });
  }
}
