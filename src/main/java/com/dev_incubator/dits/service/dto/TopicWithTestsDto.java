package com.dev_incubator.dits.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class TopicWithTestsDto {

    private Long id;
    private String name;
    private String description;
    private Integer testAmount;
    private Integer amountPassedTests;
    private List<TestDto> tests = new ArrayList<>();
}
