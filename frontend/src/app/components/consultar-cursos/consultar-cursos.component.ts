import { ConsultarCursosService } from './consultar-cursos.service';
import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { AuthHelper } from 'src/app/_helpers/auth-helpers';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-consultar-cursos',
  templateUrl: './consultar-cursos.component.html',
  styleUrls: ['./consultar-cursos.component.css']
})
export class ConsultarCursosComponent implements OnInit {

  termino: any;
  inicio: any;
  descricao: any;
  mensagem = "";
  filtro = "";

  cursos: any[] = [];

  constructor(private httpClient: HttpClient, private authHelper: AuthHelper, private service: ConsultarCursosService) {

  }

  ngOnInit(): void {
    if (this.authHelper.isAuthenticated()) {
      this.httpClient.get(
        environment.apiUrl + '/cursos/filtro')
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

  formFiltroData = new FormGroup({
    inicio: new FormControl(''),
    termino: new FormControl(''),
    descricao: new FormControl(''),
  })
  get form(): any {
    return this.formFiltroData.controls;
  }



  resetar(): void {
    this.formFiltroData.reset();
  }

  onSubmit(): void {
    this.inicio = this.formFiltroData.value.inicio;
    this.termino = this.formFiltroData.value.termino;
    this.descricao = this.formFiltroData.value.descricao;
    this.getCursos();
  }


  getCursos() {
    this.service.filtroPorDataOuDescricao(this.inicio, this.termino, this.descricao).subscribe(
      data => {
        this.cursos = data as any;
      },
      (error) => {
        console.log(error.error)
      }
    );
  }





  // filtroDescricao(descricao: string) {
  //   descricao = this.formFiltroDescricao.value.descricao!;
  //   this.httpClient.get(
  //     environment.apiUrl + '/cursos/nome/' + descricao)
  //     .subscribe(
  //       (data) => {
  //         this.cursos = data as any[];
  //       },
  //       (e) => {
  //         this.mensagem = e.error;
  //       }
  //     )
  // }

  // filtroDate(inicio: any, termino: any) {
  //   inicio = this.formFiltroData.value.inicio;
  //   termino = this.formFiltroData.value.termino;
  //   this.httpClient.get(
  //     environment.apiUrl + '/cursos/data/' + inicio + '/' + termino)
  //     .subscribe(
  //       (data) => {
  //         this.cursos = data as any[];

  //       },
  //       (e) => {
  //         this.mensagem = e.error;
  //       }
  //     )
  // }


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
