import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MessagesComponent } from './messages/messages.component';
import { AppMaterialModule } from '../app-material/app-material.module';



@NgModule({
  declarations: [
    MessagesComponent
  ],
  imports: [
    CommonModule,
    AppMaterialModule
  ]
})
export class MessagesModule { }
