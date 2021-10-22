import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {AuthGuard} from '../services/authGuard.service';
import {SignInPage} from './components/pages/signInPage.page';
import {SignUpPage} from './components/pages/signUpPage.page';
import {TodosPage} from './components/pages/todosPage.page';
import {UsersPage} from './components/pages/usersPage.page';

const routes: Routes = [
  {path: 'users', canActivate: [AuthGuard], component: UsersPage},
  {path: 'todos', canActivate: [AuthGuard], component: TodosPage},
  {path: 'sign-in', component: SignInPage},
  {path: 'sign-up', component: SignUpPage}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class SharedRoutingModule {
}
