import {SortOrder, Status, TodoUserTodosSortField} from '../../../services/backend/enums';
import {UserApiService, UserTodosRequest, UserTodosResponse} from '../../../services/backend/userApi.service';
import {AfterViewInit, Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges} from '@angular/core';
import {MatTableDataSource} from '@angular/material/table';

export class UserTodosModel {
  id: number;
  userId: number;
  task: string;
  date: Date;
  status: Status;

  constructor(id: number,
              userId: number,
              task: string,
              date: Date,
              status: Status) {
    this.id = id;
    this.userId = userId;
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
  selector: 'user-todos',
  templateUrl: './userTodos.table.html',
  styleUrls: ['./userTodos.table.scss']
})
export class UserTodos implements OnChanges, OnInit, AfterViewInit {
  resultsLength = 0;
  @Input() userId: number;
  @Input() fields: Array<TodoUserTodosSortField>;
  @Input() directions: Array<SortOrder>;
  model: Array<UserTodosModel> = [];
  @Output() onSelected = new EventEmitter<Selected>();
  dataSource: MatTableDataSource<UserTodosModel> = new MatTableDataSource(this.model);
  displayedColumns = ['id', 'userId', 'TODO_TASK', 'date', 'status'];

  constructor(private readonly userApi: UserApiService) {

  }

  ngOnInit(): void {
    this.init();
  }

  ngAfterViewInit(): void {

  }


  ngOnChanges(changes: SimpleChanges): void {
    if (changes.userId && !changes.userId.firstChange && changes.userId.previousValue !== changes.userId.currentValue) {
      this.reload(changes.userId.currentValue, this.fields, this.directions);
    }

    if (changes.fields && !changes.fields.firstChange && changes.fields.previousValue !== changes.fields.currentValue) {
      this.reload(this.userId, changes.fields.currentValue, this.directions);
    }

    if (changes.directions && !changes.directions.firstChange && changes.directions.previousValue !== changes.directions.currentValue) {
      this.reload(this.userId, this.fields, changes.directions.currentValue);
    }
  }

  init() {
    this.load();
  }

  load() {
    this.userApi.userTodos(new UserTodosRequest(this.userId,
      this.fields,
      this.directions))
      .subscribe((response: UserTodosResponse[]) => {
        this.model = response.map(item => {
          return new UserTodosModel(item.id, item.userId, item.task, item.date, item.status);
        });
        this.resultsLength = response.length;
      }, (_) => {
      });
  }

  reload(userId: number, fields: Array<TodoUserTodosSortField>, directions: Array<SortOrder>) {
    this.userId = userId;
    this.fields = fields;
    this.directions = directions;
    this.load();
  }

  select(selectedId: number) {
    this.onSelected.emit(new Selected(selectedId));
  }

  sortData(sort) {
    this.fields = [TodoUserTodosSortField[sort.active] as TodoUserTodosSortField];
    this.directions = [SortOrder[sort.direction.toUpperCase()] as SortOrder];
    this.load();
  }

}

