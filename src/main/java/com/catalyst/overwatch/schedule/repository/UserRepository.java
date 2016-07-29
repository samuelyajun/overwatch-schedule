package com.catalyst.overwatch.schedule.repository;

import com.catalyst.overwatch.schedule.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource(path = "users", collectionResourceRel = "users", itemResourceRel = "users")
public interface UserRepository extends JpaRepository<User, Long> {

  @RestResource(exported = false)
  void delete(User user);

  @RestResource(exported = false)
  User save(User user);
}
