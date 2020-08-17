package com.dev_incubator.dits.service.dto;


import com.dev_incubator.dits.persistence.entity.Question;
import com.dev_incubator.dits.persistence.entity.Topic;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class TestDto {

    private Long id;
    @NotEmpty(message = "{test.name.not.empty}")
    private String name;
    @NotEmpty(message = "{test.name.not.empty}")
    private String description;
    private Topic topic;
    private Set<Question> questions = new HashSet<>();
}
