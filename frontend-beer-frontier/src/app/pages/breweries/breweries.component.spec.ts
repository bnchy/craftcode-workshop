import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BreweriesComponent } from './breweries.component';

describe('BreweriesComponent', () => {
  let component: BreweriesComponent;
  let fixture: ComponentFixture<BreweriesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [BreweriesComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BreweriesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
