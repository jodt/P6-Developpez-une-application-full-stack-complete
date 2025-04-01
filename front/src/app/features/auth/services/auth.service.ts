import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { RegisterRequest } from '../interfaces/registerRequest.interface';
import { Observable } from 'rxjs';
import { AuthSuccess } from '../interfaces/authSuccess.interface';
import { environment } from '../../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private pathService = 'api/auth';

  constructor(private http : HttpClient) {}

  public register(registerRequest: RegisterRequest): Observable<AuthSuccess> {
    return this.http.post<AuthSuccess>(`${this.pathService}/register`, registerRequest);
  }
}
