import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  // baseUrl="http://localhost:9090/learning-management-service"
  // baseUrl="http://ec2-65-1-248-64.ap-south-1.compute.amazonaws.com:9090/learning-management-service"
  baseUrl="https://aa94n05nm8.execute-api.ap-south-1.amazonaws.com/learning-management-service"

  constructor(private http:HttpClient) { }

  //Call to server to generate token
  generateAdminToken(credentials:any){
    return this.http.post(`${this.baseUrl}/admin/admin/login`,credentials)
  }

  generateCandidateToken(credentials:any){
    return this.http.post(`${this.baseUrl}/user/user/login`,credentials)
  }

  //forLoginUser
  loginAdminUser(user:any){
    localStorage.setItem("adminToken",user.token)
    localStorage.setItem("userId",user.user_id)
  }

  loginCandidateUser(user:any){
    localStorage.setItem("candidateToken",user.token)
    localStorage.setItem("userId",user.user_id)
  }

  isAdminLoggedIn(){
    let token=localStorage.getItem("adminToken");
    if(token==undefined||token===''||token==null){
      return false;
    }
    return true;
  }

  isCandidateLoggedIn(){
    let token=localStorage.getItem("candidateToken");
    if(token==undefined||token===''||token==null){
      return false;
    }
    return true;
  }

  logout(){
    localStorage.removeItem("adminToken");
    localStorage.removeItem("candidateToken");
    localStorage.removeItem("userName");
    localStorage.removeItem("userId");
    return true;
  }

  getAdminToken(){
    return localStorage.getItem("adminToken");
  }

  getCandidateToken(){
    return localStorage.getItem("candidateToken");
  }

  getGetLoggedInUsername(){
    return localStorage.getItem("userName");
  }

  registerCandidate(data:any){
    return this.http.post<any>(`${this.baseUrl}/user/user/register`,data)
    .pipe(map((res:any)=>{
      console.log(res)
      return res
    }));
  }
}