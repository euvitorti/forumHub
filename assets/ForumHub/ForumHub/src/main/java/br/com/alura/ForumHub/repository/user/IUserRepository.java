package br.com.alura.ForumHub.repository.user;

import br.com.alura.ForumHub.models.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {

    UserDetails findByLogin(String login);
}