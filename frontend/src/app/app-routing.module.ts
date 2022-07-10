
import { CadastrarCursosComponent } from './components/cadastrar-cursos/cadastrar-cursos.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AccountComponent } from './account/account.component';
import { LoginComponent } from './login/login.component';
import { PaginaPrincipalComponent } from './components/pagina-principal/pagina-principal.component';

const routes: Routes = [
  { path: 'cadastrar-cursos', component: CadastrarCursosComponent },
  { path: '', component: LoginComponent },
  { path: 'account', component: AccountComponent },
  { path: 'pagina-inicial', component: PaginaPrincipalComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
