import { TestBed } from '@angular/core/testing';

import { CandidateAuthGuard } from './candidate-auth.guard';

describe('CandidateAuthGuard', () => {
  let guard: CandidateAuthGuard;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    guard = TestBed.inject(CandidateAuthGuard);
  });

  it('should be created', () => {
    expect(guard).toBeTruthy();
  });
});
