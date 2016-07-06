package com.catalyst.overwatch.schedule.repository;

import com.catalyst.overwatch.schedule.model.SimpleUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource(path = "users", collectionResourceRel = "users", itemResourceRel = "users")
public interface UserRepository extends JpaRepository<SimpleUser, Long>{

    @RestResource(exported = false)
    void delete(SimpleUser user);

    @RestResource(exported = false)
    SimpleUser save(SimpleUser user);
}
