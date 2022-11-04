import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, ParamMap, Router } from '@angular/router';
import { CourseService } from 'src/app/services/course.service';
import { SharedService } from 'src/app/services/shared.service';

@Component({
  selector: 'app-course',
  templateUrl: './course.component.html',
  styleUrls: ['./course.component.css']
})
export class CourseComponent implements OnInit {

  courseId:any
  userId:any
  course:any
  cart:any
  courseAddSuccessMessage=''

  constructor(
    private route:ActivatedRoute,
    private courseService:CourseService,
    private router:Router,
    private sharedService:SharedService
  ) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe((p:ParamMap)=>{
      this.courseId=p.get('courseId');
      this.getCourse(this.courseId);
    })
    this.courseAddSuccessMessage=''
  }

  getCourse(courseId:any){
    this.courseService.getCourse(courseId)
    .subscribe(
      (res: any)=>{
        this.course=res
      },
      (error: any)=>{
        console.log(error)
        this.router.navigate(['../candidate'])
      }
    )
  }

  enrollForCourse(){
    this.courseService.enrollForCourse(this.course.course_id).subscribe(
      result=>{
        this.courseAddSuccessMessage='Enrollment successfull'
      },
      error=>{
        alert('Error in enrolling for a course')
      }
    )
  }
}
