import { HttpInterceptorFn } from '@angular/common/http';
import { catchError, throwError } from 'rxjs';

export const jwtInterceptor: HttpInterceptorFn = (req, next) => {
  const token = localStorage.getItem('token');

  let request;

  if(token) {
    request = req.clone({
      setHeaders: {
        Authorization: `Bearer ${token}`
      }
    })
  } else {
    request = req;
  }

  return next(request)
};
