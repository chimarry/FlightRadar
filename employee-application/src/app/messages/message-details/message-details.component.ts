import { Component, Inject, Input, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Message } from 'src/app/models/message';

@Component({
  selector: 'app-message-details',
  templateUrl: './message-details.component.html',
  styleUrls: ['./message-details.component.css']
})
export class MessageDetailsComponent implements OnInit {

  constructor(private dialogRef: MatDialogRef<MessageDetailsComponent>, @Inject(MAT_DIALOG_DATA) public message: Message) {
  }

  ngOnInit() {
  }

  close() {
    this.dialogRef.close();
  }
}
