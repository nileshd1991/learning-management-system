import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminComponent } from './components/admin/admin.component';
import { CandidateComponent } from './components/candidate/candidate.component';
import { CourseComponent } from './components/course/course.component';
import { HomeComponent } from './components/home/home.component';
import { AdminAuthGuard } from './services/admin-auth.guard';
import { CandidateAuthGuard } from './services/candidate-auth.guard';

const routes: Routes = [
  {
    path:'',
    redirectTo:'home',
    pathMatch:"full"
  },
  {
    path:'home',
    children:[
      {
        path:'',
        component:HomeComponent,
        pathMatch:"full",
      }
    ]
  },
  {
    path:'candidate',
    children:[
      {
        path:'',
        component:CandidateComponent,
        pathMatch:"full",
        canActivate:[CandidateAuthGuard]
      },
      {
        path:'course/:courseId',
        component:CourseComponent,
        pathMatch:"full",
        canActivate:[CandidateAuthGuard]
      },
    ]
  },
  {
    path:'admin',
    children:[
      {
        path:'',
        component:AdminComponent,
        pathMatch:"full",
        canActivate:[AdminAuthGuard]
      },
    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
