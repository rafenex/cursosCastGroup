package br.com.castgroup.cursos.service;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import br.com.castgroup.cursos.form.FormCadastroCurso;
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
		System.out.println("Contador: " + cursoRepository.contador(request.getInicio(), request.getTermino()));	
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
	

	

}
