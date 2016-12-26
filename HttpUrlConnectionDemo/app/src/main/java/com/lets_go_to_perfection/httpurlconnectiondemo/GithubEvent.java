package com.lets_go_to_perfection.httpurlconnectiondemo;

/**
 * Created by Hossam on 12/24/2016.
 */

public class GithubEvent {
    int id;
    String title;
    String content;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
