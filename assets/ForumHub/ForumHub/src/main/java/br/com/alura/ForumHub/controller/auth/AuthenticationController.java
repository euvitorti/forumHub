package br.com.alura.ForumHub.controller.auth;

import br.com.alura.ForumHub.dto.authentication.AuthenticationDTO;
import br.com.alura.ForumHub.dto.authentication.TokenJwtDTO;
import br.com.alura.ForumHub.infra.security.TokenJwt;
import br.com.alura.ForumHub.models.user.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// CLASSE CONTROLLER NÃO DEVE TER REGRAS DE NOGÓCIO DA APLICAÇÃO

@RestController
@RequestMapping("/login")
public class AuthenticationController {

    // ESTA É CLASSE QUE DISPARA O PROCESSO DE AUTENTICAÇÃO
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenJwt tokenJwt;

    @PostMapping
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO authenticationDTO) {
        try {
            var authToken = new UsernamePasswordAuthenticationToken(authenticationDTO.login(), authenticationDTO.password());
            var auth = authenticationManager.authenticate(authToken);

            // PASSANDO O USUÁRIO COMO PARÂMETRO
            var token = tokenJwt.generateToken((User) auth.getPrincipal());
            return ResponseEntity.ok(new TokenJwtDTO(token));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}