package com.catalyst.overwatch.schedule.model.projections;

import com.catalyst.overwatch.schedule.model.AllowedAttribute;
import com.catalyst.overwatch.schedule.model.Respondent;
import com.catalyst.overwatch.schedule.model.User;
import org.springframework.data.rest.core.config.Projection;

import java.util.Set;

@Projection(name = "respondentDetails", types = {Respondent.class})
public interface RespondentDetailsProjection {

    Long getId();
    Set<AllowedAttribute> getAllowedAttributes();
    User getUser();
}
