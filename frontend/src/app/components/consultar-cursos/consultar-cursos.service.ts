import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ConsultarCursosService {

  constructor(private httpClient: HttpClient) { }

  filtroPorDataOuDescricao(inicio: any, termino: any, descricao: string) {

    if (descricao != null) {
      return this.httpClient.get(environment.apiUrl + '/cursos/filtro?descricao=' + descricao)
    }

    if (inicio != null) {
      return this.httpClient.get(environment.apiUrl + '/cursos/filtro?inicio=' + inicio + '&termino=' + termino)
    }


    return this.httpClient.get(environment.apiUrl + '/cursos')



  }
}
