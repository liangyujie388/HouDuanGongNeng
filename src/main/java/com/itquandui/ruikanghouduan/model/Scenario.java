package com.itquandui.ruikanghouduan.model;

public class Scenario {
    private String id;
    private String title;
    private String description;
    private String story;

    public Scenario() {
    }

    public Scenario(String id, String title, String description, String story) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.story = story;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }
}
