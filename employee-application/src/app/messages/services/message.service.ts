import { Injectable } from '@angular/core';
import { Message } from 'src/app/models/message';

@Injectable({
  providedIn: 'root'
})
export class MessageService {

  public data: Array<Message> = [];

  constructor() { }

  public getAll() {
    let data = '[{"email":"marija@gmail.com", "name":"marija", "text":"hello from the other sideeee"}, {"email":"marko@gmail.com", "name":"marko", "text":"I wanted to say goodbye"}]';
    this.data = JSON.parse(data) || [];
    return this.data;
  }
}
