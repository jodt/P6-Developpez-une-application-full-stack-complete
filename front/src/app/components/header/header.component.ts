import { Component, OnInit } from '@angular/core';
import { filter, Observable, of } from 'rxjs';
import { NavigationEnd, Router } from '@angular/router';
import { MobileService } from '../../shared/services/mobile.service';
import { SessionService } from '../../shared/services/session.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrl: './header.component.css',
})
export class HeaderComponent implements OnInit {
  showToolbar: boolean = true;
  isMobileResolution = false;
  isLogged: boolean = false;
  showMenu: boolean = false;

  constructor(private sessionService: SessionService, private router: Router, private mobileService: MobileService
  ) {}

  ngOnInit(): void {
    this.isMobile();

    this.sessionService.$isLogged().subscribe((response) => {
      this.isLogged = response;
      this.router.events.pipe(
        filter(event => event instanceof NavigationEnd)).subscribe(() => {
          this.displayToolBar();
      });
    });
  }

  logout() {
    this.sessionService.logOut();
    this.router.navigate(['/'])
  }

  private displayToolBar() {
    this.showToolbar = (!this.isLogged && !this.isMobileResolution && (this.router.url.includes("register") || this.router.url.includes("login")) || this.isLogged);
  }

  private isMobile() {
    this.isMobileResolution = this.mobileService.isMobile();
  }

  public onToggleMenu () {
    this.showMenu = !this.showMenu;
  }
}
