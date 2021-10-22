import {AppMaterialModule} from '../app-material.module';
import {AutoFocusDirective} from '../directive/autoFocus.directive';
import {AuthenticationApiService} from '../services/backend/authenticationApi.service';
import {TodoApiService} from '../services/backend/todoApi.service';
import {UserApiService} from '../services/backend/userApi.service';
import {SessionService} from '../services/session.service';
import {StatusDropDown} from './components/dropdowns/statusDropDown.dropdown';
import {UserRoleDropDown} from './components/dropdowns/userRoleDropDown.dropdown';
import {ChangePasswordForm} from './components/forms/changePasswordForm.form';
import {CreateTodo} from './components/forms/createTodo.form';
import {CreateUser} from './components/forms/createUser.form';
import {DeleteTodo} from './components/forms/deleteTodo.form';
import {DeleteUser} from './components/forms/deleteUser.form';
import {EditTodo} from './components/forms/editTodo.form';
import {EditUser} from './components/forms/editUser.form';
import {SignInForm} from './components/forms/signInForm.form';
import {SignUpForm} from './components/forms/signUpForm.form';
import {SignInPage} from './components/pages/signInPage.page';
import {SignUpPage} from './components/pages/signUpPage.page';
import {TodosPage} from './components/pages/todosPage.page';
import {UsersPage} from './components/pages/usersPage.page';
import {Todos} from './components/tables/todos.table';
import {UserTodos} from './components/tables/userTodos.table';
import {Users} from './components/tables/users.table';
import {SharedRoutingModule} from './shared-routing.module';
import {CommonModule} from '@angular/common';
import {NgModule} from '@angular/core';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';

@NgModule({
  declarations: [
    AutoFocusDirective,
    UsersPage,
    CreateUser,
    EditUser,
    DeleteUser,
    TodosPage,
    CreateTodo,
    EditTodo,
    DeleteTodo,
    SignInPage,
    SignUpPage,
    ChangePasswordForm,
    Users,
    UserTodos,
    Todos,
    SignInForm,
    SignUpForm,
    UserRoleDropDown,
    StatusDropDown
  ],
  imports: [
    CommonModule,
    AppMaterialModule,
    FormsModule,
    ReactiveFormsModule,
    SharedRoutingModule
  ],
  providers: [
    SessionService,
    AuthenticationApiService,
    UserApiService,
    TodoApiService
  ],
  exports: [
    AutoFocusDirective,
    UsersPage,
    CreateUser,
    EditUser,
    DeleteUser,
    TodosPage,
    CreateTodo,
    EditTodo,
    DeleteTodo,
    SignInPage,
    SignUpPage,
    ChangePasswordForm,
    Users,
    UserTodos,
    Todos,
    SignInForm,
    SignUpForm,
    UserRoleDropDown,
    StatusDropDown
  ]
})
export class Shared {
}
