import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Message } from 'src/app/models/message';
import { MessageStatus } from 'src/app/models/message-status';
import { RestUtilService } from 'src/app/util/rest-util.service';

@Injectable({
  providedIn: 'root'
})
export class MessageService {

  public data: Array<Message> = [];

  constructor(private httpClient: HttpClient, private restUtil: RestUtilService) { }

  public getAll(filters: MessageStatus[]): Observable<Message[]> {
    let params = this.restUtil.getParams(filters);
    return this.httpClient.get<Message[]>("http://localhost:8080/api/v0.1/messages", { params: params });
  }

  public markAsRead(message: Message): Observable<Boolean> {
    return this.httpClient.put<Boolean>("http://localhost:8080/api/v0.1/messages/" + message.messageId, null);
  }
}
