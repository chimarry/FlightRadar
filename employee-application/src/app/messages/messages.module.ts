import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MessagesComponent } from './messages/messages.component';
import { AppMaterialModule } from '../app-material/app-material.module';
import { MessageDetailsComponent } from './message-details/message-details.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MessageReplyComponent } from './message-reply/message-reply.component';

@NgModule({
  declarations: [
    MessagesComponent,
    MessageDetailsComponent,
    MessageReplyComponent
  ],
  imports: [
    CommonModule,
    AppMaterialModule,
    FormsModule,
    ReactiveFormsModule
  ]
})
export class MessagesModule { }
