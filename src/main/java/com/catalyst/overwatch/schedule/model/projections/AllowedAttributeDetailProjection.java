package com.catalyst.overwatch.schedule.model.projections;

import com.catalyst.overwatch.schedule.model.AllowedAttribute;
import com.catalyst.overwatch.schedule.model.AttributeType;
import org.springframework.data.rest.core.config.Projection;

/**
 * This projection provides details on attributes, and is used to fill in details needed for front
 * end consumption that aren't exposed on a normal "GET" request to the repository.
 *
 * @author hmccardell
 */
@Projection(name = "allowedAttributeDetails", types = {AllowedAttribute.class})
public interface AllowedAttributeDetailProjection {

  Long getId();
  AttributeType getAttributeType();
  String getAttributeValue();

}
