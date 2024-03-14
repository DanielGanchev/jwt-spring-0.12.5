package org.dodomir.jwt0125.repository;

import java.util.Optional;
import org.dodomir.jwt0125.entities.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository  extends JpaRepository<User, Long> {

  Optional<User> findByEmail(String email);

}
