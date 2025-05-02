import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { FormValidationErrorService } from '../../../../shared/services/form-validation-error.service';
import { SessionService } from '../../../../shared/services/session.service';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {

  loginForm!: FormGroup;
  errorMessage: string = '';
  

  constructor(private formBuilder: FormBuilder, private authService: AuthService, private sessionService : SessionService, private router: Router, public formValidationError: FormValidationErrorService) {}
  
    ngOnInit(): void {
      this.initForm();
    }
  
    private initForm() {
      this.loginForm = this.formBuilder.group({
        userName:['', [Validators.required]],
        password:['', [Validators.required]]
      })
    }
  
    onSubmit(): void {
      if (this.loginForm.valid) {
        const loginrequest = this.loginForm.getRawValue();
        this.authService.login(loginrequest)
        .subscribe(
          {
            next: response => {
              localStorage.setItem('token', response.token);
              this.authService.getUserInfo().subscribe(response => {
                this.sessionService.login(response);
                this.router.navigate(['/post/list']);
              })
            },
              error : err => {
                if(err.status === 401) {
                  this.errorMessage = "Verifier vos informations de connexion"
                }
              }
          });
      } else {
        this.loginForm.markAllAsTouched();
      }
    }

    public goBack(){
      window.history.back();
    }
}
