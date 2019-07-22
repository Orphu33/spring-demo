package com.nikopiko.demo.entity;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "joke")
public class Joke {

    @Id
    @GeneratedValue(generator = "joke_id_seq", strategy = GenerationType.SEQUENCE)
    @GenericGenerator(name = "joke_id_seq", strategy = "native")
    private int id;

    private String content;

    @ColumnDefault(value = "0")
    private int likes;

    @ColumnDefault(value = "0")
    private int dislikes;

    @Formula("(likes - dislikes)")
    private int diff;

    @ManyToOne()
    @JoinColumn(name = "cat_id")
    private Category category;

    public Joke() { }

    public Joke(String content, int likes, int dislikes, Category category) {
        this.content = content;
        this.likes = likes;
        this.dislikes = dislikes;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getDislikes() {
        return dislikes;
    }

    public void setDislikes(int dislikes) {
        this.dislikes = dislikes;
    }

    public int getDiff() { return diff; }

    public void setDiff(int diff) {
        this.diff = diff;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Joke{" +
                "id=" + id +
                ", content='" + content.substring(0, 25) + "..." +
                ", likes=" + likes +
                ", dislikes=" + dislikes +
                ", diff=" + diff +
                ", category=" + category.toString() +
                '}';
    }
}