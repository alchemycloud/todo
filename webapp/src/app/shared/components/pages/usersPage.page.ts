import {UserRole} from '../../../services/backend/enums';
import {SessionService} from '../../../services/session.service';
import {Closed as CreateUserClosed, CreateUser} from '../forms/createUser.form';
import {UserTodos} from '../tables/userTodos.table';
import {Users, ViewTodos as UsersViewTodos} from '../tables/users.table';
import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {MatDialog} from '@angular/material/dialog';
import {ActivatedRoute, Router} from '@angular/router';

export class UsersPageModel {


  constructor() {

  }

}

@Component({
  selector: 'users-page',
  templateUrl: './usersPage.page.html',
  styleUrls: ['./usersPage.page.scss']
})
export class UsersPage implements OnInit, AfterViewInit {
  @ViewChild(Users)
  private readonly usersElement: Users;
  @ViewChild(UserTodos)
  private readonly userTodosElement: UserTodos;
  model: UsersPageModel = new UsersPageModel();

  constructor(private readonly dialog: MatDialog, private readonly route: ActivatedRoute, private readonly sessionService: SessionService,
              private readonly router: Router) {
    if (!([UserRole.ADMIN].includes(sessionService.getSessionData().role)))
      this.router.navigate(['/sign-in']);
  }

  ngOnInit(): void {
    this.init();
  }

  ngAfterViewInit(): void {

  }


  init() {

  }

  onAddUserClick() {
    this.dialog.open(CreateUser, {
      data: {
        model: null,
        onClosed: (event: CreateUserClosed) => {
          this.usersElement.load();
        }
      },
      panelClass: 'c-dialog',
      width: '440px'
    });
  }

  onUsersViewTodos(event: UsersViewTodos) {
    this.userTodosElement.reload(event.id, null, null);
  }

}

