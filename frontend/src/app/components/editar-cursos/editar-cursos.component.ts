import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { AuthHelper } from 'src/app/_helpers/auth-helpers';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-editar-cursos',
  templateUrl: './editar-cursos.component.html',
  styleUrls: ['./editar-cursos.component.css']
})
export class EditarCursosComponent implements OnInit {


  categorias: any[] = [];
  mensagem = "";
  curso: any;
  inicio: any;
  termino: any;
  categoria: any;
  descricao: any;
  quantidadeAlunos: any;

  constructor(private httpClient: HttpClient, private activeRoute: ActivatedRoute, private authHelper: AuthHelper) { }

  formEdicao = new FormGroup({
    id_curso: new FormControl('', [Validators.required]),
    descricao: new FormControl('', [Validators.required]),
    inicio: new FormControl('', [Validators.required]),
    termino: new FormControl('', [Validators.required]),
    id_categoria: new FormControl('', [Validators.required]),
    quantidadeAlunos: new FormControl('')
  })

  get form(): any {
    return this.formEdicao.controls;
  }
  onSubmit(): void {
    let objeto: any = {
      id_curso: this.formEdicao.value.id_curso,
      descricao: this.formEdicao.value.descricao,
      inicio: this.formEdicao.value.inicio,
      termino: this.formEdicao.value.termino,
      categoria: { id_categoria: this.formEdicao.value.id_categoria },
      quantidadeAlunos: this.formEdicao.value.quantidadeAlunos
    }

    console.log(objeto);


    const idCurso = this.activeRoute.snapshot.paramMap.get('id') as string;
    this.httpClient.put(environment.apiUrl + "/cursos/" + idCurso,
      objeto,
      { responseType: 'text' })
      .subscribe(
        data => {
          this.mensagem = data;
        },
        e => {
          this.mensagem = e.error;
          console.log(e);
        }
      )
    // window.location.href = "/editar-cursos/" + idCurso;
  }

  ngOnInit(): void {
    if (this.authHelper.isAuthenticated()) {
      const idCurso = this.activeRoute.snapshot.paramMap.get('id') as string;

      this.httpClient.get(
        environment.apiUrl + '/categorias')
        .subscribe(
          (data) => {
            this.categorias = data as any[];
          },
          (e) => {
            console.log(e);
          }
        )

      this.httpClient.get(environment.apiUrl + "/cursos/" + idCurso)
        .subscribe(
          (data: any) => {

            this.curso = data;
            this.inicio = this.curso.inicio;
            this.termino = this.curso.termino;
            this.categoria = this.curso.categoria;
            this.descricao = this.curso.descricao;
            this.quantidadeAlunos = this.curso.quantidadeAlunos;
            this.formEdicao.patchValue(data);

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
