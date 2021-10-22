import {UserRole} from '../../../services/backend/enums';
import {UserApiService, UsersResponse} from '../../../services/backend/userApi.service';
import {DeleteUser} from '../forms/deleteUser.form';
import {EditUser} from '../forms/editUser.form';
import {AfterViewInit, Component, EventEmitter, OnInit, Output} from '@angular/core';
import {MatDialog} from '@angular/material/dialog';
import {MatTableDataSource} from '@angular/material/table';

export class UsersModel {
  id: number;
  firstName: string;
  lastName: string;
  role: UserRole;
  username: string;
  passwordHash: string;

  constructor(id: number,
              firstName: string,
              lastName: string,
              role: UserRole,
              username: string,
              passwordHash: string) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.role = role;
    this.username = username;
    this.passwordHash = passwordHash;
  }

}

export class ViewTodos {
  id: number;

  constructor(id: number) {
    this.id = id;
  }

}

export class Selected {
  itemId: number;

  constructor(itemId: number) {
    this.itemId = itemId;
  }

}

@Component({
  selector: 'users',
  templateUrl: './users.table.html',
  styleUrls: ['./users.table.scss']
})
export class Users implements OnInit, AfterViewInit {
  resultsLength = 0;
  model: Array<UsersModel> = [];
  @Output() onViewTodos = new EventEmitter<ViewTodos>();
  @Output() onSelected = new EventEmitter<Selected>();
  dataSource: MatTableDataSource<UsersModel> = new MatTableDataSource(this.model);
  displayedColumns = ['id', 'firstName', 'lastName', 'role', 'username', 'passwordHash', 'viewUserTodos', 'editUser', 'deleteUser'];

  constructor(private readonly userApi: UserApiService, private readonly dialog: MatDialog) {

  }

  ngOnInit(): void {
    this.init();
  }

  ngAfterViewInit(): void {

  }


  init() {
    this.load();
  }

  load() {
    this.userApi.users()
      .subscribe((response: UsersResponse[]) => {
        this.model = response.map(item => {
          return new UsersModel(item.id, item.firstName, item.lastName, item.role,
            item.username, item.passwordHash);
        });
        this.resultsLength = response.length;
      }, (_) => {
      });
  }

  select(selectedId: number) {
    this.onSelected.emit(new Selected(selectedId));
  }

  onViewUserTodosClick(item: UsersModel) {
    this.onViewTodos.emit(new ViewTodos(item.id));
  }

  onEditUserClick(item: UsersModel) {
    this.dialog.open(EditUser, {
      data: {
        id: item.id
      },
      panelClass: 'c-dialog',
      width: '440px'
    });
  }

  onDeleteUserClick(item: UsersModel) {
    this.dialog.open(DeleteUser, {
      data: {
        id: item.id
      },
      panelClass: 'c-dialog',
      width: '440px'
    });
  }

}

