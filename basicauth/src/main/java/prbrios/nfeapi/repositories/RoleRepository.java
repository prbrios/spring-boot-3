package prbrios.nfeapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import prbrios.nfeapi.entities.RoleEntity;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

}
