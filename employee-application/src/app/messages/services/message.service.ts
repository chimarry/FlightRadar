import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Message } from 'src/app/models/message';
import { MessageReply } from 'src/app/models/message-reply';
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
    return this.httpClient.get<Message[]>(RestUtilService.buildUrl("messages"),
      { params: params, headers: this.restUtil.getHeaders() });
  }

  public markAsRead(message: Message): Observable<Boolean> {
    return this.httpClient.put<Boolean>(RestUtilService.buildUrl("messages/") + message.messageId, null,
      { headers: this.restUtil.getHeaders() });
  }

  public reply(messageReply: MessageReply): Observable<Boolean> {
    return this.httpClient.post<Boolean>(RestUtilService.buildUrl("messages"), messageReply,
      { headers: this.restUtil.getHeaders() });
  }
}
