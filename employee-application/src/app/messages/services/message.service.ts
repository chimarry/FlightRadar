import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Message } from 'src/app/models/message';
import { MessageStatus } from 'src/app/models/message-status';

@Injectable({
  providedIn: 'root'
})
export class MessageService {

  public data: Array<Message> = [];

  constructor(private httpClient: HttpClient) { }

  public getAll(filters: MessageStatus[]): Observable<Message[]> {
    let params = new HttpParams();
    filters.forEach(filter => {
      var output = filter.replace(/\w+/g, function (txt) {
        return txt.charAt(0).toUpperCase() + txt.substr(1);
      }).replace(/\s/g, '');
      params = params.append("filters", output)
    });
    return this.httpClient.get<Message[]>("http://localhost:8080/api/v0.1/messages", { params: params });
  }
}
