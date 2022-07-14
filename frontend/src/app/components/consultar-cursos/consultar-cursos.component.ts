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
  size = 10;
  termino: any;
  inicio: any;
  descricao: any;
  mensagem = "";
  filtro = "";
  json: any;
  totalElements!: number;
  page = 0;
  pages: any[] = [];
  cursos: any[] = [];

  constructor(private httpClient: HttpClient, private authHelper: AuthHelper, private service: ConsultarCursosService) {

  }




  ngOnInit(): void {
    this.getCursos();
  }


  getSelectedSkill() {
    this.getCursos()
  }


  onEditClick(skill: any) {
    console.log('skill name', skill)
  }
  setPage(i: number, event: any) {
    event.preventDefault();
    this.page = i;
    this.getCursos();
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
    this.service.filtroPorDataOuDescricao(this.inicio, this.termino, this.descricao, this.page, this.size).subscribe(
      data => {
        this.cursos = data as any;
        this.json = data;
        this.cursos = this.json.content;
        this.totalElements = this.json.totalElements;
        this.pages = new Array(this.json['totalPages'])
      },
      (error) => {
        console.log(error.error)
      }
    );
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
