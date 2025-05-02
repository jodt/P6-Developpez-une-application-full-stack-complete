import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import { AuthService } from '../../services/auth.service';
import { AuthSuccess } from '../../interfaces/authSuccess.interface';
import { Router } from '@angular/router';
import { CustomValidatorService } from '../../../../shared/services/custom-validator.service';
import { FormValidationErrorService } from '../../../../shared/services/form-validation-error.service';
import { SessionService } from '../../../../shared/services/session.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent implements OnInit {

  registerForm!: FormGroup;

  constructor(private formBuilder: FormBuilder, private authService: AuthService, private sessionService : SessionService, private router: Router, private customValidatorService: CustomValidatorService, public formValidationError: FormValidationErrorService) {}

  ngOnInit(): void {
    this.initForm();
  }

  private initForm() {
    this.registerForm = this.formBuilder.group({
      userName:['', [Validators.required, Validators.minLength(3)]],
      email:['', [Validators.required, Validators.email]],
      password:['', [Validators.required, this.customValidatorService.passwordValidator()]]
    })
  }

  onSubmit(): void {
    if (this.registerForm.valid) {
      const registerRequest = this.registerForm.getRawValue();
     this.authService.register(registerRequest).subscribe((response:AuthSuccess) => {
      localStorage.setItem('token', response.token);
      this.authService.getUserInfo().subscribe(response => {
        this.sessionService.login(response);
        this.router.navigate(['/post/list'])
      })
      
     })
    } else {
      this.registerForm.markAllAsTouched();
    }
  }

  public goBack(){
    window.history.back();
  }
}
