import { Component } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { AuthService } from '../services/auth.service';
import { CommonModule, NgFor } from '@angular/common';
import { HttpClient } from '@angular/common/http';

export interface PcPoolDto {
  pcPoolId: number;
  pcPoolName: string;
}

export interface CampusDto {
  campusId: number;
  campusName: string;
  pcPools: PcPoolDto[];
}

@Component({
  selector: 'app-home',
  imports: [CommonModule, RouterLink],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {

  campuses: CampusDto[] = [];
  email: string | null = null;
  role: string = ""

  constructor(private router: Router, private authService: AuthService, private http: HttpClient){}

  ngOnInit(): void {
    this.http.get<CampusDto[]>('http://localhost:8080/api/campusWithPools')
             .subscribe(data => this.campuses = data);

    if (this.authService.isLoggedIn()) {
      this.email = this.authService.getEmail();
      if(this.authService.isMemberOfGroup("Administrator")){
        this.role = "Administrator"
      } else if (this.authService.isMemberOfGroup("User")){
        this.role = "User"
      } else {
        this.role = "User Role Not Found"
      }
    }
  }
  

}
