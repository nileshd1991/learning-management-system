import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor
} from '@angular/common/http';
import { Observable } from 'rxjs';
import { LoginService } from './login.service';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  constructor(private loginService:LoginService){}

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    let newReq=req;
    let adminToken=this.loginService.getAdminToken();
    let candidateToken=this.loginService.getCandidateToken();

    if(adminToken!=null){
      newReq=newReq.clone({setHeaders:{
        Authorization:`Bearer ${adminToken}`
      }})
      console.log("In Interceptor Admin")
    }

    if(candidateToken!=null){
      newReq=newReq.clone({setHeaders:{
        Authorization:`Bearer ${candidateToken}`
      }})
      console.log("In Interceptor Candidate")
    }

    return next.handle(newReq)
  }
}
