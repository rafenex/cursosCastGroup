import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ConsultarCursosService {

  constructor(private httpClient: HttpClient) { }



  filtroPorDataOuDescricao(inicio: any, termino: any, descricao: string, page: number, size: number) {
    var url = environment.apiUrl + '/cursos/filtro?'
    if (inicio != null) {
      url += "&inicio=" + inicio;
    }
    if (termino != null) {
      url += "&termino=" + termino;
    }
    if (page != null) {
      url += "&page=" + page;
    }
    if (size != null) {
      url += "&size=" + size;
    }
    if (descricao != null) {
      url += "&descricao=" + descricao;
    }
    console.log(url);

    return this.httpClient.get(url);

  }
}
