import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ConsultarCursosService {

  constructor(private httpClient: HttpClient) { }



  filtroPorDataOuDescricao(inicio: any, termino: any, descricao: any, page: any, size: number, sort: string, order: string) {
    var url = environment.apiUrl + '/cursos/filtro?'
    if ((inicio != "") && (inicio != null)) {
      url += "&inicio=" + inicio;
    }
    if ((termino != "") && (termino != null)) {
      url += "&termino=" + termino;
    }
    if ((descricao != "") && (descricao != null)) {
      console.log(descricao);
      url += "&descricao=" + descricao;
    }
    if (sort != "") {
      url += "&sort=" + sort;
    }
    if (order != "") {
      url += "," + order;
    }

    if ((page != -1) && (page != null)) {
      url += "&page=" + page;
    }
    if (size != -1) {
      url += "&size=" + size;
    }

    console.log(url);

    return this.httpClient.get(url);

  }


}
