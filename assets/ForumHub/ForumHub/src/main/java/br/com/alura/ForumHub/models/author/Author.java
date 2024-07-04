package br.com.alura.ForumHub.models.author;

import br.com.alura.ForumHub.dto.author.AuthorDTO;
import br.com.alura.ForumHub.models.answerTopic.AnswerTopic;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.List;

@Table(name = "autores")
@Entity(name = "Autor")
@Getter
@EqualsAndHashCode(of = "id")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String email;

    @OneToMany(mappedBy = "autor", fetch = FetchType.LAZY)
    private List<AnswerTopic> respostas;

    public Author(){}

    public Author(AuthorDTO autor) {
        this.nome = autor.nome();
        this.email = autor.email();
    }

}
