import { TestBed } from '@angular/core/testing';

import { ClassificationService } from './classification.service';

describe('ClassificationsService', () => {
  let service: ClassificationService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ClassificationService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
