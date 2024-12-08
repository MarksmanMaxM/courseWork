package com.example.courseWork.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.context.annotation.Configuration;


import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Data
@Configuration
public class CountRules {

    @JsonProperty("stats:")
    private Map<UUID, Integer> counts;

    public CountRules(Map<UUID, Integer> counts) {
        this.counts = counts;
    }


    public void setCounts(UUID id, Integer counts) {
        this.counts.put(id, counts);
    }

    @Override
    public String toString() {
        String str = "stats:[\n";

        Iterator<Map.Entry<UUID, Integer>> iterator = this.counts.entrySet().iterator();
        while (iterator.hasNext()) {
            String str1 = iterator.next().toString();
            String[] parts = str1.split("=", 2);
            str += "{\nrule_id: " + parts[0] + "\n" + "count: "
                    + parts[1] + "\n}\n";
            System.out.println(str);
        }


        return str;

    }
}
