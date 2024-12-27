package com.example.courseWork.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Objects;
import java.util.UUID;

@Data
public class CountRulesModel {

    @JsonProperty("rule_id:")
    private UUID idRule;
    @JsonProperty("count:")
    private int count;

    public CountRulesModel() {
        this.count = 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CountRulesModel that = (CountRulesModel) o;
        return Objects.equals(idRule, that.idRule);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(idRule);
    }
}
