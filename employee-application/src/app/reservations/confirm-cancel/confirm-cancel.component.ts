import { Component, OnInit } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-confirm-cancel',
  templateUrl: './confirm-cancel.component.html',
  styleUrls: ['./confirm-cancel.component.css']
})
export class ConfirmCancelComponent implements OnInit {

  public cancellationReason: string | null = null;
  constructor(private dialogRef: MatDialogRef<ConfirmCancelComponent>) { }

  ngOnInit() {
  }

  close() {
    this.dialogRef.close();
  }

  confirm() {
    this.dialogRef.close(this.cancellationReason);
  }
}
