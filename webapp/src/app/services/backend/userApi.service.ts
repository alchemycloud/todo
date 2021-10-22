import {environment} from '../../../environments/environment';
import {TraceService} from '../traceService.service';
import {SortOrder, Status, TodoUserTodosSortField, UserRole} from './enums';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable, throwError as observableThrowError} from 'rxjs';
import {catchError} from 'rxjs/operators';

@Injectable()
export class UserApiService {
  private readonly applicationJson = 'application/json';
  private readonly bearer = 'Bearer ';

  private header() {
    return new HttpHeaders({
      'Content-Type': this.applicationJson
    });
  }


  constructor(private readonly http: HttpClient, private readonly traceService: TraceService) {

  }

  deleteUser(request: DeleteUserRequest): Observable<{}> {
    return this.http.delete<{}>(
      environment.backendUrl + '/user/${request.id}', {
        headers: this.header()
      }).pipe(
      catchError(this.handleError<{}>('deleteUser'))
    );
  }

  updateUser(request: UpdateUserRequest): Observable<UpdateUserResponse> {
    return this.http.put<UpdateUserResponse>(
      environment.backendUrl + '/user/${request.id}', request, {
        headers: this.header()
      }).pipe(
      catchError(this.handleError<UpdateUserResponse>('updateUser'))
    );
  }

  createUser(request: CreateUserRequest): Observable<CreateUserResponse> {
    return this.http.post<CreateUserResponse>(
      environment.backendUrl + '/user', request, {
        headers: this.header()
      }).pipe(
      catchError(this.handleError<CreateUserResponse>('createUser'))
    );
  }

  readUser(request: ReadUserRequest): Observable<ReadUserResponse> {
    return this.http.get<ReadUserResponse>(
      environment.backendUrl + '/user/${request.id}', {
        headers: this.header()
      }).pipe(
      catchError(this.handleError<ReadUserResponse>('readUser'))
    );
  }

  userTodos(request: UserTodosRequest): Observable<Array<UserTodosResponse>> {
    let params: HttpParams = new HttpParams();
    if (request.userId != null) {
      params = params.set('userId', request.userId.toString());
    }
    if (request.fields != null) {
      params = params.set('fields', request.fields.toString());
    }
    if (request.directions != null) {
      params = params.set('directions', request.directions.toString());
    }

    return this.http.get<Array<UserTodosResponse>>(
      environment.backendUrl + '/user-todos', {
        headers: this.header(),
        params
      }).pipe(
      catchError(this.handleError<Array<UserTodosResponse>>('userTodos'))
    );
  }

  users(): Observable<Array<UsersResponse>> {
    return this.http.get<Array<UsersResponse>>(
      environment.backendUrl + '/users', {
        headers: this.header()
      }).pipe(
      catchError(this.handleError<Array<UsersResponse>>('users'))
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

export class ReadUserRequest {
  id: number;

  constructor(id: number) {
    this.id = id;
  }

}

export class UsersResponse {
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

export class DeleteUserRequest {
  id: number;

  constructor(id: number) {
    this.id = id;
  }

}

export class UserTodosResponse {
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

export class CreateUserResponse {
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

export class UpdateUserResponse {
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

export class UserTodosRequest {
  userId: number;
  fields: Array<TodoUserTodosSortField>;
  directions: Array<SortOrder>;

  constructor(userId: number,
              fields: Array<TodoUserTodosSortField>,
              directions: Array<SortOrder>) {
    this.userId = userId;
    this.fields = fields;
    this.directions = directions;
  }

}

export class CreateUserRequest {
  firstName: string;
  lastName: string;
  role: UserRole;
  username: string;
  passwordHash: string;

  constructor(firstName: string,
              lastName: string,
              role: UserRole,
              username: string,
              passwordHash: string) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.role = role;
    this.username = username;
    this.passwordHash = passwordHash;
  }

}

export class ReadUserResponse {
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

export class UpdateUserRequest {
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
