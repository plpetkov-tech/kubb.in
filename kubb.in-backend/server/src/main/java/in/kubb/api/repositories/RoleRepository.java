package in.kubb.api.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import in.kubb.api.models.ERole;
import in.kubb.api.models.Role;


public interface RoleRepository extends MongoRepository<Role, String> {
  Optional<Role> findByName(ERole name);
}