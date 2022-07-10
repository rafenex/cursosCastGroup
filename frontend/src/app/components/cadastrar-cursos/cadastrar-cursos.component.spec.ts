import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CadastrarCursosComponent } from './cadastrar-cursos.component';

describe('CadastrarCursosComponent', () => {
  let component: CadastrarCursosComponent;
  let fixture: ComponentFixture<CadastrarCursosComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CadastrarCursosComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CadastrarCursosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
