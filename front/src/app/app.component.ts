import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from './features/auth/services/auth.service';
import { SessionService } from './shared/services/session.service';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.css',
})
export class AppComponent implements OnInit {
  title = 'mdd'; 

  constructor(private router: Router, private sessionService: SessionService, private authService: AuthService){};

 ngOnInit(): void {
    this.autolog();
  }

  private autolog () {
    if (localStorage.getItem("token")){
      this.authService.getUserInfo().subscribe({
        next: response => {
          this.sessionService.login(response);
        },
        error: err => {
          this.sessionService.logOut();
          this.router.navigate(['/auth/login']);
        }
      })
    }
  }
}
