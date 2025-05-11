import { CommonModule, NgFor, NgIf } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { PcPoolDto } from '../home/home.component';

interface PcDto {
  pcId: number;
  hostname: string;
  status: string;
  busy: boolean;
}

@Component({
  selector: 'app-pcpool',
  imports: [CommonModule],
  templateUrl: './pcpool.component.html',
  styleUrl: './pcpool.component.css'
})
export class PcpoolComponent implements OnInit {

  poolId!: number;
  poolName = 'Loading...';
  pcs: PcDto[] = [];
  selectedPc: PcDto | null = null;
  test: string = ""

  api = 'http://localhost:8080/api';

  constructor(private route: ActivatedRoute, private http: HttpClient) {}

  ngOnInit(): void {
    this.poolId = +this.route.snapshot.paramMap.get('poolId')!;
    this.loadPoolInfo();  
    this.loadPcs();
  }

  loadPoolInfo(): void {
    this.http.get<PcPoolDto>(`${this.api}/pools/${this.poolId}`).subscribe(dto => this.poolName = dto.pcPoolName);
  }

  loadPcs(): void {this.http.get<PcDto[]>(`${this.api}/pools/${this.poolId}/pcs`).subscribe(list => this.pcs = list);}

  tileClass(pc: PcDto): string {
    if (pc === this.selectedPc)       { return 'pc-selected'; }
    if (pc.status === 'inactive')     { return 'pc-off'; }
    if (pc.status === 'maintenance')  { return 'pc-busy'; }
    if (pc.status === 'active')       { return 'pc-on'; }

    return 'pc-on'
  }

  pickPc(pc: PcDto): void { this.selectedPc = pc; }

  backToPool(): void { this.selectedPc = null; }

  run(cmd: string): void {
    let url = 'loading…';

    if (this.selectedPc) {
      url = `${this.api}/pcs/${this.selectedPc.pcId}/${cmd}`;
    } else {
      url = `${this.api}/pools/${this.poolId}/${cmd}`;
    }

    this.http.post(url, {})
        .subscribe(() => console.log(`sent ${cmd} -> ${url}`));
  }
}