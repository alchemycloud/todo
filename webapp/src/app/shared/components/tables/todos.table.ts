import {Status} from '../../../services/backend/enums';
import {TodoApiService, TodosResponse} from '../../../services/backend/todoApi.service';
import {DeleteTodo} from '../forms/deleteTodo.form';
import {EditTodo} from '../forms/editTodo.form';
import {AfterViewInit, Component, EventEmitter, OnInit, Output} from '@angular/core';
import {MatDialog} from '@angular/material/dialog';
import {MatTableDataSource} from '@angular/material/table';

export class TodosModel {
  id: number;
  userUsername: string;
  task: string;
  date: Date;
  status: Status;

  constructor(id: number,
              userUsername: string,
              task: string,
              date: Date,
              status: Status) {
    this.id = id;
    this.userUsername = userUsername;
    this.task = task;
    this.date = date;
    this.status = status;
  }

}

export class Selected {
  itemId: number;

  constructor(itemId: number) {
    this.itemId = itemId;
  }

}

@Component({
  selector: 'todos',
  templateUrl: './todos.table.html',
  styleUrls: ['./todos.table.scss']
})
export class Todos implements OnInit, AfterViewInit {
  resultsLength = 0;
  model: Array<TodosModel> = [];
  @Output() onSelected = new EventEmitter<Selected>();
  dataSource: MatTableDataSource<TodosModel> = new MatTableDataSource(this.model);
  displayedColumns = ['id', 'userUsername', 'task', 'date', 'status', 'editTodo', 'deleteTodo'];

  constructor(private readonly todoApi: TodoApiService, private readonly dialog: MatDialog) {

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
    this.todoApi.todos()
      .subscribe((response: TodosResponse[]) => {
        this.model = response.map(item => new TodosModel(item.id, item.userUsername, item.task, item.date,
            item.status));
        this.resultsLength = response.length;
      }, (_) => {
      });
  }

  select(selectedId: number) {
    this.onSelected.emit(new Selected(selectedId));
  }

  onEditTodoClick(item: TodosModel) {
    this.dialog.open(EditTodo, {
      data: {
        id: item.id
      },
      panelClass: 'c-dialog',
      width: '440px'
    });
  }

  onDeleteTodoClick(item: TodosModel) {
    this.dialog.open(DeleteTodo, {
      data: {
        id: item.id
      },
      panelClass: 'c-dialog',
      width: '440px'
    });
  }

}

