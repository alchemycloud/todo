import {environment} from '../../../environments/environment';
import {SessionData} from '../session.service';
import {TraceService} from '../traceService.service';
import {UserRole} from './enums';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable, throwError as observableThrowError} from 'rxjs';
import {catchError, map, share} from 'rxjs/operators';

@Injectable()
export class AuthenticationApiService {
  private readonly applicationJson = 'application/json';
  private readonly bearer = 'Bearer ';

  private header() {
    return new HttpHeaders({
      'Content-Type': this.applicationJson
    });
  }


  refreshTokenCall;

  constructor(private readonly http: HttpClient, private readonly traceService: TraceService) {

  }

  signIn(request: SignInRequest): Observable<SignInResponse> {
    return this.http.post<SignInResponse>(
      environment.backendUrl + '/sign-in', request, {
        headers: this.header()
      }).pipe(
      catchError(this.handleError<SignInResponse>('signIn'))
    );
  }

  signUp(request: SignUpRequest): Observable<{}> {
    return this.http.post<{}>(
      environment.backendUrl + '/sign-up', request, {
        headers: this.header()
      }).pipe(
      catchError(this.handleError<{}>('signUp'))
    );
  }

  refreshToken(request: RefreshTokenRequest): Observable<SignInResponse> {
    if (!this.refreshTokenCall) {
      this.refreshTokenCall = this.http.post<SignInResponse>(environment.backendUrl + '/refresh-token', request, {
        headers: this.header()
      }).pipe(
        catchError(this.handleError<SignInResponse>('refreshToken')),
        map((response) => {
          this.sessionService.save(new SessionData(response.accessToken, response.refreshToken, response.id, response.firstName, response.lastName,
            response.role, response.username));
          this.refreshTokenCall = null;
          return response;
        }),
        // we're making the observable hot, so that only one refresh token happens at the time.
        // check https://stackoverflow.com/questions/52874725/in-rxjs-why-does-a-pipe-get-executed-once-for-each-subscription for details
        share()
      );
    }

    return this.refreshTokenCall;
  }


  changePassword(request: ChangePasswordRequest): Observable<{}> {
    return this.http.post<{}>(
      environment.backendUrl + '/change-password', request, {
        headers: this.header()
      }).pipe(
      catchError(this.handleError<{}>('changePassword'))
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

export class RefreshTokenRequest {
  refreshToken: string;

  constructor(refreshToken: string) {
    this.refreshToken = refreshToken;
  }

}

export class SignUpRequest {
  firstName: string;
  lastName: string;
  username: string;
  password: string;

  constructor(firstName: string,
              lastName: string,
              username: string,
              password: string) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.username = username;
    this.password = password;
  }

}

export class SignInRequest {
  username: string;
  password: string;

  constructor(username: string,
              password: string) {
    this.username = username;
    this.password = password;
  }

}

export class SignInResponse {
  accessToken: string;
  refreshToken: string;
  id: number;
  firstName: string;
  lastName: string;
  role: UserRole;
  username: string;

  constructor(accessToken: string,
              refreshToken: string,
              id: number,
              firstName: string,
              lastName: string,
              role: UserRole,
              username: string) {
    this.accessToken = accessToken;
    this.refreshToken = refreshToken;
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.role = role;
    this.username = username;
  }

}

export class ChangePasswordRequest {
  oldPassword: string;
  newPassword: string;

  constructor(oldPassword: string,
              newPassword: string) {
    this.oldPassword = oldPassword;
    this.newPassword = newPassword;
  }

}
