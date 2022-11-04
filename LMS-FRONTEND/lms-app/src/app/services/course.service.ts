import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map } from 'rxjs';
import { Course } from '../components/admin/admin.component';

export class EnrollCourse{
  user_id?:string|null
  course_id?:any
}

@Injectable({
  providedIn: 'root'
})
export class CourseService {

  enrollCourse:EnrollCourse=new EnrollCourse()

  // baseUrl="http://localhost:9090/learning-management-service"
  // baseUrl="http://ec2-65-1-248-64.ap-south-1.compute.amazonaws.com:9090/learning-management-service"
  baseUrl="https://aa94n05nm8.execute-api.ap-south-1.amazonaws.com/learning-management-service"

  constructor(private http:HttpClient) { }
  

  enrollForCourse(courseId:number){
    this.enrollCourse.course_id=courseId;
    this.enrollCourse.user_id=localStorage.getItem("userId")
    return this.http.post(`${this.baseUrl}/user/user/enroll`,this.enrollCourse)
  }

  removeEnrollment(courseId:number){
    let userId=localStorage.getItem("userId")
    return this.http.delete(`${this.baseUrl}/user/user/${userId}/delete/${courseId}`)
  }

  addCourse(data:any){
    return this.http.post<any>(`${this.baseUrl}/course/course/add`,data)
    .pipe(map((res:any)=>{
      return res
    }));
  }

  getAllCourses(){
    return this.http.get(`${this.baseUrl}/courses/courses/getall`)
    .pipe(map((res:any)=>{
      return res
    }));
  }

  getAllEnrolledCourses(){
    let userId=localStorage.getItem("userId")
    return this.http.get(`${this.baseUrl}/user/user/${userId}/enrolled-courses`)
    .pipe(map((res:any)=>{
      return res
    }));
  }

  getCoursesByTechnology(technology:string){
    return this.http.get(`${this.baseUrl}/courses/courses/info/${technology}`)
    .pipe(map((res:any)=>{
      return res
    }));
  }

  getCoursesByTechnologyAndDuration(technology:string,from:number,to:number){
    return this.http.get(`${this.baseUrl}/courses/courses/get/${technology}/${from}/${to}`)
    .pipe(map((res:any)=>{
      return res
    }));
  }

  deleteCourse(courseId:number){
    return this.http.delete(`${this.baseUrl}/course/course/delete/${courseId}`)
  }

  getCourse(courseId: any) {
    return this.http.get(`${this.baseUrl}/course/course/${courseId}`)
    .pipe(map((res:any)=>{
      return res
    }));
  }
}
