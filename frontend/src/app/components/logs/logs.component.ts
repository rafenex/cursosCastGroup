import { LogService } from './log.service';
import { Component, OnInit } from '@angular/core';



@Component({
  selector: 'app-logs',
  templateUrl: './logs.component.html',
  styleUrls: ['./logs.component.css']
})
export class LogsComponent implements OnInit {

  private page: number = 0;
  logs: any[] = [];
  json: any;
  pages: any[] = [];

  constructor(private logService: LogService) {
  }


  getLogs() {
    this.logService.getLogsService(this.page).subscribe(
      data => {
        this.json = data;
        this.logs = this.json.content;
        this.pages = new Array(this.json['totalPages'])
        console.log(this.logs);

      },
      (error) => {
        console.log(error.error)
      }
    );
  }
  setPage(i: number, event: any) {
    event.preventDefault();
    this.page = i;
    this.getLogs();
  }



  ngOnInit() {

    this.getLogs();
  }


}
