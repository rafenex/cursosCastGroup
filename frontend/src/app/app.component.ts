import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { environment } from 'src/environments/environment';
import { AuthHelper } from './_helpers/auth-helpers';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  //atributos
  isAuthenticated = false;
  loginUsuario: String | null = '';
  cursos: any;

  constructor(private httpClient: HttpClient, private authHelper: AuthHelper) {

  }

  ngOnInit(): void {
    this.isAuthenticated = localStorage.getItem("access_token") != null
      && localStorage.getItem('login_usuario') != null;
    if (this.isAuthenticated) {
      this.loginUsuario = localStorage.getItem('login_usuario');
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
    }
  }
  //função para fazer o logout do usuario
  logout(): void {
    if (window.confirm('Deseja realmente sair do sistema?')) {
      //apagar as info do LocalStorage
      localStorage.removeItem('access_token');
      localStorage.removeItem('login_usuario');

      //redirecionar para a pagina inicial do projeto(login)
      window.location.href = '/';
    }
  }

  title = 'front-end';
}
