import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { AuthHelper } from 'src/app/_helpers/auth-helpers';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-consultar-cursos',
  templateUrl: './consultar-cursos.component.html',
  styleUrls: ['./consultar-cursos.component.css']
})
export class ConsultarCursosComponent implements OnInit {

  mensagem = "";

  cursos: any[] = [];

  constructor(private httpClient: HttpClient, private authHelper: AuthHelper) {

  }

  ngOnInit(): void {
    if (this.authHelper.isAuthenticated()) {
      this.httpClient.get(
        environment.apiUrl + '/cursos')
        .subscribe(
          (data) => {
            this.cursos = data as any[];
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

  excluir(idCurso: number): void {
    if (window.confirm('Deseja realmente excluir o aluno selecionado?')) {
      this.httpClient.delete(
        environment.apiUrl + '/cursos/' + idCurso,
        { responseType: 'text' })
        .subscribe(
          (data) => {
            alert(data);
            this.ngOnInit();
          },
          (e) => {
            console.log(e);
          }
        )

    }
  }
}
