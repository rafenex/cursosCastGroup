package br.com.castgroup.cursos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.castgroup.cursos.dtos.LogDTO;
import br.com.castgroup.cursos.repository.LogRepository;

@CrossOrigin
@RestController
@RequestMapping("/api/logs")
public class LogController {
	
	@Autowired
	LogRepository logRepository;


	@GetMapping
	public Page<LogDTO> listar(
							   @RequestParam(required = false) String descricao,
							   @PageableDefault(sort="id_log", direction = Direction.DESC, page = 0, size = 5)
							   Pageable paginacao){
			if(descricao == null) {
				return logRepository.listarLogs(paginacao);
			} else {
				return logRepository.listarPorDescricao(paginacao, descricao);
			}
	
	}




	
	
	
	

}
