import {UserRole} from './backend/enums';
import {Injectable} from '@angular/core';

@Injectable()
export class SessionService {

  constructor() {
  }

  public getAccessToken() {
    return this.getSessionData().accessToken;
  }

  public getRefreshToken() {
    return this.getSessionData().refreshToken;
  }

  public save(sessionData: SessionData) {
    localStorage.setItem('accessToken', sessionData.accessToken);
    localStorage.setItem('refreshToken', sessionData.refreshToken);
    localStorage.setItem('id', String(sessionData.id));
    localStorage.setItem('firstName', sessionData.firstName);
    localStorage.setItem('lastName', sessionData.lastName);
    localStorage.setItem('role', UserRole[sessionData.role]);
    localStorage.setItem('username', sessionData.username);
  }

  public clear() {
    localStorage.removeItem('accessToken');
    localStorage.removeItem('refreshToken');
    localStorage.removeItem('id');
    localStorage.removeItem('firstName');
    localStorage.removeItem('lastName');
    localStorage.removeItem('role');
    localStorage.removeItem('username');
  }

  public getSessionData(): SessionData {
    if (localStorage.getItem('accessToken') !== null) {
      return new SessionData(
        localStorage.getItem('accessToken'),
        localStorage.getItem('refreshToken'),
        Number(localStorage.getItem('id')),
        localStorage.getItem('firstName'),
        localStorage.getItem('lastName'),
        UserRole[localStorage.getItem('role')],
        localStorage.getItem('username')
      );
    } else {
      return null;
    }
  }

  public isLoggedIn() {
    return localStorage.getItem('accessToken') !== null;
  }

}

export class SessionData {
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
