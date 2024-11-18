package com.example.courseWork.models;

public class Recommendations {
    private String name;
    private String id;
    private String text;

    public Recommendations(String name, String id, String text) {
        this.name = name;
        this.id = id;
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
