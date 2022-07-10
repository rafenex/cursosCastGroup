import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AccountComponent } from './account/account.component';
import { LoginComponent } from './login/login.component';
import { CadastrarCursosComponent } from './components/cadastrar-cursos/cadastrar-cursos.component';
import { ConsultarCursosComponent } from './components/consultar-cursos/consultar-cursos.component';
import { EditarCursosComponent } from './components/editar-cursos/editar-cursos.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { TokenInterceptor } from './_intercepters/tokenInterceptor';
import { AuthHelper } from './_helpers/auth-helpers';
import { PaginaPrincipalComponent } from './components/pagina-principal/pagina-principal.component';

@NgModule({
  declarations: [
    AppComponent,
    AccountComponent,
    LoginComponent,
    CadastrarCursosComponent,
    ConsultarCursosComponent,
    EditarCursosComponent,
    PaginaPrincipalComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [
    {
      //Config do interceptor
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptor,
      multi: true
    },
    {
      provide: AuthHelper
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
