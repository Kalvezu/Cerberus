import { NgFor, NgIf } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';

interface CampusDto {
  campusId: number;
  campusName: string;
}

interface PcPoolDto {
  pcPoolId: number;
  pcPoolName: string;
}

interface PcDto {
  pcId: number;
  hostname: string;
  ipaddress: string;
  macaddress: string;
  status: string;
  busy: boolean;
}

@Component({
  selector: 'app-devtools',
  imports: [NgIf, NgFor],
  templateUrl: './devtools.component.html',
  styleUrl: './devtools.component.css'
})
export class DevtoolsComponent implements OnInit {
  campuses: CampusDto[] = [];
  pools: { [campusId: number]: PcPoolDto[] } = {};
  pcs: { [poolId: number]: PcDto[] } = {};

  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    this.http.get<CampusDto[]>('http://localhost:8080/api/campuses')
      .subscribe(list => this.campuses = list);
  }

  loadPools(campus: CampusDto): void {
    if (this.pools[campus.campusId]) return;
    this.http.get<PcPoolDto[]>(`/api/campuses/${campus.campusId}/pools`)
      .subscribe(list => this.pools[campus.campusId] = list);
  }

  loadPcs(pool: PcPoolDto): void {
    if (this.pcs[pool.pcPoolId]) return;
    this.http.get<PcDto[]>(`/api/pools/${pool.pcPoolId}/pcs`)
      .subscribe(list => this.pcs[pool.pcPoolId] = list);
  }
}