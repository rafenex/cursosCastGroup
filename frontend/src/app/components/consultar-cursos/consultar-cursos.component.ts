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
  order = "desc";
  termino = "";
  inicio = "";
  descricao = "";
  mensagem = "";
  filtro = "";
  json: any;
  totalElements!: number;
  page: any = -1;
  pages: any[] = [];
  cursos: any[] = [];
  size: any = 10;
  sort = "inclusao";

  constructor(private httpClient: HttpClient, private authHelper: AuthHelper, private service: ConsultarCursosService) {

  }




  ngOnInit(): void {
    this.getCursos();
  }


  getSelectedSkill() {
    this.getCursos()
  }
  getSelectedOrder() {
    this.getCursos()
  }


  getSelectedSort() {
    this.getCursos()
  }



  setOrder(order: string, event: any) {
    event.preventDefault();
    this.order = order;
    this.getCursos();
  }


  setInicio(inicio: any, event: any) {
    event.preventDefault();
    this.inicio = inicio.value;
    console.log(this.inicio)
    this.getCursos();
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

    this.formFiltroData.value.descricao = "";
    this.formFiltroData.value.inicio = "";
    this.formFiltroData.value.termino = "";
    this.page = null;
    this.descricao = "";
    this.size = 10;
    this.inicio = "";
    this.termino = "";
    this.getCursos();
  }

  onSubmit(): void {


    this.inicio = this.formFiltroData.value.inicio!;
    this.termino = this.formFiltroData.value.termino!;
    this.descricao = this.formFiltroData.value.descricao!;


    this.getCursos();

  }




  getCursos() {
    if (!this.authHelper.isAuthenticated()) {
      window.alert("Acesso negado");
      window.location.href = "/";
    }
    this.service.filtroPorDataOuDescricao(this.inicio, this.termino, this.descricao, this.page, this.size, this.sort, this.order).subscribe(
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
            alert(e.error)
            console.log(e);
          }
        )

    }
  }
}
