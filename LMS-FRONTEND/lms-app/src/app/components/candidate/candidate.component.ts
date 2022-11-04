import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { CourseService } from 'src/app/services/course.service';
import { SharedService } from 'src/app/services/shared.service';

export class Course {
  course_id?: number;
  course_name?: string;
  technology?: string;
  description?: string;
  launch_url?: string;
  start_time?: number;
  end_time?: number;
}

@Component({
  selector: 'app-candidate',
  templateUrl: './candidate.component.html',
  styleUrls: ['./candidate.component.css']
})
export class CandidateComponent implements OnInit {

  errorMessage:string = "";
  errorMessage1:string = "";
  errorMessage2:string = "";
  courseForm1!:FormGroup
  courseForm2!:FormGroup
  courseObj:Course=new Course()

  enrollment:any=[]

  courses:any=[]

  course:any

  constructor(
    private formBuilder:FormBuilder,
    private router:Router,
    private courseService:CourseService,
    private sharedService:SharedService
    ) { }

  ngOnInit(): void {
    this.getAllEnrolledCourses()
    this.courseForm1=this.formBuilder.group({
      technology: ['',Validators.required],
    })
    this.courseForm2=this.formBuilder.group({
      technology: ['',Validators.required],
      startTime: [null,Validators.required],
      endTime: [null,Validators.required]
    })
    
    localStorage.removeItem("admin");
  }

  enrollForCourse(courseId:number){
    this.courseService.enrollForCourse(courseId).subscribe(
      result=>{
        this.getAllEnrolledCourses();
      },
      error=>{
        alert('Error in enrolling for a course')
      }
    )
  }

  getAllEnrolledCourses(){
    return this.courseService.getAllEnrolledCourses()
    .subscribe(
      (res: any)=>{
        this.enrollment=res
      },
      (error: any)=>{
        alert('Error fetching courses')
      }
    )
  }
  
  removeEnrollment(courseId:number){
    
    this.courseService.removeEnrollment(courseId)
    .subscribe(
      (res: any)=>{
        this.getAllEnrolledCourses()
      },
      (error: any)=>{
        alert('Error removing enrollment')
      }
    )
  }

  getCoursesByTechnology(){
    this.errorMessage=''
    this.errorMessage1=''
    this.errorMessage2=''
    if(this.courseForm1.valid){
      console.log("Send data to server to get courses");
      return this.courseService.getCoursesByTechnology(this.courseForm1.value.technology)
      .subscribe(
        (res: any)=>{
          this.courses=res
          if(this.courses.length==0){
            this.errorMessage='Courses not found!'
          }
        },
        (error: any)=>{
          alert('Error fetching courses')
        }
      )
    } else {
      return
    }
  }

  getCoursesByTechnologyAndDuration(){
    this.errorMessage=''
    this.errorMessage1=''
    this.errorMessage2=''
    if(this.courseForm2.valid){
      console.log("Send data to server to get courses");

      let startT=this.courseForm2.value.startTime
      let startDateStr=startT.year+'-'+startT.month+'-'+startT.day
      let endT=this.courseForm2.value.endTime
      let startTime =new Date(startDateStr)
      this.courseObj.start_time=startTime.getTime();
      let endDateStr=endT.year+'-'+endT.month+'-'+endT.day
      let endTime =new Date(endDateStr)

      return this.courseService.getCoursesByTechnologyAndDuration(
        this.courseForm2.value.technology,
        startTime.getTime(),
        endTime.getTime()
        )
      .subscribe(
        (res: any)=>{
          this.courses=res
          if(this.courses.length==0){
            this.errorMessage1='Courses not found!'
          }
        },
        (error: any)=>{
          alert('Error fetching courses')
        }
      )
    } else {
      return
    }
  }

  getAllCourses(){
    this.errorMessage=''
    this.errorMessage1=''
    this.errorMessage2=''
    return this.courseService.getAllCourses()
    .subscribe(
      (res: any)=>{
        this.courses=res
        if(this.courses.length==0){
          this.errorMessage2='Courses not found!'
        }
      },
      (error: any)=>{
        alert('Error fetching courses')
      }
    )
  }
}
