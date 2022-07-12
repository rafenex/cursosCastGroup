import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class LogService {

  constructor(private httpClient: HttpClient) { }

  getLogsService(page: number) {
    return this.httpClient.get(environment.apiUrl + '/logs?page=' + page)
  }
}
