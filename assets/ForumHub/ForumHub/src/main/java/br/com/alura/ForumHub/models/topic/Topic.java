package br.com.alura.ForumHub.models.topic;

import br.com.alura.ForumHub.dto.topic.TopicDTO;
import br.com.alura.ForumHub.dto.topic.UpdateTopicDTO;
import br.com.alura.ForumHub.models.answerTopic.AnswerTopic;
import br.com.alura.ForumHub.models.author.Author;
import br.com.alura.ForumHub.models.course.Course;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Table(name = "topicos")
@Entity(name = "Topico")
@Getter
@EqualsAndHashCode(of = "id")
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String mensagem;
    private LocalDateTime dataCriacao;
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private Author autor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curso_id")
    private Course curso;

    @OneToMany(mappedBy = "topico", fetch = FetchType.LAZY)
    private List<AnswerTopic> respostas;

    private Boolean ativo;

    public Topic(){}

    public Topic(TopicDTO topicDTO) {
        this.titulo = topicDTO.titulo();
        this.mensagem = topicDTO.mensagem();
        Author autor = new Author(topicDTO.autor());
        this.ativo = true;
    }

    public void updateTopic(UpdateTopicDTO updateTopicDTO) {

        if(updateTopicDTO.mensagem() != null) {
            this.mensagem = updateTopicDTO.mensagem();
        }

        if(updateTopicDTO.titulo() != null) {
            this.titulo = updateTopicDTO.titulo();
        }
    }

    public void logicalExclusion() {
        this.ativo = false;
    }
}
