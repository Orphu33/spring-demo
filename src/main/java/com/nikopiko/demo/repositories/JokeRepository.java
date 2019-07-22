package com.nikopiko.demo.repositories;

import com.nikopiko.demo.entity.Joke;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JokeRepository extends JpaRepository<Joke, Integer> {

    List<Joke> findAllByOrderByDiffDesc();
}
