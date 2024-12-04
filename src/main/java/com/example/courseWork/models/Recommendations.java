package com.example.courseWork.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import org.springframework.data.annotation.Id;
import java.util.UUID;


@Entity
public class Recommendations {
    @jakarta.persistence.Id
    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    private String text;

    public Recommendations(String name, UUID id, String text) {
        this.name = name;
        this.id = id;
        this.text = text;
    }

    public Recommendations() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "name:" + name +
                ", id:" + id +
                ", text:" + text + '\n';
    }
}
