package br.com.alura.ForumHub.dto.topic;

import br.com.alura.ForumHub.dto.author.AuthorDTO;

public record TopicDTO(String titulo, String mensagem, AuthorDTO autor) {
}
