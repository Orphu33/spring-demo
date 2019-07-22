package com.nikopiko.demo.repositories;

import com.nikopiko.demo.entity.Category;
import com.nikopiko.demo.entity.Joke;
import com.nikopiko.demo.util.TextGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DemoAppService {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    JokeRepository jokeRepository;

    public List<Category> getAllCategories() {
        List<Category> cats = new ArrayList<>();
        categoryRepository.findAll().forEach(cats::add);
        return cats;
    }

    public void insertJoke(int catId, String content) {
        Joke joke = new Joke();
        joke.setContent(content);
        joke.setCategory(categoryRepository.getOne(catId));
        jokeRepository.save(joke);
    }

    public Page<Joke> getAllJokes(int pageNumber, int pagesize) {
        Page<Joke> jokes = jokeRepository.findAllByOrderByDiffDesc(PageRequest.of(pageNumber, pagesize));
        return jokes;
    }

    public void updateLikes(int id, String like) {
        Joke joke = jokeRepository.getOne(id);
        if (like.contentEquals("likes")) joke.setLikes(joke.getLikes() + 1);
        else joke.setDislikes(joke.getDislikes() + 1);
        jokeRepository.save(joke);
    }

    /*
    Call this method to insert jokes into database for testing pagination.
     */
    public void insertRandomData(int numberOfJokesToInsert) {
        int cat;
        int likes, dislikes;
        String[] words;

        for (int i=0; i< numberOfJokesToInsert; i++) {
            cat = TextGenerator.getRandomNumberInRange(1,5);
            words = TextGenerator.generateRandomWords(TextGenerator.getRandomNumberInRange(5,15));
            likes = TextGenerator.getRandomNumberInRange(1,30);
            dislikes = TextGenerator.getRandomNumberInRange(1,10);
            insertJokeToDb(cat, words, likes, dislikes);
        }
    }

    public void insertJokeToDb(int cat, String[] content, int likes, int dislikes) {
        Joke joke = new Joke();
        joke.setCategory(categoryRepository.getOne(cat));
        joke.setContent(String.join(" ", content));
        joke.setLikes(likes);
        joke.setDislikes(dislikes);
        jokeRepository.save(joke);
    }

}
