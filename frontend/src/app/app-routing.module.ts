import { ConsultarCursosComponent } from './components/consultar-cursos/consultar-cursos.component';

import { CadastrarCursosComponent } from './components/cadastrar-cursos/cadastrar-cursos.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AccountComponent } from './account/account.component';
import { LoginComponent } from './login/login.component';
import { PaginaPrincipalComponent } from './components/pagina-principal/pagina-principal.component';
import { EditarCursosComponent } from './components/editar-cursos/editar-cursos.component';

const routes: Routes = [
  { path: 'cadastrar-cursos', component: CadastrarCursosComponent },
  { path: 'consultar-cursos', component: ConsultarCursosComponent },
  { path: 'editar-cursos/:id', component: EditarCursosComponent },
  { path: '', component: LoginComponent },
  { path: 'account', component: AccountComponent },
  { path: 'pagina-inicial', component: PaginaPrincipalComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
