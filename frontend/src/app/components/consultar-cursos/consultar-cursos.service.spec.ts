import { TestBed } from '@angular/core/testing';

import { ConsultarCursosService } from './consultar-cursos.service';

describe('ConsultarCursosService', () => {
  let service: ConsultarCursosService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ConsultarCursosService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
