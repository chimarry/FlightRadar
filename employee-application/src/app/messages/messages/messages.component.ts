import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { MessageService } from '../services/message.service';
import { Message } from 'src/app/models/message';
import { MatTableDataSource } from '@angular/material/table';
import { MessageStatus } from 'src/app/models/message-status';
import { MatDialog } from '@angular/material/dialog';
import { MessageDetailsComponent } from '../message-details/message-details.component';
import { MessageReplyComponent } from '../message-reply/message-reply.component';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-messages',
  templateUrl: './messages.component.html',
  styleUrls: ['./messages.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class MessagesComponent implements OnInit {

  displayedColumns: string[] = ['email', 'name', 'text', 'see', 'reply'];
  dataSource = new MatTableDataSource<Message>();

  public availableFilters: MessageStatus[] = [MessageStatus.Read, MessageStatus.NotRead];
  public selectedFilters: MessageStatus[] = [];

  public searchText: string = "";

  constructor(private service: MessageService,
    private dialog: MatDialog,
    private snackBar: MatSnackBar) {
  }

  ngOnInit() {
    this.dataSource.filterPredicate =
      (data: Message, filter: string) => !filter || (data.text?.includes(filter) ?? true);
    this.refresh();
  }

  applyFilter(status: MessageStatus): void {
    const index = this.availableFilters.indexOf(status);
    if (index >= 0)
      this.availableFilters.splice(index, 1);
    this.selectedFilters.push(status);
    this.refresh();
  }

  removeFilter(status: MessageStatus): void {
    const index = this.selectedFilters.indexOf(status);
    if (index >= 0)
      this.selectedFilters.splice(index, 1);

    this.availableFilters.push(status);
    this.refresh();
  }

  refresh() {
    this.service.getAll(this.selectedFilters).subscribe(
      responseData => this.dataSource.data = responseData, error => console.log(error)
    );
  }

  reply(element: Message) {
    this.dialog.open(MessageReplyComponent, {
      data: element.email,
      width: '600px'
    }).afterClosed()
      .subscribe(response => {
        if (response)
          this.snackBar.open("Message is sending. You will be notified of outcome.", undefined, {
            duration: 2000
          }); else {
          this.snackBar.open("Sending failed.", undefined, {
            duration: 2000
          });
        }
      });
  }

  showDetails(element: Message) {
    this.dialog.open(MessageDetailsComponent, {
      data: element
    }).afterClosed()
      .subscribe(() => {
        this.service.markAsRead(element)
          .subscribe(() => {
            this.refresh();
          }
          );
      })
  }
}