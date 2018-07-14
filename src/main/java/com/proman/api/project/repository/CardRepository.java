package com.proman.api.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proman.api.project.model.Card;

public interface CardRepository extends JpaRepository<Card,Integer>{

}
