import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { AuthHelper } from 'src/app/_helpers/auth-helpers';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-logs',
  templateUrl: './logs.component.html',
  styleUrls: ['./logs.component.css']
})
export class LogsComponent implements OnInit {

  page: any;
  logs: any[] = [];


  constructor(private httpClient: HttpClient, private authHelper: AuthHelper) {

  }



  ngOnInit(): void {
    if (this.authHelper.isAuthenticated()) {
      this.httpClient.get(
        environment.apiUrl + '/logs?page=0&size=10&sort=id_log,desc')
        .subscribe(
          (data) => {
            this.page = data;
            console.log(this.page);
            this.logs = this.page.content;
          },
          (e) => {
            console.log(e);
          }
        )
    } else {
      window.alert("Acesso negado");
      window.location.href = "/";
    }

  }

}
