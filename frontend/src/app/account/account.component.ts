import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.css']
})
export class AccountComponent implements OnInit {


  mensagem_sucesso = '';
  mensagem_erro = '';
  constructor(private httpClient: HttpClient) { }

  ngOnInit(): void {
  }

  formAccount = new FormGroup({
    nome: new FormControl('', [Validators.required, Validators.minLength(3)]),
    login: new FormControl('', [Validators.required]),
    senha: new FormControl('', [Validators.required]),
  })

  get form(): any {
    return this.formAccount.controls;
  }

  onSubmit(): void {
    this.mensagem_sucesso = '';
    this.mensagem_erro = '';
    this.httpClient.post(
      environment.apiUrl + '/account',
      this.formAccount.value, { responseType: 'text' })
      .subscribe(
        data => {
          this.mensagem_sucesso = data;
          this.formAccount.reset();
        },
        e => {
          this.mensagem_erro = e.error;
          console.log(e.error);
        }
      )

  }


}
