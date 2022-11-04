import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators,ReactiveFormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginService } from 'src/app/services/login.service';
import { SharedService } from 'src/app/services/shared.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  candidateForm!:FormGroup
  candidateRForm!:FormGroup
  adminForm!:FormGroup
  credentials={
    user_name:'',
    password:'',
    email:''
  }
  errorMessageCandidate:string = "";
  errorMessageRCandidate:string = "";
  errorMessageAdmin:string = "";

  successMessageRCandidate:string = "";

  constructor(
    private loginService:LoginService,
    private formBuilder:FormBuilder,
    private router:Router,
    private sharedService:SharedService
    ) { }

  ngOnInit(): void {
    this.adminForm=this.formBuilder.group({
      adminUserName: ['',Validators.required],
      adminPassword: ['',Validators.required],
    })

    this.candidateForm=this.formBuilder.group({
      candidateUserName: ['',Validators.required],
      candidatePassword: ['',Validators.required],
    })

    this.candidateRForm=this.formBuilder.group({
      candidateUserName: ['',Validators.required],
      candidateEmail: ['', [Validators.required, Validators.email,Validators.pattern('^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$')]],
      candidatePassword: ['',[Validators.required,Validators.pattern('(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[$@$!%*?&])[A-Za-z\d$@$!%*?&].{8,}')]],
    })
  }
  candidateLogin(){
    console.log(this.candidateForm)
    this.sharedService.clearCart()
    this.errorMessageCandidate=''
    if (this.candidateForm.invalid) {
      this.errorMessageCandidate='Enter valid details'
      return;
    }
    if((this.candidateForm.value.candidateUserName!=null&&this.candidateForm.value.candidatePassword!=null)&&(this.candidateForm.value.candidateUserName!=''&&this.candidateForm.value.candidatePassword!='')){
      this.credentials.user_name=this.candidateForm.value.candidateUserName;
      this.credentials.password=this.candidateForm.value.candidatePassword;
      console.log("Send Candidate login request to server")
      this.loginService.generateCandidateToken(this.credentials).subscribe(
        (response:any)=>{
          console.log(response.token)
          this.loginService.loginCandidateUser(response)
          localStorage.setItem("userName",this.credentials.user_name)
          window.location.href="/candidate"
          // this.router.navigate(['/candidate'])
        },
        error=>{
          this.errorMessageCandidate=error.error.message
          console.log(error)
        }
      )
    }
    else{
      console.log("Fields are empty")
    }
  }
  adminLogin(){
    this.errorMessageAdmin=''
    if (this.adminForm.invalid) {
      this.errorMessageAdmin='Enter valid details'
      return;
    }
    if((this.adminForm.value.adminUserName!=null&&this.adminForm.value.adminPassword!=null)&&(this.adminForm.value.adminUserName!=''&&this.adminForm.value.adminPassword!='')){
      this.credentials.user_name=this.adminForm.value.adminUserName;
      this.credentials.password=this.adminForm.value.adminPassword;
      console.log("Send Admin login request to server")
      this.loginService.generateAdminToken(this.credentials).subscribe(
        (response:any)=>{
          console.log(response.token)
          this.loginService.loginAdminUser(response)
          localStorage.setItem("userName",this.credentials.user_name)
          window.location.href="/admin"
          // this.router.navigate(['/admin'])
        },
        error=>{
          this.errorMessageAdmin=error.error.message
          console.log(error)
        }
      )
    }
    else{
      console.log("Fields are empty")
    }
  }

  registerCandidate(){
    this.errorMessageRCandidate=''
    this.successMessageRCandidate=''
    if (this.candidateRForm.invalid) {
      this.errorMessageRCandidate='Enter valid details'
      return;
    }
    console.log(this.candidateRForm)
    if((this.candidateRForm.value.candidateUserName!=null
      &&this.candidateRForm.value.candidatePassword!=null
      &&this.candidateRForm.value.candidateEmail!=null)
      &&(this.candidateRForm.value.candidateUserName!=''
      &&this.candidateRForm.value.candidatePassword!=''
      &&this.candidateRForm.value.candidateEmail!='')){
      this.credentials.user_name=this.candidateRForm.value.candidateUserName;
      this.credentials.password=this.candidateRForm.value.candidatePassword;
      this.credentials.email=this.candidateRForm.value.candidateEmail;
      console.log("Send Candidate registration request to server")
      this.loginService.registerCandidate(this.credentials).subscribe(
        (response:any)=>{
          this.successMessageRCandidate='Candidate registered successfully'
          this.candidateRForm.reset()
        },
        (error: { error: { message: string; }; })=>{
          this.errorMessageRCandidate=error.error.message
          console.log(error)
        }
      )
    }
    else{
      this.errorMessageRCandidate="Please enter valid details"
    }
  }
}
