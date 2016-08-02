package com.catalyst.overwatch.schedule.model.projections;

import com.catalyst.overwatch.schedule.model.AllowedAttribute;
import com.catalyst.overwatch.schedule.model.AttributeType;
import org.springframework.data.rest.core.config.Projection;

/**
 * Created by hmccardell on 8/2/2016.
 */
@Projection(name = "allowedAttributeDetails", types = {AllowedAttribute.class})
public interface AllowedAttributeDetailProjection {

  Long getId();
  AttributeType getAttributeType();
  String getAttributeValue();

}
