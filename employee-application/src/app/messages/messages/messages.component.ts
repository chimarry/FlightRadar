import { Component, OnInit } from '@angular/core';
import { MessageService } from '../services/message.service';
import { Message } from 'src/app/models/message';
import { MatTableDataSource } from '@angular/material/table';
import { MatDialog } from '@angular/material/dialog';

@Component({
  selector: 'app-messages',
  templateUrl: './messages.component.html',
  styleUrls: ['./messages.component.css']
})
export class MessagesComponent implements OnInit {

  displayedColumns: string[] = ['email', 'name', 'text'];
  dataSource = new MatTableDataSource<Message>();

  constructor(private service: MessageService) {
  }

  ngOnInit() {
    this.dataSource.data = this.service.getAll();
    //  this.dataSource.sort = this.sort;
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

  delete(element: any) {
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