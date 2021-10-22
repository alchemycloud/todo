import {environment} from '../../../environments/environment';
import {TraceService} from '../traceService.service';
import {Status} from './enums';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable, throwError as observableThrowError} from 'rxjs';
import {catchError} from 'rxjs/operators';

@Injectable()
export class TodoApiService {
  private readonly applicationJson = 'application/json';
  private readonly bearer = 'Bearer ';

  private header() {
    return new HttpHeaders({
      'Content-Type': this.applicationJson
    });
  }


  constructor(private readonly http: HttpClient, private readonly traceService: TraceService) {

  }

  updateTodo(request: UpdateTodoRequest): Observable<UpdateTodoResponse> {
    return this.http.put<UpdateTodoResponse>(
      environment.backendUrl + '/todo/${request.id}', request, {
        headers: this.header()
      }).pipe(
      catchError(this.handleError<UpdateTodoResponse>('updateTodo'))
    );
  }

  readTodo(request: ReadTodoRequest): Observable<ReadTodoResponse> {
    return this.http.get<ReadTodoResponse>(
      environment.backendUrl + '/todo/${request.id}', {
        headers: this.header()
      }).pipe(
      catchError(this.handleError<ReadTodoResponse>('readTodo'))
    );
  }

  todos(): Observable<Array<TodosResponse>> {
    return this.http.get<Array<TodosResponse>>(
      environment.backendUrl + '/todos', {
        headers: this.header()
      }).pipe(
      catchError(this.handleError<Array<TodosResponse>>('todos'))
    );
  }

  deleteTodo(request: DeleteTodoRequest): Observable<{}> {
    return this.http.delete<{}>(
      environment.backendUrl + '/todo/${request.id}', {
        headers: this.header()
      }).pipe(
      catchError(this.handleError<{}>('deleteTodo'))
    );
  }

  createTodo(request: CreateTodoRequest): Observable<CreateTodoResponse> {
    return this.http.post<CreateTodoResponse>(
      environment.backendUrl + '/todo', request, {
        headers: this.header()
      }).pipe(
      catchError(this.handleError<CreateTodoResponse>('createTodo'))
    );
  }

  /**
   * Handle Http operation that failed.
   * Let the app continue.
   * @param operation - name of the operation that failed
   * @param result - optional value to return as the observable result
   */
  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      this.traceService.trace('apiError.' + operation + '.' + error.error).subscribe();

      // rethrow the error so that callers may react to it
      return observableThrowError(error);
    };
  }

}

export class UpdateTodoResponse {
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

export class UpdateTodoRequest {
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

export class DeleteTodoRequest {
  id: number;

  constructor(id: number) {
    this.id = id;
  }

}

export class CreateTodoResponse {
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

export class ReadTodoRequest {
  id: number;

  constructor(id: number) {
    this.id = id;
  }

}

export class CreateTodoRequest {
  userId: number;
  task: string;
  date: Date;
  status: Status;

  constructor(userId: number,
              task: string,
              date: Date,
              status: Status) {
    this.userId = userId;
    this.task = task;
    this.date = date;
    this.status = status;
  }

}

export class TodosResponse {
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

export class ReadTodoResponse {
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
