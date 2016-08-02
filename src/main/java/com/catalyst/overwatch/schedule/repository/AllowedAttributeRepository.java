package com.catalyst.overwatch.schedule.repository;

import com.catalyst.overwatch.schedule.model.AllowedAttribute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

/**
 * Exposes a basic RESTFUL endpoint for AllowedAttributes.
 *
 * @author hmccardell
 */
@Repository
public interface AllowedAttributeRepository  extends JpaRepository<AllowedAttribute, Long> {

  @RestResource(exported = false)
  void delete(AllowedAttribute allowedAttribute);

  @RestResource(exported = false)
  AllowedAttribute save(AllowedAttribute allowedAttribute);
}
