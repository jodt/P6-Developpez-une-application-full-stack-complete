import { Component } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { SessionService } from '../../../../services/session.service';
import { AuthSuccess } from '../../interfaces/authSuccess.interface';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {

  loginForm!: FormGroup;

  constructor(private formBuilder: FormBuilder, private authService: AuthService, private sessionService : SessionService, private router: Router) {}
  
    ngOnInit(): void {
      this.initForm();
    }
  
    private initForm() {
      this.loginForm = this.formBuilder.group({
        userName:["", [Validators.required]],
        email:["", [Validators.required]],
        password:["", [Validators.required]]
      })
    }
  
    onSubmit(): void {
      if (this.loginForm.valid) {
        const loginrequest = this.loginForm.getRawValue();
        this.authService.login(loginrequest).subscribe((response:AuthSuccess) => {
        localStorage.setItem('token', response.token);
        this.sessionService.login();
        this.router.navigate(['/posts'])
       })
      } else {
        this.loginForm.markAllAsTouched();
      }
    }

}
