import { Component, Input } from '@angular/core';
import { Router, RouterLink, RouterLinkActive } from '@angular/router';
import { AuthService } from '../../services/auth.service';


@Component({
  selector: 'app-navbar',
  imports: [RouterLink, RouterLinkActive],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent {
  @Input() userName = 'user_name';

  constructor(private router: Router, private authService: AuthService){}

  onLogout(): void {
    this.authService.logout()
    this.router.navigateByUrl('/login');
  }
}
