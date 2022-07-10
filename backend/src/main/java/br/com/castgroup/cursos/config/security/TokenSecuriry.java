package br.com.castgroup.cursos.config.security;

import java.util.Date;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

//gerar o token
public class TokenSecuriry {
	public static String generateToken(String login) {
		//Chave secreta para geração do TOKEN
		String secretKey = JwtSecurity.SECRET;	
		List<GrantedAuthority> grantedAuthorityList =
				AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER");
		
		//TREINAMENTO_JWT -> nome da aplicação que gerou o token
		String token = Jwts.builder().setId("CAST_JWT")
				.setSubject(login)
				.claim("authorities", grantedAuthorityList.stream()
				.map(GrantedAuthority::getAuthority)
				.collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+6000000))
				.signWith(SignatureAlgorithm.HS512, secretKey.getBytes())
				.compact();
		
		return token;
		
	}
	
	//Ler o login do usuario(ADM ou cliente)gravado no TOken
	public static String getLoginFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}

	private static <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		String secretKey = JwtSecurity.SECRET;
		
		final Claims claims = Jwts.parser().setSigningKey(secretKey.getBytes())
				.parseClaimsJws(token).getBody();
		        return claimsResolver.apply(claims);
	}

}