import { Inject, Injectable, PLATFORM_ID} from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { tap } from "rxjs";
import { isPlatformBrowser } from '@angular/common';

interface User {
  lastName: string;
  firstName: string;
  userRole: string;
  email: string;
  password: string;
  code: number;
}

@Injectable({
  providedIn: 'root'
})

export class AuthService {
   API_LOGIN = "http://localhost:8080/api/users";

  constructor(
    @Inject(PLATFORM_ID) private platformId: Object, private httpClient: HttpClient) {
  }

  login(data: any) {
    const email = data.email;
    const password = data.password;
    return this.httpClient.get<any>(`${this.API_LOGIN}/${email}`)
    .pipe(tap((result) => {
      if (result && result.password === password) {
        localStorage.setItem('authUser', JSON.stringify({
          lastName: result.lastName,
          firstName: result.firstName,
          userRole: result.userRole,
          email: result.email,
        }));
      } else {
        localStorage.removeItem('authUser');
      }
    }));
  }

  logout() {
    if (isPlatformBrowser(this.platformId)) {
      localStorage.removeItem('authUser');
    }
  }

  isLoggedIn() {
    if (isPlatformBrowser(this.platformId)) {
      return localStorage.getItem('authUser') !== null;
    }
    return false;
  }

  getUser(): User | null {
    const userString = localStorage.getItem('authUser');
    if (!userString) {
      return null;
    }
    try {
      return JSON.parse(userString) as User;
    } catch (error) {
      return null;
    }
  }

  getEmail(): string | null {
    const user = this.getUser();
    return user ? user.email : null;
  }

  public isMemberOfGroup(role: string): boolean {
    const user = this.getUser();
    switch (role) {
      case 'User':
        if (user && user.userRole) {
          return user.userRole.includes('zimkgrp') || user.userRole.includes('zimkazubi');
        }
        break;
      case 'Administrator':
        if (user && user.userRole) {
          return user.userRole.includes('poolmgmt');
        }
        break;
      default:
        return false;
    }
    return false;
  }

}
