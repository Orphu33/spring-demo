package com.nikopiko.demo.model;

import javax.validation.constraints.NotBlank;

public class JokeForm {

    private int catId;

    @NotBlank
    private String content;

    public JokeForm() {
    }

    public int getCatId() {
        return catId;
    }

    public void setCatId(int catId) {
        this.catId = catId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "JokeForm{" +
                "catiId=" + catId +
                ", content='" + content + '\'' +
                '}';
    }
}
