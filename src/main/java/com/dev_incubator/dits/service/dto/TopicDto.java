package com.dev_incubator.dits.service.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

@Getter
@Setter
public class TopicDto {

    private Long id;
    @NotEmpty(message = "{topic.name.not.empty}")
    private String name;
    @NotEmpty(message = "{topic.description.not.empty}")
    private String description;
    private Integer testAmount;
    private Integer amountPassedTests;
}
