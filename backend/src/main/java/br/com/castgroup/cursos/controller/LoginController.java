package br.com.castgroup.cursos.controller;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.castgroup.cursos.config.security.MD5Cryptography;
import br.com.castgroup.cursos.config.security.TokenSecuriry;
import br.com.castgroup.cursos.entities.Usuario;
import br.com.castgroup.cursos.repository.UsuarioRepository;
import br.com.castgroup.cursos.request.LoginPostRequest;
import br.com.castgroup.cursos.service.CursoService;
import io.swagger.annotations.ApiOperation;

@RestController
@Transactional
public class LoginController {
	
	@Autowired
	private CursoService cursoService;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	private static final String ENDPOINT = "/api/login";

	
	@ApiOperation("Serviço para autenticação de Usuário")
	@RequestMapping(value = ENDPOINT, method = RequestMethod.POST)
	@CrossOrigin
	public ResponseEntity<String> post(@RequestBody LoginPostRequest request){
		try {
			//pesquisar no banco de dados o usuario atraves do login e senha
			Usuario usuario = usuarioRepository.findByLoginAndSenha(request.getLogin(), 
										  MD5Cryptography.encrypt(request.getSenha()));	
			cursoService.usuarioLogado(usuario);
			//verificar se o usuario foi encontrado
			if(usuario != null) {
				return ResponseEntity.status(HttpStatus.OK).body(TokenSecuriry.generateToken(usuario.getLogin()));
			} else {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Acesso negado");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
	


}