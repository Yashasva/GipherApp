import { TestBed } from '@angular/core/testing';

import { GifguardGuard } from './gifguard.guard';

describe('GifguardGuard', () => {
  let guard: GifguardGuard;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    guard = TestBed.inject(GifguardGuard);
  });

  it('should be created', () => {
    expect(guard).toBeTruthy();
  });
});
