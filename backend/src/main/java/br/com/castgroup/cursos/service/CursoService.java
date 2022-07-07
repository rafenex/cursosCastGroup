package br.com.castgroup.cursos.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.castgroup.cursos.entities.Curso;
import br.com.castgroup.cursos.form.FormCadastroCurso;
import br.com.castgroup.cursos.form.FormUpdateCurso;
import br.com.castgroup.cursos.repository.CursoRepository;

@Service
public class CursoService {
	

	private String message = null;
	
	public boolean isValid(FormCadastroCurso request, CursoRepository cursoRepository) {
		if (request.getInicio().isBefore(LocalDate.now())) {
			message = "data menor que a de hoje";
			System.out.println(message);
			return false;							
		} 
		if (request.getInicio().isAfter(request.getTermino())) {
			message = "data de termino anterior a de inicio";
			System.out.println(message);
			return false;							
		} 

		if(cursoRepository.contador(request.getInicio(), request.getTermino())> 0) {	
			message= "ja existe curso nessa data";
			System.out.println(message);
			return false;										
		}
		
		message= "curso cadastrado com sucesso";
		System.out.println("curso cadastrado com sucesso");
		return true;
	}
	
	public String mensagem () {
		return message;
		
	}

	public boolean isValid(FormUpdateCurso request, CursoRepository cursoRepository, Curso curso) {
		
		if(request.getInicio().isEqual(curso.getInicio())&&request.getTermino().isEqual(curso.getTermino())) {
			message= "curso cadastrado com sucesso";
			System.out.println(request.getInicio());
			System.out.println(curso.getInicio());
			System.out.println("curso cadastrado com sucesso");
			return true;
			
		}
		
		if (request.getInicio().isBefore(LocalDate.now())) {
			message = "data menor que a de hoje";
			System.out.println(message);
			return false;							
		} 
		if (request.getInicio().isAfter(request.getTermino())) {
			message = "data de termino anterior a de inicio";
			System.out.println(message);
			return false;							
		} 

		if(cursoRepository.contador(request.getInicio(), request.getTermino())> 0) {	
			message= "ja existe curso nessa data";
			System.out.println(message);
			return false;										
		}
		
	
		
		message= "curso cadastrado com sucesso";
		System.out.println("curso cadastrado com sucesso");
		return true;
	}
	

	

}