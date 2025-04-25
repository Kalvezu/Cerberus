import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PcpoolComponent } from './pcpool.component';

describe('PcpoolComponent', () => {
  let component: PcpoolComponent;
  let fixture: ComponentFixture<PcpoolComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PcpoolComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PcpoolComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
