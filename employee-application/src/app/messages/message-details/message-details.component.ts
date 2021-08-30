import { Component, Inject, Input, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Message } from 'src/app/models/message';

@Component({
  selector: 'app-message-details',
  templateUrl: './message-details.component.html',
  styleUrls: ['./message-details.component.css']
})
export class MessageDetailsComponent implements OnInit {

  public form: FormGroup = new FormGroup({});

  constructor(public formBuilder: FormBuilder,
    private dialogRef: MatDialogRef<MessageDetailsComponent>, @Inject(MAT_DIALOG_DATA) public message: Message) {
  }

  ngOnInit() {
    this.form = this.formBuilder.group({
      name: [this.message.name, Validators.required],
      email: [this.message.email, Validators.required],
      text: [this.message.text, Validators.required]
    });
  }

  close() {
    this.dialogRef.close();
  }
}
