import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MessageReply } from 'src/app/models/message-reply';
import { MessageService } from '../services/message.service';

@Component({
  selector: 'app-message-reply',
  templateUrl: './message-reply.component.html',
  styleUrls: ['./message-reply.component.css']
})
export class MessageReplyComponent implements OnInit {

  public form: FormGroup = new FormGroup({});
  public messageReply: MessageReply = new MessageReply();

  constructor(public formBuilder: FormBuilder,
    private dialogRef: MatDialogRef<MessageReplyComponent>,
    private messageService: MessageService,
    @Inject(MAT_DIALOG_DATA) public email: string) {
      this.messageReply.email = email;
  }

  ngOnInit() {
    this.form = this.formBuilder.group({
      email: [this.messageReply.email, Validators.required],
      title: [this.messageReply.title, Validators.required],
      content: [this.messageReply.content, Validators.required],
    });
  }

  close() {
    this.dialogRef.close();
  }

  send({ value, valid }: { value: MessageReply, valid: boolean }) {
    if (valid) {
      this.messageService.reply(value).subscribe(response => { console.log(response) });
      this.close();
    }
    else {
      this.form.reset();
    }
  }
}
