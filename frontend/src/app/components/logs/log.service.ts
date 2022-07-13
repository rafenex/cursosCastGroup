import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class LogService {

  constructor(private httpClient: HttpClient) { }

  getLogsService(page: number, descricao: string, size: number) {

    if (descricao == null) { return this.httpClient.get(environment.apiUrl + '/logs?page=' + page + '&size=' + size) }
    else {
      return this.httpClient.get(environment.apiUrl + '/logs?descricao=' + descricao + '&page=' + page + '&size=' + size)
    }


  }
}
