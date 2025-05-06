import { TestBed } from '@angular/core/testing';

import { UserSusbcribedTopicsResolver } from './user-susbcribed-topics.resolver';

describe('UserSusbcribedTopicsResolver', () => {
  let resolver: UserSusbcribedTopicsResolver;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    resolver = TestBed.inject(UserSusbcribedTopicsResolver);
  });

  it('should be created', () => {
    expect(resolver).toBeTruthy();
  });
});
