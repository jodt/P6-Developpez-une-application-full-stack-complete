import { Component, OnInit } from '@angular/core';
import { SessionService } from '../../../services/session.service';
import { Observable, of } from 'rxjs';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrl: './header.component.css',
})
export class HeaderComponent implements OnInit {
  isLogged$: Observable<boolean> = of(false);

  constructor(private sessionService: SessionService) {}
  
  ngOnInit(): void {
    this.isLogged$ = this.sessionService.$isLogged();
  }
}
