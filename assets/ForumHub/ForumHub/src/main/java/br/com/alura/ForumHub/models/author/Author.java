package br.com.alura.ForumHub.models.author;

import br.com.alura.ForumHub.models.answerTopic.AnswerTopic;
import br.com.alura.ForumHub.models.topic.Topic;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Table(name = "autores")
@Entity(name = "Autor")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String email;

    @OneToMany(mappedBy = "autor", fetch = FetchType.LAZY)
    private List<Topic> topicos;

    @OneToMany(mappedBy = "autor", fetch = FetchType.LAZY)
    private List<AnswerTopic> respostas;

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public List<Topic> getTopicos() {
        return topicos;
    }

    public List<AnswerTopic> getRespostas() {
        return respostas;
    }

}
