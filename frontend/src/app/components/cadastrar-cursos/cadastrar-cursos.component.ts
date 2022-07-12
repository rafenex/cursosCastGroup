import { HttpClient } from '@angular/common/http';
import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { AuthHelper } from 'src/app/_helpers/auth-helpers';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-cadastrar-cursos',
  templateUrl: './cadastrar-cursos.component.html',
  styleUrls: ['./cadastrar-cursos.component.css']
})
export class CadastrarCursosComponent implements OnInit {



  mensagem = "";
  categorias: any[] = [];

  constructor(private httpClient: HttpClient, private authHelper: AuthHelper) { }

  ngOnInit(): void {
    if (!this.authHelper.isAuthenticated()) {
      window.alert("Acesso negado");
      window.location.href = "/";
    }

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


  }



  formCadastro = new FormGroup({
    descricao: new FormControl('', [Validators.required]),
    inicio: new FormControl('', [Validators.required]),
    termino: new FormControl('', [Validators.required]),
    id_categoria: new FormControl('', [Validators.required]),
    quantidadeAlunos: new FormControl('')
  })

  get form(): any {
    return this.formCadastro.controls;
  }


  onSubmit(): void {
    console.log(this.formCadastro.value)
    let objeto: any = {

      descricao: this.formCadastro.value.descricao,
      inicio: this.formCadastro.value.inicio,
      termino: this.formCadastro.value.termino,
      categoria: { id_categoria: this.formCadastro.value.id_categoria },
      quantidadeAlunos: this.formCadastro.value.quantidadeAlunos
    }


    this.httpClient.post(
      environment.apiUrl + "/cursos",
      objeto,
      { responseType: 'text' })
      .subscribe(
        data => {
          this.mensagem = data;
          this.formCadastro.reset();
        },
        e => {
          this.mensagem = e.error;
          ;

        }

      )



  }

}
