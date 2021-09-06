import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
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
    private snackBar: MatSnackBar,
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
    this.dialogRef.close(false);
  }

  send({ value, valid }: { value: MessageReply, valid: boolean }) {
    if (valid) {
      this.messageService.reply(value).subscribe(response => {
        this.snackBar.open("Message is sent", undefined, {
          duration: 2000
        });
      }, error => { console.log(error) });
      this.dialogRef.close(true);
    }
    else {
      this.form.reset();
    }
  }
}
