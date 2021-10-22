import {UserRole} from '../../../services/backend/enums';
import {SessionService} from '../../../services/session.service';
import {Closed as CreateTodoClosed, CreateTodo} from '../forms/createTodo.form';
import {Todos} from '../tables/todos.table';
import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {MatDialog} from '@angular/material/dialog';
import {ActivatedRoute, Router} from '@angular/router';

export class TodosPageModel {


  constructor() {

  }

}

@Component({
  selector: 'todos-page',
  templateUrl: './todosPage.page.html',
  styleUrls: ['./todosPage.page.scss']
})
export class TodosPage implements OnInit, AfterViewInit {
  @ViewChild(Todos)
  private readonly todosElement: Todos;
  model: TodosPageModel = new TodosPageModel();

  constructor(private readonly dialog: MatDialog, private readonly route: ActivatedRoute, private readonly sessionService: SessionService,
              private readonly router: Router) {
    if (!([UserRole.ADMIN, UserRole.MEMBER].includes(sessionService.getSessionData().role)))
      this.router.navigate(['/sign-in']);
  }

  ngOnInit(): void {
    this.init();
  }

  ngAfterViewInit(): void {

  }


  init() {

  }

  onAddTodoClick() {
    this.dialog.open(CreateTodo, {
      data: {
        model: null,
        onClosed: (event: CreateTodoClosed) => {
          this.todosElement.load();
        }
      },
      panelClass: 'c-dialog',
      width: '440px'
    });
  }

}

