package br.com.mateusfilpo.cryptography.repository;

import br.com.mateusfilpo.cryptography.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
