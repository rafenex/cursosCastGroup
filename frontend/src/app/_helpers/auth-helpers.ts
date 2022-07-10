import { Injectable } from "@angular/core";

@Injectable()
export class AuthHelper {

  isAuthenticated(): boolean {
    return localStorage.getItem('login_usuario') != null &&
      localStorage.getItem('access_token') != null;
  }
}
