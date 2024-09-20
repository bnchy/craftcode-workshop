import { Component, inject, model } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { CommonModule } from '@angular/common';
import {
  MAT_DIALOG_DATA,
  MatDialogActions,
  MatDialogClose,
  MatDialogContent,
  MatDialogRef,
  MatDialogTitle,
} from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';

@Component({
  selector: 'app-dialog-overview-',
  templateUrl: 'dialog-overview.component.html',
  styleUrls: ['dialog-overview.component.scss'],
  standalone: true,
  imports: [
    CommonModule,
    MatFormFieldModule,
    MatInputModule,
    FormsModule,
    MatButtonModule,
    MatDialogTitle,
    MatDialogContent,
    MatDialogActions,
    MatDialogClose,
  ],
})
export class DialogOverviewComponent {
  readonly dialogRef = inject(MatDialogRef<DialogOverviewComponent>);
  readonly data = inject<DialogData>(MAT_DIALOG_DATA);
  readonly itemName = model(this.data.itemName);
  onNoClick(): void {
    this.dialogRef.close();
  }

  onYesClick(): void {
    this.dialogRef.close(true);
  }
}

export interface DialogData {
  actionType: string;
  itemName: string;
  itemType: string;
  removeFrom: string;
  removeType: string;
  hasChildren: boolean;
  childType: string;
}
