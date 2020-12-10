import { TestBed } from '@angular/core/testing';

import { InMemoryStockListService } from './in-memory-stock-list.service';

describe('InMemoryStockListService', () => {
  let service: InMemoryStockListService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(InMemoryStockListService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
