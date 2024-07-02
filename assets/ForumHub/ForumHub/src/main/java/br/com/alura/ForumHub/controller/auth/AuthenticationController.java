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

    // TODO

//    Outra maneira de restringir o acesso a determinadas funcionalidades,
//    com base no perfil dos usuários, é com a utilização de um recurso do Spring Security conhecido
//    como Method Security, que funciona com a utilização de anotações em métodos:
//
//    @GetMapping("/{id}")
//    @Secured("ROLE_ADMIN")
//    public ResponseEntity detalhar(@PathVariable Long id) {
//        var medico = repository.getReferenceById(id);
//        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
//    }
//
//    No exemplo de código anterior o método foi anotado com @Secured("ROLE_ADMIN"),
//    para que apenas usuários com o perfil ADMIN possam disparar requisições para detalhar um médico.
//    A anotação @Secured pode ser adicionada em métodos individuais ou mesmo na classe,
//    que seria o equivalente a adicioná-la em todos os métodos.
//
//    Atenção! Por padrão esse recurso vem desabilitado no spring Security,
//    sendo que para o utilizar devemos adicionar a seguinte anotação na classe Security do projeto:
//
//    @EnableMethodSecurity(securedEnabled = true)
//
//    Você pode conhecer mais detalhes sobre o recurso de method security na documentação do Spring Security,
//    disponível em: https://docs.spring.io/spring-security/reference/servlet/authorization/method-security.html
}