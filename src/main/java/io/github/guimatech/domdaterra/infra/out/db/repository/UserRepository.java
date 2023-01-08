package io.github.guimatech.domdaterra.infra.out.db.repository;

import io.github.guimatech.domdaterra.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
}
