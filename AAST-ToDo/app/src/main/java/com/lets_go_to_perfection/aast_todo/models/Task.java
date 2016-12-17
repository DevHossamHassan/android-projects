package com.lets_go_to_perfection.aast_todo.models;

/**
 * Created by Hossam on 12/10/2016.
 */

public class Task {
    private int id;
    private String createdAt;
    private String content;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Task: (id= " + getId() + ", createdAt= " + getCreatedAt() + ", content= " + getContent() + " );";
    }
}
