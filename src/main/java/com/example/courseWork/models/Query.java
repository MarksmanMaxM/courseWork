package com.example.courseWork.models;

import com.example.courseWork.enums.Requests;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

@Setter
@Getter
@ToString
@Embeddable
@Cacheable
public class Query {

    @JsonProperty("query")
    private Requests query;
    @JsonProperty("arguments")
    private List<String> arguments;
    @JsonProperty("negate")
    private boolean negate;

}
