import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { MessageService } from '../services/message.service';
import { Message } from 'src/app/models/message';
import { MatTableDataSource } from '@angular/material/table';
import { MessageStatus } from 'src/app/models/message-status';
import { MatDialog } from '@angular/material/dialog';
import { MessageDetailsComponent } from '../message-details/message-details.component';

@Component({
  selector: 'app-messages',
  templateUrl: './messages.component.html',
  styleUrls: ['./messages.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class MessagesComponent implements OnInit {

  displayedColumns: string[] = ['email', 'name', 'text', 'see'];
  dataSource = new MatTableDataSource<Message>();

  public availableFilters: MessageStatus[] = [MessageStatus.Read, MessageStatus.NotRead];
  public selectedFilters: MessageStatus[] = [];

  public searchText: string = "";

  constructor(private service: MessageService, private dialog: MatDialog) {
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

  add() {
    // this.dialog.open(EventEditComponent, {
    //   width: '600px'
    // })
    //   .afterClosed()
    //   .subscribe(result => {
    //     this.dataSource.data = this.service.getAll();
    //   });
  }

  showDetails(element: Message) {
    this.dialog.open(MessageDetailsComponent, {
      data: element
    })
    //   this.dialog.open(ConfirmModalComponent, {
    //     width: '300px'
    //   })
    //     .afterClosed()
    //     .subscribe(result => {
    //       if (result) {
    //         this.service.delete(element.id);
    //         this.dataSource.data = this.service.getAll();
    //       }
    //     });
  }
}