package com.nikopiko.demo.repositories;

import com.nikopiko.demo.entity.Joke;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JokeRepository extends JpaRepository<Joke, Integer> {

    Page<Joke> findAllByOrderByDiffDesc(Pageable pageable);
}
