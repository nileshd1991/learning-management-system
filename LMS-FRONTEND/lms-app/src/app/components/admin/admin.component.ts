import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { CourseService } from 'src/app/services/course.service';

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
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {
  errorMessageAddCourse:string = "";
  successMessageAddCourse:string = "";
  successMessageDeleteCourse:string = "";
  courseForm!:FormGroup
  courseObj:Course=new Course()

  courses:any

  constructor(
    private formBuilder:FormBuilder,
    private router:Router,
    private courseService:CourseService
    ) { }

  ngOnInit(): void {
    this.getAllCourses()
    this.courseForm=this.formBuilder.group({
      courseName: ['',[Validators.required,Validators.minLength(20)]],
      technology: ['',Validators.required],
      description: ['',[Validators.required,Validators.minLength(100)]],
      launchUrl: ['',Validators.required],
      startTime: [null,Validators.required],
      endTime: [null,Validators.required]
    })
    localStorage.removeItem("candidateToken");
  }

  addCourse(){
    console.log(this.courseForm)
    this.successMessageAddCourse=''
    this.errorMessageAddCourse=''
    if(this.courseForm.valid){
      console.log("Send data to server to add course");
      this.courseObj.course_name=this.courseForm.value.courseName
      this.courseObj.technology=this.courseForm.value.technology
      this.courseObj.description=this.courseForm.value.description
      this.courseObj.launch_url=this.courseForm.value.launchUrl

      let startT=this.courseForm.value.startTime
      let startDateStr=startT.year+'-'+startT.month+'-'+startT.day
      let endT=this.courseForm.value.endTime
      let startTime =new Date(startDateStr)
      this.courseObj.start_time=startTime.getTime();
      let endDateStr=endT.year+'-'+endT.month+'-'+endT.day
      let endTime =new Date(endDateStr)
      this.courseObj.end_time=endTime.getTime()

      console.log(this.courseObj)
      this.courseService.addCourse(this.courseObj)
      .subscribe(
      ()=>{
        this.successMessageAddCourse = "Course added successfully";
        this.courseForm.reset()
      },
      ()=>{
        alert('Error adding course')
      }
    )
    } else {
      this.errorMessageAddCourse = "Please enter valid details";
      return;
    }
  }

  getAllCourses(){
    return this.courseService.getAllCourses()
    .subscribe(
      (res: any)=>{
        this.courses=res
      },
      (error: any)=>{
        alert('Error fetching courses')
        this.router.navigate(['../home'])
      }
    )
  }

  deleteCourse(courseId:number){
    this.courseService.deleteCourse(courseId).subscribe(
      result=>{
        this.successMessageDeleteCourse='Course deleted successfully'
        this.getAllCourses()
      },
      error=>{
        alert('Error in deleting course')
      }
    )
  }

}
