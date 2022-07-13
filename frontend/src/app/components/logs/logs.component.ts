import { LogService } from './log.service';
import { Component, OnInit } from '@angular/core';



@Component({
  selector: 'app-logs',
  templateUrl: './logs.component.html',
  styleUrls: ['./logs.component.css']
})
export class LogsComponent implements OnInit {
  size = 0;
  descricao!: string;
  private page: number = 0;
  logs: any[] = [];
  json: any;
  pages: any[] = [];
  totalElements!: number;

  constructor(private logService: LogService) {
  }


  getLogs() {
    this.logService.getLogsService(this.page, this.descricao, this.size).subscribe(
      data => {
        this.json = data;
        this.totalElements = this.json.totalElements;
        this.logs = this.json.content;
        this.pages = new Array(this.json['totalPages'])
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

  setDesc(desc: string, size: number) {
    this.page = 0;
    this.size = 0;
    this.size = size;
    this.descricao = desc;
    this.getLogs();
  }





  ngOnInit() {
    this.getLogs();
  }


}
