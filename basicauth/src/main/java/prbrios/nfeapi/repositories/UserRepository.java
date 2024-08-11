package prbrios.nfeapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import prbrios.nfeapi.entities.UserEntity;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByUsername(String username);

}
