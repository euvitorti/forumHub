package br.com.alura.ForumHub.repository.topic;

import br.com.alura.ForumHub.models.topic.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITopicRepository extends JpaRepository<Topic, Long> {
}
